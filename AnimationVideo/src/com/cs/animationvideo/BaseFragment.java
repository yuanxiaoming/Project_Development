package com.cs.animationvideo;

import com.cs.animationvideo.util.ImageLoaderUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment implements OnClickListener{
	public View mContentView ;
	public LayoutInflater mInflater;
	protected DisplayImageOptions mOptions ;
	private int resId = 0;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		this.mInflater = inflater ;
		mOptions = ImageLoaderUtil.getImageLoaderOptions();
		getExtraParams();
		loadViewLayout();
		if(resId != 0){
			mContentView = inflater.inflate(resId, container, false);
			findViewById(mContentView);
			processLogic();
			setListener();
			return mContentView;
		}
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	
	public void setContentView(int resId) {
		this.resId = resId;
	}
	
//	public void setContentView(String layoutId) {
//		this.resId = ResourceUtil.getLayoutId(getActivity(), layoutId);
//	}
//	
//	public View findViewById(View contentView ,String resId){
//		return contentView.findViewById(ResourceUtil.getId(getActivity(), resId));
//	}
	
	public View findViewById(View contentView ,int resId){
		return contentView.findViewById(resId);
	}

	protected abstract void findViewById(View mContentView);

    protected abstract void loadViewLayout();

    protected abstract void processLogic();

    protected abstract void setListener();
    
    protected abstract void getExtraParams();
    
}

