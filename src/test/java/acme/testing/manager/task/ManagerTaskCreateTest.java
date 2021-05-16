package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskCreateTest extends AcmePlannerTest {
	
	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/createPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createPositive(final int recordIndex, final String title, final String beginning, final String ending, 
			final String workload, final String description, final String link, final String privacy) {
		
		super.signIn("manager", "manager");
		
		super.clickOnMenu("Manager", "Create task");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("beginning", beginning);
		super.fillInputBoxIn("ending", ending);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("privacy", privacy);
		
		super.clickOnSubmitButton("Create");
		
		super.clickOnMenu("Manager", "List tasks");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, beginning);
		super.checkColumnHasValue(recordIndex, 2, ending);
		super.checkColumnHasValue(recordIndex, 3, workload);
		super.checkColumnHasValue(recordIndex, 4, description);
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/createNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	public void createNegative(final int recordIndex, final String title, final String beginning, final String ending, 
			final String workload, final String description, final String link, final String privacy) {

		super.signIn("manager2", "manager2");
		
		super.clickOnMenu("Manager", "Create task");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("beginning", beginning);
		super.fillInputBoxIn("ending", ending);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("privacy", privacy);
		
		super.clickOnSubmitButton("Create");
		
		super.checkErrorsExist();
		
		super.signOut();
	}
	
	// Ancillary methods ------------------------------------------------------
	
}
