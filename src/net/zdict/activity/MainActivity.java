package net.zdict.activity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;



import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private Button PostButton = null;
	private Button GETButton = null;
	private EditText editText = null;
	private TextView textview = null;
	private WebView webview = null;
	Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			textview.setText((String)msg.obj);
			
		}
		
	};
	Message message = handler.obtainMessage();
	
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
//		PostButton = (Button)findViewById(R.id.POST);
//		editText = (EditText)findViewById(R.id.editText1);
//		webview = (WebView)findViewById(R.id.webView1);
//		textview.setText("OKOKOK");
//		
//		PostButton.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				//postbutton1onClick(v);
//				new postbutton1onClick().start();
//			}
//		});
//		
//
//		
//
//	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_main);
		webview = (WebView)findViewById(R.id.webView1);
		WebSettings settings = webview.getSettings();
		settings.setJavaScriptEnabled(true);
		webview.loadUrl("http://wap.zdic.net");
	        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
		webview.setWebViewClient(new WebViewClient(){
	           @Override
	        public boolean shouldOverrideUrlLoading(WebView view, String url) {
	            // TODO Auto-generated method stub
	               //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
	             view.loadUrl(url);
	            return true;
	        }
	       });		

	}
	
	
	//改写物理按键——返回的逻辑
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            if(webview.canGoBack())
            {
            	webview.goBack();//返回上一页面
                return true;
            }
            else
            {
                System.exit(0);//退出程序
            }
        }
        return super.onKeyDown(keyCode, event);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * 以post方式提交数据
	 * @param url 服务器的地址，直接写Action的地址，如tm/WoHandleAction.do?method=query
	 * @param parameter 拼接好的json字符串
	 * @return 服务端json字符串，当服务器端返回AppException或SysException时，得到的字符串是一个html文档
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String getPostHttpContent(String url, String parameter) {
		HttpClient client = createHttpClient();

		HttpPost post = new HttpPost(url);
		post.addHeader("Content-Type", "application/x-www-form-urlencoded");
		String result = null;
		byte[] resultByteType = null;
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
			resultByteType = EntityUtils.toByteArray(respEntity);
			
			if(resultByteType == null || resultByteType.length==0){
				return result;
			}
			

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private HttpClient createHttpClient() {
		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(params, 500000);
		HttpConnectionParams.setSoTimeout(params, 1800000);
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params,
				HTTP.DEFAULT_CONTENT_CHARSET);
		HttpProtocolParams.setUseExpectContinue(params, true);

		SchemeRegistry schReg = new SchemeRegistry();
		schReg.register(new Scheme("http", PlainSocketFactory
				.getSocketFactory(), 80));
		schReg.register(new Scheme("https",
				SSLSocketFactory.getSocketFactory(), 443));

		ClientConnectionManager conMgr = new ThreadSafeClientConnManager(
				params, schReg);

		return new DefaultHttpClient(conMgr, params);
	}


	class postbutton1onClick  extends Thread {


		//Toast.makeText(MainActivity.this,"成功", Toast.LENGTH_SHORT).show();
		//HttpPost httpRequest=new HttpPost("http://www.zdic.net/sousuo");
		 @Override
		public void run() {
			// TODO Auto-generated method stub
			 	String findworld =editText.getText().toString();
			 	//String 	url = "http://www.zdic.net/sousuo?lb_a=hp&lb_b=mh&lb_c=mh&q="+findworld+"&tp=tp1";
			 	String url = "http://wap.zdic.net/s?q="+findworld+"&submit.x=56&submit.y=21";
			 	 	//url = "http://www.baidu.com";
			 	HttpClient client = new DefaultHttpClient(); 
				HttpPost httpPostRequest=new HttpPost(url);	
				HttpGet httpGet = new HttpGet(url);
				httpGet.addHeader("Content-Type", "text/html; Charset=UTF-8");
				httpPostRequest.addHeader("Content-Type", "text/html; Charset=UTF-8");

			     try { 
		            //取得H  TTP response 
			            HttpResponse httpResponse=client.execute(httpGet);

			            // 若状态 码为 200 
			            if(httpResponse.getStatusLine().getStatusCode()==200){
			                //取出回应字串 
			                String strResult=EntityUtils.toString(httpResponse.getEntity(),"utf-8"); 
			            	System.out.println(strResult);		            	
			            	Document doc = Jsoup.parse(strResult);
			            	String tit =  doc.title();
			            	message.obj=tit;
			            	handler.sendMessage(message);

			            } 
			        } catch (Exception e) {
			        	Log.d("chp", e.getMessage());
			            // TODO Auto-generated catch block 
			            //tv.setText(e.getMessage().toString());
			        } 
		}
	     }
	
	
	class postbutton1onClick_1  extends Thread {


		//Toast.makeText(MainActivity.this,"成功", Toast.LENGTH_SHORT).show();
		//HttpPost httpRequest=new HttpPost("http://www.zdic.net/sousuo");
		 @Override
		public void run() {
			 String findworld =editText.getText().toString();
			 String url = "http://wap.zdic.net/s?q="+findworld+"&submit.x=56&submit.y=21";
			 Document doc;
			try {
				doc = Jsoup.connect(url).get();
				 String title = doc.title();
				 
				 
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			 
		 }
	     }
	
	public void getbutton1onClick(View v) {
        String result = null;  
        try{                 
            HttpClient client = new DefaultHttpClient();         
            HttpGet get = new HttpGet("http://www.deutschine.com");        
            HttpResponse response = client.execute(get);          
            HttpEntity entity = response.getEntity();              
            long length = entity.getContentLength();                
            InputStream is = entity.getContent();                  
            System.out.print(length);  
            if(is != null) {                       
                ByteArrayOutputStream baos = new ByteArrayOutputStream();            
                byte[] buf = new byte[128];                    
                int ch = -1;                     
                int count = 0;            
                int percent=0;  
                while((ch = is.read(buf)) != -1) {
                    baos.write(buf, 0, ch);     
                      
                    count += ch;                          
                    if(length > 0) {          
                          
                        percent=(int) ((count / (float) length) * 100);  
                          
                        Looper looper = Looper.getMainLooper();      

                        Message msg =new Message();  
                        if(percent<100){  
                            msg.what=1;  
                            msg.obj= String.valueOf(percent);    
                        }  
                        else{  
                            result = new String(baos.toByteArray());  
                            msg.what=2;  
                            msg.obj= String.valueOf(result);  
                        }  
                              
                         // 将Message对象送入到main thread的MessageQueue里面     

                              
                        }                           
                        Thread.sleep(50);    // 让线程休眠50ms                  
                    }            
                            
            }                
        } catch(Exception e) {           
            e.printStackTrace();          
        }  
		
	}
	
    class ReceiveMessageThread extends Thread {    
        
        @Override    
        public void run(){    
            Looper.prepare();    
              System.out.println("thread is start!");  
                  
                String result = null;  
                try{                 
                    HttpClient client = new DefaultHttpClient();         
                    HttpGet get = new HttpGet("http://www.zdic.com");        
                    HttpResponse response = client.execute(get);          
                    HttpEntity entity = response.getEntity();              
                    long length = entity.getContentLength();                
                    InputStream is = entity.getContent();                  
                    System.out.print(length);  
                    if(is != null) {                       
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();            
                        byte[] buf = new byte[128];                    
                        int ch = -1;                     
                        int count = 0;            
                        int percent=0;  
                        while((ch = is.read(buf)) != -1) {          
                            baos.write(buf, 0, ch);     
                              
                            count += ch;                          
                            if(length > 0) {          
                                  
                                percent=(int) ((count / (float) length) * 100);  
                                  
                                Looper looper = Looper.getMainLooper();      

                                Message msg =new Message();  
                                if(percent<100){  
                                    msg.what=1;  
                                    msg.obj= String.valueOf(percent);    
                                }  
                                else{  
                                    result = new String(baos.toByteArray());  
                                    msg.what=2;  
                                    msg.obj= String.valueOf(result);  
                                }  
                                      

                                      
                                }                           
                                Thread.sleep(50);    // 让线程休眠50ms                  
                            }            
                                    
                    }                
                } catch(Exception e) {
                    e.printStackTrace();          
                }               
                  
             Looper.loop();    
        }    
    }  
  

}
