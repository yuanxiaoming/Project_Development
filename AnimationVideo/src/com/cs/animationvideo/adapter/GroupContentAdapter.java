package com.cs.animationvideo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.cs.animationvideo.R;
import com.cs.animationvideo.entity.GroupContentList;
import com.cs.animationvideo.util.CommonUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class GroupContentAdapter extends ArrayAdapter<GroupContentList> {
	private LayoutInflater m_inflater ;
	private DisplayImageOptions m_options ;
	public GroupContentAdapter(Context context, int resource,GroupContentList[] objects,DisplayImageOptions m_options) {
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
		holder.pic.setLayoutParams(new LayoutParams(CommonUtil.getWidthMetrics(getContext())/3, (int)(CommonUtil.getWidthMetrics(getContext())/(float)2.5)));
		holder.pic.setScaleType(ScaleType.CENTER_CROP);
		
		final GroupContentList groupContentList = getItem(position);
		holder.name.setText(groupContentList.getName());
		ImageLoader.getInstance().displayImage(groupContentList.getCover(), holder.pic, m_options);
		
		return convertView;
	}
	
	private class ViewHolder {
		public ImageView pic ;
		public TextView name ;
	}
}
