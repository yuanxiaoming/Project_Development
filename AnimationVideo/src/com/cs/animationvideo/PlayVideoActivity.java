package com.cs.animationvideo;

import java.util.List;
import org.apache.http.Header;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.cs.animationvideo.entity.MyAsyncHttpResponseHandler;
import com.cs.animationvideo.entity.PlayVideoAddress;
import com.cs.animationvideo.entity.Video;
import com.cs.animationvideo.parser.BaseParse;
import com.cs.animationvideo.parser.PlayAddressParser;
import com.cs.animationvideo.service.RequestParams;
import com.cs.animationvideo.util.AHttpClient;
import com.cs.animationvideo.util.Constant;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class PlayVideoActivity extends BaseActivity {
	private String m_id ;
	private int m_video_id ;
	private VideoView m_video_series ;
	private MediaController m_media_controller;
	@Override
	public void onClick(View v) {

	}

	@Override
	protected void findViewById() {
		m_video_series = (VideoView) findViewById(R.id.videoview_play_series);
		m_media_controller = new MediaController(mContext);
	}

	@Override
	protected void loadViewLayout() {
		setContentView(R.layout.activity_play_video);
	}

	@Override
	protected void processLogic() {
		RequestParams params = new RequestParams();
		params.put("id", m_id);
		params.put("video_id", m_video_id+"");
		params.put("format", "1");
		BaseParse<PlayVideoAddress> parse = new PlayAddressParser();
		AHttpClient.get(Constant.play_address, params, new MyAsyncHttpResponseHandler<PlayVideoAddress>(mContext,parse) {

			@Override
			public void onSuccess(int statusCode, Header[] headers,PlayVideoAddress serverData) {
				JSONArray url = serverData.getUrl();
				List<Video> videos = JSON.parseArray(url.toString(), Video.class);
				//play video 
				playVideo(videos);
			}
		});
	}


	@Override
	protected void setListener() {

	}

	@Override
	protected void getExtraParams() {
		Bundle bundle = getIntent().getExtras();
		if(bundle != null){
			m_id = bundle.getString("id");
			m_video_id = bundle.getInt("video_id");
		}
	}
	
	protected void playVideo(List<Video> videos) {
//		String url = videos.get(0).getUrl();
		m_video_series.setVideoURI(Uri.parse(videos.get(0).getUrl()));
//		m_video_series.setVideoURI(Uri.parse("http://pl.youku.com/playlist/m3u8?vid=XNjc0OTM3NDYw&type=hd2&ts=1378714714&keyframe=0"));
		m_video_series.setMediaController(m_media_controller);
		m_media_controller.setMediaPlayer(m_video_series);
		m_video_series.requestFocus();  
		m_video_series.start(); 
	}
}
