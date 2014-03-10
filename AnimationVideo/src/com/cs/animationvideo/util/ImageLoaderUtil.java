package com.cs.animationvideo.util;

import android.graphics.Bitmap;

import com.cs.animationvideo.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
public class ImageLoaderUtil {
	
	public static DisplayImageOptions getImageLoaderOptions(){
		DisplayImageOptions options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.biz_news_specialmedia_default_bg)
		.showImageForEmptyUri(R.drawable.biz_news_specialmedia_default_bg)
		.showImageOnFail(R.drawable.biz_news_specialmedia_default_bg)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.displayer(new FadeInBitmapDisplayer(400))
		.bitmapConfig(Bitmap.Config.RGB_565)
		.build();
		return options ;
	}
}
