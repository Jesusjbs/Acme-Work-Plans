package acme.testing.anonymous.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeWorkplansTest;

public class AnonymousWorkplanListTasksTest extends AcmeWorkplansTest {
	
	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	
	/* Al ser un listado, solo se puede comprobar que se acceda correctamente a la 
	 vista, seleccionando en el menú el apartado correspondiente, comprobar las
	 columnas y luego comprobar los inputs en el show cuando seleccionamos la 
	 tarea en concreto.*/
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/workplan/tasks.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(60)	
	public void tasks(final int recordIndex, final String title, final String beginning, final String ending, 
			final String workload, final String description, final String link, final String privacy) {
		super.clickOnMenu("Anonymous", "Public active work plans");
		super.clickOnListingRecord(1);
		super.clickOnButtonButton("Associated tasks");
		
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
		super.checkInputBoxHasValue("privacy", privacy);

	}
		

}
