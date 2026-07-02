package com.wallet.principal;
import java.util.Locale;


public class Cuenta {
	
	// Atributos privados de la clase para implementar encapculamiento de datos
	private String idCuenta;
	private double saldoActual;
	private String moneda;
	
	// Constructor para instanciar (invocar) la cuenta con sus vaores iniciales desde la clase Principal
	public Cuenta(String idCuenta, double saldoActual, String moneda) {
		
			// Inicialización de atributos utilizando 'this' para diferenciar los parámetros
			this.idCuenta = idCuenta;
			this.saldoActual = saldoActual;
			this.moneda = moneda;
		
	} 
	
	// --- Métodos de comportamiento (Lógica de Negocio)---:
		
	// 1. Método consultor para visualizar el saldo actual disponible
	public double verSaldo() {
		return this.saldoActual;	
	}
	
	// 2. Método mutador para realizar ingresos de dinero	
	public void realizarIngreso(double montoAIngresar) {
			this.saldoActual = this.saldoActual + montoAIngresar;
			// Se usa %s para dar formato al ID, Locale.Germany para formatear el monto con separador de miles, ej: 1.000
			System.out.printf(Locale.GERMANY, "\n¡Ingreso exitoso en tu cuenta %s! Se han ingresado $%,.0f %s.\n", 
				          this.idCuenta, montoAIngresar, this.moneda);
	}
	
	// 3. Método mutador para realizar retiros de dinero
	// Devolverá true si la transacción fue aprobada o false si no hay fondos suficientes
	public boolean realizarRetiro(double montoARetirar) {
		
			// Valida si el saldo actual en cuenta es suficiente para cubrir el monto solicitado
			if (this.saldoActual >= montoARetirar) {
			
					// Si el saldo es suficiente, efectúa el cobro descontándolo del saldo actual 
					this.saldoActual = this.saldoActual - montoARetirar; 
			
					// Imprime notificación de éxito para el usuario final
					System.out.printf(Locale.GERMANY, "\n¡Retiro exitoso! Se han retirado $%,.0f %s.\n", montoARetirar, this.moneda);
					
					return true; // Retiro aprobado exitosamente
			
			} else {
					// Bloque de resguardo en caso de fondos insuficientes
					System.out.println("\nError: Fondos insuficientes para realizar el retiro. ");
					return false; // Retiro rechazado
		}
		
	}
		
}
