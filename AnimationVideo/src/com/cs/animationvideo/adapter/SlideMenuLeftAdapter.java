package com.cs.animationvideo.adapter;

import java.util.List;
import com.cs.animationvideo.R;
import com.cs.animationvideo.entity.GroupList;
import com.cs.animationvideo.util.Provider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SlideMenuLeftAdapter extends ArrayAdapter<GroupList> {
	private LayoutInflater m_inflater ;
	public SlideMenuLeftAdapter(Context context, int resource,
			List<GroupList> objects) {
		super(context, resource, objects);
		m_inflater = LayoutInflater.from(context);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null ;
		if(convertView ==  null){
			holder = new ViewHolder();
			convertView = m_inflater.inflate(R.layout.fragment_slidingmenu_left_listview_item, parent, false);
			holder.image = (ImageView) convertView.findViewById(R.id.iv_sliding_left);
			holder.text = (TextView) convertView.findViewById(R.id.tv_sliding_left_china);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		GroupList groupList = getItem(position);
		if(groupList != null){
			holder.image.setImageResource(Provider.slidingImage[(int)(Math.random()*Provider.slidingImage.length)]);
			holder.text.setText(groupList.getName());
		}
		return convertView;
	}
	
	private class ViewHolder {
		public ImageView image ;
		public TextView text ;
	}

}
