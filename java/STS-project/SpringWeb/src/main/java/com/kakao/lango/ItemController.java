package com.kakao.lango;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kakao.lango.dto.ItemDTO;
import com.kakao.lango.service.ItemService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ItemController {
	
	// private final로 주입하려면 @Autowired는 필요없다.
	// @Autowired
	private final ItemService itemService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		// 서비스 메서드 호출하기
		List<ItemDTO> list = itemService.allItem();
		
		// 결과 저장하기
		model.addAttribute("list", list);
		
		return "home";
	}

}
