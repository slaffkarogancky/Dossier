package kharkov.kp.gic.dossier.domain;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

public class SimpleEntityEventListener {
	
	@PrePersist 
	public void prePersist(SimpleEntity entity) {
		System.out.println(String.format("I'm in SimpleEntityEventListener@PrePersist with '%s'", entity.getDescription()));
	}
	
	@PostPersist 
	public void postPersist(SimpleEntity entity) {
		System.out.println(String.format("I'm in SimpleEntityEventListener@PostPersist with '%s'", entity.getDescription()));
	}
	 
	@PreUpdate  
	public void preUpdate(SimpleEntity entity) {
		System.out.println(String.format("I'm in SimpleEntityEventListener@PreUpdate with '%s'", entity.getDescription()));
	}
	
	@PostUpdate 
	public void postUpdate(SimpleEntity entity) {
		System.out.println(String.format("I'm in SimpleEntityEventListener@PostUpdate with '%s'", entity.getDescription()));
	}
	 
	@PreRemove  
	public void preRemove(SimpleEntity entity) {
		System.out.println(String.format("I'm in SimpleEntityEventListener@PreRemove with '%s'", entity.getDescription()));
	}
	
	@PostRemove  
	public void postRemove(SimpleEntity entity) {
		System.out.println(String.format("I'm in SimpleEntityEventListener@PostRemove with '%s'", entity.getDescription()));
	}
	
	@PostLoad 
	public void postLoad(SimpleEntity entity) {
		System.out.println(String.format("I'm in SimpleEntityEventListener@PostLoad with '%s'", entity.getDescription()));
	}
}
