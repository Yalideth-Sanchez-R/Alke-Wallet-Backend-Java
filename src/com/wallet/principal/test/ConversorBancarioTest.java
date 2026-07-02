package com.wallet.principal.test;

// Importación de aserciones y anotaciones para la ejecución de pruebas con JUnit 5
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Importación de la clase bajo prueba desde el paquete principal de producción
import com.wallet.principal.ConversorBancario;

public class ConversorBancarioTest {
	
		// Atributos de referencia para el componente conversor
		private ConversorBancario conversor;
	
		// BeforeEach para instanciar un entorno de conversión limpio antes de cada test
		@BeforeEach
		public void configuracionInicial() {
				conversor = new ConversorBancario();
		}
	
	@Test
	public void testConvertirAUSD() {
			// Act: 92.593 CLP dividido en la tasa fija de 925.93 debe dar 100 USD
			double resultadoUSD = conversor.convertir(92593.0, "USD");
		
			// Assert: Se usa un margen de error de 0.01 por los valores tipo double
			assertEquals(100.0, resultadoUSD, 0.01, "La regla de cálculo para USD falló");	
	}
	
	@Test
	public void testConvertirAEUR() {
			// Act: 105.114 CLP dividido en la tasa fija de 1051.14 debe dar 100 EUR
			double resultadoEUR = conversor.convertir(105114.0, "EUR");
		
			// Assert: Valida la exactitud del código matemático de la conversión a Euros
			assertEquals(100.0, resultadoEUR, 0.01, "La regla de cálculo para EUR falló");
	}

	@Test
	public void testConvertirMonedaInvalida() {
			// Act: Evalúa el comportamiento si el usuario ingresa un código de divisa no soportado
			double resultadoInvalido = conversor.convertir(5000.0, "XYZ");
		
			// Assert: Comprueba que el sistema responda con el valor numérico de control de error (-1.0)
			assertEquals(-1.0, resultadoInvalido, "Debería retornar -1.0 al recibir una moneda desconocida.");
	}
	
}
