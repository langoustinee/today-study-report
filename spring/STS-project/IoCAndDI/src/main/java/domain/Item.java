package domain;

import lombok.Data;

// @Data 어노테이션은 getter, setter, equals, hashCode, toString 을 생성해준다.
@Data 
public class Item {

	private int itemId;
	private String itemName;
	private int price;
	private String description;
	private String pictureUrl;
	
}
