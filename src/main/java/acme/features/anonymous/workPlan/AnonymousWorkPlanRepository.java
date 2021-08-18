package acme.features.anonymous.workPlan;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.WorkPlan;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousWorkPlanRepository extends AbstractRepository {
	
	@Query("select w from WorkPlan w where w.privacy = 'PUBLIC' and w.ending >= :date")
	Collection<WorkPlan> findNoFinishedPublicWorkPlan(Date date);

	@Query("select w from WorkPlan w where w.id = :id")
	WorkPlan findOneWorkPlanById(int id);
	


}
