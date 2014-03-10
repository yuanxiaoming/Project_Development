package com.cs.animationvideo.parser;

import java.util.List;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cs.animationvideo.entity.GroupIntro;
import com.cs.animationvideo.entity.GroupIntroList;

public class GroupIntroParser implements BaseParse<GroupIntro> {

	@Override
	public GroupIntro parseJSON(String resultData) {
		GroupIntro groupIntro = null ;
		if(resultData != null){
			JSONObject jsonObject = JSON.parseObject(resultData);
			int video_id = Integer.parseInt( jsonObject.getString("video_id")) ;
			String name = jsonObject.getString("name");
			String intro = jsonObject.getString("intro");
			String area = jsonObject.getString("area");
			String cover = jsonObject.getString("cover");
			String character = jsonObject.getString("character");
			String category = jsonObject.getString("category");
			String score = jsonObject.getString("score");
			String publish_time = jsonObject.getString("publish_time");//episode
			JSONArray array = jsonObject.getJSONArray("episode");
			List<GroupIntroList> list = JSON.parseArray(array.toString(), GroupIntroList.class);
			groupIntro = new GroupIntro(video_id, name, intro, area, cover, character, category, score, publish_time, list);
		}
		return groupIntro;
	}
}
