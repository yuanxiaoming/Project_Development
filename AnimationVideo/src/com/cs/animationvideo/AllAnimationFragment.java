package com.cs.animationvideo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.Header;

import android.os.Bundle;
import android.view.View;

import com.cs.animationvideo.adapter.AllAnimationAdapter;
import com.cs.animationvideo.entity.GroupContent;
import com.cs.animationvideo.entity.GroupContentList;
import com.cs.animationvideo.entity.MyAsyncHttpResponseHandler;
import com.cs.animationvideo.parser.BaseParse;
import com.cs.animationvideo.parser.GroupContentParser;
import com.cs.animationvideo.service.RequestParams;
import com.cs.animationvideo.util.AHttpClient;
import com.cs.animationvideo.util.Constant;
import com.cs.animationvideo.widget.XListView;
import com.cs.animationvideo.widget.XListView.IXListViewListener;

public class AllAnimationFragment extends BaseFragment {
	private XListView m_listview_allanimation ;
	
	private AllAnimationAdapter m_allanimation_adapter ;
	private List<GroupContentList> m_data = new ArrayList<GroupContentList>();
	private int m_id ;
	private int page = 3 ;
	@Override
	public void onClick(View v) {

	}

	@Override
	protected void findViewById(View mContentView) {
		m_listview_allanimation = (XListView) findViewById(mContentView, R.id.xlistview_all_animation);
	}

	@Override
	protected void loadViewLayout() {
		setContentView(R.layout.fragment_all_animation);
	}

	@Override
	protected void processLogic() {
		//获取分组内容
		
		m_allanimation_adapter = new AllAnimationAdapter(getActivity(),m_data,mOptions);
		m_listview_allanimation.setAdapter(m_allanimation_adapter);
		m_listview_allanimation.setPullRefreshEnable(false);
		m_listview_allanimation.setPullLoadEnable(true);
	}

	@Override
	protected void setListener() {
		m_listview_allanimation.setXListViewListener(new IXListViewListener() {
			
			@Override
			public void onRefresh() {
				
			}
			
			@Override
			public void onLoadMore() {
				loadMoreServerData(15,page++);
			}
		});
	}
	
	private void loadMoreComplete(){
		m_listview_allanimation.stopLoadMore();
	}
	
	private void loadMoreServerData(final int limit,int page) {
		//加载更多
		RequestParams params = new RequestParams();
		params.put("limit",limit+"");
		params.put("id", String.valueOf(m_id));
		params.put("page", page+"");
		BaseParse<GroupContent> parser = new GroupContentParser();
		AHttpClient.get(Constant.group_content, params, new MyAsyncHttpResponseHandler<GroupContent>(getActivity(),parser) {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					GroupContent serverData) {
				if(serverData.getList().size() == limit){
					m_data.addAll(serverData.getList());
					m_allanimation_adapter.notifyDataSetChanged();
				}else{
					
				}
				loadMoreComplete();
			}
		});
	}

	@Override
	protected void getExtraParams() {
		Bundle bundle = getArguments();
		if(bundle != null){
			GroupContentList[] data = (GroupContentList[]) bundle.getParcelableArray("data");
			m_data.addAll(Arrays.asList(data));
			m_id = bundle.getInt("id");
		}
	}
}
