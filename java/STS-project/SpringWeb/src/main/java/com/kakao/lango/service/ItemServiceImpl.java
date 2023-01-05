package com.kakao.lango.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kakao.lango.domain.ItemEntity;
import com.kakao.lango.dto.ItemDTO;
import com.kakao.lango.persistence.ItemMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
	
	// @Autowired
	private final ItemMapper itemMapper;

	@Override
	public List<ItemDTO> allItem() {
		List<ItemDTO> list = new ArrayList<>();
		
		// Repository의 메서드 호출하기
		List<ItemEntity> result = itemMapper.allItem();
		
		// 결과 밙환하기
		for(ItemEntity entity : result) {
			list.add(toDTO(entity));
		}
		
		return list;
	}
	
	
}
