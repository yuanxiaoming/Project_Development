package com.cs.animationvideo.entity;

import com.alibaba.fastjson.JSONArray;

public class PlayVideoAddress {
	private int cur_format;
	private JSONArray url;
	private String type;
	private int play_time;
	private String vid;
	private String from;
	private int error;
	private JSONArray format;

	public PlayVideoAddress() {
	}

	public PlayVideoAddress(int cur_format, JSONArray url, String type,
			int play_time, String vid, String from, int error, JSONArray format) {
		this.cur_format = cur_format;
		this.url = url;
		this.type = type;
		this.play_time = play_time;
		this.vid = vid;
		this.from = from;
		this.error = error;
		this.format = format;
	}

	public int getCur_format() {
		return cur_format;
	}

	public void setCur_format(int cur_format) {
		this.cur_format = cur_format;
	}

	public JSONArray getUrl() {
		return url;
	}

	public void setUrl(JSONArray url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPlay_time() {
		return play_time;
	}

	public void setPlay_time(int play_time) {
		this.play_time = play_time;
	}

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public JSONArray getFormat() {
		return format;
	}

	public void setFormat(JSONArray format) {
		this.format = format;
	}
}
