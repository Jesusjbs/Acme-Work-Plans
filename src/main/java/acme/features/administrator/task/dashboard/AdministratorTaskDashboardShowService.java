package acme.features.administrator.task.dashboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Privacy;
import acme.framework.entities.Task;
import acme.framework.entities.WorkPlan;
import acme.framework.services.AbstractShowService;


@Service
public class AdministratorTaskDashboardShowService implements AbstractShowService<Administrator, Dashboard>{
	
	
	@Autowired
	protected AdministratorTaskDashboardRepository repository;

	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, //
			"totalNumberPublicTask", "totalNumberPrivateTask", // 
			"totalNumberFinishedTask", "totalNumberNoFinishedTask", //
			"averageExecutionPeriodsTask", "deviationExecutionPeriodsTask","minExecutionPeriodsTask","maxExecutionPeriodsTask", //
			"averageWorkloadTask", "deviationWorkloadTask", "minWorkloadTask", "maxWorkloadTask", 
			"totalNumberPublicWorkplan", "totalNumberPrivateWorkplan", // 
			"totalNumberFinishedWorkplan", "totalNumberNoFinishedWorkplan" , // 
			"averageExecutionPeriodsWorkplan", "deviationExecutionPeriodsWorkplan","minExecutionPeriodsWorkplan","maxExecutionPeriodsWorkplan", //
			"averageWorkloadWorkplan", "deviationWorkloadWorkplan", "minWorkloadWorkplan", "maxWorkloadWorkplan");
	}


	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;
		
		Dashboard result;
		final Dashboard taskDashboard = this.taskDashboard();
		result = this.workplanDashboard(taskDashboard);
		
		return result;
	}
	
	private Dashboard taskDashboard() {
		final Dashboard result;
		final Integer totalNumberPublicTask;
		final Integer totalNumberPrivateTask;
		final Integer totalNumberFinishedTask;
		final Integer totalNumberNoFinishedTask;
		String averageExecutionPeriodsTask="";
		String deviationExecutionPeriodsTask="";
		String minExecutionPeriodsTask="";
		String maxExecutionPeriodsTask="";
		final Double averageWorkloadTask;
		Double deviationWorkloadTask=0.;
		final Double minWorkloadTask;
		final Double maxWorkloadTask;
		
		totalNumberPublicTask = this.repository.totalNumberPrivacityTask(Privacy.PUBLIC).size();
		totalNumberPrivateTask = this.repository.totalNumberPrivacityTask(Privacy.PRIVATE).size();
		totalNumberFinishedTask = this.repository.totalNumberFinishedTask().size();
		totalNumberNoFinishedTask = this.repository.totalNumberNoFinishedTask().size();
		
		if(totalNumberPublicTask != 0 || totalNumberPrivateTask != 0) {
		
		averageWorkloadTask = this.repository.averageWorkloadTask();
		
		final List<Long> executionPeriod = new ArrayList<>();
		for(final Task task: this.repository.findAllTask()) {
			
			final long time = task.getEnding().getTime() - task.getBeginning().getTime();
	        final long hours = TimeUnit.MILLISECONDS.toMinutes(time);
			executionPeriod.add(hours);
		}
		
		final IntSummaryStatistics estadisticas = executionPeriod.stream().mapToInt(Long::intValue).summaryStatistics();
		
		//Calculo del periodo de ejecución máximo
		final Integer max = estadisticas.getMax();
		maxExecutionPeriodsTask = this.calcularHoras(max,maxExecutionPeriodsTask);
		
		//Calculo del periodo de ejecución minimo
		final Integer minimo = estadisticas.getMin();
		minExecutionPeriodsTask=this.calcularHoras(minimo,minExecutionPeriodsTask);
		
		//Calculo del periodo de ejecución de la media
		final Integer avgEP = (int)estadisticas.getAverage();
		averageExecutionPeriodsTask=this.calcularHoras(avgEP,averageExecutionPeriodsTask);
		
		//Calcular la desviación del periodo de ejecución
		Double deviationExecution = 0.;
		for(final Long x:executionPeriod) {
			deviationExecution += Math.pow(x-avgEP.doubleValue(), 2);
		}
		deviationExecution = Math.sqrt(deviationExecution/executionPeriod.size());
		
		deviationExecutionPeriodsTask = this.calcularHoras(deviationExecution.intValue(), deviationExecutionPeriodsTask) ;
		
		
		//Para la desviación tipica de los workload hacemos lo siguiente
		final List<Double> workloadList = this.repository.findAllWorkload();
		for(final Double x:workloadList) {
			deviationWorkloadTask += Math.pow(x - averageWorkloadTask,2); 
		}
		deviationWorkloadTask = Math.sqrt(deviationWorkloadTask/workloadList.size());
		
		minWorkloadTask = this.repository.minWorkloadTask();
		maxWorkloadTask = this.repository.maxWorkloadTask();

		result = new Dashboard();
		result.setTotalNumberPublicTask(totalNumberPublicTask);
		result.setTotalNumberPrivateTask(totalNumberPrivateTask);
		result.setTotalNumberFinishedTask(totalNumberFinishedTask);
		result.setTotalNumberNoFinishedTask(totalNumberNoFinishedTask);
		result.setAverageWorkloadTask(averageWorkloadTask);
		result.setMinWorkloadTask(minWorkloadTask);
		result.setMaxWorkloadTask(maxWorkloadTask);
		result.setDeviationWorkloadTask(deviationWorkloadTask);
		result.setMaxExecutionPeriodsTask(maxExecutionPeriodsTask);
		result.setMinExecutionPeriodsTask(minExecutionPeriodsTask);
		result.setAverageExecutionPeriodsTask(averageExecutionPeriodsTask);
		result.setDeviationExecutionPeriodsTask(deviationExecutionPeriodsTask);
		
		}
		else {
			averageWorkloadTask = 0.;
			minWorkloadTask = 0.;
			maxWorkloadTask = 0.;
			
			result = new Dashboard();
			result.setTotalNumberPublicTask(totalNumberPublicTask);
			result.setTotalNumberPrivateTask(totalNumberPrivateTask);
			result.setTotalNumberFinishedTask(totalNumberFinishedTask);
			result.setTotalNumberNoFinishedTask(totalNumberNoFinishedTask);
			result.setAverageWorkloadTask(averageWorkloadTask);
			result.setMinWorkloadTask(minWorkloadTask);
			result.setMaxWorkloadTask(maxWorkloadTask);
			result.setDeviationWorkloadTask(deviationWorkloadTask);
			result.setMaxExecutionPeriodsTask(maxExecutionPeriodsTask);
			result.setMinExecutionPeriodsTask(minExecutionPeriodsTask);
			result.setAverageExecutionPeriodsTask(averageExecutionPeriodsTask);
			result.setDeviationExecutionPeriodsTask(deviationExecutionPeriodsTask);
			
		}

		return result;
	}

	private Dashboard workplanDashboard(final Dashboard result) {
		final Integer totalNumberPublicWorkplan;
		final Integer totalNumberPrivateWorkplan;
		final Integer totalNumberFinishedWorkplan;
		final Integer totalNumberNoFinishedWorkplan;
		String averageExecutionPeriodsWorkplan="";
		String deviationExecutionPeriodsWorkplan="";
		String minExecutionPeriodsWorkplan="";
		String maxExecutionPeriodsWorkplan="";
		final Double averageWorkloadWorkplan;
		Double deviationWorkloadWorkplan=0.;
		final Double minWorkloadWorkplan;
		final Double maxWorkloadWorkplan;
		
		totalNumberPublicWorkplan = this.repository.totalNumberPrivacityWorkplan(Privacy.PUBLIC).size();
		totalNumberPrivateWorkplan = this.repository.totalNumberPrivacityWorkplan(Privacy.PRIVATE).size();
		totalNumberFinishedWorkplan = this.repository.totalNumberFinishedWorkplan().size();
		totalNumberNoFinishedWorkplan = this.repository.totalNumberNoFinishedWorkplan().size();
		
		final List<WorkPlan> workplans = this.repository.allWorkplans();
		if(!workplans.isEmpty()) {
			averageWorkloadWorkplan = workplans.stream().mapToDouble(WorkPlan::getWorkload).average().getAsDouble();

		final List<Long> executionPeriod = new ArrayList<>();
		for(final WorkPlan workplan: this.repository.allWorkplans()) {
			final long time = workplan.getEnding().getTime() - workplan.getBeginning().getTime();
	        final long hours = TimeUnit.MILLISECONDS.toMinutes(time);
			executionPeriod.add(hours);
		}
		
		final IntSummaryStatistics estadisticas = executionPeriod.stream().mapToInt(Long::intValue).summaryStatistics();

		//Calculo del periodo de ejecución máximo
		final Integer max = estadisticas.getMax();
		maxExecutionPeriodsWorkplan = this.calcularHoras(max,maxExecutionPeriodsWorkplan);
				
		//Calculo del periodo de ejecución minimo
		final Integer minimo = estadisticas.getMin();
		minExecutionPeriodsWorkplan = this.calcularHoras(minimo,minExecutionPeriodsWorkplan);
				
		//Calculo del periodo de ejecución de la media
		final Integer avgEP = (int)estadisticas.getAverage();
		averageExecutionPeriodsWorkplan = this.calcularHoras(avgEP,averageExecutionPeriodsWorkplan);		
		
		//Calcular la desviación del periodo de ejecución
		Double deviationExecution = 0.;
		for(final Long x : executionPeriod) {
			deviationExecution += Math.pow(x - avgEP.doubleValue(), 2);
		}
		deviationExecution = Math.sqrt(deviationExecution/executionPeriod.size());
		
		deviationExecutionPeriodsWorkplan = this.calcularHoras(deviationExecution.intValue(), deviationExecutionPeriodsWorkplan) ;
		
		final List<Double> workloadList = workplans.stream().map(WorkPlan::getWorkload).collect(Collectors.toList());
		for(final Double x:workloadList) {
			deviationWorkloadWorkplan += Math.pow(x - averageWorkloadWorkplan,2); 
		}
		deviationWorkloadWorkplan = Math.sqrt(deviationWorkloadWorkplan/workloadList.size());
		
		minWorkloadWorkplan = Collections.min(workloadList);
		maxWorkloadWorkplan = Collections.max(workloadList);
		
		result.setTotalNumberPublicWorkplan(totalNumberPublicWorkplan);
		result.setTotalNumberPrivateWorkplan(totalNumberPrivateWorkplan);
		result.setTotalNumberFinishedWorkplan(totalNumberFinishedWorkplan);
		result.setTotalNumberNoFinishedWorkplan(totalNumberNoFinishedWorkplan);
		result.setAverageWorkloadWorkplan(averageWorkloadWorkplan);
		result.setMinWorkloadWorkplan(minWorkloadWorkplan);
		result.setMaxWorkloadWorkplan(maxWorkloadWorkplan);
		result.setDeviationWorkloadWorkplan(deviationWorkloadWorkplan);
		result.setMaxExecutionPeriodsWorkplan(maxExecutionPeriodsWorkplan);
		result.setMinExecutionPeriodsWorkplan(minExecutionPeriodsWorkplan);
		result.setAverageExecutionPeriodsWorkplan(averageExecutionPeriodsWorkplan);
		result.setDeviationExecutionPeriodsWorkplan(deviationExecutionPeriodsWorkplan);
		}
		else {
			minWorkloadWorkplan = 0.;
			maxWorkloadWorkplan = 0.;
			averageWorkloadWorkplan = 0.;
			result.setTotalNumberPublicWorkplan(totalNumberPublicWorkplan);
			result.setTotalNumberPrivateWorkplan(totalNumberPrivateWorkplan);
			result.setTotalNumberFinishedWorkplan(totalNumberFinishedWorkplan);
			result.setTotalNumberNoFinishedWorkplan(totalNumberNoFinishedWorkplan);
			result.setAverageWorkloadWorkplan(averageWorkloadWorkplan);
			result.setMinWorkloadWorkplan(minWorkloadWorkplan);
			result.setMaxWorkloadWorkplan(maxWorkloadWorkplan);
			result.setDeviationWorkloadWorkplan(deviationWorkloadWorkplan);
			result.setMaxExecutionPeriodsWorkplan(maxExecutionPeriodsWorkplan);
			result.setMinExecutionPeriodsWorkplan(minExecutionPeriodsWorkplan);
			result.setAverageExecutionPeriodsWorkplan(averageExecutionPeriodsWorkplan);
			result.setDeviationExecutionPeriodsWorkplan(deviationExecutionPeriodsWorkplan);
		}

		return result;
	}
		
	private String calcularHoras(final Integer digitos,final String cadena) {
		final String result;
		if(digitos>=1440) { //Existe Días
			final Integer dias = (int)Math.floor(digitos/1440.);
			final Integer horas = (int)Math.floor((digitos- dias*1440)/60.);
			final Integer minutos = digitos-dias*1440-horas*60; 
			result = dias+" D. "+ horas+" h. "+minutos+" min." ;
		}else if(digitos>=60) {//Existe Horas
			final Integer horas = (int)Math.floor(digitos/60.);
			final Integer minutos = digitos-horas*60; 
			result = horas+" h. "+minutos+" min." ;
		}else {
			result = digitos+" min." ;
		}
		return result;
			
	}
	
}
