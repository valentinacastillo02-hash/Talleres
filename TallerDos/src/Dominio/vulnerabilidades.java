//Emiliano Angel Toro Rojas, RUT: 21.512.702-8, Carrera: Ingenieria en tecnologias de información.
//Valentina Castillo,Rut; 15.166.692-2, Carrera: Ingenieria en tecnologias de información.


package desafio2_pack;

public class vulnerabilidades {
    private int puerto;
    private String nombre;
    private String descripcion;

    public vulnerabilidades(int puerto, String nombre, String descripcion) {
        this.puerto = puerto;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getPuerto() { 
    	return puerto; 
    	}
    public String getNombre() { 
    	return nombre; 
    	}
    public String getDescripcion() { 
    	return descripcion; 
    	}
}
