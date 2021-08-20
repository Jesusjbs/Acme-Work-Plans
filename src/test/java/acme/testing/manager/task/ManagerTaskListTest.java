package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeWorkplansTest;

public class ManagerTaskListTest extends AcmeWorkplansTest{
	
	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------

	/*Este test prueba la visualización satisfactoria de una tarea, utilizando los datos del fichero list.csv
	Una vez se autentica el usuario como manager, intenta visualizar una tarea. Una vez se accede a los detalles,
	se comparan con los datos de prueba. Se prueban 3 casos, los cuales resultan exitosos. */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void list(final int recordIndex, final String title, final String beginning, final String ending, 
			final String workload, final String description, final String link, final String privacy) {
		
		super.signIn("manager2", "manager2");
		
		super.clickOnMenu("Manager", "List tasks");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, beginning);
		super.checkColumnHasValue(recordIndex, 2, ending);
		super.checkColumnHasValue(recordIndex, 3, workload);
		super.checkColumnHasValue(recordIndex, 4, description);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("beginning", beginning);
		super.checkInputBoxHasValue("ending", ending);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("link", link);
//		super.checkInputBoxHasValue("privacy", privacy);
		
		super.signOut();
	}
	
	// Ancillary methods ------------------------------------------------------
	
	
}
