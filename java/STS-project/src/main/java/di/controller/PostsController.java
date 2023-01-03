package di.controller;

import org.springframework.beans.factory.annotation.Autowired;

import di.service.PostsService;
import lombok.RequiredArgsConstructor;

// Controller는 다른 클래스에 주입되지 않기 때문에 별도로 템플릿 메서드 패턴을 적용하지 않아도 되기에 인터페이스가 필요없다.
@RequiredArgsConstructor
public class PostsController {
	// Service 주입받기
	@Autowired
	private final PostsService postService;
	
	public void findById(int postId) {
		System.out.println("findByID: " + postService.findById(postId));
	}

}
