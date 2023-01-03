package di.service;

import di.dto.PostsDTO;

public interface PostsService {
	// 기본키 하나를 받아서 하나의 데이터를 리턴하는 메서드
	// 매개변수나 리턴타입에 Entity 타입을 사용하면 안된다.
	// DTO를 사용하여 Entity에 접근하여 들고 가야한다.
	public PostsDTO findById(int postId); 

}
