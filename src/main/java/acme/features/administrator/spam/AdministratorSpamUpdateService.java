package acme.features.administrator.spam;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Spam;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorSpamUpdateService implements AbstractUpdateService<Administrator, Spam>{
	
	@Autowired
	protected AdministratorSpamRepository repository;


	@Override
	public boolean authorise(final Request<Spam> request) {
		assert request != null;

		return true;
	}
	
	@Override
	public void bind(final Request<Spam> request, final Spam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
		
	}

	@Override
	public void unbind(final Request<Spam> request, final Spam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
	}

	@Override
	public Spam findOne(final Request<Spam> request) {
		assert request != null;
		
		return this.repository.findSpam().get(0);
	}



	@Override
	public void validate(final Request<Spam> request, final Spam entity, final Errors errors) {
		
	}

	@Override
	public void update(final Request<Spam> request, final Spam entity) {
		final List<String> cadenas = entity.getWords();
		final List<String> words = new ArrayList<>();
		for(final String cadena:cadenas) {
			if(!cadena.isEmpty()) {
				words.add(cadena);
			}
		}
		entity.setWords(words);
		this.repository.save(entity);
	}
	


}
