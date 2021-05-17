/*
 * EmployerApplicationUpdateTest.java
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

public class ManagerWorkPlanDeleteTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	//Aquí hemos comprobado simplemente si al pulsar el botón de borrar en un workload, es borrado correctamente
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/delete.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void delete(final int recordIndex) {		
		super.signIn("manager2", "manager2");
		
		super.clickOnMenu("Manager", "List workplans");		
		super.clickOnListingRecord(recordIndex);
		super.clickOnSubmitButton("Delete workplan");
		
		super.signOut();
	}

}
