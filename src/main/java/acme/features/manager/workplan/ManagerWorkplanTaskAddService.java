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

		return true;
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
		
		final Task tarea = this.taskRepository.findOneTaskById(request.getModel().getInteger("task"));
		final boolean validacion = entity.getPrivacy().equals(Privacy.PUBLIC) && tarea.getPrivacy().equals(Privacy.PRIVATE);
		errors.state(request, !validacion, "task", "manager.workplan.error.invalidTask");

		if(errors.hasErrors()) {
			final String username = request.getPrincipal().getUsername();
			final List<Task> assignedTasks = entity.getTasks();
			List<Task> nonAssignedTasks;
			if(assignedTasks.isEmpty()) {
				nonAssignedTasks = this.workPlanRepository.findAllMyTasks(username);
			} else {
				nonAssignedTasks = this.workPlanRepository.findNonAssignedTasks(username, assignedTasks);
			}
			// COMPROBAR POR QUÉ SE PIERDEN TODOS LOS DATOS
			// Si falla el update, se pierden todos los datos y hay que añadir los atributos
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
		this.workPlanRepository.addTask(entity.getId(), request.getModel().getInteger("task"));
	}

}
