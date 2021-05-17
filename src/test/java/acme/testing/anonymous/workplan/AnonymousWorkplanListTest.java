package acme.testing.anonymous.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousWorkplanListTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
		
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/workplan/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(60)	
	public void list(final int recordIndex, final String title, final String beginning, final String ending, final String privacy) {
		
		super.clickOnMenu("Anonymous", "List work plans");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, beginning);
		super.checkColumnHasValue(recordIndex, 2, ending);
		super.checkColumnHasValue(recordIndex, 4, privacy);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("beginning", beginning);
		super.checkInputBoxHasValue("ending", ending);
		super.checkInputBoxHasValue("privacy", privacy);
	
	}
	
	// Ancillary methods ------------------------------------------------------
}
