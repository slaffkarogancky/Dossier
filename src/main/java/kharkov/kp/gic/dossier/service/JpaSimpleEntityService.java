package kharkov.kp.gic.dossier.service;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kharkov.kp.gic.dossier.domain.SimpleEntity;

@Service
@Transactional
@Profile("jpa")
public class JpaSimpleEntityService implements SimpleEntityService{

	@PersistenceContext
	private EntityManager em;

	@Override
	public Long save(SimpleEntity entity) {
		em.persist(entity);
		em.flush();
		return entity.getId();
	}

	@Override
	public void update(SimpleEntity entity) {
		em.merge(entity);		
	}

	@Override
	public SimpleEntity getById(Long id) {
		return em.find(SimpleEntity.class, id);
	}

	@Override
	public List<SimpleEntity> getByName(String substr) {
		String sql = "select se from SimpleEntity se where se.description like :sbstr order by se.description ";
		return em.createQuery(sql, SimpleEntity.class).setParameter("sbstr", "%"+substr+"%").getResultList();
	}

	@Override
	public List<SimpleEntity> getAll(int page, int size) {		
		return em.createQuery("select se from SimpleEntity se order by se.description ", SimpleEntity.class)
				.setFirstResult(page * size)
				.setMaxResults(size)
				.getResultList();
	}

	@Override
	public List<SimpleEntity> getAll() {
		return em.createQuery("select se from SimpleEntity se order by se.description ", SimpleEntity.class)
				.getResultList();
	}

	@Override
	public Long count() {
		Query query = em.createNativeQuery("select count(*) from ds_simple_entity ");
		BigInteger count = (BigInteger)query.getSingleResult();
		return count.longValue();
		//return ((BigInteger)em.createNativeQuery("select count(*) from ds_simple_entity ").getSingleResult()).longValue(); 
	}

	@Override
	public boolean isExists(Long id) {
		return em.find(SimpleEntity.class, id) != null;
	}

	@Override
	public void delete(Long id) {
		SimpleEntity entity = em.find(SimpleEntity.class, id);
		if (entity != null) {
			em.remove(entity);	
		}
	}

	@Override
	public void deleteByName(String substr) {
		String sql = "delete from SimpleEntity se where se.description like :sbstr ";
		em.createQuery(sql).setParameter("sbstr", "%"+substr+"%").executeUpdate();		
	}

	@Override
	public String whoAmI() {
		return "----------------- Hi! I'm JpaSimpleEntityService -----------------------------";
	}
}
