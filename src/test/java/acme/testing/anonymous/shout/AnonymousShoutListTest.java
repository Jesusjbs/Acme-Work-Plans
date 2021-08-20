package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeWorkplansTest;

public class AnonymousShoutListTest extends AcmeWorkplansTest {
	
	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	
	/* Al ser un listado, solo se puede comprobar que se acceda correctamente a la 
	 vista, seleccionando en el menú el apartado correspondiente, comprobar las
	 columnas */
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void list(final int recordIndex, final String moment, final String author, final String text) {

		super.clickOnMenu("Anonymous", "List recent shouts");
		
		super.checkColumnHasValue(recordIndex, 0, moment);
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, text);
	}
	
	// Ancillary methods ------------------------------------------------------

}
