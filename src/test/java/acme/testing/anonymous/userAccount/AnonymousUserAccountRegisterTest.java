package acme.testing.anonymous.userAccount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousUserAccountRegisterTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	// Caso positivo en el que se registra un usuario correctamente. Todos los campos
	// tienen el valor tester, excepto el correo que debe ser tester@gmail.com. Al
	// no existir errores en ningún campo, el usuario queda registrado correctamente
	// y se comprueba que ha sido redirigido a la pantalla de inicio
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/userAccount/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String username, final String password, final String confirmation, final String name, final String surname, final String email) {		
		super.signUp(username, password, confirmation, name, surname, email);
		super.checkSimplePath("/master/welcome");
	}
	
	// Se prueban 11 casos negativos: usuario duplicado ; username vacío ; password vacía ; confirmation vacía ;
	// name vacío ; surname vacío ; email vacío ; password demasiado corta ; password demasiado larga ;
	// confirmation no se corresponde con contraseña ; email no válido. En cada prueba se comprueba que no se
	// ha registrado correctamente, comprobando que existen errores en el formulario
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/userAccount/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createNegative(final int recordIndex, final String username, final String password, final String confirmation, final String name, final String surname, final String email) {
		super.signUp(username, password, confirmation, name, surname, email);
		super.checkErrorsExist();
	}
}
