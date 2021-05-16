package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskUpdateTest extends AcmePlannerTest {
	
	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/updatePositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(50)
	public void updatePositive(final int recordIndex, final String title, final String beginning, final String ending, 
		final String workload, final String description, final String link, final String privacy) {
	
		super.signIn("manager2", "manager2");
		
		super.clickOnMenu("Manager", "List tasks");
		
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("beginning", beginning);
		super.fillInputBoxIn("ending", ending);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("privacy", privacy);
		
		super.clickOnSubmitButton("Update");
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/updateNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(60)
	public void updateNegative(final int recordIndex, final String title, final String beginning, final String ending, 
		final String workload, final String description, final String link, final String privacy) {
		
		super.signIn("manager2", "manager2");
		
		super.clickOnMenu("Manager", "List tasks");
		
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("beginning", beginning);
		super.fillInputBoxIn("ending", ending);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("privacy", privacy);
		
		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist();
		
		super.signOut();
	}
	
	// Ancillary methods ------------------------------------------------------
	
}
