package com.cs.animationvideo.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.cs.animationvideo.R;
import com.cs.animationvideo.entity.Group;
import com.cs.animationvideo.entity.GroupList;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

public class GroupAdapter extends ArrayAdapter<Group> {
	private DisplayImageOptions m_options ;
	private LayoutInflater m_inflater ;
	public GroupAdapter(Context context, int resource, List<Group> group, DisplayImageOptions mOptions) {
		super(context, resource, group);
		m_inflater = LayoutInflater.from(context);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null ;
		if(convertView == null){
			holder = new ViewHolder() ;
			convertView = m_inflater.inflate(R.layout.mainactivity_listview_groupadapter_layout, parent, false);
			holder.group = (TextView) convertView.findViewById(R.id.textview_group_name);
			holder.gridView = (GridView) convertView.findViewById(R.id.gridview_group_content);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		Group group = getItem(position);
		holder.group.setText(group.getGroup());
		List<GroupList> groupList = group.getGroupList();
//		View m_header = null ;
//		if(groupList.size() %2 != 0){  //表示是单数  将前三项加入GridView的Header
//			groupList.subList(0, 3);    //表示groupList的前三项
//			groupList = groupList.subList(3, groupList.size());  //将分割后的groupList重新赋给原先的groupList
//		}
		GroupListAdapter m_adapter = new GroupListAdapter(getContext(), 0, groupList,m_options);
//		if(m_header != null){
//			
//		}
		holder.gridView.setAdapter(m_adapter);
		return convertView;
	}
	
	private class ViewHolder{
		public TextView group ;
		public GridView gridView ;
	}
}
