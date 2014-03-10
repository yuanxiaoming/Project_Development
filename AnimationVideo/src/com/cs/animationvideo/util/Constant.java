package com.cs.animationvideo.util;

public interface Constant {
	
	//获得全部的分组
	public static String  channel_group= "http://manhua.tmcdn.net/api/channel_group.json";
	
	//获取分组内容
	//?android_version=203&m=Api&a=listByType&limit=15&id=57&order=3&page=1
	public static String group_content = "http://manhua.haomee.net/?android_version=203&m=Api&a=listByType&order=3";
	
	//&video_id=4271获取详情
	public static String group_intro = "http://manhua.haomee.net/?android_version=203&m=Api&a=getInfo";
	
	//?id=520082&video_id=4271& 获取播放地址
	public static String play_address = "http://manhua.haomee.net/?android_version=203&m=Api&a=play&android=1";
}
