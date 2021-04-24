package acme.features.administrator.task.dashboard;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Privacy;
import acme.framework.entities.Task;
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
			"averageWorkloadTask", "deviationWorkloadTask", "minWorkloadTask", "maxWorkloadTask");
		
	}


	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

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
		
		averageWorkloadTask = this.repository.averageWorkloadTask();
		
		final List<Long> executionPeriod = new ArrayList<>();
		for(final Task task: this.repository.findAllTask()) {
			
			final long time = task.getEnding().getTime() - task.getBeginning().getTime();
	        final long hours = TimeUnit.MILLISECONDS.toMinutes(time);
			executionPeriod.add(hours);
		}
		
		final IntSummaryStatistics estadisticas = executionPeriod.stream().mapToInt(x->x.intValue()).summaryStatistics();
		
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
			deviationExecution += Math.pow(x-avgEP, 2);
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

		return result;
	}
	private String calcularHoras(final Integer digitos,String cadena) {
		if(digitos>=1440) { //Existe Días
			final Integer dias = (int)Math.floor(digitos/1440);
			final Integer horas = (int)Math.floor((digitos- dias*1440)/60);
			final Integer minutos = digitos-dias*1440-horas*60; 
			cadena = dias+" D. "+ horas+" h. "+minutos+" min." ;
		}else if(digitos>=60) {//Existe Horas
			final Integer horas = (int)Math.floor(digitos/60);
			final Integer minutos = digitos-horas*60; 
			cadena = horas+" h. "+minutos+" min." ;
		}else {
			cadena = digitos+" min." ;
		}
		return cadena;
			
	}




}
