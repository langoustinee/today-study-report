package di.persistence;

import di.entity.PostsEntity;

public class PostsRepositoryImpl implements PostsRepository {

	@Override
	public PostsEntity findById(int postId) {
		
		// Builder 패턴을 적용한다면 new Posts(1, "게시글의 제목입니다!", "게시글의 내용입니다.", "lango"); 
		// 와 같은 생성자 방식보다 가독성이 뛰어나다.
		PostsEntity postsEntity = PostsEntity.builder()
				.postId(1)
				.title("게시글의 제목입니다!")
				.content("게시글의 내용입니다..")
				.author("lango")
				.build();
						
		return postsEntity;
	}

}
