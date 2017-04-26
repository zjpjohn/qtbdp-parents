package com.qtdbp.trading.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.4
 *修改日期：2016-03-08
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String partner = "2088221727788503";
	
	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
	public static String seller_id = "2088221727788503";

	//商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
	public static String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOwA7AqP0Z+pXhd0eohPuY+xFQgm0Bf23GLx5X1ARdrrXaUzcoAozIexy05ZaBr+YH4fffMKvZf2fzpRDbiFvV+Be+vgMxN9hczgTOJ1wiAI2BDwULP9YxKLORdgNiPprfDeQPXN83NCWRvWt+cCp9ldp94bodqYFy3QWzS5vgUrAgMBAAECgYAr0kwB9gx9cgx3nHg4IV2aUc5O8i4HlR38/V+YbzvJV5TO3QuwwmKf+6J9jNpo0L0IDj7rxx3HPs0G/g/nKGYuH2u/xtU9a1hHDA5fMeyLPK6Klic2loPNMJomW1KJaz2OYiOSkClstTRf2AibiwtG0RmdRFByCXWmm9xTmI9DkQJBAPYwm9zOWDcGqG2Xj6gAxQX2jvEd+QoOhy8Xg1oonnmOzKSWRoEJnpvgmvEdqqaHD1bfA5Eg+aNLaTVyB1U+OEkCQQD1aGcUI32E8OcODuTbmmmbMaHf226SFrkAdy3LbL2MasGxryE+JUi9lrYyOyLZaFbR00Jhp8+aR7IlFWidQpnTAkBiFvwC/jQnuH9jKwAR8DyQI6zQZSDU4PT7zmKHiaeax0rCSR3OTAbmFJeneiqEL0vtUO4a5QiSB3nj0Ysaa7ABAkEAimzsj+CBAcb9HeHq17Be12qEm4fj88WbbxbKnw9kT9s8DKTzhGF1iXOErArNWoLPTiXzMLB+mtDMPLy5uNMDfwJBAPMtAe8tLJrAKkFEfOy0P+bwdri5ThVG9JYsogyczubgtgbYleMHjXnrb0S50FMuUklxIGh+CHbat0UaWcPDkls=";
	
	// 支付宝的公钥,查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String alipay_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://qttest4.qtbigdata.com/api/trade/alipay/toNotify_url";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://jytest.qtbigdata.com/api/trade/alipay/returnUrl";

	// 签名方式
	public static String sign_type = "RSA";
	
	// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
	public static String log_path = "C:\\";
		
	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
		
	// 支付类型 ，无需修改
	public static String payment_type = "1";
		
	// 调用的接口名，无需修改
	public static String service = "create_direct_pay_by_user";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	
//↓↓↓↓↓↓↓↓↓↓ 请在这里配置防钓鱼信息，如果没开通防钓鱼功能，为空即可 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	
	// 防钓鱼时间戳  若要使用请调用类文件submit中的query_timestamp函数
	public static String anti_phishing_key = "";
	
	// 客户端的IP地址 非局域网的外网IP地址，如：221.0.0.1
	public static String exter_invoke_ip = "";
		
//↑↑↑↑↑↑↑↑↑↑请在这里配置防钓鱼信息，如果没开通防钓鱼功能，为空即可 ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	
}

