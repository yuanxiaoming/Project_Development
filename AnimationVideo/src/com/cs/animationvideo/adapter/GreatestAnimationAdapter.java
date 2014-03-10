package com.cs.animationvideo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.GridLayout.LayoutParams;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.cs.animationvideo.R;
import com.cs.animationvideo.VideoIntroActivity;
import com.cs.animationvideo.entity.GroupContentList;
import com.cs.animationvideo.util.CommonUtil;
import com.cs.animationvideo.widget.MyImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class GreatestAnimationAdapter extends BaseAdapter {
	private DisplayImageOptions m_options ;
	private LayoutInflater m_inflater;
	private GroupContentList[] m_data ;
	private Context m_context;
	public GreatestAnimationAdapter(Context context,GroupContentList[] objects, DisplayImageOptions mOptions) {
		m_options = mOptions;
		m_data = objects ;
		m_context = context;
		m_inflater = LayoutInflater.from(context);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null ;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = m_inflater.inflate(R.layout.fragment_greatest_animation_listview_item, parent, false);
			holder.images[0] = (MyImageView) convertView.findViewById(R.id.image1_greatest_animation);
			holder.images[1] = (MyImageView) convertView.findViewById(R.id.image2_greatest_animation);
			holder.images[2] = (MyImageView) convertView.findViewById(R.id.image3_greatest_animation);
			holder.images[3] = (MyImageView) convertView.findViewById(R.id.image4_greatest_animation);
			holder.images[4] = (MyImageView) convertView.findViewById(R.id.image5_greatest_animation);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		GroupContentList[] item_data = (GroupContentList[]) getItem(position);
		for (int i = 0; i < item_data.length; i++) {
			holder.images[i].setText(item_data[i].getName());
			if( i == 0){
				LayoutParams big = new LayoutParams(GridLayout.spec(i), GridLayout.spec(0,2));
				big.width = (int)(CommonUtil.getWidthMetrics(m_context)*0.66-10);
				big.setMargins(10, 10, 0, 10);
				big.setGravity(Gravity.FILL);
				big.height = (int)(CommonUtil.getWidthMetrics(m_context)*0.33) + 30;
				holder.images[i].setLayoutParams(big);
			}else if(i == 1){
				LayoutParams small = new LayoutParams(GridLayout.spec(0), GridLayout.spec(2));
				small.setMargins(10, 10, 0, 0);
				small.width = (int)(CommonUtil.getWidthMetrics(m_context)*0.33-10);
				small.height = (int)(CommonUtil.getWidthMetrics(m_context)*0.33) + 30;
				holder.images[i].setLayoutParams(small);
			}else{
				LayoutParams small = new LayoutParams(GridLayout.spec(1), GridLayout.spec(i-2));
				small.width = (int)(CommonUtil.getWidthMetrics(m_context)*0.33-10);
				small.height = (int)(CommonUtil.getWidthMetrics(m_context)*0.33) + 30;
				small.setMargins(10, 0, 0, 0);
				holder.images[i].setLayoutParams(small);
			}
			final int video_id = item_data[i].getVideo_id();
			holder.images[i].setOnClickListener(new OnClickListener() {
				public void onClick(View position) {
					Intent video_intro_intent = new Intent(m_context, VideoIntroActivity.class);
					video_intro_intent.putExtra("video_id", video_id);
					video_intro_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					m_context.startActivity(video_intro_intent);
				}
			});
			ImageLoader.getInstance().displayImage(item_data[i].getCover(), holder.images[i].getImageView(), m_options);
		}
		return convertView;
	}
	
	private class ViewHolder {
		public MyImageView[] images = new MyImageView[5];
	}

	@Override
	public int getCount() {
		return m_data.length/5;
	}

	@Override
	public Object getItem(int position) {
		GroupContentList[] item_data = new GroupContentList[5];
		for (int i = 0; i < 5; i++) {
			item_data[i] = m_data[position*5+i];
		}
		return item_data;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
}
