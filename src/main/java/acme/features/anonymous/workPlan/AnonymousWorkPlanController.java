package acme.features.anonymous.workPlan;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;
import acme.framework.entities.WorkPlan;

@Controller
@RequestMapping("/anonymous/workplan/")
public class AnonymousWorkPlanController extends AbstractController<Anonymous, WorkPlan>{
	
	// Internal state ---------------------------------------------------------

		@Autowired
		private AnonymousWorkPlanListService listService;
		
		@Autowired
		private AnonymousWorkPlanShowService showService;
		
		// Constructors -----------------------------------------------------------

		
		@PostConstruct
		private void initialise() {
			super.addBasicCommand(BasicCommand.LIST, this.listService);
			super.addBasicCommand(BasicCommand.SHOW, this.showService);
		}

}
