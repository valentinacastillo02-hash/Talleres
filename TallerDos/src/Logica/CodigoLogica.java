package Logica;

 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CodigoLogica {
	
		
	public static void main(String[] args ) throws FileNotFoundException {
		ArrayList<String>Username = new ArrayList<>();
		ArrayList<String>Contraseña = new ArrayList<>();
		ArrayList<String>Rol = new ArrayList<>();
		
		System.out.println("1)Menú Admin");
		System.out.println("2)Menú Usuario");
		
		
		
		Scanner scanner = new Scanner(System.in);
		
		int tipoMenu=scanner.nextInt();
		
		while (tipoMenu != 1 && tipoMenu !=2) {
			System.out.println("Opcion no valida, ingrese el dato de nuevo");
			
			tipoMenu=scanner.nextInt();
		}
		
		
		
		if (tipoMenu == 1) {
		
		
		System.out.println("Menú Admin");
		System.out.println("1)Ver lista completa de PCs junto a su información:");
		System.out.println("2)Agregar o eliminar un PC de la lista:");
		System.out.println("3)Clasificar PCs según su nivel de riesgo:");
		System.out.println("4)Salir de este menú");
		int mEnuAdmin=scanner.nextInt();
		
		if (mEnuAdmin == 4) {
			
		}
		
		
		
		while (mEnuAdmin <= 0 || mEnuAdmin >4) {
			System.out.println("Opcion no valida, ingrese el dato de nuevo");
			System.out.println("Menú Admin");
			System.out.println("1)Ver lista completa de PCs junto a su información:");
			System.out.println("2)Agregar o eliminar un PC de la lista:");
			System.out.println("3)Clasificar PCs según su nivel de riesgo:");
			System.out.println("4)Salir de este menú");
			mEnuAdmin=scanner.nextInt();
			
		
		}
		
		}
		
		else {
			if (tipoMenu == 2) {
				System.out.println("1)Menú Usuario: ");
				System.out.println("2)Ver lista de PCs: ");
				System.out.println("3)Escanear un PC: ");
				System.out.println("4)Ver total de puertos abiertos en todos los PCs de la red: ");
				System.out.println("5)Ordenar PCs según IP:");

			}
		}
		leer_archivo("usuarios.txt",Username,Contraseña,Rol);
		
		
		
			
		//Scanner Usuario = new Scanner(System.in);
	
		
		scanner.close();
	}

	private static void leer_archivo(String string, ArrayList<String> username, ArrayList<String> contraseña,
			ArrayList<String> rol) throws FileNotFoundException {
		// TODO Auto-generated method stub
		File file = new File(string);
		Scanner s = new Scanner(file);
		
		while (s.hasNextLine()) {
			String linea =s.nextLine();
			System.out.println(linea);
			String [] Partes = linea.split(";");
			
			username.add(Partes[0]);
			contraseña.add(Partes[1]);
			rol.add(Partes[2]);
			
		
			
			
			
			}
			s.close();
		
	}


}