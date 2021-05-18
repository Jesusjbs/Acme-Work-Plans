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

package acme.testing.authenticated.provider;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedProviderCreateTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

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
