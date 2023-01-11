package com.kakao.lango.guestbook.controller;

import com.kakao.lango.guestbook.dto.GuestBookDTO;
import com.kakao.lango.guestbook.dto.PageRequestDTO;
import com.kakao.lango.guestbook.service.GuestBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@Controller
@RequiredArgsConstructor
public class ViewController {

    private final GuestBookService guestBookService;

    @GetMapping("/")
    public String list() {
        log.info("[main view]");
        return "/guestbook/list";
    }

    @GetMapping("/guestbook/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        model.addAttribute("result", guestBookService.getList(pageRequestDTO));
    }

    @GetMapping("/guestbook/register")
    public void register() {
        log.info("Data Insert Request!");
    }

    @PostMapping("/guestbook/register")
    public String register(GuestBookDTO dto, RedirectAttributes rattr) {
        // 파라미터 확인하기
        log.info(dto);

        Long gno = guestBookService.register(dto);

        // RedirectAttributes는 세션에 저장하는데 한 번 사용하고 자동 소멸된다.
        // 데이터 전송하기
        rattr.addFlashAttribute("msg", gno + " 등록");
        
        // 데이터베이스 변경 작업을 수행하고 나면 반드시 리다이렉트를 해줘야 한다.
        return "redirect:/guestbook/list";
    }
}
