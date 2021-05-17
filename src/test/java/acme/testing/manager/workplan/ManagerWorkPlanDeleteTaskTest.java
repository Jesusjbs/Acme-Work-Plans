package acme.testing.manager.workplan;

import org.junit.jupiter.api.Test;

import acme.testing.AcmePlannerTest;

public class ManagerWorkPlanDeleteTaskTest extends AcmePlannerTest {

	// Se prueba a eliminar una tarea asociada a un workplan determinado. En este
	// caso solo se prueba con borrar la primera tarea del workplan que aparece
	// primero en el listado, ya que no hay caso negativo y en cualquier otro 
	// workplan o tarea, la acción se ejecutaría de la misma forma.
	@Test
	public void delete() {		
		super.signIn("manager2", "manager2");
		
		super.clickOnMenu("Manager", "List workplans");		
		super.clickOnListingRecord(0);
		super.clickOnSubmitButton("Delete");
		super.checkSimplePath("/management/workplan/list");
		
		super.signOut();
	}
	
}
