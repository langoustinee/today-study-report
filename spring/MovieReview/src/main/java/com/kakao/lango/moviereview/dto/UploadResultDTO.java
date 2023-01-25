package com.kakao.lango.moviereview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
@AllArgsConstructor
// Serializable(직렬화)은 데이터를 전송할 때 객체 단위로 전송할 수 있도록 도와주는 인터페이스이다.
public class UploadResultDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String fileName;
    private String uuid;
    private String uploadPath;

    // 파생 속성 - 속성이지는 않지만 속성으로 계산해서 나오는 함수이다.
    // 실제 이미지 경로를 리턴해주는 메소드
    public String getImageURL() {
        try {
            return URLEncoder.encode(uploadPath + "/" + uuid + fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
        return "";
    }

    public String getThumbnailURL(){
        try {
            return URLEncoder.encode(uploadPath + "/s_" + uuid + fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
        return "";
    }
}
