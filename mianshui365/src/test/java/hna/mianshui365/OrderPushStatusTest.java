package hna.mianshui365;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.Reporter;

/**
 * 测试用例
 * 
 * @author Jimmy Lee
 * @version 1.0 2017/2/9
 **/

public class OrderPushStatusTest{
	public String httpResult = null;
	public String head = null;
	public String headinfo = null;
	public String outParams = null;
	public String returnCode = null;
	public String bodyinfo = null;
	public String msg = null;
	public String exp_returnCode = null;
	public String exp_msg = null;
	public String orderNumber = null;
	public static getOrderPushStatus orderpushstatus = new getOrderPushStatus();
	
	@Parameters({ "orderNumber" })
	@Test
	public void getOrder(String orderNumber) throws IOException{
		exp_returnCode = "000000";
		exp_msg = "推送成功";
		System.out.println("推送的订单号："+ orderNumber);
		Reporter.log("请求传入订单号"+orderNumber+"开始推送");
		httpResult = orderpushstatus.getHttpRespone(orderNumber);
		Reporter.log("请求地址："+orderpushstatus.geturl());
		Reporter.log("返回结果: "+httpResult);
		
		JSONObject resultJsonObject = new JSONObject(httpResult);
		JSONObject headinfoObject= new JSONObject(resultJsonObject.get("head").toString());
		headinfo = headinfoObject.toString();
		returnCode = Common.getJsonValue(headinfo,"returnCode");
		Reporter.log("returnCode返回的预期结果:" + exp_returnCode + ",returnCode返回的实际结果:" + returnCode);
		Assert.assertEquals(returnCode,exp_returnCode);
		
		JSONObject body= new JSONObject(resultJsonObject.get("body").toString());
		JSONArray outParamsArray=new JSONArray(body.get("outParams").toString());
		outParams = outParamsArray.get(0).toString();
		msg = Common.getJsonValue(outParams,"msg");
		Reporter.log("msg返回的预期结果:" + exp_msg + ",msg返回的实际结果:" + msg);
		//Assert.assertEquals(msg,exp_msg);
		Assert.assertNotEquals(msg, exp_msg);

	}
	
	
}