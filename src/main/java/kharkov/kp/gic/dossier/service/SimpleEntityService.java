package kharkov.kp.gic.dossier.service;

import java.util.List;

import kharkov.kp.gic.dossier.domain.SimpleEntity;

public interface SimpleEntityService {

	Long save(SimpleEntity entity);
	
	void update(SimpleEntity entity);
	
	SimpleEntity getById(Long id);
	
	List<SimpleEntity> getByName(String substr);
	
	List<SimpleEntity> getAll(int page, int size);
	
	List<SimpleEntity> getAll();	
	
	Long count();
	
	boolean isExists(Long id);
	
	void delete(Long id);
	
	void deleteByName(String substr);
	
	String whoAmI();
	
}
