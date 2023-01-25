package com.kakao.lango;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kakao.lango.domain.Command;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "lango", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		return "home";
	}
	
	// hello 요청을 GET 방식으로 전송했을 때 처리하는 메서드
	@RequestMapping(value="hello", method=RequestMethod.GET)
	public String hello(Model model) {
		model.addAttribute("message", "Data To View");
		return "hello";
	}
	
	// message/detail/{num}에 해당하는 GET방식의 요청을 처리하는 메서드
	@RequestMapping(value="/message/detail/{num}", method=RequestMethod.GET)
	public String detail(Model model, @PathVariable("num") Integer num) {
		System.out.println("num: " + num);
		model.addAttribute("num", num);
		return "detail";
	}
	
	@RequestMapping(value = "paramOne", method = RequestMethod.GET)
	public String paramOne(Model model, HttpServletRequest request) {
		// 일반적으로 자바 웹 프로그래밍에서 파라미터 읽는 방법은 아래와 같다.
		String name = request.getParameter("name");
		String pwd = request.getParameter("password");
		System.out.println("paramOne - name: " + name + " pwd: " + pwd);
		return "";
	}
	
	@RequestMapping(value = "paramSecond", method = RequestMethod.POST)
	public String paramSecond(Model model, @RequestParam("name") String name, @RequestParam("password") String pwd) {
		System.out.println("paramSecond - name: " + name + " pwd: " + pwd);
		
		return "";
	}
	
	@RequestMapping(value = "paramThird", method = RequestMethod.POST)
	public String paramThird(Model model, Command command) {
		System.out.println(command);
		return "";
	}
	
	@RequestMapping(value = "redirect", method = RequestMethod.GET)
	public String redirect(Model model, HttpSession session, RedirectAttributes rettr) {
		// 데이터 생성하기
		// request.setAttribute("msg", "Hello Redirect!")와 동일하게 동작한다.
		// redirect로 이동하게되면 requestㅇ가 새로 생성되기에 request의 데이터는 전달되지 않는다.
		// 이 때, session이나 RedirectAttributes를 이용해야 한다.
		model.addAttribute("msg", "Hello Redirect!");

		// 브라우저를 종료하거나 세션 초기화를 하지않는다면 계속 유지된다.
		// session.setAttribute("msg", "Hello Session To Data!");
		
		// RedirectAttributes를 통해 한번만 쓰고 버려질 데이터를 전달할 수 있다.
		rettr.addFlashAttribute("msg", "일회용 데이터 전달");
		
		// 리다이렉트 할 때는 redirect:요청경로 형식으로 설정한다.
		return "redirect:display";
	}	
	
	@RequestMapping(value = "display", method = RequestMethod.GET)
	public String display(Model model) {
		
		return "redirect";
	}
}
