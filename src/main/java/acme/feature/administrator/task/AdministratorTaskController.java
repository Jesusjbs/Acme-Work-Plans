package acme.feature.administrator.task;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;
import acme.framework.entities.Task;

@Controller
@RequestMapping("/administrator/task/")
public class AdministratorTaskController extends AbstractController<Administrator, Task>{
	
	@Autowired
	protected AdministratorTaskListService	listService;


	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
	}

}
