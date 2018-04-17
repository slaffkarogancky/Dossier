package kharkov.kp.gic.dossier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kharkov.kp.gic.dossier.domain.SimpleEntity;

public interface SimpleEntityRepository extends JpaRepository<SimpleEntity, Long>{

	@Query("SELECT se FROM SimpleEntity se WHERE LOWER(se.description) LIKE LOWER(CONCAT('%', :substr,'%'))")
	public List<SimpleEntity> getByName(@Param("substr") String substr);
	
	@Query("DELETE FROM SimpleEntity se WHERE LOWER(se.description) LIKE LOWER(CONCAT('%', :substr,'%'))")
	public void deleteByName(@Param("substr") String substr);
	
	/*@Modifying	
	where lower(customer_name_rus) like lower('%'||'ОЛЕГ'||'%')
	@Query(value = "delete from ds_simple_entity where lower(simple_description) like lower(concat('%', concat(:substr, '%')))", 
	       nativeQuery = true)
	public void deleteByName(@Param("substr") String substr);  */	
	
}
