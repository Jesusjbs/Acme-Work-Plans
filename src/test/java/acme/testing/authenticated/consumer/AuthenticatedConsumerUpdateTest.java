package acme.testing.authenticated.consumer;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeWorkplansTest;

public class AuthenticatedConsumerUpdateTest extends AcmeWorkplansTest {
	
	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	//Se comprueba con solo 1 caso la actualización de los datos de un consumer, cambiando los valores compañía y sector

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/consumer/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void updatePositive(final int recordIndex, final String company, final String sector) {
		super.signIn("manager", "manager");
		
		super.clickOnMenu("Account", "Become a consumer");

		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", sector);
		

		super.clickOnSubmitButton("Register");
		
		super.clickOnMenu("Account", "Consumer data");

		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", sector);
		
		super.clickOnSubmitButton("Update");
		
		super.clickOnMenu("Account", "Consumer data");
		
		super.checkInputBoxHasValue("company", company);
		super.checkInputBoxHasValue("sector", sector);
		
		super.signOut();
	}
	/* 3 casos positivos: uno con todos los campos vacíos, otro comprobando que únicamente el campo compañía no esté vacía y el último comprobando
	 que únicamente el campo sector no esté vacío*/
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/consumer/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void updateNegative(final int recordIndex, final String company, final String sector) {
		super.signIn("manager", "manager");

		super.clickOnMenu("Account", "Consumer data");

		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", sector);

		
		super.clickOnSubmitButton("Update");

		super.checkErrorsExist();

		super.signOut();
	}
	
	// Ancillary methods ------------------------------------------------------
	
}
