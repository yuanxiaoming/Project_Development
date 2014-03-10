package com.cs.animationvideo.parser;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cs.animationvideo.entity.GroupContent;
import com.cs.animationvideo.entity.GroupContentList;

public class GroupContentParser implements BaseParse<GroupContent> {

	@Override
	public GroupContent parseJSON(String resultData) {
		GroupContent groupContent = new GroupContent();
		if(resultData != null){
			JSONObject jsonObject = JSON.parseObject(resultData);
			int count = Integer.parseInt(jsonObject.getString("count"));
			JSONArray jsonArray = jsonObject.getJSONArray("list");
			List<GroupContentList> array = JSON.parseArray(jsonArray.toString(), GroupContentList.class);
			groupContent.setCount(count);
			groupContent.setList(array);
		}
		return groupContent;
	}
}
