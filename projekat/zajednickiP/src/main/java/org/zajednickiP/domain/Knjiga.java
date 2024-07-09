package org.zajednickiP.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class Knjiga extends Proizvod{
	private int izdanje;
	private String opis;
	private ArrayList<Autor> autori;
	
	
	
	
	
	

	public Knjiga(Long proizvodID, double cena, String naziv,int tip,int izdanje,String opis,ArrayList<Autor> autori) {
		super(proizvodID, cena, naziv,tip);
		setIzdanje(izdanje);
		
		setOpis(opis);
		
		setAutori(autori);
		
		// TODO Auto-generated constructor stub
	}
	
	
	




	public int getIzdanje() {
		return izdanje;
	}




	public void setIzdanje(int izdanje) {
		if(izdanje<=0) {
			throw new IllegalArgumentException("izdanje mora biti vece od 0");
		}
		this.izdanje = izdanje;
	}




	public String getOpis() {
		return opis;
	}




	public void setOpis(String opis) {
		if(opis==null) {
			throw new NullPointerException("opis ne sme biti null");
		}
		if(opis.equals("")) {
			throw new IllegalArgumentException("opis ne sme biti prazan string");
		}
		this.opis = opis;
	}

	



	public ArrayList<Autor> getAutori() {
		return autori;
	}




	public void setAutori(ArrayList<Autor> autori) {
		//ne radim proveru da li su autori prazna lista jer postoje knjige kod kojih se ne znaju autori samim tim moze lista da bude prazna
		if(autori==null) {
			throw new NullPointerException("autori ne smeju biti null");
		}
		this.autori = autori;
	}




	@Override
	public String nazivTabele() {
		return " Knjiga ";
	}

	@Override
	public String alijas() {
		return " k ";
	}

	@Override
	public String join() {
		return " JOIN PROIZVOD P ON (P.PROIZVODID = K.PROIZVODID) ";
	}

	@Override
	public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
		 ArrayList<AbstractDomainObject> lista = new ArrayList<AbstractDomainObject>();

	        while (rs.next()) {
	            Knjiga a = new Knjiga(rs.getLong("ProizvodID"),
	                    rs.getDouble("Cena"), rs.getString("Naziv"),rs.getInt("tip"),rs.getInt("Izdanje"),rs.getString("Opis"),new ArrayList<>());
	                   

	            lista.add(a);
	        }

	        rs.close();
	        return lista;
	}

	@Override
	public String koloneZaInsert() {
		return " (proizvodID,izdanje,opis) ";
	}

	@Override
	public String uslov() {
		return " proizvodID = " +getProizvodID();
	}

	@Override
	public String vrednostiZaInsert() {
		return getProizvodID()+", "+izdanje+", '"+opis+"'";
		
		
				
	}

	@Override
	public String vrednostiZaUpdate() {
		 return " Izdanje = " + izdanje + ", Opis = '" + opis + "' ";
		         
	}

	@Override
	public String uslovZaSelect() {
		return "";
	}




	@Override
	public String toString() {
		return super.toString()+ "Knjiga [ izdanje=" + izdanje + ", opis=" + opis + ", autori=" + autori + "]";
	}







	







	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Knjiga other = (Knjiga) obj;
		return   super.equals(obj) && 
				 izdanje == other.izdanje && Objects.equals(opis, other.opis);
	}



     
	
	
	

}

