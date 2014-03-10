package com.cs.animationvideo.entity;

import org.apache.http.Header;

import android.content.Context;
import android.widget.Toast;

import com.cs.animationvideo.parser.BaseParse;
import com.cs.animationvideo.service.AsyncHttpResponseHandler;

public abstract class MyAsyncHttpResponseHandler<T>  extends AsyncHttpResponseHandler{
	private Context mContext ;
	private BaseParse<T> baseParse ;
	public MyAsyncHttpResponseHandler(Context mContext,BaseParse<T> baseParse) {
		this.mContext = mContext ;
		this.baseParse = baseParse ;
	}
	public MyAsyncHttpResponseHandler(Context mContext) {
		this.mContext = mContext ;
	}

	@Override
	public void onFailure(int statusCode, Header[] headers,byte[] responseBody, Throwable error) {
		switch (statusCode) {
		case 404:
			Toast.makeText(mContext, "ÍøÂç´íÎó", Toast.LENGTH_SHORT).show();
			break;
		case 500:
			Toast.makeText(mContext, "·þÎñÆ÷Ã¦", Toast.LENGTH_SHORT).show();
			break ;
		default:
			Toast.makeText(mContext, "·þÎñÆ÷Ã¦", Toast.LENGTH_SHORT).show();
			break;
		}
		System.out.println(error);
	}
	
	@Override
	public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
		String data = new String(responseBody);
		T parseJSON = null ;
		if(baseParse != null){
			parseJSON = baseParse.parseJSON(data);
			onSuccess(statusCode, headers, parseJSON);
		}
	}
	
	@Override
	public void onStart() {
		
	}
	
	@Override
	public void onFinish() {
		
	}
	
	public abstract void onSuccess(int statusCode , Header[] headers , T serverData);
}
