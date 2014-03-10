package com.cs.animationvideo.entity;

public class Video {
	private String url;
	private int play_time;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPlay_time() {
		return play_time;
	}

	public void setPlay_time(int play_time) {
		this.play_time = play_time;
	}

	public Video() {
	}

	public Video(String url, int play_time) {
		super();
		this.url = url;
		this.play_time = play_time;
	}

}
