//Emiliano Angel Toro Rojas, RUT: 21.512.702-8, Carrera: Ingenieria en tecnologias de información.
//Valentina Castillo,Rut; 15.166.692-2, Carrera: Ingenieria en tecnologias de información.

package Dominio;

public class puertos {
    private int numero;
    private boolean abierto;
    private vulnerabilidades vulnerabilidad;

    public puertos(int numero, boolean abierto) {
        this.numero = numero;
        this.abierto = abierto;
        this.vulnerabilidad = null;
    }

    public int getNumero() { 
    	return numero; 
    	}
    public boolean isAbierto() { 
    	return abierto; 
    	}
    public vulnerabilidades getVulnerabilidad() { 
    	return vulnerabilidad; 
    	}
    public void setVulnerabilidad(vulnerabilidades v) { 
    	this.vulnerabilidad = v; 
    	}
}
