package com.cs.animationvideo.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import com.cs.animationvideo.application.AnimationApplication;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.WindowManager;


public class CommonUtil {
	public static void caculateView(View view){
		int w = View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
		view.measure(w, h);
	}
	
	private static DisplayMetrics getScreenMetrics(Context context){
		DisplayMetrics  dm = new DisplayMetrics(); 
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		windowManager.getDefaultDisplay().getMetrics(dm);
		return dm ;
	}
	
	public static int getWidthMetrics(Context context){
		return getScreenMetrics(context).widthPixels;
	}
	
	public static int getHeightMetrics(Context context){
		return getScreenMetrics(context).heightPixels;
	}
	
	public static int px2dip(Context context, float pxValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (pxValue / scale + 0.5f);  
    }  
	
	public static int dip2px(Context context, float dipValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dipValue * scale + 0.5f);  
    } 
	
	public static int getScreenWidth(Context context){
		return px2dip(context, getWidthMetrics(context));
	}
	
	public static int getScreenHeight(Context context){
		return px2dip(context, getHeightMetrics(context));
	}

	public static String getMobileIp() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()&& inetAddress instanceof Inet4Address) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (Exception e) {}
		return null;
	}
	
	public static String getMobileIMIE(Context context){
		TelephonyManager telephonyManager=(TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getDeviceId();
	}
	
	
	/**
	 * 
	 * @param fragmentActivity   FragmentActivity
	 * @param contentid          包含Fragment的容器Id
	 * @param to				   要显示的Fragment
	 * @param tag			               为要显示的Fragment加一个别名 默认别名为to.getClass().getName().contact(tag);
	 */
	public static void switchToFragment(FragmentActivity fragmentActivity ,int contentid ,Fragment to,String tag){
		AnimationApplication app = (AnimationApplication) fragmentActivity.getApplication();
		FragmentManager manager = fragmentActivity.getSupportFragmentManager();
		String fragment_tag = to.getClass().getName().concat(tag);
		System.out.println("tag"+fragment_tag);
		Fragment to_fragment = manager.findFragmentByTag(fragment_tag);
		Fragment current_fragment = app.m_current_fragment_array.get(contentid);
		if(to_fragment == null){
			if(current_fragment == null){
				manager.beginTransaction().add(contentid, to, fragment_tag).commit();
			}else{
				manager.beginTransaction().add(contentid, to, fragment_tag).hide(current_fragment).commit();
			}
			to_fragment = to ;
		}else if(to_fragment == current_fragment){
			
		}else{
			manager.beginTransaction().show(to_fragment).hide(current_fragment).commit();
		}
		app.m_current_fragment_array.put(contentid, to_fragment) ;
	}
}
