package com.cs.animationvideo;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.cs.animationvideo.SlideMenuLeftFragment.OnSlideMenuLeftItemClickListener;
import com.cs.animationvideo.util.CommonUtil;
import com.cs.animationvideo.widget.SlidingMenu;

public class MainActivity extends BaseActivity {
	private SlidingMenu m_sliding_menu ;
	private SlideMenuLeftFragment m_left_fragment ;
	private TextView m_title ;
	private ImageView m_setting_slidemenu ;
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_main_setting_slidemenu:
			toggle();
			break;

		default:
			break;
		}
	}

	@Override
	protected void findViewById() {
		m_title = (TextView) findViewById(R.id.tv_main_title);
		m_setting_slidemenu = (ImageView) findViewById(R.id.iv_main_setting_slidemenu);
	}

	@Override
	protected void loadViewLayout() {
		setContentView(R.layout.activity_main);
		setBehindContentView(R.layout.activity_sliding_behind);
	}

	@Override
	protected void processLogic() {
		initSlidingMenu();
		m_left_fragment = new SlideMenuLeftFragment();
		CommonUtil.switchToFragment(mContext, R.id.sliding_content,m_left_fragment,"");
		
		//
		SlideMenuCenterFragment center_fragment = new SlideMenuCenterFragment();
		Bundle args = new Bundle();
		args.putInt("id", 57);
		center_fragment.setArguments(args);
		CommonUtil.switchToFragment(mContext, R.id.slide_center_content, center_fragment, "热门排行");
		
	}

	private void initSlidingMenu() {
		m_sliding_menu = getSlidingMenu();
		m_sliding_menu.setFadeDegree(0.35f);
		m_sliding_menu.setMode(SlidingMenu.LEFT);
		m_sliding_menu.setBehindOffset(200);
		m_sliding_menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);// 设置能否触发侧滑菜单
		m_sliding_menu.setBehindScrollScale(0);// 设置底层不跟着移动
		
	}

	@Override
	protected void setListener() {
		m_left_fragment.setOnSlideMenuLeftItemClickListener(new OnSlideMenuLeftItemClickListener() {
			
			@Override
			public void onSlideMenuItemClick(String title, int id) {
				m_title.setText(title);
				
				//show fragment of center
				SlideMenuCenterFragment center_fragment = new SlideMenuCenterFragment();
				Bundle args = new Bundle();
				args.putInt("id", id);
				center_fragment.setArguments(args);
				CommonUtil.switchToFragment(mContext, R.id.slide_center_content, center_fragment, title);
			}
		});
		
		m_setting_slidemenu.setOnClickListener(this);
	}

	@Override
	protected void getExtraParams() {
		
	}
}
