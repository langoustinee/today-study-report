package com.kakao.lango.moviereview.controller;


import com.kakao.lango.moviereview.dto.UploadResultDTO;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
public class UploadController {

    @Value("${com.lango.upload.path}")
    private String uploadPath;

    // 날짜 별로 디렉토리 생성해주는 메소드
    private String mkDir() {
        // 현재 날짜로 이루어진 문자열 만들기
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String realUploadPath = today.replace("//", File.separator);

        // 디렉토리 없다면 생성하고 있다면 생성하지 않는다.
        File uploadPathDir = new File(uploadPath, realUploadPath);
        if (uploadPathDir.exists() == false) {
            uploadPathDir.mkdirs();
        }
        return realUploadPath;
    }

    @PostMapping("/uploadajax")
    public ResponseEntity<List<UploadResultDTO>> uploadFile(MultipartFile[] uploadFiles) {
        // 결과를 전달할 객체 생성하기
        List<UploadResultDTO> resultDTOList = new ArrayList<>();

        for (MultipartFile uploadFile : uploadFiles) {
            // 이미지 파일만 업로드 가능하도록 제한을 두기
            if (uploadFile.getContentType().startsWith("image") == false) {
                log.warn("this file is not image type");
                // 이미지 파일이 아닐 경우 403 에러를 전달한다.
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            // 업로드 파일의 이름
            String originalName = uploadFile.getOriginalFilename();
            // IE는 파일이름이 아니라 전체 경로를 전송하기 때문에 마지막 \의 뒷 부분만 추출해야 한다.
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);
            log.warn("fileName: " + fileName);

            // 디렉토리 생성하기
            String realUploadPath = mkDir();
            // UUID로 랜덤 난수 생성하기
            String uuid = UUID.randomUUID().toString();
            // 실제 파일이 저장될 경로 생성하기
            // 저장할 파일 이름 중간에 _를 이용해서 구분한다.
            String saveName = uploadPath + File.separator + realUploadPath + File.separator + uuid + fileName;
//            Path savePath = Paths.get(saveName);
            File saveFile = new File(saveName);

            // 파일 업로드 진행하기
            try {
                // 썸네일 생성시 Path가 아닌 File이어야 한다.
//                uploadFile.transferTo(savePath);
                uploadFile.transferTo(saveFile);

                // 썸네일 파일 이름 생성하기(파일 이름은 중간에 s_로 시작한다.)
                File thumbnailFile = new File(uploadPath +
                        File.separator + realUploadPath +
                        File.separator + "s_" + uuid + fileName);
                // 썸네일 생성하기
                Thumbnailator.createThumbnail(saveFile, thumbnailFile,100,100);

                // 파일 업로드 후 filename, uuid, realUploadPath를 List에 담아서 전달한다.
                resultDTOList.add(new UploadResultDTO(fileName, uuid, realUploadPath));

            } catch (IOException e) {
                System.out.println(e.getLocalizedMessage());
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(resultDTOList, HttpStatus.OK);
    }

    // 파일의 내용을 전송하는 메소드
    @GetMapping("/display")
    public ResponseEntity<byte[]> getFile(String filename) {
        ResponseEntity<byte[]> result = null;
        try {
            log.warn("file name: " + filename);
            // 전송할 파일의 경로를 생성하기
            File file = new File(uploadPath + File.separator + URLDecoder.decode(filename,"UTF-8"));
            // 헤더에 파일을 전송할 것이라고 명시하기
            HttpHeaders header = new HttpHeaders();
            header.add("Content-type", Files.probeContentType(file.toPath()));
            // 파일의 내용을 응답 객체로 생성하기
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    // 이미지 삭제 요청 처리 메소드
    @PostMapping("/removefile")
    public ResponseEntity<Boolean> removeFile(String fileName) {
        try {
            // 원래 파일 이름 가져오기
            String srcFileName = URLDecoder.decode(fileName,"UTF-8");
            
            // 원본 이미지 파일 객체 생성하기
            File file = new File(uploadPath + File.separator + srcFileName);
            // 원본 이미지 파일 삭제하기
            file.delete();

            // 썸네일 이미지 파일 객체 생성하기
            File thumbnail = new File(file.getParent(), "s_" + file.getName());
            // 썸네일 이미지 파일 삭제하기
            thumbnail.delete();

            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
