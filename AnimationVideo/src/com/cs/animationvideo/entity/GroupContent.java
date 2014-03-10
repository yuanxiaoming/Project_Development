package com.cs.animationvideo.entity;

import java.util.List;


public class GroupContent {
	private int count;
	private List<GroupContentList> list;

	public GroupContent() {
	}

	public GroupContent(int count, List<GroupContentList> list) {
		super();
		this.count = count;
		this.list = list;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<GroupContentList> getList() {
		return list;
	}

	public void setList(List<GroupContentList> list) {
		this.list = list;
	}
}
