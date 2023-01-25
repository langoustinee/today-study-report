package com.kakao.lango.moviereview.controller;

import com.kakao.lango.moviereview.dto.MovieDTO;
import com.kakao.lango.moviereview.dto.PageRequestDTO;
import com.kakao.lango.moviereview.dto.PageResponseDTO;
import com.kakao.lango.moviereview.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Log4j2
@RequiredArgsConstructor
@RequestMapping("/movie")
@Controller
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/register")
    public void register() {

    }

    // 영화 등록 요청 메소드
    @PostMapping("/register")
    public String register(MovieDTO movieDTO, RedirectAttributes redirectAttributes) {
        log.info("movieDTO: " + movieDTO);
        Long mno = movieService.register(movieDTO);
        redirectAttributes.addFlashAttribute("msg", mno);
        return "redirect:/movie/list";
    }

    // 목록 보기 요청 메소드
    @GetMapping("/list")
    public void getList(PageRequestDTO pageRequestDTO, Model model) {
        log.info("[MovieController] getList pageRequestDTO: " + pageRequestDTO);
        PageResponseDTO response = movieService.getList(pageRequestDTO);
        model.addAttribute("result", response);
    }

    // 영화 상세보기 및 수정 요청 메소드
    @GetMapping({"/read", "/modify"})
    public void read(Long mno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model ) {
        log.info("mno: " + mno);
        MovieDTO movieDTO = movieService.getMovie(mno);
        model.addAttribute("dto", movieDTO);
    }
}
