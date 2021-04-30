package acme.features.manager.task;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.entities.Task;
import acme.framework.services.AbstractListService;

@Service
public class ManagerTaskWorkplanListService implements AbstractListService<Manager, Task> {
	
	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected ManagerTaskRepository repository;

	// AbstractListService<Manager, Task> interface --------------
	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;
		
		final String username = request.getPrincipal().getUsername();
		assert this.repository.findOneWorkplanById(request.getModel().getInteger("workplanId")).getManager().getUserAccount()
			.getUsername().equals(username);
		
		return true;
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,"id", "title", "beginning", "ending", "workload", "description");
	}

	@Override
	public Collection<Task> findMany(final Request<Task> request) {
		assert request != null;
		final Integer id = request.getModel().getInteger("workplanId");
		return this.repository.findWorkplanTasks(id);
	}
	
}
