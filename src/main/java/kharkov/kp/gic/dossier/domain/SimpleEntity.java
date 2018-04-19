package kharkov.kp.gic.dossier.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ds_simple_entity")
@EntityListeners({SimpleEntityEventListener.class})
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class SimpleEntity {

	@Id
	@Column(name="simple_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="generate_id")	
	@SequenceGenerator(name="generate_id", sequenceName = "DS_GENERATE_ID", allocationSize=1)
	private Long id;
	
	@Column(name="simple_description")
	@NotNull
	@Size(min=4, max=200) // throw javax.validation.ConstraintViolationException 
	// validation happens on PrePersist, PreUpdate, PreRemove - page 
	// details http://www.allitebooks.com/pro-jpa-2-in-java-ee-8-3rd-edition/ page 544 
	private String description;

	public SimpleEntity() {
		super();
	}

	public SimpleEntity(String description) {
		super();
		this.description = description;
	}

	public SimpleEntity(Long id, String description) {
		super();
		this.id = id;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "SimpleEntity [id=" + id + ", description=" + description + "]";
	}
	
	
	// http://www.allitebooks.com/pro-jpa-2-in-java-ee-8-3rd-edition/ chapter 12
	@PrePersist 
	private void prePersist() {
		// for example, you can add validation and throw ValidationException
		System.out.println("I'm in SimpleEntity@PrePersist ");
	}
	
	@PostPersist 
	private void postPersist() {
		System.out.println("I'm in SimpleEntity@PostPersist ");
	}
	 
	@PreUpdate  
	private void preUpdate() {
		System.out.println("I'm in SimpleEntity@PreUpdate ");
	}
	
	@PostUpdate 
	private void postUpdate() {
		System.out.println("I'm in SimpleEntity@PostUpdate ");
	}
	 
	@PreRemove  
	private void preRemove() {
		System.out.println("I'm in SimpleEntity@PreRemove ");
	}
	
	@PostRemove  
	private void postRemove() {
		System.out.println("I'm in SimpleEntity@PostRemove ");
	}
	
	@PostLoad 
	private void postLoad() {
		System.out.println("I'm in SimpleEntity@PostLoad ");
	}
	
}


