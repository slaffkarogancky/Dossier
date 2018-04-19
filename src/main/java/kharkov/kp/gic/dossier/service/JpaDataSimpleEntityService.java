package kharkov.kp.gic.dossier.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kharkov.kp.gic.dossier.domain.SimpleEntity;
import kharkov.kp.gic.dossier.repository.SimpleEntityRepository;

@Service
@Transactional
@Profile("jpa-data")
public class JpaDataSimpleEntityService implements SimpleEntityService {

	@Autowired
	private SimpleEntityRepository repository;

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return repository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isExists(Long id) {
		return repository.existsById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public SimpleEntity getById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<SimpleEntity> getByName(String substr) {
		return repository.getByName(substr);
	}

	@Override
	@Transactional(readOnly = true)
	public List<SimpleEntity> getAll(int page, int size) {
		return repository.findAll(PageRequest.of(page, size, Direction.ASC, "description")).getContent();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<SimpleEntity> getAll() {
		return repository.findAll(Sort.by(Direction.ASC, "description"));
	}
	
	@Override
	public Long save(SimpleEntity entity) {
		SimpleEntity saved = repository.saveAndFlush(entity);
		return saved.getId();
	}

	@Override
	public void update(SimpleEntity entity) {
		repository.save(entity);		
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);		
	}

	@Override
	public void deleteByName(String substr) {
		repository.deleteByName(substr);			
	}
	
	@Override
	public String whoAmI() {
		return "----------------- Hi! I'm JpaDataSimpleEntityService -----------------------------";
	}

}
