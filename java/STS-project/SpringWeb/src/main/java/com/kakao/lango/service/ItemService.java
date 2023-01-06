package com.kakao.lango.service;

import java.util.List;

import com.kakao.lango.domain.ItemEntity;
import com.kakao.lango.dto.ItemDTO;

public interface ItemService {

	// 전체 데이터를 가져오는 메서드
	public List<ItemDTO> allItem();
	
	// 데이터 하나를 가져오는 메서드
	public ItemDTO getItem(int itemid);
	
	// DTO를 Entity로 변환하는 메서드
	// default 키워드로 메서드 구현하기
	public default ItemEntity toEntity(ItemDTO dto) {
		ItemEntity entity = ItemEntity.builder()
				.itemid(dto.getItemid())
				.itemname(dto.getItemname())
				.price(dto.getPrice())
				.description(dto.getDescription())
				.pictureUrl(dto.getPictureUrl())
				.build();
		return entity;
	}
	
	// Entity를 DTO로 변환하는 메서드
	public default ItemDTO toDTO(ItemEntity entity) {
		ItemDTO dto = ItemDTO.builder()
				.itemid(entity.getItemid())
				.itemname(entity.getItemname())
				.price(entity.getPrice())
				.description(entity.getDescription())
				.pictureUrl(entity.getPictureUrl())
				.build();
		return dto;
	}
}
