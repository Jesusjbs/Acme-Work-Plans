/*
 * EmployerJobCreateTest.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing.administrator.spam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamCreateTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

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
