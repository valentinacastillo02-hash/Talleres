package Dominio;

public class puertos {
	private String ID;
	private double numero;
	private String Estado;
	
	public puertos(String ID,double numero,String Estado) {
		this.setID(ID);
		this.setNumero(numero);
		this.setEstado(Estado);
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public double getNumero() {
		return numero;
	}

	public void setNumero(double numero) {
		this.numero = numero;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}
	

}
