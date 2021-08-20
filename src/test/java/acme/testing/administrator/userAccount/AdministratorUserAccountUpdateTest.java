package acme.testing.administrator.userAccount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeWorkplansTest;

public class AdministratorUserAccountUpdateTest extends AcmeWorkplansTest {
	
	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------

	/*Este test prueba la actualización satisfactoria de una cuenta, utilizando los datos del fichero updatePositive.csv
	Una vez se autentica el usuario como administrador, intenta editar una cuenta. Una vez se edita, se muestran los detalles de esta.
	Se prueba 1 caso. */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/userAccount/updatePositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(50)
	public void updatePositive(final int recordIndex, final String status) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "User accounts");
		
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("newStatus", status);
		
		super.clickOnSubmitButton("Update");
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("status", status);
		
		super.signOut();
	}
	
	// Ancillary methods ------------------------------------------------------
	
}
