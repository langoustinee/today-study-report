package di.persistence;

import di.entity.PostsEntity;

public interface PostsRepository {
	// 기본 키를 가지고 하나의 데이터를 찾아오는 메서드
	public PostsEntity findById(int postId);
}
