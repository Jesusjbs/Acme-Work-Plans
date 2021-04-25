package acme.features.manager.task;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Manager;
import acme.framework.entities.Task;

@Controller
@RequestMapping("/manager/task/")
public class ManagerTaskController extends AbstractController<Manager, Task> {

	// Internal state ---------------------------------------------------------
	
	@Autowired
	private ManagerTaskCreateService createService;	

	@Autowired
	private ManagerTaskListService listService;
	
	@Autowired
	private ManagerTaskShowService showService;
	
	@Autowired
	private ManagerTaskUpdateService updateService;	
	
	@Autowired
	private ManagerTaskDeleteService deleteService;
	
	// Constructors -----------------------------------------------------------

	
	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}
	
}
