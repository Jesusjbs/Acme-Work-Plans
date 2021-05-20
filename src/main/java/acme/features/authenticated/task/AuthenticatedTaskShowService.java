package acme.features.authenticated.task;

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
			
			assert this.repository.findOneTaskById(request.getModel().getInteger("id"))
				.getPrivacy().equals(Privacy.PUBLIC);
			
			return true;
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
