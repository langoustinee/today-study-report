package com.kakao.lango.springboottutorial;

import com.kakao.lango.springboottutorial.dto.PostsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/rest-api") // 공통된 URL 설정
public class JSONController {

    // Logging 가능한 객체를 생성한다.
    private final Logger LOGGER = LoggerFactory.getLogger(JSONController.class);

    // @GetMapping은 Spring 3.4에서 추가된 요청 처리 어노테이션이다.
    @GetMapping("/hello")
    public String getHello() {
        return "GET Hello";
    }

    @GetMapping("/post/{postId}")
    public String getPost(@PathVariable("postId") int postId) {
        String result = "게시글의 번호는 " + postId + "번 입니다.";
        return result;
    }

    @GetMapping("/comment")
    public String getComment(HttpServletRequest request) {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String organization = request.getParameter("organization");
        String result = title + " " + content + " " + organization;
        return result;
    }

    @GetMapping("/attachs")
    public String getAttachs(
            @RequestParam("imgId") int imgId,
            @RequestParam("path") String path,
            @RequestParam("description") String description) {
        String result = "imgId: " + imgId + " path: " + path + " description: " + description;
        return result;
    }

    @GetMapping("/posts")
    public String getPosts(
            PostsDTO postsDTO) {
        String result = postsDTO.getPostId() + " - " + postsDTO.getTitle() + ", " + postsDTO.getContent() + ", " + postsDTO.getAuthor();
        return result;
    }

    @PostMapping("/posts")
    public String getPostParam(@RequestBody PostsDTO postsDTO) {
        return postsDTO.toString();
    }

    @PutMapping("/posts")
    public String getPutParam(@RequestBody PostsDTO postsDTO) {
        return postsDTO.toString();
    }

    @PutMapping("/postParam")
    public PostsDTO getPutPostParam(@RequestBody PostsDTO postsDTO) {
        return postsDTO;
    }

    @PutMapping("/postParamSecond")
    public ResponseEntity<PostsDTO> getPutPostParamSecond(@RequestBody PostsDTO postsDTO) {
        // 상태코드를 설정해서 결과를 리턴한다.
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(postsDTO);
    }

    @DeleteMapping("/post/{postId}")
    public String deletePost1(@PathVariable("postId") int postId) {
        String result = "삭제할 게시글의 번호는 " + postId + "번 입니다.";
        return result;
    }

    @DeleteMapping("/post")
    public String deletePost2(@RequestParam("postId") int postId) {
        LOGGER.debug("postId: " + postId);
        String result = "삭제할 게시글의 번호는 " + postId + "번 입니다.";
        return result;
    }

}
