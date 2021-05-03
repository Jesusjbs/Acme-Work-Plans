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
import acme.framework.entities.Privacy;
import acme.framework.entities.Spam;
import acme.framework.entities.WorkPlan;
import acme.framework.services.AbstractCreateService;
import acme.utilities.ValidateSpam;

@Service
public class ManagerWorkplanCreateService implements AbstractCreateService<Manager,WorkPlan> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerWorkplanRepository repository;
	
	// AbstractCreateService<Manager, Workplan> interface --------------

	
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
	public WorkPlan instantiate(final Request<WorkPlan> request) {
		final WorkPlan result;
		final Manager manager = this.repository.findManagerInSession(request.getPrincipal().getUsername());
		final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");

		Date ini = null;
		Date end = null;

		try {
			ini = format.parse("2021/06/30 17:15");
			end = format.parse("2021/10/30 19:45");
		} catch (final ParseException e) {
			e.printStackTrace();
		}
		
		result = new WorkPlan();
		result.setTitle("New Workplan");
		result.setManager(manager);
		result.setBeginning(ini);
		result.setEnding(end);
		result.setPrivacy(Privacy.PRIVATE);
		
		return result;
	}

	@Override
	public void validate(final Request<WorkPlan> request, final WorkPlan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(request.getModel().getString("privacy").equals("PUBLIC")) {
			final Spam spam = this.repository.getSpamWords().get(0);
			final ValidateSpam validaSpam = new ValidateSpam();
			final String title = request.getModel().getString("title").toLowerCase();
			
			errors.state(request, !validaSpam.validateSpam(title, spam), "title", "manager.workplan.error.spam");
		}
		
		if(!request.getModel().getString("beginning").isEmpty() && !request.getModel().getString("ending").isEmpty()) {
			final boolean español = request.getLocale().toString().equals("es");
			final SimpleDateFormat format = !español ? new SimpleDateFormat("yyyy/MM/dd HH:mm") : new SimpleDateFormat("dd/MM/yyyy HH:mm");
			try {
				Date ini = null;
				Date end = null;
				ini = format.parse(request.getModel().getString("beginning"));
				end = format.parse(request.getModel().getString("ending"));
				
				errors.state(request, ini.after(new Date()), "beginning", "manager.workplan.form.beginning.error1");
				errors.state(request, end.after(new Date()), "ending", "manager.workplan.form.ending.error1");
				errors.state(request, !end.before(ini), "ending", "manager.workplan.form.ending.error2");
				errors.state(request, !end.equals(ini), "ending", "manager.workplan.form.ending.error3");
				errors.state(request, !end.equals(ini), "beginning", "manager.workplan.form.beginning.error2");
			} catch(final ParseException e) {
				
			}
		}
	}

	@Override
	public void create(final Request<WorkPlan> request, final WorkPlan entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
	
}
