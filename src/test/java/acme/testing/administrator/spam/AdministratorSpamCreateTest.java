package acme.testing.administrator.spam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeWorkplansTest;

public class AdministratorSpamCreateTest extends AcmeWorkplansTest {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	/* 4 casos: enviar sin introducir nuevas palabras de spam (campo vacío), enviar con el threshold mínimo (1.00),
	 enviar con el theshold máximo (100.00) y enviar con varias palabras nuevas en spam */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spam/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String threshold, final String wordSpam) {
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "Spam");

		super.fillInputBoxIn("threshold", threshold);
		super.fillInputBoxIn("wordSpam", wordSpam);
		

		super.clickOnSubmitButton("Update");

		super.clickOnMenu("Administrator", "Spam");
	
		super.checkInputBoxHasValue("threshold", threshold);
		super.checkInputBoxHasValue("wordSpam", wordSpam);
		
		super.signOut();
	}
	
	/* 5 casos: enviar campos vacíos (falla por threshold), enviar threshold vacío, enviar threshold superando
	 el máximo de 100.00, enviar threshold menor al mínimo aceptado (1.00), enviar una palabra como threshold */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spam/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createNegative(final int recordIndex, final String threshold, final String wordSpam) {
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "Spam");

		super.fillInputBoxIn("threshold", threshold);
		super.fillInputBoxIn("wordSpam", wordSpam);
		

		super.clickOnSubmitButton("Update");

		super.checkErrorsExist();

		super.signOut();
	}

	// Ancillary methods ------------------------------------------------------

}
