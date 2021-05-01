package acme.features.anonymous.workPlan;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.WorkPlan;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousWorkPlanRepository extends AbstractRepository {
	
	@Query("select w from WorkPlan w where w.privacy = 'PUBLIC' and w.ending >= current_date()")
	Collection<WorkPlan> findNoFinishedPublicWorkPlan();

	@Query("select w from WorkPlan w where w.id = :id")
	WorkPlan findOneWorkPlanById(int id);
	


}
