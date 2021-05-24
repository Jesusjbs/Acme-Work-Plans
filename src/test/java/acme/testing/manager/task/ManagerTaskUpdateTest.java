package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskUpdateTest extends AcmePlannerTest {
	
	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------

	/*Este test prueba la actualización satisfactoria de una tarea, utilizando los datos del fichero updatePositive.csv
	Una vez se autentica el usuario como manager, intenta editar una tarea. Una vez se edita, se muestran los detalles de esta.
	Se prueban 3 casos, los cuales resultan exitosos. En el primero se actualiza el título y se pone privado. 
	En el segundo se actualiza el título y el workload. En el tercero se actualiza el título, las fechas y el workload. */

	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/updatePositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(50)
	public void updatePositive(final int recordIndex, final String title, final String beginning, final String ending, 
		final String workload, final String description, final String link, final String privacy) {
	
		super.signIn("manager2", "manager2");
		
		super.clickOnMenu("Manager", "List tasks");
		
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("beginning", beginning);
		super.fillInputBoxIn("ending", ending);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("privacy", privacy);
		
		super.clickOnSubmitButton("Update");
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("beginning", beginning);
		super.checkInputBoxHasValue("ending", ending);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("link", link);
		
		super.signOut();
	}
	
	/*Este test prueba la actualización errónea de una tarea, utilizando los datos del fichero updateNegative.csv
	Una vez se autentica el usuario como manager, intenta editar una tarea. Una vez falla, se muestran los errores de esta.
	Se prueban 17 casos. Caso 1: el formulario se envía vacío. Caso 2: el formulario se envía con el campo título vacío. 
	Caso 3: el formulario se envía con el campo inicio vacío. Caso 4: el formulario se envía con el campo fin vacío.
	Caso 5: el formulario se envía con el campo workload vacío. Caso 6: el formulario se envía con el campo descripción vacío.
	Caso 7: el formulario se envía con el campo enlace incorrecto. Caso 8: el título es considerado spam. Caso 9: el título y 
	la descripción son considerados spam. Caso 10: la descripción es spam. Caso 11: el workload es negativo. Caso 12: el workload
	no es válido. Caso 13: la fecha de fin es anterior a la de inicio. Caso 14: la hora de fin es anterior a la de inicio.
	Caso 15: la fecha de fin y de inicio son iguales. Caso 16: el workplan sobrepasa la duración de la tarea. 
	Caso 17: valor de inicio no válido(string) y finalmente que el título sea mayor a 80 y la descripción mayor a 500 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/updateNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(60)
	public void updateNegative(final int recordIndex, final String title, final String beginning, final String ending, 
		final String workload, final String description, final String link, final String privacy) {
		
		super.signIn("manager2", "manager2");
		
		super.clickOnMenu("Manager", "List tasks");
		
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("beginning", beginning);
		super.fillInputBoxIn("ending", ending);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("privacy", privacy);
		
		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist();
		
		super.signOut();
	}
	
	// Ancillary methods ------------------------------------------------------
	
}
