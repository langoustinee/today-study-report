package persistence;

import domain.Item;

public class ItemRepository {
	// 다른 패키지에서 인스턴스 생성하지 못하도록 생성자의 접근 제한자를 디폴트 접근자로 설정한다.
	ItemRepository() { }
	
	public Item get() {
		Item item = null;
		item = new Item();
		item.setItemId(1);
		item.setItemName("패딩");
		item.setPrice(299000);
		item.setDescription("유명한 연예인이 입은 패딩입니다.");
		item.setPictureUrl("padding.png");
		return item;
	}
}
