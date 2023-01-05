package di.persistence;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import di.domain.Good;

@Repository
public class HibernateRepository {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void insertGood(Good good) {
		Session session = sessionFactory.getCurrentSession();
		
		// 데이터 삽입
		session.save(good);
	}
	
	@Transactional
	public void updateGood(Good good) {
		Session session = sessionFactory.getCurrentSession();
		
		// 동일한 기본 키의 데이터를 찾아서 나머지 데이터를 수정한다.
		session.update(good);
	}
	
	@Transactional
	public void deleteGood(Good good) {
		Session session = sessionFactory.getCurrentSession();
		
		// 동일한 기본 키의 데이터를 삭제한다.
		session.delete(good);
	}
	
	// 모든 데이터를 가져오는 메서드
	@Transactional
	public List<Good> allGood() {
		Session session = sessionFactory.getCurrentSession();

		// 제약 조건 생성하기
		CriteriaQuery<Good> criteriaQuery = session.getCriteriaBuilder().createQuery(Good.class);
		criteriaQuery.from(Good.class);
		
		return session.createQuery(criteriaQuery).getResultList();
		
		// 일반 SQL 쿼리로도 모든 Good 데이터를 가져올 수 있지만 경고가 발생한다.
		//return session.createSQLQuery("select * from goods").addEntity(Good.class).getResultList();
	}
	
	// 기본 키로 하나의 데이터를 가져오는 메서드
	@Transactional
	public Good getGood(int code) {
		return sessionFactory.getCurrentSession().get(Good.class, code);
	}

}
