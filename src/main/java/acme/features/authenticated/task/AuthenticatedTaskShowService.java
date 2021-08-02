package acme.features.authenticated.task;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Privacy;
import acme.framework.entities.Task;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedTaskShowService implements AbstractShowService<Authenticated, Task> {
	
	// Internal state ---------------------------------------------------------
	
		@Autowired
		protected AuthenticatedTaskRepository repository;
		
		@Override
		public boolean authorise(final Request<Task> request) {
			assert request != null;
			
			final Integer taskId = request.getModel().getInteger("id");
			final Task task = this.repository.findOneTaskById(taskId);
			
			return task.getPrivacy().equals(Privacy.PUBLIC) && task.getEnding().before(new Date());
		}
		
	// AbstractShowService<Authenticated, Task> interface --------------------------

		@Override
		public void unbind(final Request<Task> request, final Task entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;
			
			request.unbind(entity, model, "title", "beginning", "ending", "workload");
			request.unbind(entity, model, "description", "link", "privacy");
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
