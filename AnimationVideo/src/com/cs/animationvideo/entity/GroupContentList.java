package com.cs.animationvideo.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class GroupContentList implements Parcelable{
	private int video_id;
	private String name;
	private String intro;
	private String area;
	private String cover;
	private String character;
	private String category;

	public GroupContentList() {
	}

	public GroupContentList(int video_id, String name, String intro,
			String area, String cover, String character, String category) {
		this.video_id = video_id;
		this.name = name;
		this.intro = intro;
		this.area = area;
		this.cover = cover;
		this.character = character;
		this.category = category;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(video_id);
		dest.writeString(name);
		dest.writeString(intro);
		dest.writeString(area);
		dest.writeString(cover);
		dest.writeString(character);
		dest.writeString(category);
	}

	public GroupContentList(Parcel source) {
		video_id = source.readInt();
		name = source.readString();
		intro = source.readString();
		area = source.readString();
		cover = source.readString();
		character = source.readString();
		category = source.readString();
	}

	public int getVideo_id() {
		return video_id;
	}

	public void setVideo_id(int video_id) {
		this.video_id = video_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	
	public static final Parcelable.Creator<GroupContentList> CREATOR = new Creator<GroupContentList>() {
		
		@Override
		public GroupContentList[] newArray(int size) {
			return new GroupContentList[size];
		}
		
		@Override
		public GroupContentList createFromParcel(Parcel source) {
			return new GroupContentList(source);
		}
	};
}
