//Emiliano Angel Toro Rojas, RUT: 21.512.702-8, Carrera: Ingenieria en tecnologias de información.
//Valentina Castillo,Rut; 15.166.692-2, Carrera: Ingenieria en tecnologias de información.


package Dominio;

public class Usuarioscodigo {
    private String username;
    private String contrasenaHash;
    private String rol;

    public Usuarioscodigo(String username, String contrasenaHash, String rol) {
        this.username = username;
        this.contrasenaHash = contrasenaHash;
        this.rol = rol;
    }

    public String getUsername() { 
    	return username; 
    	}
    public String getContrasenaHash() { 
    	return contrasenaHash; 
    	}
    public String getRol() { 
    	return rol; 
    	}
    public boolean esAdmin() { 
    	return rol.equalsIgnoreCase("ADMIN"); 
    	}
    public boolean esUser() { 
    	return rol.equalsIgnoreCase("USER"); 
    	}
}
