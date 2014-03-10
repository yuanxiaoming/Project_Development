package com.cs.animationvideo.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class GroupIntroList implements Parcelable{
	private String id;
	private String name;
	private String from_site;

	public GroupIntroList() {
	}

	public GroupIntroList(String id, String name, String from_site) {
		this.id = id;
		this.name = name;
		this.from_site = from_site;
	}

	public GroupIntroList(Parcel source) {
		id = source.readString();
		name = source.readString();
		from_site = source.readString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFrom_site() {
		return from_site;
	}

	public void setFrom_site(String from_site) {
		this.from_site = from_site;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
		dest.writeString(name);
		dest.writeString(from_site);
	}
	
	public static final Parcelable.Creator<GroupIntroList> CREATOR = new Creator<GroupIntroList>() {
		
		@Override
		public GroupIntroList[] newArray(int size) {
			return new GroupIntroList[size];
		}
		
		@Override
		public GroupIntroList createFromParcel(Parcel source) {
			return new GroupIntroList(source);
		}
	};
}
