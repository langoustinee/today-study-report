package com.kakao.lango;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kakao.lango.dto.ItemDTO;
import com.kakao.lango.dto.Member;
import com.kakao.lango.service.ItemService;
import com.kakao.lango.validation.MemberValidator;

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
	
	@RequestMapping(value = "detail/{itemid}", method = RequestMethod.GET)
	public String detail(Model model, @PathVariable("itemid") int itemid) {
		// 서비스 메서드 호출하여 하나의 item 가져오기
		ItemDTO item = itemService.getItem(itemid);
		// 데이터 저장하여 전달하기
		model.addAttribute("item", item);
		// detail 뷰에 출력하기
		return "detail";
	}
	
	// item.xls 요청이 왔을 때 excel 뷰로 출력하는 요청 처리 메서드
	@RequestMapping("item.xls")
	public String excel(Model model) {
		List<ItemDTO> list = itemService.allItem();
		model.addAttribute("list", list);
		return "excel";
	}
	
	// pdf 요청이 왔을 때 pdf 뷰로 출력하는 요청 처리 메서드
	@RequestMapping("pdf")
	public String pdfReport(Model model) {
		List<ItemDTO> list = itemService.allItem();
		model.addAttribute("list", list);
		return "pdf";
	}
	
	// JSON 요청을 처리하는 메서드
	@RequestMapping("itemlist.json")
	public String jsonReport(Model model) {
		List<ItemDTO> list = itemService.allItem();
		model.addAttribute("list", list);
		return "itemlist";
	}
	
	@RequestMapping(value = "exception", method = RequestMethod.GET)
	public String input(Model model) {
		return "input";
	}
	
	@RequestMapping(value = "exception", method = RequestMethod.POST)
	public String cal(
			@RequestParam("dividend") int dividend, 
			@RequestParam("divisor") int divisor, 
			Model model) {
		model.addAttribute("result", dividend/divisor);
		return "result";
	}
	
	// 예외 발생시 예외 처리를 위한 메서드
	@ExceptionHandler(Exception.class)
	public String handleException(Model model, Exception e) {
		model.addAttribute("result", e.getLocalizedMessage());
		return "error/exception";
	}
	
	// 메시지 요청을 처리할 메서드
	// 유효성 검사에 실패할 경우 이전에 입력한 내용을 가지고 loginform으로 가도록 수정한다.
	@RequestMapping(value="message", method=RequestMethod.GET)
	public String form(@ModelAttribute("member") Member member) {
		return "loginform";
	}
	
	// @ModelAttribute 어노테이션이 추가된 메서드는 리턴하는 데이터를 모든 뷰에게 전송하게 된다.
	@ModelAttribute("loginTypes")
	public List<String> loginTypes(){
		List<String>list = new ArrayList<String>();
		list.add("일반회원");
		list.add("기업회원");
		list.add("비회원");
		return list;
	}
	
	@RequestMapping(value="message", method = RequestMethod.POST)
	public String submit(@ModelAttribute("command") Member member, Member memberInfo, BindingResult result) {
		// 유효성 검사 수행하기
		new MemberValidator().validate(memberInfo, result);
		if (result.hasErrors()) {
			return "loginform";
		} else {
			return "created";
		}
	}
}
