package acme.features.manager.workplan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.features.manager.task.ManagerTaskRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.entities.Privacy;
import acme.framework.entities.Task;
import acme.framework.entities.WorkPlan;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagerWorkplanTaskAddService implements AbstractUpdateService<Manager, WorkPlan> {

	@Autowired
	protected ManagerWorkplanRepository workPlanRepository;
	
	@Autowired
	protected ManagerTaskRepository taskRepository;
	
	@Override
	public boolean authorise(final Request<WorkPlan> request) {
		assert request != null;
		final int workplanId = request.getModel().getInteger("id");
		final WorkPlan workplan = this.workPlanRepository.findOneWorkplanById(workplanId);
		
		final int taskId = request.getModel().getInteger("task");
		final Task task = this.taskRepository.findOneTaskById(taskId);
		
		final int managerId = request.getPrincipal().getActiveRoleId();
		
		return workplan.getManager().getId() == managerId && task.getManager().getId() == managerId;
	}

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
		model.setAttribute("workload", 0.00);
		request.unbind(entity, model, "title", "beginning", "ending", "privacy");
	}

	@Override
	public WorkPlan findOne(final Request<WorkPlan> request) {
		assert request != null;

		WorkPlan result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.workPlanRepository.findOneWorkplanById(id);

		return result;
	}

	@Override
	public void validate(final Request<WorkPlan> request, final WorkPlan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final String username = request.getPrincipal().getUsername();
		final Task tarea = this.taskRepository.findOneTaskById(request.getModel().getInteger("task"));
		final List<Task> assignedTasks = entity.getTasks();
		List<Task> nonAssignedTasks;
		if(assignedTasks.isEmpty()) {
			nonAssignedTasks = this.workPlanRepository.findAllMyActiveTasks(username,entity.getBeginning(),entity.getEnding());
		} else {
			nonAssignedTasks = this.workPlanRepository.findNonAssignedTasks(username, assignedTasks,entity.getBeginning(),entity.getEnding());
		}
		
		final boolean validacion = entity.getPrivacy().equals(Privacy.PUBLIC) && tarea.getPrivacy().equals(Privacy.PRIVATE);
		final boolean validacionIdTask = nonAssignedTasks.contains(tarea);
		final boolean validacionTareaFueraDeRango = tarea.getBeginning().before(entity.getBeginning()) || tarea.getEnding().after(entity.getEnding()); 
		errors.state(request, !validacion, "task", "manager.workplan.error.invalidTask");
		errors.state(request, validacionIdTask, "task", "manager.workplan.error.invalidTask2");
		errors.state(request, !validacionTareaFueraDeRango, "task", "manager.workplan.error.invalidTask3");

		if(errors.hasErrors()) {
			request.getModel().setAttribute("assignedTasks", assignedTasks);
			request.getModel().setAttribute("nonAssignedTasks", nonAssignedTasks);
			request.getModel().setAttribute("title", entity.getTitle());
			request.getModel().setAttribute("beginning", entity.getBeginning());
			request.getModel().setAttribute("ending", entity.getEnding());
			request.getModel().setAttribute("workload", entity.getWorkload());
		}

	}

	@Override
	public void update(final Request<WorkPlan> request, final WorkPlan entity) {
		final Task tarea = this.taskRepository.findOneTaskById(request.getModel().getInteger("task"));
		entity.getTasks().add(tarea);
		tarea.getWorkPlans().add(entity);
		this.taskRepository.save(tarea);
		this.workPlanRepository.save(entity);
		
		//this.workPlanRepository.addTask(request.getModel().getInteger("id"), request.getModel().getInteger("task"));
	}

}
