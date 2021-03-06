package acme.features.manager.workplan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.features.manager.task.ManagerTaskRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.entities.Task;
import acme.framework.entities.WorkPlan;
import acme.framework.services.AbstractDeleteService;

@Service
public class ManagerWorkplanDeleteService implements AbstractDeleteService<Manager,WorkPlan> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerWorkplanRepository repository;
	
	@Autowired
	protected ManagerTaskRepository taskRepository;
	
	@Override
	public boolean authorise(final Request<WorkPlan> request) {
		assert request != null;
		
		final int workplanId = request.getModel().getInteger("id");
		final WorkPlan workplan = this.repository.findOneWorkplanById(workplanId);
		final int managerId = request.getPrincipal().getActiveRoleId();
		
		return workplan.getManager().getId() == managerId;
	}
	
	// AbstractDeleteService<Manager, Workplan> interface -------------------------

	@Override
	public void bind(final Request<WorkPlan> request, final WorkPlan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<WorkPlan> request, final WorkPlan entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		model.setAttribute("workload", entity.getWorkload());
		request.unbind(entity, model, "title", "beginning", "ending", "privacy");		
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

	@Override
	public void validate(final Request<WorkPlan> request, final WorkPlan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<WorkPlan> request, final WorkPlan entity) {
		final List<Task> associatedTasks = entity.getTasks();
		for(final Task task : associatedTasks) {
			task.getWorkPlans().remove(entity);
			this.taskRepository.save(task);
		}
		this.repository.delete(entity);
	}
	
}
