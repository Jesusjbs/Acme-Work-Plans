package acme.features.manager.workplan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.entities.WorkPlan;
import acme.framework.services.AbstractShowService;

@Service
public class ManagerWorkplanShowService implements AbstractShowService<Manager, WorkPlan> {

	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected ManagerWorkplanRepository repository;	
	
	@Override
	public boolean authorise(final Request<WorkPlan> request) {
		assert request != null;
		
		final String username = request.getPrincipal().getUsername();
		
		assert this.repository.findOneWorkplanById(request.getModel().getInteger("id")).getManager().getUserAccount()
			.getUsername().equals(username);
		
		return true;
	}

	@Override
	public void unbind(final Request<WorkPlan> request, final WorkPlan entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		model.setAttribute("workload", entity.getWorkload());
		model.setAttribute("workplanId", entity.getId());
		request.unbind(entity, model, "beginning", "ending", "privacy");
	}

	@Override
	public WorkPlan findOne(final Request<WorkPlan> request) {
		assert request != null;
		
		WorkPlan result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneWorkplanById(id);
			
		return result;
	}

}
