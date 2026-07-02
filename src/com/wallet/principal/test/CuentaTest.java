package com.wallet.principal.test;

// Importación de las aserciones estáticas de JUnit 5 para validar los resultados
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

// Importación de las anotaciones del ciclo de vida y entorno de ejecución de JUnit 5
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Importaión de la clase Cuenta del paquete de producción para ser testeada
import com.wallet.principal.Cuenta;

public class CuentaTest {
	
		// Atributo de referencia para la clase bajo prueba (Sustem Under Test)
		private Cuenta cuenta;

		// BeforeEach se ejecuta antes de cada test para inicializar un estado limpio
		@BeforeEach
		public void configuracionInic() {
				// Arrange (Organizar): Se instancia la cuenta con un saldo de 30.000 CLP
				cuenta = new Cuenta("CLIENTE-123", 30000.0, "CLP");
	}
	
		// Se ejecuta después de cada test para liberar recuros del entrono de pruebas
		@AfterEach
		public void limpiezaFinal() {
				// Deja la referencia de la cuenta vacía para liberar memoria RAM
				cuenta = null;
		
				System.out.println("Test finalizado y objeto Cuenta destruido de la memoria.");
	}
	
	@Test
	public void testVerSaldo() {
			// Act (Actuar): Invoca el método consultor de la clase Cuenta
			double saldoReal = cuenta.verSaldo();
		
			// Assert (Afirmar): Valida que el saldo obtenido coincida exactamente con el esperado
			assertEquals(30000.0, saldoReal, "El saldo inicial no coincide con el registrado");
	}
	
	@Test
	public void testRealizarIngresoOk() {
			// Act: Se ejecuta un abono de monto válido por 10.000 CLP
			cuenta.realizarIngreso(10000.0);
		
			// Assert: Verifica que el saldo final se incremente de forma excata a 40.000 CLP 
			assertEquals(40000.0, cuenta.verSaldo(), "El monto ingresado no se sumó correctamente.");
		
	}
	
	@Test
	public void testRealizarRetiroConFondos() {
			// Act: Se ejecuta un cargo de monto menor al saldo disponible (15.000 CLP)
			boolean resultadoRetiro = cuenta.realizarRetiro(15000.0);
		
			// Assert: El método debe devolver true y dejar el saldo en 15.000 CLP
			assertTrue(resultadoRetiro, "El retiro debió haber sido aprobado.");
			assertEquals(15000.0, cuenta.verSaldo(), "El saldo restante después del retiro es incorrecto.");
		
	}
	
	@Test
	public void testRealizarRetiroSinFondos() {
			// Act: Se intenta retirar un monto que excede los fondos disponobles (50.000 CLP)
			boolean resultadoRetiro = cuenta.realizarRetiro(50000.0);
		
			// Assert: Comprueba que la operación sea rechazada (false) y el saldo permanezca intacto
			assertFalse(resultadoRetiro, "El retiro debió haber sido rechazado por fondos insuficintes.");
			assertEquals(30000.0, cuenta.verSaldo(), "El saldo cambió a pesar de haber fallado la operación.");
	}

}


