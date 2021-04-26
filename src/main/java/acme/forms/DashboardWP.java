package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DashboardWP implements Serializable {

	protected static final long	serialVersionUID	= 1L;
	
	// Attributes -------------------------------------------------------------

	Integer						totalNumberPublicWorkplan;
	Integer						totalNumberPrivateWorkplan;
	Integer						totalNumberFinishedWorkplan;
	Integer						totalNumberNoFinishedWorkplan;
	String						averageExecutionPeriodsWorkplan;
	String						deviationExecutionPeriodsWorkplan;
	String						minExecutionPeriodsWorkplan;
	String						maxExecutionPeriodsWorkplan;
	Double						averageWorkloadWorkplan;
	Double						deviationWorkloadWorkplan;
	Double						minWorkloadWorkplan;
	Double						maxWorkloadWorkplan;
}
