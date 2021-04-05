package acme.feature.administrator.task;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Task;
import acme.framework.entities.UserRole;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorTaskListService implements AbstractListService<Administrator, Task>{
	
	
	@Autowired
	protected AdministratorTaskRepository repository;
	
	
	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		final StringBuilder buffer;
		final Collection<UserRole> roles;

		request.unbind(entity, model, "username", "identity.name", "identity.surname", "identity.email");

	}

	@Override
	public Collection<Task> findMany(final Request<Task> request) {
		// TODO Auto-generated method stub
		return null;
	}




}
