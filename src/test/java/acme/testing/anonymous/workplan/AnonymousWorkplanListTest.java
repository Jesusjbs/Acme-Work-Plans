package acme.testing.anonymous.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeWorkplansTest;

public class AnonymousWorkplanListTest extends AcmeWorkplansTest {

	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	
	/*Este test prueba la visualización satisfactoria de un workplan, utilizando los datos del fichero list.csv
	Se intenta visualizar una tarea. Una vez se accede a los detalles, se comparan con los datos de prueba. 
	Se prueban 3 casos, los cuales resultan exitosos. */
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/workplan/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(60)	
	public void list(final int recordIndex, final String title, final String beginning, final String ending, final String privacy) {
		
		super.clickOnMenu("Anonymous", "Public active work plans");
		
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
