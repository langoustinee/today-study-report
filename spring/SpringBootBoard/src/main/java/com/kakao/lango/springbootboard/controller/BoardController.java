package com.kakao.lango.springbootboard.controller;

import com.kakao.lango.springbootboard.dto.BoardDTO;
import com.kakao.lango.springbootboard.dto.PageRequestDTO;
import com.kakao.lango.springbootboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    // 게시글 등록 화면으로 이동시키는 메소드 - Forwarding
    @GetMapping("/board/register")
    public void register(Model model) {
        log.info("[BoardController] /board/register forwarding");
    }

    // 게시글을 등록하는 요청을 처리하는 메소드 - Redirect
    // RedirectAttributessms 일회용 세션이다.
    @PostMapping("/board/register")
    public String register(BoardDTO dto, RedirectAttributes rattr) {
        // 파라미터 확인하기
        log.info("[BoardController] /board/register dto: " + dto);
        // 데이터 삽입하기
        Long bno = boardService.register(dto);
        rattr.addFlashAttribute("msg", bno + " 등록");
        return "redirect:/board/list";
    }
    
    // 게시글 상세보기를 처리할 메소드
    // @ModelAttribute 어노테이션은 파라미터로 사용한다면 넘겨받은 데이터를 그대로 전달할 목적으로 사용할 수 있다,
    @GetMapping({"/board/read", "/board/modify"})
    public void read(@ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO,
                     Long bno,
                     Model model) {
        // 데이터 확인하기
        log.info("[BoardController] /board/read bno: " + bno);
        BoardDTO dto = boardService.get(bno);
        model.addAttribute("dto", dto);
    }

    // 게시글 수정을 처리하는 메소드
    @PostMapping("/board/modify")
    public String modify(BoardDTO dto,
                         @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
                         RedirectAttributes redirectAttributes){
        log.info("[BoardController] /board/modify dto: " + dto);
        boardService.modify(dto);
        redirectAttributes.addFlashAttribute("bno",dto.getBno());
        redirectAttributes.addFlashAttribute("page",requestDTO.getPage());
        redirectAttributes.addFlashAttribute("type",requestDTO.getType());
        redirectAttributes.addFlashAttribute("keyword",requestDTO.getKeyword());
        log.info("[BoardController] /board/modify after modify bno: " + dto.getBno());
        return "redirect:/board/read?bno=" + dto.getBno()
                + "&page=" + requestDTO.getPage()
                + "&type=" + requestDTO.getType()
                + "&keyword=" + requestDTO.getKeyword();
    }

    // 게시글 삭제를 처리하는 메소드
    @PostMapping("/board/remove")
    public String remove(BoardDTO dto, RedirectAttributes redirectAttributes){
        log.info("[BoardController] /board/remove dto: " + dto);
        boardService.removeWithReplies(dto.getBno());
        redirectAttributes.addFlashAttribute("msg", dto.getBno() +" 번 삭제");
        return "redirect:/board/list";
    }
}
