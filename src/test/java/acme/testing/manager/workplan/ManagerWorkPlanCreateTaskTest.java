package acme.testing.manager.workplan;

import org.junit.jupiter.api.Test;

import acme.testing.AcmePlannerTest;

public class ManagerWorkPlanCreateTaskTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@Test
	public void createNegative() {
		
		super.signIn("manager2", "manager2");
		super.clickOnMenu("Manager", "List workplans");
		super.clickOnListingRecord(0);
		super.fillInputBoxIn("privacy", "PRIVATE");
		
		super.clickOnSubmitButton("Add task");
	}
	
//	@Test
//	public void createPositive() {
//		
//		super.signIn("manager2", "manager2");
//		super.clickOnMenu("Manager", "List workplans");
//		super.clickOnListingRecord(0);
//		super.fillInputBoxIn("task", "");
//		
//		super.clickOnSubmitButton("Add task");
//	}
}
