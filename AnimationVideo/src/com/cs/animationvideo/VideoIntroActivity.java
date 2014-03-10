package com.cs.animationvideo;

import org.apache.http.Header;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.cs.animationvideo.entity.GroupIntro;
import com.cs.animationvideo.entity.MyAsyncHttpResponseHandler;
import com.cs.animationvideo.parser.GroupIntroParser;
import com.cs.animationvideo.service.RequestParams;
import com.cs.animationvideo.util.AHttpClient;
import com.cs.animationvideo.util.Constant;
import com.nostra13.universalimageloader.core.ImageLoader;

public class VideoIntroActivity extends BaseActivity {
	private int m_video_id ;
	private ImageView m_video_cover ;
	private TextView m_video_name , m_video_score, m_video_publishtime , m_video_area;
	private RadioButton m_video_intro_content, m_video_intro_series ;
	private GroupIntro m_server_data ;
	private Fragment m_current_fragment ;
	
	@Override
	public void onClick(View v) {

	}

	@Override
	protected void findViewById() {
		m_video_cover = (ImageView) findViewById(R.id.image_cover);
		m_video_name = (TextView) findViewById(R.id.tv_name);
		m_video_score = (TextView) findViewById(R.id.tv_score);
		m_video_publishtime = (TextView) findViewById(R.id.tv_publish_time);
		m_video_area = (TextView) findViewById(R.id.tv_area);
		m_video_intro_content = (RadioButton) findViewById(R.id.rb_video_intro_content);
		m_video_intro_series = (RadioButton) findViewById(R.id.rb_video_intro_series);
	}

	@Override
	protected void loadViewLayout() {
		setContentView(R.layout.activity_vedio_intro);
	}

	@Override
	protected void processLogic() {
		RequestParams params = new RequestParams();
		params.put("video_id", m_video_id+"");
		GroupIntroParser introParser = new GroupIntroParser();
		AHttpClient.get(Constant.group_intro, params, new MyAsyncHttpResponseHandler<GroupIntro>(mContext,introParser) {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					GroupIntro serverData) {
				m_server_data = serverData ;
				ImageLoader.getInstance().displayImage(serverData.getCover(), m_video_cover, mOptions);
				m_video_name.setText(serverData.getName());
				m_video_score.setText(serverData.getScore());
				m_video_publishtime.setText(serverData.getPublish_time());
				m_video_area.setText(serverData.getArea());
				
				VideoIntroFragment introFragment = new VideoIntroFragment();
				Bundle args = new Bundle();
				args.putParcelable("serverData", serverData);
				introFragment.setArguments(args);
				mFragmentManager.beginTransaction().add(R.id.intro_content, introFragment, VideoIntroFragment.class.getName()).commit();
				m_current_fragment = introFragment ;
			}
		});
	}

	@Override
	protected void setListener() {
		m_video_intro_series.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked && m_server_data != null){
					VideoSeriesFragment series = (VideoSeriesFragment) mFragmentManager.findFragmentByTag(VideoSeriesFragment.class.getName());
					if(series == null){
						series = new VideoSeriesFragment();
						Bundle args = new Bundle();
						args.putParcelable("serverData", m_server_data);
						series.setArguments(args);
						mFragmentManager.beginTransaction().add(R.id.intro_content, series, VideoSeriesFragment.class.getName()).commit();
					}else{
						mFragmentManager.beginTransaction().show(series).commit();
					}
					mFragmentManager.beginTransaction().hide(m_current_fragment).commit();
					m_current_fragment = series ;
				}
			}
		});
		
		m_video_intro_content.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					Fragment intro = mFragmentManager.findFragmentByTag(VideoIntroFragment.class.getName());
					if(intro != null){
						mFragmentManager.beginTransaction().show(intro).commit();
						mFragmentManager.beginTransaction().hide(m_current_fragment).commit();
						m_current_fragment = intro ; 
					}
				}
			}
		});
	}

	@Override
	protected void getExtraParams() {
		m_video_id = getIntent().getExtras().getInt("video_id");
	}
}
