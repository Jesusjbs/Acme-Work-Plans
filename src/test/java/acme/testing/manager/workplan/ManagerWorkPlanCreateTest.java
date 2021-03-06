
package acme.testing.manager.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeWorkplansTest;

public class ManagerWorkPlanCreateTest extends AcmeWorkplansTest {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	//Se comprueba la creación de un workplan con los valores que están en el fichero create-positive.csv
	//Una vez  que creamos el workplan con los datos dados, nos dirigimos al listado de workplan, y podemos ver que ahí se encuentra el nuevo workplan
	//creado, luego nos metemos dentro de esta y vemos que los datos coinciden con los que se habían creado.
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
	
	//Comprobamos todos los input del formulario de crear workplan (create-negative.csv).
	//14 casos: todos los input vacíos, solo el campo title relleno, solo el campo beginning relleno, solo el campo ending relleno,
	//solo el campo ending vacío, solo el campo begining vacíco, solo el campo title vacío, 
	//fecha fin anterior a fecha inicio, fecha de inicio igual a la de fin, fecha fin anterior a hoy, fecha inicio anterior a hoy y 
	//en el título introducir palabras spam (mayor al threshold), comprobación que el título sea mayor a 80
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
