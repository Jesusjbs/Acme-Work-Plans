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
    Se prueban 2 casos, los cuales resultan exitosos. En el primero se actualizan los campos name, surname y email. 
    En el segundo se actualizan los campos pertenecientes a la contraseña. */
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
		
		super.checkInputBoxHasValue("identity.name", name);
		super.checkInputBoxHasValue("identity.surname", surname);
		super.checkInputBoxHasValue("identity.email", email);
		
		super.signOut();
	}
	
	/*Este test prueba la actualización errónea de una usuario, utilizando los datos del fichero updateNegative.csv
    Una vez se autentica el usuario, intenta editar sus datos. Una vez se edita, se muestran los errores.
    Se prueban 15 casos, los cuales resultan exitosos. Caso 1: el formulario se envía vacío. Caso 2: el formulario se envía
    con el campo de confirmación vacío. Caso 3: el formulario se envía con la contraseña vacía. Caso 4: el formulario se envía con
    el campo nombre vacío. Caso 5: el formulario se envía con el campo apellido vacio. Caso 6: el formulario se envía con el campo email vacío.
    Caso 7: la contraseña no coincide con la confirmación. Caso 8: la confirmación no coincide con la contraseña. Caso 9: los campos contraseña 
    y confirmación no tienen la longitud necesaria. Casos 10 y 11: el formulario se envía con el campo email incorrecto. 
    Caso 12: se comprueba que el tamaño de la contraseña no sea mayor a 60 y los demás casos se correspondería a entradas donde están todas vacías
    excepto una de las entradas*/
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
