package com.qtdbp.trading.controller;

import com.qtdbp.trading.constants.AppConstants;
import com.qtdbp.trading.constants.SysRoleContants;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.mapper.DataTransactionOrderMapper;
import com.qtdbp.trading.model.DataTransactionOrderModel;
import com.qtdbp.trading.service.DataTransactionOrderService;
import com.qtdbp.trading.service.FdfsFileService;
import com.qtdbp.trading.service.security.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;

/**
 * 个人中心Controller
 *
 * Created by dell on 2017/4/17.
 */
@Secured({SysRoleContants.ROLE_USER})
@Controller
public class UserCenterController extends BaseController {

    @Autowired
    private FdfsFileService fileService ;

    @Autowired
    private DataTransactionOrderService orderService ;

    @Autowired
    private DataTransactionOrderMapper orderMapper ;

    private DataTransactionOrderModel orderModel = null;

    /**
     * 数据商城首页
     */
    private static final String PAGE_USER_CENTER = "usercenter/index" ;

    /**
     * 个人中心首页
     * @return
     */
    @RequestMapping(value = "/usercenter",method = RequestMethod.GET)
    public ModelAndView index() {

        ModelAndView result = new ModelAndView(PAGE_USER_CENTER);
        if(orderModel != null){
            //当订单为已支付时
            if (orderModel.getOrderState() == AppConstants.ORDER_STATE_PAYED) {

            } else {
                result.addObject("isOrder", 4);
                result.addObject("order", orderModel);
                result.addObject("orderState", orderModel.getOrderState());
            }

            orderModel = null;
        }

        return result ;
    }

    /**
     * 下载文件
     * @param orderNo
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/download/{no}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> fileDownload(@PathVariable("no") String orderNo) throws GlobalException {

        SysUser user = getPrincipal() ;

        if(user == null) throw new GlobalException("授权过期，请重新登陆") ;

        ResponseEntity<byte[]> file ;
        try {
            file = fileService.downloadFile(orderNo, user.getId(), false) ;
        } catch (Exception e) {
            throw new GlobalException("下载出错："+e.getMessage()) ;
        }

        return file ;
    }

    /**
     * 创建订单并且跳转到个人中心
     * @param productId
     * @param productType
     * @return
     */
    @RequestMapping(value = "/getOrderInfoAndGoto/{productId}/{productType}", method = RequestMethod.GET)
    public ModelAndView getIdAndType(@PathVariable("productId") String productId,
                                     @PathVariable("productType") String productType) throws GlobalException{

        ModelAndView view = new ModelAndView();

        // 未登陆请先登录
        SysUser user = getPrincipal() ;
        if(user == null) {
            view.setViewName("login");
            return view;
        }

        if(productId == null || "0".equals(productId)) throw new GlobalException("此产品不存在，请选择其他产品购买") ;
        int type = productType != null ? Integer.parseInt(productType) : AppConstants.PRODUCT_TYPE_PACKAGE ;
        int prodId = Integer.parseInt(productId) ;

        DataTransactionOrderModel dataTransactionOrderModel = new DataTransactionOrderModel();
        dataTransactionOrderModel.setUserId(user.getId());
        dataTransactionOrderModel.setProductId(prodId);
        dataTransactionOrderModel.setProductType((byte)type);
        view.setViewName("redirect:/usercenter");
        try {
            DataTransactionOrderModel result = orderService.insertNewOrder(dataTransactionOrderModel);
            if (result.getAmount().compareTo(new BigDecimal(0)) == 0 ) {
                //当付款金额为0时，直接改变订单状态，支付宝交易流水号为"xxxxxxxxxxxxx"
                orderService.updateOrder(result.getOrderNo(),"xxxxxxxxxxxxx");
            }
            //重新获取订单信息
            orderModel = orderMapper.findOrderByNo(result.getOrderNo());
        } catch (GlobalException e) {
            e.getMessage();
        }
        return view;
    }

    /**
     * 获取订单信息并且跳转到订单付款页面
     * @param productId
     * @param productType
     * @return
     */
    @RequestMapping(value = "/selectOrderAndPay/{productId}/{productType}", method = RequestMethod.GET)
    public ModelAndView selectOrderAndPay(@PathVariable("productId") String productId,
                                          @PathVariable("productType") String productType){

        ModelAndView view = new ModelAndView();
        // 未登陆请先登录
        SysUser user = getPrincipal() ;
        if(user == null) {
            view.setViewName("login");
            return view;
        }
        DataTransactionOrderModel dataTransactionOrderModel = new DataTransactionOrderModel();
        int prodId = Integer.parseInt(productId) ;
        dataTransactionOrderModel.setProductId(prodId);
        int type = productType != null ? Integer.parseInt(productType) : AppConstants.PRODUCT_TYPE_PACKAGE ;
        dataTransactionOrderModel.setProductType((byte)type);
        dataTransactionOrderModel.setUserId(user.getId());
        List<DataTransactionOrderModel> list = orderMapper.findOrderByUserIdAndProductIdAndType(dataTransactionOrderModel);
        // 如果当前用户已经购买了此数据包产品，则无需购买
        if(list != null && !list.isEmpty()) {
            for(DataTransactionOrderModel order : list){
                // 只要有一条订单待支付或已支付，则不能购买
                if(order.getOrderState().intValue() == AppConstants.ORDER_STATE_PAYING) {
                    orderModel = order;
                    view.setViewName("redirect:/usercenter");
                }
            }
        }
        return view;
    }
}