package acme.testing.authenticated.manager;

import org.junit.jupiter.api.Test;

import acme.testing.AcmePlannerTest;

public class AuthenticatedBecomeManagerTest extends AcmePlannerTest {
	
	// Probamos a convertirnos en manager como usuario autenticado. El único usuario
	// que ya existe, no es manager y que está autenticado es el administrador, por lo
	// que lo usaremos para este caso. Iniciamos sesión y pulsamos en los dos botones
	// necesarios para convertirse correctamente.
	@Test
	public void becomeManager() {		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Account", "Become a manager");
		super.clickOnSubmitButton("Register");
		
		super.signOut();
	}
}
