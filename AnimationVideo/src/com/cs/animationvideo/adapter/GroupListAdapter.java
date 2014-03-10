package com.cs.animationvideo.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.cs.animationvideo.AllAnimationFragment;
import com.cs.animationvideo.R;
import com.cs.animationvideo.entity.GroupList;
import com.cs.animationvideo.util.CommonUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class GroupListAdapter extends ArrayAdapter<GroupList> {
	private LayoutInflater m_inflater ;
	private DisplayImageOptions m_options ;
	public GroupListAdapter(Context context, int resource,List<GroupList> objects, DisplayImageOptions m_options) {
		super(context, resource, objects);
		m_inflater = LayoutInflater.from(context);
		this.m_options = m_options ;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			holder = new ViewHolder() ;
			convertView = m_inflater.inflate(R.layout.mainactivity_listview_groupadapter_layout_listview_grouplistadapter_layout, parent, false);
			holder.pic = (ImageView) convertView.findViewById(R.id.imageview_group_pic);
			holder.name = (TextView) convertView.findViewById(R.id.textview_group_name);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.pic.setLayoutParams(new LayoutParams(CommonUtil.getWidthMetrics(getContext())/2, CommonUtil.getWidthMetrics(getContext())/2));
		holder.pic.setScaleType(ScaleType.CENTER_CROP);
		
		final GroupList groupList = getItem(position);
		holder.name.setText(groupList.getName());
		ImageLoader.getInstance().displayImage(groupList.getPic(), holder.pic, m_options);
		holder.pic.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getContext(), AllAnimationFragment.class);
				intent.putExtra("video_id", groupList.getId());
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				getContext().startActivity(intent);
			}
		});
		return convertView;
	}
	
	private class ViewHolder {
		public ImageView pic ;
		public TextView name ;
	}
}
