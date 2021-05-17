package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskDeleteTest extends AcmePlannerTest {

	/*Este test prueba la eliminación satisfactoria de una tarea, utilizando los datos del fichero delete.csv
	Una vez se autentica el usuario como manager, intenta eliminar una tarea. Una vez se elimina, se muestra el listado de tareas.
	Se prueban 1 caso, el cual resulta exitoso. */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/delete.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(40)
	public void delete(final int recordIndex) {
		super.signIn("manager2", "manager2");
		
		super.clickOnMenu("Manager", "List tasks");
		
		super.clickOnListingRecord(recordIndex);
		
		super.clickOnSubmitButton("Delete");
		
		super.signOut();
	}
}
