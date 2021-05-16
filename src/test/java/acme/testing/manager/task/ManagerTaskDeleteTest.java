package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskDeleteTest extends AcmePlannerTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/delete.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(40)
	public void delete(final int recordIndex) {
		super.signIn("manager2", "manager2");
		
		super.clickOnMenu("Manager", "List tasks");
		
		super.clickOnListingRecord(recordIndex);
		
		super.clickOnSubmitButton("Delete");
		
		super.signOut();
	}
}
