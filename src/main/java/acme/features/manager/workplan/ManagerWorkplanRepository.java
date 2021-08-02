package acme.features.manager.workplan;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.Manager;
import acme.framework.entities.Spam;
import acme.framework.entities.Task;
import acme.framework.entities.WorkPlan;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerWorkplanRepository extends AbstractRepository {
	
	@Query("select w from WorkPlan w where w.manager.userAccount.username = ?1")
	Collection<WorkPlan> findMyWorkplan(String username);
	
	@Query("select t from Task t where t.manager.userAccount.username = ?1 and t not in ?2 and t.ending <= ?4 and t.beginning >= ?3")
	List<Task> findNonAssignedTasks(String username, List<Task> assignedTasks,Date fechaInicio, Date fechaFin);
	
	@Query("select t from Task t where t.manager.userAccount.username = ?1 and t.ending <= ?3 and t.beginning >= ?2")
	List<Task> findAllMyActiveTasks(String username,Date fechaInicio, Date fechaFin);
	
	@Query("select t from Task t where t.manager.userAccount.username = ?1")
	List<Task> findAllMyTasks(String username);
	
	@Query("select w from WorkPlan w where w.id = ?1")
	WorkPlan findOneWorkplanById(int id);
	
	@Query("select t from Task t where t.id = ?1")
	Task findTaskById(int id);
	
	@Query("select m from Manager m where m.userAccount.username = ?1")
	Manager findManagerInSession(String username);	
	
	@Query("select s from Spam s")
	List<Spam> getSpamWords();
	
}
