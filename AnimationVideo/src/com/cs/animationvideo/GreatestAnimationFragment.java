package com.cs.animationvideo;

import com.cs.animationvideo.adapter.GreatestAnimationAdapter;
import com.cs.animationvideo.entity.GroupContentList;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class GreatestAnimationFragment extends BaseFragment {
	private ListView m_greatest_animation_list ;
	private GroupContentList [] m_data ;
	
	@Override
	public void onClick(View arg0) {

	}

	@Override
	protected void findViewById(View mContentView) {
		m_greatest_animation_list = (ListView) findViewById(mContentView, R.id.lv_greatest_animation);
	}

	@Override
	protected void loadViewLayout() {
		setContentView(R.layout.fragment_greatest_animation);
	}

	@Override
	protected void processLogic() {
		GreatestAnimationAdapter adapter = new GreatestAnimationAdapter(getActivity(), m_data,mOptions);
		m_greatest_animation_list.setAdapter(adapter);
	}

	@Override
	protected void setListener() {

	}

	@Override
	protected void getExtraParams() {
		Bundle bundle = getArguments();
		if(bundle != null){
			m_data = (GroupContentList[]) bundle.getParcelableArray("data");
		}
	}
}
