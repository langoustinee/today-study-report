package com.kakao.lango.springbootboard.controller;

import com.kakao.lango.springbootboard.dto.PageRequestDTO;
import com.kakao.lango.springbootboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardService boardService;

    @GetMapping({"/", "/board/list"})
    public String list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("[BoardController] /board/list: " + pageRequestDTO);
        model.addAttribute("result", boardService.getList(pageRequestDTO));
        return "/board/list";
    }


}
