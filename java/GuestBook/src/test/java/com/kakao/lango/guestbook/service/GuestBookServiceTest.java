package com.kakao.lango.guestbook.service;

import com.kakao.lango.guestbook.domain.GuestBook;
import com.kakao.lango.guestbook.dto.GuestBookDTO;
import com.kakao.lango.guestbook.dto.PageRequestDTO;
import com.kakao.lango.guestbook.dto.PageResponseDTO;
import com.kakao.lango.guestbook.persistence.GuestBookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GuestBookServiceTest {
    @Autowired
    protected GuestBookService guestBookService;

    @Test
    public void register() {
        GuestBookDTO dto = GuestBookDTO.builder()
                .title("추가할 데이터1")
                .content("추가할 내용1")
                .writer("lango")
                .build();
        System.out.println(guestBookService.register(dto));
    }

    @Test
    public void getList() {
        // 1페이지 당 10개의 데이터 세팅하기
        PageRequestDTO requestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();
        PageResponseDTO<GuestBookDTO, GuestBook> result = guestBookService.getList(requestDTO);
        for (GuestBookDTO dto : result.getDtoList()) {
            System.out.println(dto);
        }
    }

    @Test
    public void getListInfo() {
        PageRequestDTO requestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();
        PageResponseDTO<GuestBookDTO, GuestBook> result = guestBookService.getList(requestDTO);

        // 데이터 확인하기
        System.out.println("Prev: " + result.isPrev());
        System.out.println("Next: " + result.isNext());
        System.out.println("Total: " + result.getTotalPage());
        System.out.println("PageList: " + result.getPageList());

        System.out.println("---------------------");
        for (GuestBookDTO dto : result.getDtoList()) {
            System.out.println(dto);
        }
        System.out.println("---------------------");
    }

}
