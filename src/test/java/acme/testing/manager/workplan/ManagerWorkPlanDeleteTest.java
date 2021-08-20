
package acme.testing.manager.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeWorkplansTest;

public class ManagerWorkPlanDeleteTest extends AcmeWorkplansTest {

	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	//Aquí hemos comprobado simplemente si al pulsar el botón de borrar en un workload, es borrado correctamente
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/delete.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void delete(final int recordIndex) {		
		super.signIn("manager2", "manager2");
		
		super.clickOnMenu("Manager", "List workplans");		
		super.clickOnListingRecord(recordIndex);
		super.clickOnSubmitButton("Delete workplan");
		
		super.signOut();
	}

}
