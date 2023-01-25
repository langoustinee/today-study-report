package di.service;

import org.springframework.beans.factory.annotation.Autowired;

import di.dto.PostsDTO;
import di.entity.PostsEntity;
import di.persistence.PostsRepository;
import lombok.RequiredArgsConstructor;

// final 속성으로 만들어진 속성들의 동일한 자료형의 bean이 있다면 생성자롤 이용하여 자동 주입한다.
@RequiredArgsConstructor
public class PostsServiceImpl implements PostsService {

	// Service는 Repository를 주입받아서 사용해야 한다.
	// setter 메서드를 생성해주고 동일한 자료형의 bean이 있다면 자동으로 주입해준다.
	@Autowired
	private final PostsRepository postsRepository;
	
	@Override
	public PostsDTO findById(int postId) {
		// Repository에 필요한 매개변수 생성하기
		
		// Repository 메서드 호출하기
		PostsEntity postsEntity = postsRepository.findById(postId);
		
		// Controller에 전달할 데이터 생성하기
		PostsDTO postsDTO = PostsDTO.builder()
				.postId(postsEntity.getPostId())
				.title(postsEntity.getTitle())
				.content(postsEntity.getContent())
				.author(postsEntity.getAuthor())
				.build();
		return postsDTO;
	}
}
