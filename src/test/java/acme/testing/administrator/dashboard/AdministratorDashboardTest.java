package acme.testing.administrator.dashboard;

import org.junit.jupiter.api.Test;

import acme.testing.AcmePlannerTest;

public class AdministratorDashboardTest extends AcmePlannerTest {
	
		// Lifecycle management ---------------------------------------------------
	
		// Test cases -------------------------------------------------------------
		
		// Caso 1: listar el dashboard con los datos de ejemplo, donde se visualizan
		// datos ya existentes de tareas y workplans. Además aparece la gráfica 
		// también con datos.
		@Test
		public void dashboard() {
			super.signIn("administrator", "administrator");
			
			super.clickOnMenu("Administrator", "Dashboard");
			
			super.signOut();
		}
		
		// Caso 2: listar el dashboard sin datos de ejemplo, donde se visualizan
		// todas las estadísticas a 0. Además aparece la gráfica también vacía.
		@Test
		public void emptyDashboard() {
			this.signIn("administrator", "administrator");
			
			super.clickOnMenu("Administrator", "Populate DB (initial)");
			super.clickOnMenu("Administrator", "Dashboard");
			
			super.signOut();
		}
		
		// Ancillary methods ------------------------------------------------------

}
