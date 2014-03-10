package com.cs.animationvideo.util;

import org.apache.http.client.CookieStore;

import com.cs.animationvideo.service.AsyncHttpClient;
import com.cs.animationvideo.service.AsyncHttpResponseHandler;
import com.cs.animationvideo.service.RequestParams;

public class AHttpClient {
	
	public static AsyncHttpClient mAsyncHttpClient = new AsyncHttpClient();

	public static void post(String url,RequestParams params ,AsyncHttpResponseHandler responseHandler){
		mAsyncHttpClient.post(url, params, responseHandler);
	}
	
	public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
		mAsyncHttpClient.get(url, params, responseHandler);
    }
	
	public static void setCookieStore(CookieStore cookieStore){
		mAsyncHttpClient.setCookieStore(cookieStore);
	}
	
}
