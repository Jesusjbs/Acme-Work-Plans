package acme.testing.manager.workplan;

import org.junit.jupiter.api.Test;

import acme.testing.AcmePlannerTest;

public class ManagerWorkPlanCreateTaskTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	
	// Caso positivo: se intenta añadir una tarea, seleccionando una tarea en 
	// concreto que es válida (la que tiene id=25). Cuando se intenta añadir, no
	// hay ningún fallo.
	@Test
	public void createPositive() {
		
		super.signIn("manager2", "manager2");
		super.clickOnMenu("Manager", "List workplans");
		super.clickOnListingRecord(0);
		super.fillInputBoxIn("task", "25");
		
		super.clickOnSubmitButton("Add task");
	}
}
