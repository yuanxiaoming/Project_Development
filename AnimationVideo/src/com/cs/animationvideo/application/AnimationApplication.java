package com.cs.animationvideo.application;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LRULimitedMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;


public class AnimationApplication extends Application {
	public SparseArray<Fragment> m_current_fragment_array;
	@Override
	public void onCreate() {
		super.onCreate();
		initImageLoader(getApplicationContext());
		m_current_fragment_array = new SparseArray<Fragment>();
	}
	
	private void initImageLoader(Context context) {
		int width = context.getResources().getDisplayMetrics().widthPixels;
		int height = context.getResources().getDisplayMetrics().heightPixels;
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
				.memoryCacheExtraOptions(width, height)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
//				.writeDebugLogs() // Remove for release app
				.memoryCache(new LRULimitedMemoryCache(12 * 1024 * 1024))
				.memoryCacheSize(12 * 1024 * 1024)
				.memoryCacheSizePercentage(50) // default
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}
}
