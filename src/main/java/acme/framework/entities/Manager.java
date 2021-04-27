package acme.framework.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Manager extends UserRole {

	// Serialisation identifier -----------------------------------------------

	protected static final long serialVersionUID = 1L;

	// Attributes -------------------------------------------------------------

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

	@OneToMany(mappedBy = "manager")
	protected Collection<@Valid Task> tasks;
	
	@OneToMany(mappedBy = "manager")
	protected Collection<@Valid WorkPlan> workplans;
}
