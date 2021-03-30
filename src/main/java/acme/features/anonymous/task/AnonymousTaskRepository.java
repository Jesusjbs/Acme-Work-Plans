package acme.features.anonymous.task;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousTaskRepository extends AbstractRepository {
	
	@Query("select t from Task t where t.privacy = 'PUBLIC' and t.ending >= :date")
	Collection<Task> findActivePublicTask(Date date);
}
