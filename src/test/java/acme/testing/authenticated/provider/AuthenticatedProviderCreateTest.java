
package acme.testing.authenticated.provider;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedProviderCreateTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	//Se comprueba con solo 1 caso el registro de provider, introduciendo una compañia y un sector dado

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/provider/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String company, final String sector) {
		super.signIn("manager", "manager");

		super.clickOnMenu("Account", "Become a provider");

		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", sector);
		

		super.clickOnSubmitButton("Register");

		super.clickOnMenu("Account", "Provider data");
	
		super.checkInputBoxHasValue("company", company);
		super.checkInputBoxHasValue("sector", sector);
		
		super.signOut();
	}
	/* 3 casos positivos: uno con todos los campos vacíos, otro comprobando que únicamente el campo compañía no esté vacía y el último comprobando
	 que únicamente el campo sector no esté vacío*/
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/provider/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createNegative(final int recordIndex, final String company, final String sector) {
		super.signIn("manager2", "manager2");

		super.clickOnMenu("Account", "Become a provider");

		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", sector);
		

		super.clickOnSubmitButton("Register");

		super.checkErrorsExist();
		
		super.signOut();
	}

	// Ancillary methods ------------------------------------------------------

}
