package acme.features.administrator.spam;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;
import acme.framework.entities.Spam;

@Controller
@RequestMapping("/administrator/spam/")
public class AdministratorSpamController extends AbstractController<Administrator, Spam>{
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorSpamShowService	showService;

	@Autowired
	protected AdministratorSpamUpdateService	updateService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}

}
