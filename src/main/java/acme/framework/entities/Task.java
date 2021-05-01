package acme.framework.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Task extends DomainEntity {
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;
		
	// Attributes -------------------------------------------------------------
	
	@NotEmpty
	@Length(max = 80)
	protected String 		title;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date			beginning;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date			ending;
	
	@NotNull
	protected double		workload;
	
	@NotEmpty
	@Length(max = 500)
	protected String 		description;
	
	@URL
	protected String 		link;
	
	@NotNull
	protected Privacy 		privacy;
	
	// Derived attributes -----------------------------------------------------
	
	// Relationships ----------------------------------------------------------
	
	@ManyToMany(fetch = FetchType.EAGER)
	protected List<WorkPlan> workPlans;
	
	@NotNull
	@ManyToOne
	protected Manager manager;
	
}
