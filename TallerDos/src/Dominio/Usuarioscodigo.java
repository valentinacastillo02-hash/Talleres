package Dominio;


public class Usuarioscodigo {
	private String Username;
	private String contrasena;
	private String rol;
	
	public Usuarioscodigo(String Username,String contrasena,String rol) {
		this.setUsername(Username);
		this.setContrasena(contrasena);
		this.setRol(rol);
		
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	
	
}