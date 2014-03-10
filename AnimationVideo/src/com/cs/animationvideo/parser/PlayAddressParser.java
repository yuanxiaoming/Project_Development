package com.cs.animationvideo.parser;

import com.alibaba.fastjson.JSON;
import com.cs.animationvideo.entity.PlayVideoAddress;

public class PlayAddressParser implements BaseParse<PlayVideoAddress> {

	@Override
	public PlayVideoAddress parseJSON(String resultData) {
		if(resultData != null){
			PlayVideoAddress playVideoAddress = JSON.parseObject(resultData, PlayVideoAddress.class);
			return playVideoAddress;
		}
		return null;
	}

}
