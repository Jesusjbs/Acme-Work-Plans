package acme.features.manager.workplan;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Manager;
import acme.framework.entities.WorkPlan;

@Controller
@RequestMapping("/management/workplan/")
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
	
	@Autowired
	private ManagerWorkplanUpdateService updateService;
	
	@Autowired
	private ManagerWorkplanTaskAddService addTaskService;
	
	@Autowired
	private ManagerWorkplanTaskDeleteService deleteTaskService;
	
	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addCustomCommand(CustomCommand.DELETE_TASK_WORKPLAN, BasicCommand.DELETE, this.deleteTaskService);
		super.addCustomCommand(CustomCommand.ADD_TASK_WORKPLAN, BasicCommand.UPDATE, this.addTaskService);

	}
	
}
