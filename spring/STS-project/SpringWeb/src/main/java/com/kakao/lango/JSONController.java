package com.kakao.lango;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kakao.lango.dto.ItemDTO;
import com.kakao.lango.service.ItemService;

@RestController
public class JSONController {
	
	@Autowired
	private ItemService ItemService;
	
	// csv를 리턴하는 메서드
	@RequestMapping(value="item.csv", method=RequestMethod.GET)
	public String csvText() {
		return "DataFormat : csv, xml, json";
	}
	
	// json을 리턴하는 메서드
	@RequestMapping(value="itemlistrest.json", method=RequestMethod.GET)
	public List<ItemDTO> json() {
		return ItemService.allItem();
	}

}
