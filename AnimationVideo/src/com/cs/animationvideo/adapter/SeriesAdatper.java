package com.cs.animationvideo.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout.LayoutParams;

import com.cs.animationvideo.PlayVideoActivity;
import com.cs.animationvideo.R;
import com.cs.animationvideo.entity.GroupIntroList;
import com.cs.animationvideo.util.CommonUtil;

public class SeriesAdatper extends ArrayAdapter<GroupIntroList> {
	private LayoutInflater m_inflater ;
	private int m_start_position ;
	private int m_video_id ;
	
	public void setStartPosition(int startPosition) {
		this.m_start_position = startPosition;
	}
	
	public void setVideoId(int videoId ){
		m_video_id = videoId ;
	}

	public SeriesAdatper(Context context, int resource,List<GroupIntroList> objects) {
		super(context, resource, objects);
		m_inflater = LayoutInflater.from(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = m_inflater.inflate(R.layout.fragment_series_adapter_item, parent, false);
		Button series_number = (Button) convertView.findViewById(R.id.btn_series_number);
		series_number.setLayoutParams(new LayoutParams(CommonUtil.getWidthMetrics(getContext())/5, CommonUtil.getWidthMetrics(getContext())/5));
		series_number.setText(String.valueOf(m_start_position + position + 1));
		final GroupIntroList introList = getItem(position);
		series_number.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent play_video_intent = new Intent(getContext(), PlayVideoActivity.class);
				play_video_intent.putExtra("id", introList.getId());
				play_video_intent.putExtra("video_id",m_video_id);
				play_video_intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				getContext().startActivity(play_video_intent);
			}
		});
		return convertView;
	}
}
