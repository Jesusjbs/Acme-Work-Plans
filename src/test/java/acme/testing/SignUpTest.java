/*
 * SignUpTest.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class SignUpTest extends AcmePlannerTest{

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	// Caso positivo en el que se registra un usuario correctamente. Todos los campos
	// tienen el valor tester, excepto el correo que debe ser tester@gmail.com. Al
	// no existir errores en ningún campo, el usuario queda registrado correctamente
	// y se comprueba que ha sido redirigido a la pantalla de inicio
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/userAccount/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String username, final String password, final String confirmation, final String name, final String surname, final String email, final Boolean accept) {		
		super.signUp(username, password, confirmation, name, surname, email, accept);
		super.checkSimplePath("/master/welcome");
	}
	
	// Se prueban 22 casos negativos: usuario duplicado ; username vacío ; password vacía ; confirmation vacía ;
	// name vacío ; surname vacío ; email vacío ; password demasiado corta ; password demasiado larga ;
	// confirmation no se corresponde con contraseña ; email no válido. En cada prueba se comprueba que no se
	// ha registrado correctamente, comprobando que existen errores en el formulario, comprobar que el campo username tiene un tamaño menor a 5 
	// y que no sea mayor a 60, se ha comprobado todas las posibles combinaciones dejando todos los parámetros vacíos excepto uno y finalamente
	// se ha comprobado que el campo de aceptar las condiciones esté seleccionado.
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/userAccount/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createNegative(final int recordIndex, final String username, final String password, final String confirmation, final String name, final String surname, final String email,final Boolean accept) {
		super.signUp(username, password, confirmation, name, surname, email, accept);
		super.checkErrorsExist();
	}
}
