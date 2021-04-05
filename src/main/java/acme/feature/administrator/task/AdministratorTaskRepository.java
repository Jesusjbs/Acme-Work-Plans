package acme.feature.administrator.task;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorTaskRepository extends AbstractRepository{
	

	@Query("select u from Task u where u.privacy = :privacity")
	List<Task> findAllTaskPrivacity(String privacity);
	

}
