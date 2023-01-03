package di.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // @Builder는 @NoArgsConstructor가 없으면 안된다.
public class PostsEntity {
	private int postId;
	private String title;
	private String content;
	private String author;
}
