package com.wallet.principal;  // Paquete que agrupa y organiza las clases principales de la app
import java.util.Scanner;      // Permite capturar las entradas del usuario por consola
import java.util.Locale;       // Se importa para dar formato a los números y monedas


public class Principal {

	public static void main(String[] args) {
		
		//1. Creación de Scanner para que el usuario ingrese sus datos
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("=== BIENVENIDO A ALKE WALLET ===");
		System.out.println("Para comenzar, vamos a crear tu cuenta digital.");
		
		// 2. Creamos cuenta con los datos iniciales
		System.out.print("Ingrese un número de identificación para su cuenta: ");
		String idCuenta = teclado.next();
		
		System.out.print("Ingrese el monto de su depósito inicial ($): ");
		double saldoInicial = teclado.nextDouble();
		
		// Se crea la cuenta digital del usuario
		Cuenta miCuenta = new Cuenta (idCuenta, saldoInicial, "CLP");
		
		System.out.println("\n¡Cuenta creada con éxito para el usuario " +  idCuenta + "!");
		
		// Solo muestra la respuesta del case 1. Evita que aparezca el menú al mismo tiempo
		System.out.print("\nPresione ENTER para ver el menú de opciones");
		
		// Doble ENTER para mostrar nuevamente el menú
		teclado.nextLine();
		teclado.nextLine();		
		
		// Variables de control para el menú de opciones
		int opcionSeleccionada = 0;
		
		// Creacion de conversor de moneda
		ConversorBancario convertir = new ConversorBancario();
		
		// 3. Usamos un bucle do-while, se repite hasta que se elija la opcion 5
		
		do {
			// Se muestran las opciones del menú en la pantalla
			System.out.println("\n ----- ALKE WALLET -----");
			System.out.println("¡Tu billetera de confianza!");
			System.out.println("\n 1. Ver saldo");
			System.out.println(" 2. Realizar ingresos");
			System.out.println(" 3. Realizar retiros");
			System.out.println(" 4. Convertir moneda");
			System.out.println(" 5. Salir");
			System.out.print("\n Por favor, selecciona una opción (1 - 5): ");
			
			// Esperamos que el usuario seleccione una opción y presione ENTER
			opcionSeleccionada = teclado.nextInt();
			
			// 4. Se usa Switch para evaluar las opciones
			switch (opcionSeleccionada) {
			
			case 1: // Muestra el saldo actaul
				
				// Recupera el saldo disponible y lo imprime con formato regional
				double saldoActual = miCuenta.verSaldo();
				System.out.printf(Locale.GERMANY, "\nSu saldo actual es: $%,.0f CLP.\n", saldoActual);
				
				// Solicita presionar ENTER para pausar pantalla antes de mostrar menú
				System.out.print("\nPresione ENTER para ver el menú de opciones");
				
				// Doble ENTER para mostrar nuevamente el menú
				teclado.nextLine();
				teclado.nextLine();
				break;
				
			case 2: // Realizar ingreso de dinero a la cuenta
				
				// Creación de una variable para controlar el bucle while 
				boolean montoValido = false;
				
				// El bucle se repetirá hasta que se ingrese un monto válido
				while (montoValido == false) {
					
					// Se pide al usuario que ingrese el monto a cargar y se guarda en la variable
					System.out.print("\nIngrese el monto a cargar ($): ");	
					// Se usa string por si ingresa texto. Se lee como texto simple
					String montoIng = teclado.next();
					
				
					// Valida si son solo números
					if (!montoIng.matches("\\d+(\\.\\d+)?")) {
					System.out.println("\nError: El monto ingresado debe ser numérico.");
					
					} else {
					// Si es válido, se transforman de texto a números y se ingresa a la cuenta
					double montoAIngresar = Double.parseDouble(montoIng);
					miCuenta.realizarIngreso(montoAIngresar);
					
					// Rompe el bucle while al obtener un ingreso válido
					montoValido = true;
				}
			}
				
				// Pausa entre la respuesta y el menú para no llenar la consola
				System.out.print("\nPresione ENTER para ver el menú de opciones");
				
				// Doble ENTER para mostrar nuevamente el menú
				teclado.nextLine();
				teclado.nextLine();
				break;
				
			case 3: // Realizar retiro de dinero de la cuenta
				
				boolean montoValidoR = false;
				while (montoValidoR == false) {	
					// Se pide al usuario monto a retirar y se guarda en variable
					System.out.print("\nIngrese el monto a retirar ($): ");
					String montoIngre = teclado.next();
				
					// Valida si son solo números
					if (!montoIngre.matches("\\d+(\\.\\d+)?")) {
					System.out.println("\nError: El monto ingresado debe ser numérico.");
					
					} else {
						// Se transforman de texto a números
						double montoARetirar = Double.parseDouble(montoIngre);
				
							// Valida si el monto a retirar es mayor o igual al saldo actual para proceder o negar la solicitud
							if (miCuenta.realizarRetiro(montoARetirar)) {
					
							} else {
								System.out.println("Intente con un monto menor.");	
							}	
							
						 montoValidoR = true;
					}
				}
				
				System.out.print("\nPresione ENTER para ver el menú de opciones");
				
				teclado.nextLine();
				teclado.nextLine();
				break;
				
			case 4: // Conversor del saldo de la cuenta de CLP a USD o EUR
				
				// Se toma el saldo actual de la cuenta
				double saldoActualParaConvertir = miCuenta.verSaldo();
				
				boolean monedaValidaC = false;
				while (monedaValidaC == false) {
					
					// Se pide al usuario la moneda de destino
					System.out.print("\nA qué moneda desea convertir su saldo actual? (USD / EUR): ");
					String monedaDestino = teclado.next().toUpperCase();
					
					// Se envía en saldo actual para que haga la división
					double saldoConvertido = convertir.convertir(saldoActualParaConvertir, monedaDestino);
					
					// Si devuelve -1 la moneda no existe. Repite el bucle
					if (saldoConvertido == -1.0) {
						System.out.println("\nError: La moneda no es válida. Intente nuevamente");
					
					} else {				
						// Si es correcto, se muestra el resultado final con solo 2 decimales y en mayusculas para el tipo de moneda
						System.out.printf(Locale.GERMANY, "\nSu saldo de $%,.0f CLP equivale actualmente a: %,.2f %s\n", 
												saldoActualParaConvertir, saldoConvertido, monedaDestino);
						// Se rompe el bucle
						monedaValidaC = true;
					}
				}	
				
				System.out.print("\nPresione ENTER para ver el menú de opciones");
				teclado.nextLine();
				teclado.nextLine();
				break;
				
				
			case 5: // Opción salir del sistema
				System.out.println("\n¡Gracias por utilizar Alke Wallet!");
				System.out.print("\nPresione ENTER para ver el menú de opciones");
				
				teclado.nextLine();
				teclado.nextLine();
				break;
			
			default: // Valida que se marquen numeros del 1 al 5 
				System.out.println("\nOpción no válida. Por favor, marque del 1 al 5.");
				System.out.print("\nPresione ENTER para ver el menú de opciones");
				
				teclado.nextLine();
				teclado.nextLine();
				break;	
		}
			
		// Mantiene el menú activo hasta que el usuario decida salir (opción 5)
		} while (opcionSeleccionada != 5); 
		
		// Se cierra el scanner
		teclado.close();
		
	}

}
