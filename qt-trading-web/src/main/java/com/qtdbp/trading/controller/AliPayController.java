package com.qtdbp.trading.controller;

import com.qtdbp.trading.service.DataTransactionOrderService;
import com.qtdbp.trading.service.security.model.SysUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * Created by dell on 2017/4/26.
 */
@Controller
public class AliPayController extends BaseController{

    /**
     * 数据商城首页
     */
    private static final String PAGE_USER_CENTER = "usercenter/index" ;

    @Autowired
    private DataTransactionOrderService orderService ;

    /**
     * 支付宝同步回调
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/alipay/returnUrl", method = RequestMethod.GET)
    public ModelAndView returnUrl(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{

        ModelAndView modelAndView = new ModelAndView(PAGE_USER_CENTER);

        SysUser user = getPrincipal() ;

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        //商户订单号
        String orderNoArr[] = request.getParameter("out_trade_no").split("：");//支付宝返回的订单编号为：订单编号:17829187298172  (所以需要截取)
        String order_no = orderNoArr[1];
     //   String order_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        System.out.println("order_no="+order_no);
        //支付宝交易号
        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
        System.out.println("trade_no="+trade_no);
        //成功标识
        String trade_status = new String(request.getParameter("is_success").getBytes("ISO-8859-1"),"UTF-8");
        System.out.println("trade_status="+trade_status);
        //支付金额
        String total_fee = new String(request.getParameter("total_fee").getBytes("ISO-8859-1"),"UTF-8");
        System.out.println("total_fee="+total_fee);
        modelAndView.addObject("orderState",0);
        if("T".equals(trade_status)){
            try {
                int i = orderService.updateOrder(order_no,trade_no);
                if(i > 0){
                    return modelAndView;
                }
            }catch (Exception e){
                e.getMessage();
            }
        }

        return modelAndView;
    }
}
