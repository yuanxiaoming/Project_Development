package com.cs.animationvideo.util;

public interface Constant {
	
	//���ȫ���ķ���
	public static String  channel_group= "http://manhua.tmcdn.net/api/channel_group.json";
	
	//��ȡ��������
	//?android_version=203&m=Api&a=listByType&limit=15&id=57&order=3&page=1
	public static String group_content = "http://manhua.haomee.net/?android_version=203&m=Api&a=listByType&order=3";
	
	//&video_id=4271��ȡ����
	public static String group_intro = "http://manhua.haomee.net/?android_version=203&m=Api&a=getInfo";
	
	//?id=520082&video_id=4271& ��ȡ���ŵ�ַ
	public static String play_address = "http://manhua.haomee.net/?android_version=203&m=Api&a=play&android=1";
}
