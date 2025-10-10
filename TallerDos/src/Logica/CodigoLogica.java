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
		
		File file = new File("usuarios.txt");
		Scanner s = new Scanner(file);
		
		while (s.hasNextLine()) {
			String linea =s.nextLine();
			System.out.println(linea);
			String [] Partes = linea.split(";");
			
			Username.add(Partes[0]);
			Contraseña.add(Partes[1]);
			Rol.add(Partes[2]);
			
		
			
			
			
			}
			s.close();
			
		//Scanner Usuario = new Scanner(System.in);
	
		}
	}


