package acme.framework.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class WorkPlan extends DomainEntity{
	
	// Serialisation identifier -----------------------------------------------

		protected static final long	serialVersionUID	= 1L;
			
		// Attributes -------------------------------------------------------------
		
		@NotNull
		@Temporal(TemporalType.TIMESTAMP)
		protected Date			beginning;
		
		@NotNull
		@Temporal(TemporalType.TIMESTAMP)
		protected Date			ending;
		
		@NotNull
		protected Privacy 		privacy;
		
		
		// Derived attributes -----------------------------------------------------
		
		public Double getWorkload(){
			Double result=0.0;
			for(final Task task:this.tasks) {
				String cad = String.valueOf(task.workload);
				final String resultString = String.valueOf(result);
				cad = cad.replace(",", ".");
				final Double entero = Double.valueOf(cad.substring(0, cad.indexOf(".")));
				String decimal = cad.substring(cad.indexOf(".") + 1);
				if(decimal.length()!=2) {
					decimal +="0";
				}
				
				final Double resultEntero = Double.valueOf(resultString.substring(0, cad.indexOf(".")));
				String resultDecimal = resultString.substring(cad.indexOf(".") + 1);
				
				if(resultDecimal.length()!=2) {
					resultDecimal +="0";
				}
				
				if(Integer.valueOf(decimal) + Integer.valueOf(resultDecimal) >= 60) {
					result = resultEntero + entero + 1 + (Integer.valueOf(decimal) + Integer.valueOf(resultDecimal)-60)/100 ;
				}else {
					result += task.workload;
				}
				
			}
			return result;
		}
		
		// Relationships ----------------------------------------------------------

		@ManyToMany(mappedBy = "workPlans", fetch = FetchType.EAGER)
		protected List<Task> tasks;
		
		
}
