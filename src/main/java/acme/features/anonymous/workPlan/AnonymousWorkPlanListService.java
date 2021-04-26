package acme.features.anonymous.workPlan;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.entities.WorkPlan;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousWorkPlanListService implements AbstractListService<Anonymous, WorkPlan>{
	
	@Autowired
	protected AnonymousWorkPlanRepository repository;

	@Override
	public boolean authorise(final Request<WorkPlan> request) {

		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<WorkPlan> request, final WorkPlan entity, final Model model) {
		
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("workload", entity.getWorkload());

		request.unbind(entity, model, "beginning", "ending","privacy","workload");
		
	}

	@Override
	public Collection<WorkPlan> findMany(final Request<WorkPlan> request) {
		assert request != null;
		
		final Collection<WorkPlan> result = this.repository.findNoFinishedPublicWorkPlan();
		return result;
	}
	
	

}
