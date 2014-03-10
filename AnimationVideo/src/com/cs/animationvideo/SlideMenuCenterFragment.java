package com.cs.animationvideo;

import com.cs.animationvideo.entity.GroupContent;
import com.cs.animationvideo.entity.GroupContentList;
import com.cs.animationvideo.entity.MyAsyncHttpResponseHandler;
import com.cs.animationvideo.parser.BaseParse;
import com.cs.animationvideo.parser.GroupContentParser;
import com.cs.animationvideo.service.RequestParams;
import com.cs.animationvideo.util.AHttpClient;
import com.cs.animationvideo.util.Constant;
import com.cs.animationvideo.widget.SlidingMenu;
import com.viewpagerindicator.TabPageIndicator;
import com.viewpagerindicator.TabPageIndicator.OnMyPageChangeListener;

import org.apache.http.Header;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

public class SlideMenuCenterFragment extends BaseFragment {
    private ViewPager m_vpager;
    private TabPageIndicator m_tabindicator;
    private int m_id ;
    @Override
    public void onClick(View v) {

    }

    @Override
    protected void findViewById(View mContentView) {
        m_vpager = (ViewPager) findViewById(mContentView, R.id.vpager_slidemenu_center);
        m_tabindicator = (TabPageIndicator) findViewById(mContentView, R.id.indicator);
    }

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.fragment_slidemenu_center);
    }

    @Override
    protected void processLogic() {

        //get server data
        RequestParams params = new RequestParams();
        params.put("limit","30");//����35�����  ��һ��ҳ��20�� �ڶ���ҳ��15�����
        params.put("id", String.valueOf(m_id));
        params.put("page", "1");
        BaseParse<GroupContent> parser = new GroupContentParser();
        AHttpClient.get(Constant.group_content, params, new MyAsyncHttpResponseHandler<GroupContent>(getActivity(),parser) {

            @Override
            public void onSuccess(int statusCode, Header[] headers,
                    GroupContent serverData) {
//                int count = serverData.getCount();
                List<GroupContentList> list = serverData.getList();
                if(list.size() >= 20){
                    List<GroupContentList> greatest_animation_list = list.subList(0, 15);

                    List<GroupContentList> all_animation_list = list.subList(15, list.size());

                    GreatestAnimationFragment greatest_animation_fragment = new GreatestAnimationFragment();
                    Bundle greatest_animation_args = new Bundle();
                    greatest_animation_args.putParcelableArray("data", greatest_animation_list.toArray(new GroupContentList[]{}));
                    greatest_animation_fragment.setArguments(greatest_animation_args);

                    AllAnimationFragment all_animation_fragment = new AllAnimationFragment();
                    Bundle all_animation_args = new Bundle();
                    all_animation_args.putInt("id", m_id);
                    all_animation_args.putParcelableArray("data", all_animation_list.toArray(new GroupContentList[]{}));
                    all_animation_fragment.setArguments(all_animation_args);

                    Fragment[] fragments = new Fragment[]{greatest_animation_fragment,all_animation_fragment};
                    MyFragmentAdapter adapter = new MyFragmentAdapter(getChildFragmentManager(),fragments);
                    m_vpager.setAdapter(adapter);
                    m_tabindicator.setViewPager(m_vpager);
                }else{
                    //
                }
            }
        });
    }

    @Override
    protected void setListener() {
        m_tabindicator.setOnMyPageChangeListener(new OnMyPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                if(position == m_vpager.getAdapter().getCount() - 1){
                    ((MainActivity)getActivity()).getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
                }else{
                    ((MainActivity)getActivity()).getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                }
            }
        });
    }

    @Override
    protected void getExtraParams() {
        Bundle bundle = getArguments();
        if(bundle !=  null){
            m_id = bundle.getInt("id");
        }
    }

    private class MyFragmentAdapter extends FragmentPagerAdapter{
        private Fragment [] fragments ;
        public MyFragmentAdapter(FragmentManager fm, Fragment[] fragments) {
            super(fm);
            this.fragments = fragments ;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return position == 0?"��ѡ":"ȫ��";
        }
    }
}
