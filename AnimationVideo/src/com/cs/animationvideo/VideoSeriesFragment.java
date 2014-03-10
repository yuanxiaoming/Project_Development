package com.cs.animationvideo;

import java.util.ArrayList;
import java.util.List;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.cs.animationvideo.adapter.SeriesAdatper;
import com.cs.animationvideo.entity.GroupIntro;
import com.cs.animationvideo.entity.GroupIntroList;

public class VideoSeriesFragment extends BaseFragment {
	private static final int PAGE_NUMBER = 45 ;   //每页有多少集
	private List<List<GroupIntroList>> m_page_series_list = new ArrayList<List<GroupIntroList>>();
	private GridView m_gridview_series ;
	private HorizontalScrollView m_horizontal_scrollview ;
	private RadioGroup m_radiogroup ;
	private List<GroupIntroList> m_current_show_series ;  //当前需要显示的剧集
	private SeriesAdatper m_series_adatper ;
	private int m_video_id ;
	@Override
	public void onClick(View v) {

	}

	@Override
	protected void findViewById(View mContentView) {
		m_gridview_series = (GridView) mContentView.findViewById(R.id.gv_series);
		m_horizontal_scrollview = (HorizontalScrollView) mContentView.findViewById(R.id.horizontal_scrollview);
		m_radiogroup = (RadioGroup) findViewById(mContentView, R.id.rg_series);
	}

	@Override
	protected void loadViewLayout() {
		setContentView(R.layout.fragment_intro_series);
	}

	@Override
	protected void processLogic() {
		GroupIntro groupIntro = (GroupIntro) getArguments().getParcelable("serverData");
		m_video_id = groupIntro.getVideo_id();
		List<GroupIntroList> seriesList = groupIntro.getGroupIntroList();
		int size = seriesList.size();  //359/60
		System.out.println(size);
		int pageCount = size / PAGE_NUMBER + 1;      //有几页
		
		for (int i = 0; i < pageCount; i++) {
			//取出一页所显示的数据
			List<GroupIntroList> pageSeriesList = null;
			if(i == pageCount - 1){
				pageSeriesList = seriesList.subList(PAGE_NUMBER*i, seriesList.size());
			}else{
				pageSeriesList = seriesList.subList(PAGE_NUMBER*i, PAGE_NUMBER*(i+1));
			}
			m_page_series_list.add(pageSeriesList);
		}
		
		//显示界面
		if(pageCount < 2){
			m_horizontal_scrollview.setVisibility(View.GONE);
		}else{
			//动态添加RadioButton到RadioGroup
			m_horizontal_scrollview.setVisibility(View.VISIBLE);
			for (int i = 0; i < pageCount; i++) {
				final RadioButton radioButton = new RadioButton(getActivity());
				radioButton.setButtonDrawable(getResources().getDrawable(android.R.color.transparent)) ;
				if(i == pageCount - 1){
					radioButton.setText(PAGE_NUMBER*i+1+"-"+seriesList.size());
				}else{
					radioButton.setText(i*PAGE_NUMBER+1+"-"+(i+1)*PAGE_NUMBER);
				}
				radioButton.setBackgroundResource(R.drawable.btn_register_selector);
				radioButton.setTextColor(Color.parseColor("#FFFFFF"));
				radioButton.setGravity(Gravity.CENTER);
				radioButton.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
					
					@Override
					public void onGlobalLayout() {
						LayoutParams params = new LayoutParams(radioButton.getWidth(), radioButton.getHeight());
						params.setMargins(5, 0, 0, 0);
						radioButton.setLayoutParams(params);
					}
				});
				radioButton.setPadding(10, 6, 10, 6);
				radioButton.setId(i);
				m_radiogroup.addView(radioButton, i);
			}
			m_radiogroup.check(0);
		}
		
		m_current_show_series = m_page_series_list.get(0);
		m_series_adatper = new SeriesAdatper(getActivity(), 0, m_current_show_series);
		m_series_adatper.setVideoId(m_video_id);
		m_gridview_series.setAdapter(m_series_adatper);
	}

	@Override
	protected void setListener() {
		m_radiogroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				m_current_show_series = m_page_series_list.get(checkedId);
				System.out.println(m_current_show_series.size());
				m_series_adatper = new SeriesAdatper(getActivity(), 0, m_current_show_series);
				m_series_adatper.setStartPosition(checkedId*PAGE_NUMBER);
				m_series_adatper.setVideoId(m_video_id);
				m_gridview_series.setAdapter(m_series_adatper);
//				m_series_adatper.notifyDataSetInvalidated();
			}
		});
	}

	@Override
	protected void getExtraParams() {
		
	}
}
