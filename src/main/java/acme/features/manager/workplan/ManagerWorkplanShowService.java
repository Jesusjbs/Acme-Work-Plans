package acme.features.manager.workplan;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.entities.Task;
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
		
		final int workplanId = entity.getId();
		final String username = request.getPrincipal().getUsername();
		
		final List<Task> assignedTasks = entity.getTasks();
		List<Task> nonAssignedTasks;
		Date ini;
		Date end;
		if(assignedTasks.isEmpty()) {
			nonAssignedTasks = this.repository.findAllMyActiveTasks(username);
			ini = entity.getBeginning();
			end = entity.getEnding();
		} else {
			nonAssignedTasks = this.repository.findNonAssignedTasks(username, assignedTasks);
			ini = assignedTasks.stream().map(t -> t.getBeginning()).min(Comparator.naturalOrder()).get();
			final Calendar c1 = Calendar.getInstance();
			c1.setTime(ini);
			c1.add(Calendar.DATE, -1);
			ini = c1.getTime();
			ini.setHours(8);
			ini.setMinutes(0);
			end = assignedTasks.stream().map(t -> t.getEnding()).max(Comparator.naturalOrder()).get();
			final Calendar c2 = Calendar.getInstance();
			c2.setTime(end);
			c2.add(Calendar.DATE, 1);
			end = c2.getTime();
			end.setHours(17);
			end.setMinutes(0);
		}

		model.setAttribute("suggestedBeginning", ini);
		model.setAttribute("suggestedEnding", end);
		model.setAttribute("assignedTasks", assignedTasks);
		model.setAttribute("nonAssignedTasks", nonAssignedTasks);
		model.setAttribute("workload", entity.getWorkload());
		model.setAttribute("workplanId", workplanId);
		request.unbind(entity, model, "title", "beginning", "ending", "privacy", "tasks");
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
