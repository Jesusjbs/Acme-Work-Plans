package acme.features.manager.workplan;

import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import acme.framework.entities.Manager;
import acme.framework.entities.WorkPlan;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerWorkplanRepository extends AbstractRepository {
	
	@Query("select w from WorkPlan w where w.manager.userAccount.username = ?1")
	Collection<WorkPlan> findMyWorkplan(String username);
	
	@Query("select w from WorkPlan w where w.id = ?1")
	WorkPlan findOneWorkplanById(int id);
	
	@Query("select m from Manager m where m.userAccount.username = ?1")
	Manager findManagerInSession(String username);	
	
	@Modifying
	@Transactional
	@Query(value = "DELETE from TASK_WORK_PLAN WHERE WORK_PLANS_ID = ?1 ;", nativeQuery = true)
	void deleteDependencies(int id);
}
