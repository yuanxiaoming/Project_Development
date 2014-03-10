package com.cs.animationvideo.parser;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cs.animationvideo.entity.Group;
import com.cs.animationvideo.entity.GroupList;

public class GroupParser implements BaseParse<List<Group>> {

	@Override
	public List<Group> parseJSON(String resultData) {
		List<Group> groups = new ArrayList<Group>() ;
		if(resultData != null){
			JSONArray jsonArray = JSON.parseArray(resultData);
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				String gp = jsonObject.getString("group");
				JSONArray array = jsonObject.getJSONArray("list");
				List<GroupList> list = JSON.parseArray(array.toString(), GroupList.class);
				Group group = new Group(gp,list);
				groups.add(group);
			}
		}
		return groups;
	}
}
