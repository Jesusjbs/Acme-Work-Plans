
package acme.testing.administrator.userAccount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorUserAccountListTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	/* Al ser un listado, solo se puede comprobar que se acceda correctamente a la 
	 vista, seleccionando en el menú el apartado correspondiente, comprobar las
	 columnas y luego comprobar los inputs en el show cuando seleccionamos la 
	 cuenta en concreto. Esto se comprueba para las primeras 3 cuentas del listado */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/userAccount/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void list(final int recordIndex, final String username, final String name, final String surname, final String email, final String roles, final String status) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "User accounts");

		super.checkColumnHasValue(recordIndex, 0, username);
		super.checkColumnHasValue(recordIndex, 1, name);
		super.checkColumnHasValue(recordIndex, 2, surname);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("username", username);
		super.checkInputBoxHasValue("identity.name", name);		
		super.checkInputBoxHasValue("identity.surname", surname);
		super.checkInputBoxHasValue("identity.email", email);
		super.checkInputBoxHasValue("roleList", roles);
		super.checkInputBoxHasValue("status", status);
		
		super.signOut();
	}

	// Ancillary methods ------------------------------------------------------

}
