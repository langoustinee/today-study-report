package com.kakao.lango.guestbook.service;

import com.kakao.lango.guestbook.domain.GuestBook;
import com.kakao.lango.guestbook.dto.GuestBookDTO;
import com.kakao.lango.guestbook.dto.PageRequestDTO;
import com.kakao.lango.guestbook.dto.PageResponseDTO;
import com.kakao.lango.guestbook.persistence.GuestBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor // 의존성을 자동으로 주입해주는 어노테이션이다.
public class GuestBookServiceImpl implements GuestBookService {

    private final GuestBookRepository guestBookRepository;


    // 등록하기
    @Override
    public Long register(GuestBookDTO dto) {
        // 파라미터 넘어오는지 확인하기
        log.info("등록할 데이터:" + dto.toString());
        // repository의 메서드를 매개변수로 변환하기
        GuestBook entity = dtoToEntity(dto);
        // save할 때 설정한 내역을 가지고 필요한 데이터인 gno, regDate, modDate가 설정된다.
        guestBookRepository.save(entity);
        return entity.getGno();
    }

    @Override
    public PageResponseDTO<GuestBookDTO, GuestBook> getList(PageRequestDTO requestDTO) {
        // Repository에게 전달할 Pageable 객체를 생성한다.
        // gno의 내림차순으로 정렬한다.
        Pageable pageable = requestDTO.getPageable(
                Sort.by("gno").descending()
        );
        // 데이터 찾아오기
        Page<GuestBook> result = guestBookRepository.findAll(pageable);
        // 데이터 목록을 받아서 데이터 목록을 순회하여 제공된 메소드가 리턴하는 목록으로 변경해주는 람다식이다.
        Function<GuestBook, GuestBookDTO> fn = (entity -> entityToDTO(entity));
        return new PageResponseDTO<>(result, fn);
    }

}
