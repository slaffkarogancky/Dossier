package kharkov.kp.gic.dossier.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ds_simple_entity")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class SimpleEntity {

	@Id
	@Column(name="simple_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="generate_id")	
	@SequenceGenerator(name="generate_id", sequenceName = "DS_GENERATE_ID", allocationSize=1)
	private Long id;
	
	@Column(name="simple_description")
	private String description;
}


