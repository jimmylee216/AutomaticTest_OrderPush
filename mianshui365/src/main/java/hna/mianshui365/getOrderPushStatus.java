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

public class getOrderPushStatus {
	private String url = null;

	public String geturl() {
		return url;
	}

	public String getHttpRespone(String orderNumber) {
		String httpResults = null;
		url = ("http://10.167.7.56:20881/order/push");
		String params = "{\"body\":{ \"inParams\": [ { \"orderSn\": \"" + orderNumber + "\" }]}}";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		CloseableHttpResponse response = null;
		try {
			httppost.addHeader("Content-type", "application/json; charset=utf-8");
			httppost.setHeader("Accept", "application/json");
			StringEntity requestEntity;
			requestEntity = new StringEntity(params, "utf-8");

			httppost.setEntity(requestEntity);
			//System.out.println("executing request " + httppost.getURI());
			response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				httpResults = EntityUtils.toString(entity, "UTF-8");
				//System.out.println("--------------------------------------");
				//System.out.println("Response content: " + httpResults);
				//System.out.println("--------------------------------------");
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

		return httpResults;
	}
}
