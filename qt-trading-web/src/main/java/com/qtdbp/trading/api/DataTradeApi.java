package com.qtdbp.trading.api;

import com.github.pagehelper.PageInfo;
import com.qtdbp.trading.alipay.config.AlipayConfig;
import com.qtdbp.trading.alipay.sign.RSA;
import com.qtdbp.trading.alipay.util.AlipayNotify;
import com.qtdbp.trading.alipay.util.AlipaySubmit;
import com.qtdbp.trading.constants.ApiConstants;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.model.AlipayModel;
import com.qtdbp.trading.model.DataAuthorizeOrderModel;
import com.qtdbp.trading.model.DataItemModel;
import com.qtdbp.trading.model.DataTransactionOrderModel;
import com.qtdbp.trading.service.DataAuthorizeOrderService;
import com.qtdbp.trading.service.DataProductService;
import com.qtdbp.trading.service.DataTransactionOrderService;
import com.qtdbp.trading.utils.AliPayOrderUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 订单接口
 *
 * @author: caidchen
 * @create: 2017-04-19 13:21
 * To change this template use File | Settings | File Templates.
 */
@Api(description = "订单接口 - 业务API接口")
@RestController
@RequestMapping(value = "/api/trade")
public class DataTradeApi {

    @Autowired
    private DataProductService productService;

    @Autowired
    private DataTransactionOrderService orderService ;

    @Autowired
    private DataAuthorizeOrderService demandOrderService ;

    //===================================================================
    // 订单API接口
    //===================================================================

    @ApiOperation(value="最新前5条订单数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID（如：1）", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "page", value = "前几条数据（如：5）", defaultValue = "5", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @ResponseBody
    @RequestMapping(value = "/neworders", method = RequestMethod.GET)
    public ModelMap loadDataNewOrders(int userId, int page) throws GlobalException {

        ModelMap map = new ModelMap() ;
        // 设置默认每页显示记录数
        try {
            List<DataTransactionOrderModel> orderModelList = orderService.findNewOrdersByUserId(userId, page);
            map.put("pageInfo", new PageInfo<>(orderModelList));
        } catch (Exception e) {
            throw new GlobalException(e.getMessage()) ;
        }

        return map ;
    }

    @ApiOperation(value="订单数据接口，分页获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID（如：1）", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "orderState", value = "订单状态（1待支付、2已撤销、3已支付）,不传表示所有", dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "page", value = "当前页（如：1）", defaultValue = "1", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "rows", value = "每页显示记录数（如：10）", defaultValue = "10", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @ResponseBody
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public ModelMap loadDataOrders(DataTransactionOrderModel orderModel) throws GlobalException {

        ModelMap map = new ModelMap() ;
        // 设置默认每页显示记录数
        try {
            if (orderModel.getRows() == null || orderModel.getRows() == 0) orderModel.setRows(10);
            List<DataTransactionOrderModel> orderModelList = orderService.findOrdersForPage(orderModel);
            map.put("pageInfo", new PageInfo<>(orderModelList));
            map.put("queryParam", orderModel);
            map.put("page", orderModel.getPage());
            map.put("rows", orderModel.getRows());
        } catch (Exception e) {
            throw new GlobalException(e.getMessage()) ;
        }

        return map ;
    }

    @ApiOperation(value="添加新订单API接口")
    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public ModelMap addDataOrders(@RequestBody DataTransactionOrderModel orderModel) throws GlobalException {

        ModelMap map = new ModelMap() ;
        try {
            int id = orderService.insertOrder(orderModel) ;
            map.put("success", id>0?true:false);
            map.put("id", id);
        } catch (Exception e) {
            throw new GlobalException(e.getMessage()) ;
        }
        return map ;
    }


    @ApiOperation(value = "统计订单数量数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID（如：1）", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
    })
    @ResponseBody
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ModelMap loadAllOrderInfo(DataAuthorizeOrderModel orderModel) throws GlobalException {

        ModelMap map = new ModelMap() ;
        try {
            Map<String, Integer> orderMap = demandOrderService.findAllOrderInfo(orderModel) ;
            map.put("pageInfo", orderMap);
        } catch (GlobalException e) {
            throw new GlobalException(e.getMessage()) ;
        }
        return map;
    }

    /**
     * 调用支付宝接口
     */
    @ApiOperation(value = "调用支付宝接口", notes = "{}")
    @ResponseBody
    @RequestMapping(value = "/alipayapi", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ModelMap openAlipay(@RequestBody AlipayModel alipayModel) {


        ModelMap result = new ModelMap() ;

        Map<String, Object> map = new HashMap<>();
        // 请求参数
        BigDecimal amount = alipayModel.getAmount();
        if (null == amount || amount.equals(BigDecimal.ZERO)) {

        } else {

            //	TODO 添加订单

            // 商户订单号，商户网站订单系统中唯一订单号，必填
            String orderNo = alipayModel.getOrderNo();
            // 订单名称，必填
            String subject = alipayModel.getSubject();
            // 付款金额，必填
            String total_fee = amount.toString();
            // 商品描述，可空
            String body = alipayModel.getBody();

            //加签
            String needsign = AliPayOrderUtil.getOrderInfo2(subject, body, orderNo, total_fee);
            String mysign = RSA.sign(needsign, AlipayConfig.private_key, AlipayConfig.input_charset);

            // 防钓鱼时间戳
//		AlipayConfig.anti_phishing_key = AlipaySubmit.query_timestamp();

            // 把请求参数打包成数组
            Map<String, String> sParaTemp = new HashMap<String, String>();
            sParaTemp.put("service", AlipayConfig.service);
            sParaTemp.put("partner", AlipayConfig.partner);
            sParaTemp.put("seller_id", AlipayConfig.seller_id);
            sParaTemp.put("_input_charset", AlipayConfig.input_charset);
            sParaTemp.put("payment_type", AlipayConfig.payment_type);
            sParaTemp.put("notify_url", AlipayConfig.notify_url);
            sParaTemp.put("return_url", AlipayConfig.return_url);
            sParaTemp.put("anti_phishing_key", AlipayConfig.anti_phishing_key);
            sParaTemp.put("exter_invoke_ip", AlipayConfig.exter_invoke_ip);
            sParaTemp.put("out_trade_no", orderNo);
            sParaTemp.put("subject", subject);
            sParaTemp.put("total_fee", total_fee);
            sParaTemp.put("body", body);
            sParaTemp.put("extra_common_param", alipayModel.getReturnUrl());
            // 其他业务参数根据在线开发文档，添加参数.文档地址:https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.O9yorI&treeId=62&articleId=103740&docType=1
            // 如sParaTemp.put("参数名","参数值");

            // 建立请求
            String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
            map.put("sHtmlText", sHtmlText);
        }
        // 保存支付记录
        // hysWebMeetingAliService.insertSelective(sParaTemp);

        result.put("pageInfo", new ResponseEntity(map, HttpStatus.OK).getBody());
        result.put("status", new ResponseEntity(map, HttpStatus.OK).getStatusCode());

        return result ;

    }

    /**
     * 支付回调
     */
    @ApiOperation(value = "支付宝异步回调", notes = "{}")
    @PostMapping(value = "/alipay/toNotifyUrl")
    public void verifyOrderNum(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
            System.out.println("AlipayNotify:"+name+"+"+valueStr);
        }

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        //商户订单号
        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        System.out.println("order_no="+out_trade_no);
        //支付宝交易号
        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
        System.out.println("trade_no="+trade_no);
        //交易状态
        String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
        System.out.println("trade_status="+trade_status);
        //支付金额
        String total_fee = new String(request.getParameter("total_fee").getBytes("ISO-8859-1"),"UTF-8");
        System.out.println("total_fee="+total_fee);

        //保存支付宝回调记录
        try {
          //  payNotifyService.insertPayNotify(out_trade_no,trade_status,1,trade_no,new BigDecimal(total_fee));
        }catch (Exception e){
          //  LOGGER.error(e.getMessage());
        }

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
        //计算得出通知验证结果
        boolean verify_result = AlipayNotify.verify(params);
        System.out.println("Alipay 验证结果  verify_result........"+verify_result);
        System.out.println("Alipay trade_status......"+trade_status);
        if(verify_result){//验证成功
            //请在这里加上商户的业务逻辑程序代码
            //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
            if(trade_status.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            } else if (trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商
                //
                //
                // 户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序 TRADE_SUCCESS

                //修改订单信息
                try {
                    orderService.updateOrder(out_trade_no,trade_no);
                }catch (Exception e){
                    e.getMessage();
                }

                //修改余额

                printOutMsg(response, "success");//请不要修改或删除
                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
            } else {
                printOutMsg(response, "success");//请不要修改或删除
            }
            //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
        }else{//验证失败
            printOutMsg(response, "fail");
        }
    }

    /**
     * 输出信息到页面
     * @param response
     * @param msg
     */
    public void printOutMsg(HttpServletResponse response, String msg){
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = null;
            out = response.getWriter();
            out.print(msg);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
