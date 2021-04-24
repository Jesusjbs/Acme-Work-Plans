package acme.features.administrator.workplan.dashboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.DashboardWP;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Privacy;
import acme.framework.entities.WorkPlan;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorWorkplanDashboardShowService implements AbstractShowService<Administrator, DashboardWP> {
	
	
	@Autowired
	protected AdministratorWorkplanDashboardRepository repository;
	
	@Override
	public boolean authorise(final Request<DashboardWP> request) {
		assert request != null;

		return true;
	}
	
	@Override
	public void unbind(final Request<DashboardWP> request, final DashboardWP entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, //
			"totalNumberPublicWorkplan", "totalNumberPrivateWorkplan", // 
			"totalNumberFinishedWorkplan", "totalNumberNoFinishedWorkplan" , // 
			"averageExecutionPeriodsWorkplan", "deviationExecutionPeriodsWorkplan","minExecutionPeriodsWorkplan","maxExecutionPeriodsWorkplan", //
			"averageWorkloadWorkplan", "deviationWorkloadWorkplan", "minWorkloadWorkplan", "maxWorkloadWorkplan");
		
	}
	
	@Override
	public DashboardWP findOne(final Request<DashboardWP> request) {
		assert request != null;

		final DashboardWP result;
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
		averageWorkloadWorkplan = workplans.stream().mapToDouble(WorkPlan::getWorkload).average().getAsDouble();
		
		final List<Long> executionPeriod = new ArrayList<>();
		for(final WorkPlan workplan: this.repository.allWorkplans()) {
			final long time = workplan.getEnding().getTime() - workplan.getBeginning().getTime();
	        final long hours = TimeUnit.MILLISECONDS.toMinutes(time);
			executionPeriod.add(hours);
		}
		
		final IntSummaryStatistics estadisticas = executionPeriod.stream().mapToInt(x->x.intValue()).summaryStatistics();

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
			deviationExecution += Math.pow(x - avgEP, 2);
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
		
		result = new DashboardWP();
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
