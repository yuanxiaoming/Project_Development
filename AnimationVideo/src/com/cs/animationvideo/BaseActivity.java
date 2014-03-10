package com.cs.animationvideo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.Window;
import com.cs.animationvideo.slidingmenu.SlidingFragmentActivity;
import com.cs.animationvideo.util.ImageLoaderUtil;
import com.cs.animationvideo.widget.SlidingMenu;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

public abstract class BaseActivity extends SlidingFragmentActivity implements OnClickListener{
    protected FragmentActivity mContext ;
    protected DisplayImageOptions mOptions ;
    protected FragmentManager mFragmentManager ;
    private int m_behindview_id = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        mContext = this ;
        mOptions = ImageLoaderUtil.getImageLoaderOptions();
        mFragmentManager = getSupportFragmentManager();
        initView();
    }

    private void initView() {
        getExtraParams();
        loadViewLayout();
        if(m_behindview_id == 0){
            setBehindContentView(new View(mContext));
            getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
        findViewById();
        processLogic();
        setListener();

    }


    // 验证密码是否格式良好
    protected boolean isPasswordCorrect(String password) {
        if (!TextUtils.isEmpty(password)) {
            Pattern pattern = Pattern.compile("^[0-9a-zA-Z_]{6,16}$");
            Matcher matcher = pattern.matcher(password);
            return matcher.find();
        }
        return false;
    }

    // 用户是否格式良好
    protected boolean isUserNameCorrect(String userName) {
        if (!TextUtils.isEmpty(userName)) {
            Pattern pattern = Pattern.compile("^[0-9a-zA-Z_]{4,20}$");
            Matcher matcher = pattern.matcher(userName);
            return matcher.find();
        }
        return false;
    }

    public void setContentView(int layoutId) {
        super.setContentView(layoutId);
    }

    @Override
    public void setBehindContentView(int layoutId){
        m_behindview_id = layoutId ;
        super.setBehindContentView(layoutId);
    }

//    public void setContentView(String layoutId) {
//        super.setContentView(ResourceUtil.getLayoutId(mContext, layoutId));
//    }

    public View findViewById(int viewId) {
        return super.findViewById(viewId);
    }

//    public View findViewById(String viewId) {
//        return super.findViewById(ResourceUtil.getId(mContext, viewId));
//    }

    protected abstract void findViewById();

    protected abstract void loadViewLayout();

    protected abstract void processLogic();

    protected abstract void setListener();

    protected abstract void getExtraParams();

}
