package acme.features.authenticated.task;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;

import acme.framework.entities.Task;
import acme.framework.repositories.AbstractRepository;

public interface AuthenticatedTaskRepository extends AbstractRepository{

	@Query("select t from Task t where t.privacy = 'PUBLIC' and t.ending < :date")
	Collection<Task> findFinishedPublicTask(Date date);
	
	@Query("select t from Task t where t.id = ?1")
	Task findOneTaskById(int id);
}
