package net.zdict.connect;

import java.io.IOException;

import net.zdict.pub.Constant;
import net.zdict.util.StringUtil;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import android.content.SharedPreferences;


/**
 * 用于和服务器端通信
 * 
 * @author CreateTime Aug 22, 2012 1:33:37 PM
 */
public class Communication {

	private static HttpClient httpClient;
	public static HttpPost post;
	private static String encryptUsable;
	private static SharedPreferences sharedPreferences;

	public static String getPostHttpContent(HttpClient client, String url,
			String parameter) {
		String serverUrl = Constant.ServerURL + url;
		HttpPost post = new HttpPost(serverUrl);
		post.addHeader("Content-Type", "application/json");
		String result = null;
		String parameterEncrypted = null;
		try {

			parameterEncrypted = parameter;

			StringEntity resEntity = new StringEntity(parameterEncrypted,
					"UTF-8");
			post.setEntity(resEntity);
			// 获取响应的结果
			HttpResponse response = client.execute(post);
			// 获取HttpEntity
			HttpEntity respEntity = response.getEntity();
			// 获取响应的结果信息
			byte[] resultByteType = EntityUtils.toByteArray(respEntity);
			// 解压返回的字符串
			result = StringUtil.unCompress(resultByteType);

			return result;

		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = StringUtil.getAppException4MOS("网络连接异常，请检查网络设置！");
		} catch (IOException e) {
			e.printStackTrace();
			result = StringUtil.getAppException4MOS("网络连接异常，请检查网络设置！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
