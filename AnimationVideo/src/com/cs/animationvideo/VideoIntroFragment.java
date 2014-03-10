package com.cs.animationvideo;

import android.view.View;
import android.widget.TextView;
import com.cs.animationvideo.entity.GroupIntro;

public class VideoIntroFragment extends BaseFragment {
	private TextView m_intro_name , m_intro_area, m_intro_character,m_intro_category,m_intro_content ;
	@Override
	public void onClick(View v) {

	}

	@Override
	protected void findViewById(View mContentView) {
		m_intro_name = (TextView) mContentView.findViewById(R.id.tv_intro_name);
		m_intro_area = (TextView) mContentView.findViewById(R.id.tv_intro_area);
		m_intro_character = (TextView) mContentView.findViewById(R.id.tv_intro_character);
		m_intro_category = (TextView) mContentView.findViewById(R.id.tv_intro_category);
		m_intro_content = (TextView) mContentView.findViewById(R.id.tv_video_intro);
	}

	@Override
	protected void loadViewLayout() {
		setContentView(R.layout.fragment_video_intro);
	}

	@Override
	protected void processLogic() {
		GroupIntro groupIntro = (GroupIntro) getArguments().getParcelable("serverData");
		if(groupIntro != null){
			m_intro_name.setText(groupIntro.getName());
			m_intro_area.setText(groupIntro.getArea());
			m_intro_character.setText(groupIntro.getCharacter());
			m_intro_category.setText(groupIntro.getCategory());
			m_intro_content.setText(groupIntro.getIntro());
		}
	}

	@Override
	protected void setListener() {

	}

	@Override
	protected void getExtraParams() {
		// TODO Auto-generated method stub
		
	}
}
