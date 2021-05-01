package acme.features.manager.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.Manager;
import acme.framework.entities.Spam;
import acme.framework.entities.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerTaskRepository extends AbstractRepository {
	
	@Query("select t from Task t where t.manager.userAccount.username = ?1")
	Collection<Task> findTasks(String username);
	
	@Query("select t from Task t where t.id = ?1")
	Task findOneTaskById(int id);
	
	@Query("select m from Manager m where m.userAccount.username = ?1")
	Manager findManegerInSession(String username);	
	
	@Query("select s from Spam s")
	Spam getSpamWords();
	
	@Query("select s.threshold from Spam s")
	Double getThreshold();
	
}
