package hna.mianshui365;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;
import java.io.BufferedReader;

import org.json.JSONArray;
import org.json.JSONObject;

public class OrderPush {

	public static final String params = "{\"body\":{ \"inParams\": [ { \"orderSn\": \"2017020633424\" }]}}";

	public static void orderPush() {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://10.167.7.56:20881/order/push");

		CloseableHttpResponse response = null;
		try {
			httppost.addHeader("Content-type", "application/json; charset=utf-8");
			httppost.setHeader("Accept", "application/json");
			StringEntity requestEntity = new StringEntity(params, "utf-8");

			httppost.setEntity(requestEntity);
			System.out.println("executing request " + httppost.getURI());
			response = httpclient.execute(httppost);

			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String resultStr=EntityUtils.toString(entity, "UTF-8");
				System.out.println("--------------------------------------");
				System.out.println("Response content: " +resultStr );
				System.out.println("--------------------------------------");

				JSONObject resultJsonObject = new JSONObject(resultStr);
				System.out.println("resultJsonObject=========>"+resultJsonObject);
				
				JSONObject head= new JSONObject(resultJsonObject.get("head").toString());
				String returnCode=head.get("returnCode").toString();
				System.out.println("returnCode=========>"+returnCode);
				/*第一个要断言的returnCode
				 */
				
				JSONObject body= new JSONObject(resultJsonObject.get("body").toString());
				JSONArray outParams=new JSONArray(body.get("outParams").toString());
				System.out.println("nihao" + outParams);
					
					JSONObject param=new JSONObject(outParams.get(0).toString());
					boolean result=(Boolean) param.get("result");
					String msg=param.get("msg").toString();
					System.out.println(param);
					System.out.println(result);
					System.out.println(msg);
									
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				
				httpclient.close();
				if (response != null) {
					response.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
