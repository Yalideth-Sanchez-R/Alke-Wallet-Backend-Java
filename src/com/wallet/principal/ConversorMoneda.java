package com.wallet.principal;

// Interfaz que establece el contrato técnico para cualquier módulo conversor de divisas
public interface ConversorMoneda {
	
		// Define los parámetros de entrada obligatorios y el tipo de dato que se retorna
		double convertir(double monto, String monedaDestino);
		
}

