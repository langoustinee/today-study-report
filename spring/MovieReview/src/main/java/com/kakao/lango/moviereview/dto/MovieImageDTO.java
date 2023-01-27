package com.kakao.lango.moviereview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieImageDTO {
    private String uuid;
    private String imgName;
    private String path;

    public String getImageURL() {
        try {
            return URLEncoder.encode(path + "/" + uuid + "_" + imgName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
    public String getThumbnailURL() {
        try {
            return URLEncoder.encode(path + "/s_" + uuid + imgName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

}