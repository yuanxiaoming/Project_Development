package com.cs.animationvideo;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.Header;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.cs.animationvideo.adapter.SlideMenuLeftAdapter;
import com.cs.animationvideo.entity.Group;
import com.cs.animationvideo.entity.GroupList;
import com.cs.animationvideo.entity.MyAsyncHttpResponseHandler;
import com.cs.animationvideo.parser.BaseParse;
import com.cs.animationvideo.parser.GroupParser;
import com.cs.animationvideo.util.AHttpClient;
import com.cs.animationvideo.util.Constant;


public class SlideMenuLeftFragment extends BaseFragment {

	private ListView m_listview ;
	private OnSlideMenuLeftItemClickListener m_slidemenu_item_click_listener;
	
	public void setOnSlideMenuLeftItemClickListener(OnSlideMenuLeftItemClickListener listener){
		this.m_slidemenu_item_click_listener = listener ;
	}
	@Override
	public void onClick(View arg0) {
		
	}

	@Override
	protected void findViewById(View mContentView) {
		m_listview = (ListView) findViewById(mContentView, R.id.lv_slidingmenu_left);
	}

	@Override
	protected void loadViewLayout() {
		setContentView(R.layout.fragment_slidingmenu_left);
	}

	@Override
	protected void processLogic() {
		BaseParse<List<Group>> baseParse = new GroupParser();
		AHttpClient.get(Constant.channel_group, null, new MyAsyncHttpResponseHandler<List<Group>>(getActivity(),baseParse) {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					List<Group> serverData) {
				List<GroupList> list = new ArrayList<GroupList>();
				for (Group group : serverData) {
					list.addAll(group.getGroupList());
				}
				SlideMenuLeftAdapter adapter = new SlideMenuLeftAdapter(getActivity(), 0, list);
				m_listview.setAdapter(adapter);
			}
		});
	}

	@Override
	protected void setListener() {
		m_listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View arg1, int position,
					long arg3) {
				if(m_slidemenu_item_click_listener != null){
					GroupList groupList = (GroupList) adapterView.getAdapter().getItem(position);
					m_slidemenu_item_click_listener.onSlideMenuItemClick(groupList.getName(),groupList.getId());
				}
				((MainActivity)getActivity()).toggle();
			}
		});
	}
	
	public interface OnSlideMenuLeftItemClickListener{
		void onSlideMenuItemClick(String title,int id);
	}

	@Override
	protected void getExtraParams() {
		// TODO Auto-generated method stub
		
	}
}
