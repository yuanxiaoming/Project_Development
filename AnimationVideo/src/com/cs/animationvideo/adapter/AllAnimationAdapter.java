package com.cs.animationvideo.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout.LayoutParams;
import com.cs.animationvideo.R;
import com.cs.animationvideo.VideoIntroActivity;
import com.cs.animationvideo.entity.GroupContentList;
import com.cs.animationvideo.util.CommonUtil;
import com.cs.animationvideo.widget.MyImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class AllAnimationAdapter extends BaseAdapter {
	private LayoutInflater m_inflater ;
	private List<GroupContentList> m_data ;
	private DisplayImageOptions m_options;
	private Activity m_context ;
	public AllAnimationAdapter(Activity activity,
			List<GroupContentList> m_data, DisplayImageOptions mOptions) {
		this.m_inflater = LayoutInflater.from(activity);
		this.m_context = activity;
		this.m_data = m_data ;
		this.m_options = mOptions ;
	}

	@Override
	public int getCount() {
		return m_data.size()/3;
	}

	@Override
	public Object getItem(int position) {
		GroupContentList[] item_data = new GroupContentList[3];
		for (int i = 0; i < 3; i++) {
			item_data[i] = m_data.get(position*3+i);
		}
		return item_data;
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = m_inflater.inflate(R.layout.fragment_all_animation_listview_item, parent, false);
			holder.images[0] = (MyImageView) convertView.findViewById(R.id.image1_all_animation);
			holder.images[1] = (MyImageView) convertView.findViewById(R.id.image2_all_animation);
			holder.images[2] = (MyImageView) convertView.findViewById(R.id.image3_all_animation);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		GroupContentList[] item_data = (GroupContentList[]) getItem(position);
		for (int i = 0; i < item_data.length; i++) {
			holder.images[i].setText(item_data[i].getName());
			LayoutParams params = new LayoutParams(0,(int)(CommonUtil.getWidthMetrics(m_context)*0.33)+20);
			params.weight = 1;
			if( i != 0){
				params.leftMargin = CommonUtil.dip2px(m_context, 5);
			}
			holder.images[i].setLayoutParams(params);
			final int video_id = item_data[i].getVideo_id();
			holder.images[i].setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View view) {
					Intent video_intro_intent = new Intent(m_context, VideoIntroActivity.class);
					video_intro_intent.putExtra("video_id", video_id);
					m_context.startActivity(video_intro_intent);
				}
			});
			ImageLoader.getInstance().displayImage(item_data[i].getCover(), holder.images[i].getImageView(), m_options);
		}
		
		return convertView;
	}
	
	private class ViewHolder {
		public MyImageView [] images = new MyImageView[3];
	}
}
