
package acme.features.manager.workplan;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.entities.Privacy;
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

		final int workplanId = request.getModel().getInteger("id");
		final WorkPlan workplan = this.repository.findOneWorkplanById(workplanId);
		final int managerId = request.getPrincipal().getActiveRoleId();

		return workplan.getManager().getId() == managerId;

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
		if (assignedTasks.isEmpty()) {
			ini = entity.getBeginning();
			end = entity.getEnding();
			if (entity.getPrivacy().equals(Privacy.PUBLIC)) {
				nonAssignedTasks = this.repository.findAllMyActiveTasksNoPrivates(username, ini, end);
			} else {
				nonAssignedTasks = this.repository.findAllMyActiveTasks(username, ini, end);
			}
		} else {
			if (entity.getPrivacy().equals(Privacy.PUBLIC)) {
				nonAssignedTasks = this.repository.findNonAssignedTasksNoPrivates(username, assignedTasks, entity.getBeginning(), entity.getEnding());
			} else {
				nonAssignedTasks = this.repository.findNonAssignedTasks(username, assignedTasks, entity.getBeginning(), entity.getEnding());
			}
			final Optional<Date> opt = assignedTasks.stream().map(Task::getBeginning).min(Comparator.naturalOrder());
			ini = opt.isPresent() ? opt.get() : new Date();
			final Calendar c1 = Calendar.getInstance();
			c1.setTime(ini);
			c1.add(Calendar.DATE, -1);
			c1.set(Calendar.HOUR_OF_DAY, 8);
			c1.set(Calendar.MINUTE, 0);
			ini = c1.getTime();
			if(ini.before(new Date())) {
				ini = opt.isPresent() ? opt.get() : new Date();
				c1.setTime(ini);
				ini = c1.getTime();
			}
	
			final Optional<Date> optEnd = assignedTasks.stream().map(Task::getEnding).max(Comparator.naturalOrder());
			end = optEnd.isPresent() ? optEnd.get() : new Date();
			final Calendar c2 = Calendar.getInstance();
			c2.setTime(end);
			c2.add(Calendar.DATE, 1);
			c2.set(Calendar.HOUR_OF_DAY, 17);
			c2.set(Calendar.MINUTE, 0);
			end = c2.getTime();
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
