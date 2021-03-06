package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable{
	
	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Integer						totalNumberPublicTask;
	Integer						totalNumberPrivateTask;
	Integer						totalNumberFinishedTask;
	Integer						totalNumberNoFinishedTask;
	String						averageExecutionPeriodsTask;
	String						deviationExecutionPeriodsTask;
	String						minExecutionPeriodsTask;
	String						maxExecutionPeriodsTask;
	Double						averageWorkloadTask;
	Double						deviationWorkloadTask;
	Double						minWorkloadTask;
	Double						maxWorkloadTask;
	
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
