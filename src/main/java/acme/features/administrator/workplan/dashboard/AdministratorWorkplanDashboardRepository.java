package acme.features.administrator.workplan.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.Privacy;
import acme.framework.entities.WorkPlan;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorWorkplanDashboardRepository extends AbstractRepository {

	@Query("select u from WorkPlan u where u.privacy = :privacity")
	List<WorkPlan> totalNumberPrivacityWorkplan(Privacy privacity);
	
	@Query("select u from WorkPlan u where u.ending < current_date()")
	List<WorkPlan> totalNumberFinishedWorkplan();
	
	@Query("select u from WorkPlan u where u.ending >= current_date()")
	List<WorkPlan> totalNumberNoFinishedWorkplan();
	
	@Query("select u from WorkPlan u where u.ending >= current_date()")
	List<WorkPlan> allWorkplans();
}
