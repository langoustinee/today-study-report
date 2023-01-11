package com.kakao.lango.guestbook.service;

import com.kakao.lango.guestbook.domain.GuestBook;
import com.kakao.lango.guestbook.dto.GuestBookDTO;
import com.kakao.lango.guestbook.dto.PageRequestDTO;
import com.kakao.lango.guestbook.dto.PageResponseDTO;


public interface GuestBookService {
    /**
     * 등록하기: 데이터를 삽입하는 메소드
     * @param dto
     * @return Long gno
     * 데이터 삽입시의 매개변수는 보통 DTO 객체를 넘겨준다.
     * 리턴 타입은 삽입된 데이터를 그대로 리턴하거나, 성공여부를 위해 boolean을 리턴하기도 하며,
     * 영향받은 행의 개수를 정수로 리턴하거나 기본 키의 값을 리턴하는 경우가 있다.
     * 해당 메서드에서는 기본 키 값을 리턴한다.
     */
    public Long register(GuestBookDTO dto);

    // 목록 보기를 위한 메소드
    PageResponseDTO<GuestBookDTO, GuestBook> getList(PageRequestDTO requestDTO);

    // DTO를 Entity로 변환하는 메소드
    // 삽입날짜나 수정날짜 같은 entity는 자동으로 수정되므로 옮길 필요가 없기에
    // 필요한 속성들만 옮겨도 무방하다.
    default GuestBook dtoToEntity(GuestBookDTO dto) {
        GuestBook entity = GuestBook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
        return entity;
    }

    // Entity를 DTO로 변환하는 메소드
    // 모든 속성들을 전부 옮겨 주어야 한다.
    default GuestBookDTO entityToDTO(GuestBook entity) {
        GuestBookDTO dto = GuestBookDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegdate())
                .modDate(entity.getModDate())
                .build();
        return dto;
    }
}
