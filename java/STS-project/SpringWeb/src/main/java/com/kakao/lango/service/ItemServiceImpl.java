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
		// list는 null 여부를 확인하지 않아도 무방하다.
		// 결과 밙환하기
		for(ItemEntity entity : result) {
			list.add(toDTO(entity));
		}
		
		return list;
	}

	@Override
	public ItemDTO getItem(int itemid) {
		ItemDTO dto = null;
		// 데이터 가져오기
		ItemEntity entity = itemMapper.getItem(itemid);
		// entity null 여부 확인하고 DTO로 변환하기
		if(entity != null) {
			dto = toDTO(entity);
		}
		return dto;
	}
	
	
}
