package Dominio;

public class vulnerabilidades {
	private  String Puerto;
	private String Nombre;
	private String Descripción;
	
	public vulnerabilidades(String Puerto,String Nombre, String Descripción) {
		this.setPuerto(Puerto);
		this.setNombre(Nombre);
		this.setDescripción(Descripción);
	}

	public String getPuerto() {
		return Puerto;
	}

	public void setPuerto(String puerto) {
		Puerto = puerto;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getDescripción() {
		return Descripción;
	}

	public void setDescripción(String descripción) {
		Descripción = descripción;
	}
	

}
