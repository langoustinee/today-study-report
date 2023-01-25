package com.kakao.lango.springbootview.controller;

import com.kakao.lango.springbootview.domain.SampleVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class PageController {

    private Logger LOGGER = LoggerFactory.getLogger(PageController.class);

    @GetMapping("/")
    public String main(Model model) {
        Map<String, Object> m = new HashMap<>();
        m.put("title", "작전명 청!춘");
        m.put("artist", "잔나비");
        m.put("allbum", "3번째 소곡집");
        LOGGER.debug("map: " + m);
        model.addAttribute("map", m);

        List<String> l = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            l.add((i + 1) + "집 앨범");
        }
        LOGGER.debug("list: " + l);
        model.addAttribute("list", l);

        return "main";
    }

    @GetMapping("/exampleOne")
    // 리턴 타입이 void이면 출력하는 뷰 이름은 요청 URL이 되기에 example1이 된다.
    public void exampleOne(Model model) {
        LOGGER.info("exampleOne method");
    }

    @GetMapping("/exampleTwo")
    public void exampleTwo(Model model) {
        LOGGER.debug("exampleOne example");
        List<SampleVO> list = IntStream.range(1, 20)
                .asLongStream().mapToObj(i -> {
                    SampleVO vo = SampleVO.builder()
                            .sno(i)
                            .first("First .." + i)
                            .last("Last .." + i)
                            .regTime(LocalDateTime.now())
                            .build();
                    return vo;
                }).collect(Collectors.toList());
        model.addAttribute("list", list);
    }

    @GetMapping({"/link", "/format"})
    public void linkTest(Model model) {
        List<SampleVO> list = new ArrayList<>();
        for (Long i = 0L; i < 10; i++) {
            SampleVO vo = SampleVO.builder()
                    .sno(i)
                    .first("Frist .. " + i)
                    .last("Last .. " + i)
                    .regTime(LocalDateTime.now())
                    .build();
            list.add(vo);
        }
        model.addAttribute("list", list);
        System.out.println(list);
    }

    @GetMapping("/layouts")
    public void layouts() { }
}
