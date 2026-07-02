package com.wallet.principal;

// Clase que implementa el contrato de la interfaz ConverMoneda para la conversión de divisas
public class ConversorBancario implements ConversorMoneda {
	
		// Constantes de instacia protegidas (private final) con las tasas de cambio de referencia
		private final double tasaDolar = 925.93;
		private final double tasaEuro = 1051.14;	
	
		@Override
		public double convertir(double monto, String monedaDestino) {
		
			// Condición 1: Procesa la conversión si la divisa destino es el Dólar Estadounidense (USD) 
			// Se usa 'equalsIgnoreCase' para tolerar entradas tanto en mayúsculas como en minúsculas
			if (monedaDestino.equalsIgnoreCase("USD")) {
					return monto / tasaDolar; 
			}
		
			// Condición 2: Procesa la conversión si la divisa destino es el Euro (EUR)
			else if (monedaDestino.equalsIgnoreCase("EUR")) {
					return monto / tasaEuro;
			}
		
			// Condición de resguardo: Se ejecuta si el tipo de moneda ingresada no es ninguna de las anteriores
			else {
					return -1.0; 
					// Devuelve el valor de control negativo para alertar un error al flujo principal
		}
	
	}

}
