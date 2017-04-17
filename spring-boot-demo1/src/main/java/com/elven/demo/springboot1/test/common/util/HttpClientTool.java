package com.elven.demo.springboot1.test.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component("httpClientTool")
@ConfigurationProperties(prefix = "httpclient.common.proxy")
public class HttpClientTool {
	
	static Log logger = LogFactory.getLog("");
	
	/**
	 * 代理相关配置
	 */
	private String host;
	private int port;
	private String user;
	private String password;
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * http get
	 * @param url http请求地址
	 * @param isProxy 是否需要代理
	 * @return 回复报文
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String get(String url, boolean isProxy) throws ClientProtocolException, IOException{
		
    	logger.info("::HttpClientTool::url::::::"+url);

		/**
		 * httpclient配置相关
		 */
		HttpClientBuilder httpClientBuilder = HttpClients.custom();
		/**
		 * 设置超时时间
		 */
		httpClientBuilder.setConnectionTimeToLive(5*1000, TimeUnit.MICROSECONDS );
		
		/**
		 * 代理相关
		 */
		if(isProxy){
			
			CredentialsProvider credsProvider = new BasicCredentialsProvider();
			credsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(user, password));
			
			httpClientBuilder.setDefaultCredentialsProvider(credsProvider).setProxy(new HttpHost(host, port));
		}
        
		/**
		 * 定义httpclient
		 */
		CloseableHttpClient httpclient = httpClientBuilder.build();
		
		try{
			/**
			 * 发起http请求
			 */
			HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpclient.execute(httpGet);
            
            try{
            	int statusCode = response.getStatusLine().getStatusCode();
            	
            	logger.info("::HttpClientTool::statusCode::::::"+statusCode);
                
                if(HttpStatus.SC_OK == statusCode){
                	HttpEntity entity = response.getEntity();
                	
                	InputStream in = entity.getContent();
                	
                	String result = IOUtils.toString(in, "UTF-8");
                	
                	logger.info("::HttpClientTool::result::::::"+result);
                	
                	EntityUtils.consume(entity);
                	
                	return result;
                }
            }
            finally{
            	response.close();
            }
		}
		finally{
			httpclient.close();
		}
		
		return null;
	}
	
	/**
	 * http get
	 * @param url http请求地址
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String get(String url) throws ClientProtocolException, IOException{
		return get(url, false);
	}
	
	/**
	 * 适配两种post提交
	 * @param url 请求连接
	 * @param params map参数
	 * @param request 流数据
	 * @param isProxy 是否需要代理
	 * @return 回复报文
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Deprecated
	public String post(String url, Map<String, String> params, String request, boolean isProxy) throws ClientProtocolException, IOException{
    	logger.info("::HttpClientTool::url::::::"+url);
    	logger.info("::HttpClientTool::params::::::"+params);
    	logger.info("::HttpClientTool::request::::::"+request);

		/**
		 * httpclient配置相关
		 */
		HttpClientBuilder httpClientBuilder = HttpClients.custom();
		/**
		 * 设置超时时间
		 */
		httpClientBuilder.setConnectionTimeToLive(5*1000, TimeUnit.MICROSECONDS );
		
		/**
		 * 代理相关
		 */
		if(isProxy){
			
			CredentialsProvider credsProvider = new BasicCredentialsProvider();
			credsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(user, password));
			
			httpClientBuilder.setDefaultCredentialsProvider(credsProvider).setProxy(new HttpHost(host, port));
		}
        
		/**
		 * 定义httpclient
		 */
		CloseableHttpClient httpclient = httpClientBuilder.build();
		
		
		try{
			/**
			 * 发起http请求
			 */
			HttpPost httpPost = new HttpPost(url);
			
			if(params != null){
				List <NameValuePair> nvps = new ArrayList <NameValuePair>();
				for(String key : params.keySet()){
					nvps.add(new BasicNameValuePair(key, params.get(key)));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			}
            
            if(request != null && !"".equals(request.trim())){
            	StringEntity requestEntity =  new StringEntity(request.trim(), "UTF-8");
            	httpPost.setEntity(requestEntity);
            }
            
            
            CloseableHttpResponse response = httpclient.execute(httpPost);
            
            try{
            	int statusCode = response.getStatusLine().getStatusCode();
            	
            	logger.info("::HttpClientTool::statusCode::::::"+statusCode);
                
                if(HttpStatus.SC_OK == statusCode){
                	HttpEntity entity = response.getEntity();
                	
                	InputStream in = entity.getContent();
                	
                	String result = IOUtils.toString(in, "UTF-8");
                	
                	logger.info("::HttpClientTool::result::::::"+result);
                	
                	EntityUtils.consume(entity);
                	
                	return result;
                }
            }
            finally{
            	response.close();
            }
		}
		finally{
			httpclient.close();
		}
		
		return null;
	}
	
	/**
	 * http post
	 * @param url http请求地址
	 * @param params http请求参数
	 * @param isProxy 是否使用代理
	 * @return 回复报文
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public String post(String url, Map<String, String> params, boolean isProxy) throws ClientProtocolException, IOException{
		logger.info("::HttpClientTool::url::::::"+url);
    	logger.info("::HttpClientTool::params::::::"+params);
    	
		/**
		 * httpclient配置相关
		 */
		HttpClientBuilder httpClientBuilder = HttpClients.custom();
		/**
		 * 设置超时时间
		 */
		httpClientBuilder.setConnectionTimeToLive(5*1000, TimeUnit.MICROSECONDS );
		
		/**
		 * 代理相关
		 */
		if(isProxy){
			
			CredentialsProvider credsProvider = new BasicCredentialsProvider();
			credsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(user, password));
			
			httpClientBuilder.setDefaultCredentialsProvider(credsProvider).setProxy(new HttpHost(host, port));
		}
        
		/**
		 * 定义httpclient
		 */
		CloseableHttpClient httpclient = httpClientBuilder.build();
		
		
		try{
			/**
			 * 发起http请求
			 */
			HttpPost httpPost = new HttpPost(url);
			
			List <NameValuePair> nvps = new ArrayList <NameValuePair>();
			
			if(params != null){
				for(String key : params.keySet()){
					nvps.add(new BasicNameValuePair(key, params.get(key)));
				}
			}
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            
            CloseableHttpResponse response = httpclient.execute(httpPost);
            
            try{
            	int statusCode = response.getStatusLine().getStatusCode();
            	
            	logger.info("::HttpClientTool::statusCode::::::"+statusCode);
                
                if(HttpStatus.SC_OK == statusCode){
                	HttpEntity entity = response.getEntity();
                	
                	InputStream in = entity.getContent();
                	
                	String result = IOUtils.toString(in, "UTF-8");
                	
                	logger.info("::HttpClientTool::result::::::"+result);
                	
                	EntityUtils.consume(entity);
                	
                	return result;
                }
            }
            finally{
            	response.close();
            }
		}
		finally{
			httpclient.close();
		}
		
		return null;
	}
	
	/**
	 * http post
	 * @param url http请求地址
	 * @param params http请求参数
	 * @return 回复报文
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String post(String url, Map<String, String> params) throws ClientProtocolException, IOException{
		return post(url, params, false);
	}
	
	/**
	 * http post
	 * @param url http请求地址
	 * @param request 请求报文
	 * @param isProxy 是否使用代理
	 * @return 回复报文
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String post(String url, String request, boolean isProxy) throws ClientProtocolException, IOException{
		logger.info("::HttpClientTool::url::::::"+url);
    	logger.info("::HttpClientTool::request::::::"+request);
    	
		/**
		 * httpclient配置相关
		 */
		HttpClientBuilder httpClientBuilder = HttpClients.custom();
		/**
		 * 设置超时时间
		 */
		httpClientBuilder.setConnectionTimeToLive(5*1000, TimeUnit.MICROSECONDS );
		
		/**
		 * 代理相关
		 */
		if(isProxy){
			
			CredentialsProvider credsProvider = new BasicCredentialsProvider();
			credsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(user, password));
			
			httpClientBuilder.setDefaultCredentialsProvider(credsProvider).setProxy(new HttpHost(host, port));
		}
        
		/**
		 * 定义httpclient
		 */
		CloseableHttpClient httpclient = httpClientBuilder.build();
		
		try{
			/**
			 * 发起http请求
			 */
			HttpPost httpPost = new HttpPost(url);
			
			StringEntity requestEntity =  new StringEntity(request, "UTF-8");
            httpPost.setEntity(requestEntity);
            
            CloseableHttpResponse response = httpclient.execute(httpPost);
            
            try{
            	int statusCode = response.getStatusLine().getStatusCode();
            	
            	logger.info("::HttpClientTool::statusCode::::::"+statusCode);
                
                if(HttpStatus.SC_OK == statusCode){
                	HttpEntity entity = response.getEntity();
                	
                	InputStream in = entity.getContent();
                	
                	String result = IOUtils.toString(in, "UTF-8");
                	
                	logger.info("::HttpClientTool::result::::::"+result);
                	
                	EntityUtils.consume(entity);
                	
                	return result;
                }
            }
            finally{
            	response.close();
            }
		}
		finally{
			httpclient.close();
		}
		
		return null;
	}
	
	/**
	 * http post
	 * @param url http请求地址
	 * @param request 请求报文
	 * @return 回复报文
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String post(String url, String request) throws ClientProtocolException, IOException{
		return post(url, request, false);
	}
}
