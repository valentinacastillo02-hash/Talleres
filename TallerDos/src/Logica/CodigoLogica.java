//Emiliano Angel Toro Rojas, RUT: 21.512.702-8, Carrera: Ingenieria en tecnologias de información.
//Valentina Castillo,Rut; 15.166.692-2, Carrera: Ingenieria en tecnologias de información.


package Logica;

import java.io.*;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;




public class CodigoLogica {

    static ArrayList<Usuarioscodigo> usuarios = new ArrayList<>();
    static ArrayList<pcs> pcsList = new ArrayList<>();
    static ArrayList<vulnerabilidades> vulnerabilidadesList = new ArrayList<>();
    private static final Scanner INPUT = new Scanner(System.in);

    public static void main(String[] args) {
        cargar_usuarios("usuarios.txt");
        cargar_pcs("pcs.txt");
        cargar_vulnerabilidades("vulnerabilidades.txt");
        cargar_puertos("puertos.txt");

        Usuarioscodigo user = login();
        if (user == null) {
            System.out.println("Acceso denegado.");
            return;
        }
        if (user.esAdmin()) {
            menuAdmin();
        } else if (user.esUser()) {
            menuUsuario(user);
        } else {
            System.out.println("Rol desconocido.");
        }
    }

    public static void cargar_usuarios(String archivo) {
        try {
            File f = new File(archivo);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String l = sc.nextLine();
                String[] p = l.split(";");
                if (p.length == 3)
                    usuarios.add(new Usuarioscodigo(p[0], p[1], p[2]));
            }
            sc.close();
        } catch (Exception e) { System.out.println("Error cargando usuarios: "+e); }
    }

    public static void cargar_pcs(String archivo) {
        try {
            File f = new File(archivo);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String l = sc.nextLine();
                String[] p = l.split("\\|");
                if (p.length == 3)
                    pcsList.add(new pcs(p[0], p[1], p[2]));
            }
            sc.close();
        } catch (Exception e) { System.out.println("Error cargando PCs: "+e); }
    }

    public static void cargar_vulnerabilidades(String archivo) {
        try {
            File f = new File(archivo);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String l = sc.nextLine();
                String[] p = l.split(";");
                if (p.length == 3)
                    vulnerabilidadesList.add(new vulnerabilidades(Integer.parseInt(p[0]), p[1], p[2]));
            }
            sc.close();
        } catch (Exception e) { System.out.println("Error cargando vulnerabilidades: "+e); }
    }

    public static void cargar_puertos(String archivo) {
        try {
            File f = new File(archivo);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String l = sc.nextLine();
                String[] p = l.split(";");
                if (p.length == 3) {
                    String pcId = p[0];
                    int numero = Integer.parseInt(p[1]);
                    boolean abierto = p[2].equalsIgnoreCase("abierto");
                    pcs pcObj = buscarPCporID(pcId);
                    if (pcObj != null) {
                        puertos puerto = new puertos(numero, abierto);
                        vulnerabilidades v = buscarVulnPorPuerto(numero);
                        if (v != null) puerto.setVulnerabilidad(v);
                        pcObj.addPuerto(puerto);
                    }
                }
            }
            sc.close();
        } catch (Exception e) { System.out.println("Error cargando puertos: "+e); }
    }

    public static pcs buscarPCporID(String id) {
        for (int i = 0; i < pcsList.size(); i++)
            if (pcsList.get(i).getId().equals(id)) return pcsList.get(i);
        return null;
    }
    public static vulnerabilidades buscarVulnPorPuerto(int numero) {
        for (int i = 0; i < vulnerabilidadesList.size(); i++)
            if (vulnerabilidadesList.get(i).getPuerto() == numero) return vulnerabilidadesList.get(i);
        return null;
    }

    public static Usuarioscodigo login() {
        System.out.print("Usuario: ");
        String usuario = INPUT.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = INPUT.nextLine();
        String hash = hashear(contraseña);
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getUsername().equals(usuario) &&
                usuarios.get(i).getContrasenaHash().equals(hash)) {
                return usuarios.get(i);
            }
        }
        return null;
    }

    public static String hashear(String pwd) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] sha = md.digest(pwd.getBytes());
            return Base64.getEncoder().encodeToString(sha);
        } catch (Exception e) { return ""; }
    }

    public static void menuAdmin() {
    	int op = -1;
        while (op != 0) {
            System.out.println("--- Menú Admin ---");
            System.out.println("1. Ver lista completa de PCs");
            System.out.println("2. Agregar o eliminar un PC");
            System.out.println("3. Clasificar PCs según nivel de riesgo");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            // usar nextLine para evitar problemas de salto de línea
            String line = INPUT.nextLine();
            try { op = Integer.parseInt(line); } catch (NumberFormatException e) { op = -1; }

            if (op == 1) mostrarPCsDetalle();
            else if (op == 2) menuAgregarEliminarPC();
            else if (op == 3) clasificarPCsRiesgo();
        }
    }

    public static void menuUsuario(Usuarioscodigo user) {
        int op = -1;
        while (op != 0) {
            System.out.println("--- Menú Usuario ---");
            System.out.println("1. Ver lista de PCs");
            System.out.println("2. Escanear un PC");
            System.out.println("3. Ver total de puertos abiertos");
            System.out.println("4. Ordenar PCs por IP");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            String line = INPUT.nextLine();
            try { op = Integer.parseInt(line); } catch (NumberFormatException e) { op = -1; }

            if (op == 1) mostrarPCsBreve();
            else if (op == 2) escanearPC(user);
            else if (op == 3) mostrarPuertosAbiertos();
            else if (op == 4) ordenarPCsporIP();
        }
    }

    public static void mostrarPCsDetalle() {
        for (int i = 0; i < pcsList.size(); i++) {
            pcs pc = pcsList.get(i);
            System.out.println("ID: " + pc.getId() + ", IP: " + pc.getIp() + ", SO: " + pc.getSo());
            for (int j = 0; j < pc.getPuertos().size(); j++) {
                puertos p = pc.getPuertos().get(j);
                System.out.print("    Puerto: " + p.getNumero() + " " + (p.isAbierto() ? "Abierto" : "Cerrado"));
                if (p.getVulnerabilidad() != null)
                    System.out.print(" | Vulnerabilidad: " + p.getVulnerabilidad().getNombre());
                System.out.println();
            }
        }
    }

    public static void menuAgregarEliminarPC() {
        System.out.println("1. Agregar PC\n2. Eliminar PC");
        int op;
        try { op = Integer.parseInt(INPUT.nextLine()); } catch (NumberFormatException e) { op = -1; }

        if (op == 1) {
            System.out.print("ID: "); String id = INPUT.nextLine();
            System.out.print("IP: "); String ip = INPUT.nextLine();
            System.out.print("SO: "); String so = INPUT.nextLine();
            pcs nuevo = new pcs(id, ip, so);

            System.out.print("Cantidad de puertos: ");
            int n; try { n = Integer.parseInt(INPUT.nextLine()); } catch (NumberFormatException e) { n = 0; }
            for (int i = 0; i < n; i++) {
                System.out.print("Número puerto: ");
                int num; try { num = Integer.parseInt(INPUT.nextLine()); } catch (NumberFormatException e) { num = 0; }
                System.out.print("¿Abierto? (true/false): ");
                boolean ab = Boolean.parseBoolean(INPUT.nextLine());
                puertos p = new puertos(num, ab);
                vulnerabilidades v = buscarVulnPorPuerto(num);
                if (v != null) p.setVulnerabilidad(v);
                nuevo.addPuerto(p);
            }
            pcsList.add(nuevo);
            guardarPCs();
            guardarPuertos();
            System.out.println("PC agregada.");
        } else if (op == 2) {
            System.out.print("ID a eliminar: ");
            String id = INPUT.nextLine();
            pcs pc = buscarPCporID(id);
            if (pc != null) {
                pcsList.remove(pc);
                guardarPCs();
                guardarPuertos();
                System.out.println("PC eliminada.");
            } else {
                System.out.println("No existe.");
            }
        }
    }

    public static void guardarPCs() {
        try {
            FileWriter fw = new FileWriter("pcs.txt");
            for (int i = 0; i < pcsList.size(); i++) {
                pcs pc = pcsList.get(i);
                fw.write(pc.getId() + "|" + pc.getIp() + "|" + pc.getSo() + "\n");
            }
            fw.close();
        } catch (Exception e) { System.out.println("Error guardando PCs: "+e); }
    }
    public static void guardarPuertos() {
        try {
            FileWriter fw = new FileWriter("puertos.txt");
            for (int i = 0; i < pcsList.size(); i++) {
                pcs pc = pcsList.get(i);
                for (int j = 0; j < pc.getPuertos().size(); j++) {
                    puertos p = pc.getPuertos().get(j);
                    fw.write(pc.getId() + ";" + p.getNumero() + ";" + (p.isAbierto() ? "abierto" : "cerrado") + "\n");
                }
            }
            fw.close();
        } catch (Exception e) { System.out.println("Error guardando puertos: "+e); }
    }

    public static void clasificarPCsRiesgo() {
        for (int i = 0; i < pcsList.size(); i++) {
            pcs pc = pcsList.get(i);
            System.out.println("ID: " + pc.getId() + ", IP: " + pc.getIp() +
                    ", SO: " + pc.getSo() + ", Nivel de riesgo: " + pc.nivelRiesgo());
            for (int j = 0; j < pc.getPuertos().size(); j++) {
                puertos p = pc.getPuertos().get(j);
                if (p.isAbierto() && p.getVulnerabilidad() != null)
                    System.out.println("    Puerto " + p.getNumero() +
                            ": " + p.getVulnerabilidad().getNombre() +
                            " (" + p.getVulnerabilidad().getDescripcion() + ")");
            }
        }
    }

    public static void mostrarPCsBreve() {
        for (int i = 0; i < pcsList.size(); i++) {
            pcs pc = pcsList.get(i);
            System.out.println("ID: " + pc.getId() + ", IP: " + pc.getIp() + ", SO: " + pc.getSo());
        }
    }

    public static void escanearPC(Usuarioscodigo user) {
        System.out.print("ID del PC a escanear: ");
        String id = INPUT.nextLine();
        pcs pc = buscarPCporID(id);
        if (pc == null) {
            System.out.println("PC no existe.");
            return;
        }
        System.out.println("Escaneando PC: " + pc.getId() + " (" + pc.getIp() + ")");
        for (int i = 0; i < pc.getPuertos().size(); i++) {
            puertos p = pc.getPuertos().get(i);
            System.out.print("Puerto: " + p.getNumero() + " - " + (p.isAbierto() ? "Abierto" : "Cerrado"));
            if (p.isAbierto() && p.getVulnerabilidad() != null)
                System.out.print(" [Vulnerabilidad: " + p.getVulnerabilidad().getNombre() + "]");
            System.out.println();
        }
        try {
            FileWriter fw = new FileWriter("reportes.txt", true);
            fw.write("--- Reporte de Escaneo ---\n");
            fw.write("Usuario: " + user.getUsername() + "\n");
            fw.write("PC: " + pc.getId() + ", IP: " + pc.getIp() + ", SO: " + pc.getSo() + "\n");
            fw.write("Nivel de riesgo: " + pc.nivelRiesgo() + "\n");
            for (int i = 0; i < pc.getPuertos().size(); i++) {
                puertos p = pc.getPuertos().get(i);
                fw.write("Puerto: " + p.getNumero() + " - " + (p.isAbierto() ? "Abierto" : "Cerrado"));
                if (p.isAbierto() && p.getVulnerabilidad() != null)
                    fw.write(" [Vulnerabilidad: " + p.getVulnerabilidad().getNombre() + "]");
                fw.write("\n");
            }
            fw.write("\n");
            fw.close();
        } catch (Exception e) { System.out.println("Error guardando reporte: "+e); }
        System.out.println("Reporte guardado en reportes.txt");
    }

    public static void mostrarPuertosAbiertos() {
        for (int i = 0; i < pcsList.size(); i++) {
            pcs pc = pcsList.get(i);
            for (int j = 0; j < pc.getPuertos().size(); j++) {
                puertos p = pc.getPuertos().get(j);
                if (p.isAbierto()) {
                    System.out.print("PC: " + pc.getId() + ", Puerto: " + p.getNumero());
                    if (p.getVulnerabilidad() != null)
                        System.out.print(" | Vulnerabilidad: " + p.getVulnerabilidad().getNombre());
                    System.out.println();
                }
            }
        }
    }

    public static void ordenarPCsporIP() {
        for (int clase = 0; clase < 3; clase++) {
            for (int i = 0; i < pcsList.size(); i++) {
                pcs pc = pcsList.get(i);
                String c = pc.claseIP();
                if ((clase == 0 && c.equals("A")) || (clase == 1 && c.equals("B")) ||(clase == 2 && c.equals("C"))) {
                    System.out.println("ID: " + pc.getId() + ", IP: " + pc.getIp() +
                            ", Clase: " + pc.claseIP() + ", SO: " + pc.getSo());
                }
            }
        }
    }

}
