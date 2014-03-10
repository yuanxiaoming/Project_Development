package com.cs.animationvideo.entity;

import java.util.List;


public class Group {
	private String group;
	private List<GroupList> groupList;

	public Group(String group, List<GroupList> groupList) {
		super();
		this.group = group;
		this.groupList = groupList;
	}

	public Group() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public List<GroupList> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<GroupList> groupList) {
		this.groupList = groupList;
	}

}
