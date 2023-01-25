package com.kakao.lango.springbootmybatis.persistence;

import com.kakao.lango.springbootmybatis.dto.MemoDTO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemoMapper {
    @Select("select * from tbl_memo")
    public List<MemoDTO> allMemo();
}
