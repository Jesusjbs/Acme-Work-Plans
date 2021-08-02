package acme.features.manager.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.entities.Task;
import acme.framework.services.AbstractShowService;

@Service
public class ManagerTaskShowService implements AbstractShowService<Manager, Task> {

	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected ManagerTaskRepository repository;
	
	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;
		
		final int taskId = request.getModel().getInteger("id");
		final Task task = this.repository.findOneTaskById(taskId);
		final int managerId = request.getPrincipal().getActiveRoleId();
		
		return task.getManager().getId() == managerId;
	}
	
	// AbstractShowService<Manager, Task> interface --------------------------
	
	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("workplans", entity.getWorkPlans());
		request.unbind(entity, model, "title", "beginning", "ending", "workload", "description", "link", "privacy");

	}
	
	@Override
	public Task findOne(final Request<Task> request) {
		assert request != null;
		
		Task result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneTaskById(id);
			
		return result;
	}
}
