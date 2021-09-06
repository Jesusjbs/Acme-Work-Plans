package acme.features.anonymous.task;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.entities.Privacy;
import acme.framework.entities.Task;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousWorkPlanListTaskService implements AbstractListService<Anonymous, Task>{
	
	@Autowired
	protected AnonymousTaskRepository repository;

	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;

		return this.repository.findOneWorkPlanById(request.getModel().getInteger("workplanId")).getPrivacy().equals(Privacy.PUBLIC);

	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		

		request.unbind(entity, model, "beginning", "ending","privacy","workload","title","description");
		
	}

	@Override
	public Collection<Task> findMany(final Request<Task> request) {
		
		final Integer id = request.getModel().getInteger("workplanId");
		
		return this.repository.findTaskByWorkPlan(id);
	}

}
