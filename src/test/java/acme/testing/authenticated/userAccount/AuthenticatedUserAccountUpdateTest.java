package acme.testing.authenticated.userAccount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedUserAccountUpdateTest extends AcmePlannerTest{

	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
		
	/*Este test prueba la actualización satisfactoria de una usuario, utilizando los datos del fichero updatePositive.csv
	Una vez se autentica el usuario, intenta editar sus datos. Una vez se edita, se muestran los detalles del usuario.
	Se prueban 2 casos, los cuales resultan exitosos. */
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/userAccount/updatePositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void updatePositive(final int recordIndex, final String password, final String confirmation, final String name, final String surname, final String email) {
		
		super.signIn("manager", "manager");
		
		super.clickOnMenu("Account", "General data");
		
		super.fillInputBoxIn("password", password);
		super.fillInputBoxIn("confirmation", confirmation);
		super.fillInputBoxIn("identity.name", name);
		super.fillInputBoxIn("identity.surname", surname);
		super.fillInputBoxIn("identity.email", email);
		
		super.clickOnSubmitButton("Update");
		
		super.clickOnMenu("Account", "General data");
		
//		super.checkInputBoxHasValue("password", password);
//		super.checkInputBoxHasValue("confirmation", confirmation);
		super.checkInputBoxHasValue("identity.name", name);
		super.checkInputBoxHasValue("identity.surname", surname);
		super.checkInputBoxHasValue("identity.email", email);
		
		super.signOut();
	}
	
	/*Este test prueba la actualización errónea de una usuario, utilizando los datos del fichero updateNegative.csv
	Una vez se autentica el usuario, intenta editar sus datos. Una vez se edita, se muestran los errores.
	Se prueban 15 casos, los cuales resultan exitosos. */
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/userAccount/updateNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void updateNegative(final int recordIndex, final String password, final String confirmation, final String name, final String surname, final String email) {
		
		super.signIn("manager2", "manager2");
		
		super.clickOnMenu("Account", "General data");
		
		super.fillInputBoxIn("password", password);
		super.fillInputBoxIn("confirmation", confirmation);
		super.fillInputBoxIn("identity.name", name);
		super.fillInputBoxIn("identity.surname", surname);
		super.fillInputBoxIn("identity.email", email);
		
		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist();
		
		super.signOut();
	}
	
	// Ancillary methods ------------------------------------------------------
}
