package acme.features.anonymous.task;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.Task;
import acme.framework.entities.WorkPlan;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousTaskRepository extends AbstractRepository {
	
	@Query("select t from Task t where t.privacy = 'PUBLIC' and t.ending >= :date order by t.workload desc")
	Collection<Task> findActivePublicTask(Date date);
	
	@Query("select t from Task t where t.id = ?1")
	Task findOneTaskById(int id);
	
	@Query("select w.tasks from WorkPlan w where w.id = :workPlanId")
	Collection<Task> findTaskByWorkPlan(Integer workPlanId);
	
	@Query("select w from WorkPlan w where w.id = :id")
	WorkPlan findOneWorkPlanById(int id);
}
