package acme.features.anonymous.task;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;
import acme.framework.entities.Task;

@Controller
@RequestMapping("/anonymous/task/")
public class AnonymousTaskController extends AbstractController<Anonymous, Task> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnonymousTaskListPublicActiveService listPublicActiveService;
	
	@Autowired
	private AnonymousTaskShowService showService;
	
	// Constructors -----------------------------------------------------------

	
	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_PUBLIC_ACTIVE, BasicCommand.LIST, this.listPublicActiveService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
	
}