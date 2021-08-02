
package acme.features.administrator.spam;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Spam;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorSpamShowService implements AbstractShowService<Administrator, Spam> {

	@Autowired
	protected AdministratorSpamRepository repository;


	@Override
	public boolean authorise(final Request<Spam> request) {
		assert request != null;
		
		return !this.repository.findSpam().get(0).getWords().isEmpty();
	}

	@Override
	public void unbind(final Request<Spam> request, final Spam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		final List<String> spam = entity.getWords();
		model.setAttribute("staticSpam", entity.getWords().subList(0, 11));
		final List<String> wordSpam = spam.size() == 11 ? new ArrayList<>() : spam.subList(11, spam.size());

		model.setAttribute("wordSpam", wordSpam);
		request.unbind(entity, model, "threshold");
	}

	@Override
	public Spam findOne(final Request<Spam> request) {
		return this.repository.findSpam().get(0);
	}

}
