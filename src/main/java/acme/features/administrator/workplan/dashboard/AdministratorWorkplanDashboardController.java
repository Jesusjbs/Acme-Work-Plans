package acme.features.administrator.workplan.dashboard;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.forms.DashboardWP;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/workplan/")
public class AdministratorWorkplanDashboardController extends AbstractController<Administrator, DashboardWP> {
	
	@Autowired
	protected AdministratorWorkplanDashboardShowService	showService;


	// Constructors -----------------------------------------------------------
	
	
	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
	
}
