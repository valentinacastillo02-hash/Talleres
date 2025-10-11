package Dominio;

public class pcs {
	private String ID;
	private String IP;
	private String SO;
	
	public pcs(String ID,String IP,String SO) {
		this.setID(ID);
		this.setIP(IP);
		this.setSO(SO);
		
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public String getSO() {
		return SO;
	}

	public void setSO(String sO) {
		SO = sO;
	}

}
