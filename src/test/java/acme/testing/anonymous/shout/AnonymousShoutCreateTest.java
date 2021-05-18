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

package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutCreateTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	/* 4 casos positivos: uno con datos iniciales, otro comprobando que el texto no se considera spam con 11 palabras (1 de ellas es spam),
	 otro comprobando que el autor no se considera spam con 11 palabras (1 de ellas es spam) y otro enviando info vacío */
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String author, final String text, final String info) {


		super.clickOnMenu("Anonymous", "Shout!");

		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		

		super.clickOnSubmitButton("Shout!");

		super.clickOnMenu("Anonymous", "List recent shouts");
	
		//No compruebo el campo moment debido a que es un campo autogenerado y no cabe posibilidad a fallo
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, text);

	}
	
	/* 15 casos negativos: enviar todo vacío, enviar solo en autor vacío, enviar solo el texto vacío, enviar autor y texto vacíos
	 enviar autor e info vacíos, enviar texto e info vacíos, enviar una cadena que no es una URL, enviar un número en vez de una URL
	 enviar un autor con menos de 5 caracteres, enviar un autor con más de 25 caracteres, enviar un texto con más de 100 caracteres,
	 enviar un texto con 10 palabras (1 de ellas es spam), enviar un texto con menos de 10 palabras (1 de ellas es spam), enviar un
	 autor con menos de 10 palabras (1 de ellas es spam), enviar un autor con 10 palabras (1 de ellas es spam) */
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createNegative(final int recordIndex, final String author, final String text, final String info) {

		super.clickOnMenu("Anonymous", "Shout!");

		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		
		super.clickOnSubmitButton("Shout!");

		super.checkErrorsExist();

	}

	// Ancillary methods ------------------------------------------------------

}
