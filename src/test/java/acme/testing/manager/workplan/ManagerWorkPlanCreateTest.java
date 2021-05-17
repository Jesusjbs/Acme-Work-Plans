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

	//Este es un test positivo, y se comprueba la creación de un workplan con los valores que están en el fichero create-positive.cs
	//Una vez que pulsamos al botón nos dirigimos al listado de workplan, y podemos ver que ahí se encuentra la nueva creada y nos metemos 
	//dentro de esta que se ha creado y vemos que los datos coinciden con los que se habían creado, por tanto, estamos seguro que se ha creado correctamente.
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
	
	//Test negativo, en el que comprobamos todos los input del formulario de crear workplan (create-negative.csv).
	//Realizamos test como, todos los input vacíos, solo un campo vacío en las diferentes posiciones, fehcas incorrectas, 
	//tales como, tanto fecha de fin como de inicio anteiores ha hoy, solo una de ella, fecha fin antes a la de inicio...,
	//palabras spam en campo de texto y las diferentes variantes
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
