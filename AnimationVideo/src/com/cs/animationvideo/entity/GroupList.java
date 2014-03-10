package com.cs.animationvideo.entity;

public class GroupList {
	private int id;
	private String name;
	private String pic;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public GroupList() {
		super();
	}

	public GroupList(int id, String name, String pic) {
		super();
		this.id = id;
		this.name = name;
		this.pic = pic;
	}
}
