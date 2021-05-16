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

package acme.testing.manager.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerWorkPlanCreateTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String title, final String beginning, final String ending, final String privacy) {

		super.signIn("manager", "manager");
		
		super.clickOnMenu("Manager", "Create workplan");

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("beginning", beginning);
		super.fillInputBoxIn("ending", ending);
		super.fillInputBoxIn("privacy", privacy);
		
		super.clickOnSubmitButton("Create");

		super.clickOnMenu("Manager", "List workplans");
	
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, beginning);
		super.checkColumnHasValue(recordIndex, 2, ending);
		//No compruebo el campo workload debido a que es un campo autogenerado y no cabe posibilidad a fallo
		super.checkColumnHasValue(recordIndex, 4, privacy);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("beginning", beginning);
		super.checkInputBoxHasValue("ending", ending);

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createNegative(final int recordIndex, final String title, final String beginning, final String ending, final String privacy) {
		
		super.signIn("manager", "manager");
		
		super.clickOnMenu("Manager", "Create workplan");

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("beginning", beginning);
		super.fillInputBoxIn("ending", ending);
		super.fillInputBoxIn("privacy", privacy);
		
		super.clickOnSubmitButton("Create");

		super.checkErrorsExist();

		super.signOut();
	}

	// Ancillary methods ------------------------------------------------------

}
