//Emiliano Angel Toro Rojas, RUT: 21.512.702-8, Carrera: Ingenieria en tecnologias de información.
//Valentina Castillo,Rut; 15.166.692-2, Carrera: Ingenieria en tecnologias de información.


package Dominio;

import java.util.ArrayList;

public class pcs {
    private String id;
    private String ip;
    private String so;
    private ArrayList<puertos> puertos;

    public pcs(String id, String ip, String so) {
        this.id = id;
        this.ip = ip;
        this.so = so;
        this.puertos = new ArrayList<puertos>();
    }

    public String getId() { 
    	return id; 
    	}
    public String getIp() { 
    	return ip; 
    	}
    public String getSo() { 
    	return so; 
    	}
    public ArrayList<puertos> getPuertos() { 
    	return puertos; 
    	}
    public void addPuerto(puertos p) { 
    	puertos.add(p); 
    	}

    public int contarVulnerabilidades() {
        int c = 0;
        for (int i = 0; i < puertos.size(); i++) {
            puertos p = puertos.get(i);
            if (p.isAbierto() && p.getVulnerabilidad() != null) c++;
        }
        return c;
    }

    public String nivelRiesgo() {
        int v = contarVulnerabilidades();
        if (v == 0) return "Bajo";
        if (v == 1 || v == 2) return "Medio";
        return "Alto";
    }

    public String claseIP() {
        String[] partes = ip.split("\\.");
        int primer = Integer.parseInt(partes[0]);
        if (primer >= 0 && primer <= 127) return "A";
        if (primer >= 128 && primer <= 191) return "B";
        if (primer >= 192 && primer <= 223) return "C";
        return "Desconocida";
    }
}
