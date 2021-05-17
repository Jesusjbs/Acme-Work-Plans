package acme.framework.entities;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Spam extends DomainEntity{

	// Serialisation identifier -----------------------------------------------

		protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
		
		@NotNull
		@Min(1)
		@Max(100)
		protected double threshold;
		
		@NotNull
		@ElementCollection()
		protected List<String> words;
		
	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
		
}
