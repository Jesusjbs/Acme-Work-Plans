package acme.features.manager.workplan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.entities.WorkPlan;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagerWorkplanUpdateService implements AbstractUpdateService<Manager, WorkPlan> {
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerWorkplanRepository repository;

	// AbstractUpdateService<Manager, WorkPlan> interface -------------	

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
		request.unbind(entity, model, "beginning", "ending", "privacy");
	}

	@Override
	public WorkPlan findOne(final Request<WorkPlan> request) {
		assert request != null;
		return this.repository.findOneWorkplanById(request.getModel().getInteger("id"));	
	}

	@Override
	public void validate(final Request<WorkPlan> request, final WorkPlan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!request.getModel().getString("beginning").isEmpty() && !request.getModel().getString("ending").isEmpty()) {
			final boolean español = request.getLocale().toString().equals("es");
			final SimpleDateFormat format = !español ? new SimpleDateFormat("yyyy/MM/dd HH:mm") : new SimpleDateFormat("dd/MM/yyyy HH:mm");
			try {
				Date ini = null;
				Date end = null;
				ini = format.parse(request.getModel().getString("beginning"));
				end = format.parse(request.getModel().getString("ending"));
				
				if (!español) {
					if (ini.before(new Date())) {
						errors.add("beginning", "The beginning must be later than the current one");
					}
					if (end.before(new Date())) {
						errors.add("ending", "The ending must be later than the current one");
					}
					if (end.before(ini)) {
						errors.add("ending", "The ending must be later than the beginning");
					} else if (end.equals(ini)) {
						errors.add("ending", "The ending can't be same that the beginning");
						errors.add("beginning", "The beginning can't be same that the ending");
					}
				} else {
					if (ini.before(new Date())) {
						errors.add("beginning", "El comienzo debe ser posterior a la fecha actual");
					}
					if (end.before(new Date())) {
						errors.add("ending", "El final debe ser posterior a la fecha actual");
					}
					if (end.before(ini)) {
						errors.add("ending", "El final debe ser posterior al comienzo");
					} else if (end.equals(ini)) {
						errors.add("ending", "El final no puede ser igual al comienzo");
						errors.add("beginning", "El comienzo no puede ser igual al final");
					}
				}
			} catch(final ParseException e) {
				
			}
		}
	}

	@Override
	public void update(final Request<WorkPlan> request, final WorkPlan entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	
}
