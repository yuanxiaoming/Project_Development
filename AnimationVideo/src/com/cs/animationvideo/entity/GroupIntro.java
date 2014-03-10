package com.cs.animationvideo.entity;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

public class GroupIntro implements Parcelable{
	private int video_id;
	private String name;
	private String intro;
	private String area;
	private String cover;
	private String character;
	private String category;
	private String score;
	private String publish_time;
	private List<GroupIntroList> groupIntroList;

	public GroupIntro() {
	}

	public GroupIntro(int video_id, String name, String intro, String area,
			String cover, String character, String category, String score,
			String publish_time, List<GroupIntroList> groupIntroList) {
		this.video_id = video_id;
		this.name = name;
		this.intro = intro;
		this.area = area;
		this.cover = cover;
		this.character = character;
		this.category = category;
		this.score = score;
		this.publish_time = publish_time;
		this.groupIntroList = groupIntroList;
	}

	public GroupIntro(Parcel source) {
		video_id = source.readInt();
		name = source.readString();
		intro = source.readString();
		area = source.readString();
		cover = source.readString();
		character = source.readString();
		category = source.readString();
		score = source.readString();
		publish_time = source.readString();
		source.readList(groupIntroList, GroupIntroList.class.getClassLoader());
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
		dest.writeString(score);
		dest.writeString(publish_time);
		dest.writeList(groupIntroList);
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

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getPublish_time() {
		return publish_time;
	}

	public void setPublish_time(String publish_time) {
		this.publish_time = publish_time;
	}

	public List<GroupIntroList> getGroupIntroList() {
		return groupIntroList;
	}

	public void setGroupIntroList(List<GroupIntroList> groupIntroList) {
		this.groupIntroList = groupIntroList;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Parcelable.Creator<GroupIntro> CREATOR = new Creator<GroupIntro>() {
		
		@Override
		public GroupIntro[] newArray(int size) {
			return new GroupIntro[size];
		}
		
		@Override
		public GroupIntro createFromParcel(Parcel source) {
			return new GroupIntro(source);
		}
	};
}
