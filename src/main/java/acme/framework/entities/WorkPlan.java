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

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class WorkPlan extends DomainEntity{
	
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
		protected Privacy 		privacy;
		
		
		// Derived attributes -----------------------------------------------------
		
		public Double getWorkload(){
			Double result=0.00;
			for(final Task task:this.tasks) {
				String cad = String.valueOf(task.workload);
				String resultString = String.valueOf(result);
				cad = cad.replace(",", ".");
				resultString = resultString.replace(",", ".");
				final Double entero = Double.valueOf(cad.substring(0, cad.indexOf(".")));
				String decimal = cad.substring(cad.indexOf("."));
				decimal = "0" + decimal;
				if(decimal.length()!=2) {
					decimal +="0";
				}
				
				final Integer resultEntero = Integer.valueOf(resultString.substring(0, resultString.indexOf(".")));
				String resultDecimal = resultString.substring(resultString.indexOf("."));
				resultDecimal = "0" + resultDecimal;
				
				if(resultDecimal.length()!=2) {
					resultDecimal +="0";
				}
				
				if(Double.valueOf(decimal) + Double.valueOf(resultDecimal) >= 0.60) {
					final Double redondeoResultDecimal = Math.round(Double.valueOf(resultDecimal) * 100.0) / 100.0;
					final Double parteDecimal = Double.valueOf(decimal) + redondeoResultDecimal - 0.60;
					result = resultEntero + entero + 1 + parteDecimal;
					
				}else {
					result += task.workload;
				}
				
			}
			return result;
		}
		
		// Relationships ----------------------------------------------------------

		@ManyToMany(mappedBy = "workPlans", fetch = FetchType.EAGER)
		protected List<Task> tasks;
		
		@NotNull
		@ManyToOne
		protected Manager manager;
}
