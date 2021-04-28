package acme.features.manager.workplan;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Manager;
import acme.framework.entities.WorkPlan;

@Controller
@RequestMapping("/manager/workplan/")
public class ManagerWorkplanController extends AbstractController<Manager, WorkPlan> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerWorkplanListService listService;
	
	@Autowired
	private ManagerWorkplanShowService showService;
	
	@Autowired
	private ManagerWorkplanDeleteService deleteService;
	
	@Autowired
	private ManagerWorkplanCreateService createService;
	
	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}
	
}
