package acme.features.administrator.task.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.Privacy;
import acme.framework.entities.Task;
import acme.framework.entities.WorkPlan;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorTaskDashboardRepository extends AbstractRepository{
	
	// Tasks
	
	@Query("select u from Task u where u.privacy = :privacity")
	List<Task> totalNumberPrivacityTask(Privacy privacity);
	
	@Query("select u from Task u where u.ending < current_date()")
	List<Task> totalNumberFinishedTask();
	
	@Query("select u from Task u where u.ending >= current_date()")
	List<Task> totalNumberNoFinishedTask();
	
	@Query("select u from Task u")
	List<Task> findAllTask();
	
	@Query("select u.workload from Task u ")
	List<Double> findAllWorkload();
	
	@Query("select avg(u.workload) from Task u ")
	Double averageWorkloadTask();
	
	@Query("select min(u.workload) from Task u ")
	Double minWorkloadTask();
	
	@Query("select max(u.workload) from Task u ")
	Double maxWorkloadTask();
	
	// Workplans
	
	@Query("select u from WorkPlan u where u.privacy = :privacity")
	List<WorkPlan> totalNumberPrivacityWorkplan(Privacy privacity);
	
	@Query("select u from WorkPlan u where u.ending < current_date()")
	List<WorkPlan> totalNumberFinishedWorkplan();
	
	@Query("select u from WorkPlan u where u.ending >= current_date()")
	List<WorkPlan> totalNumberNoFinishedWorkplan();
	
	@Query("select u from WorkPlan u where u.ending >= current_date()")
	List<WorkPlan> allWorkplans();
	

}
