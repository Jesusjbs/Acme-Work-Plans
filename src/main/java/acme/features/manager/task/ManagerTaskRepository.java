package acme.features.manager.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerTaskRepository extends AbstractRepository {
	
	@Query("select t from Task t")
	Collection<Task> findTasks();
	
	@Query("select t from Task t where t.id = ?1")
	Task findOneTaskById(int id);
	
}
