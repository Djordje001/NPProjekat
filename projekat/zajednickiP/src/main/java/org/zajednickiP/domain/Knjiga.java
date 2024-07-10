package org.zajednickiP.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
/**
 * predstavlja knjigu sa atributima izdanje,opis i autori
 * nasledjuje klazu proizvod
 * @author Lenovo
 *
 */
public class Knjiga extends Proizvod{
	/**
	 * izdanje knjige kao integer vrednost
	 */
	private int izdanje;
	
	/**
	 * kratak opis Knjige kao String
	 */
	private String opis;
	
	/**
	 * lista autora knjige
	 */
	private ArrayList<Autor> autori;
	
	
	
	
	
	
	/**
	 * postavlja sve atribute knjige na zeljene vrednosti
	 * @param proizvodID id proizvoda kao Long
	 * @param cena proizvoda Double
	 * @param naziv proizvoda kao String
	 * @param tip proizvoda kao Int
	 * @param izdanje knjige kao int
	 * @param opis knjige kao String
	 * @param autori knjige kao lista
	 * @throws java.lang.IllegalArgumentException ukoliko je za tip prosledjena vrednost razlicita od 2,ili ako nije prosledjena pozitivna vrednost za cenu ili izdanje
	 * @throws java.lang.NullPointerException ukoliko je prosledjena null vrednost za naziv ili opis ili za autore
	 */
	public Knjiga(Long proizvodID, double cena, String naziv,int tip,int izdanje,String opis,ArrayList<Autor> autori) {
		super(proizvodID, cena, naziv,tip);
		if(tip!=2) {
			throw new IllegalArgumentException("Za knjigu je odredjen tip=2");
		}
		setIzdanje(izdanje);
		
		setOpis(opis);
		
		setAutori(autori);
		
		// TODO Auto-generated constructor stub
	}
	
	
	




	/**
	 * vraca trenutnu vrednost atributa izdanje knjige
	 * @return izdanje knjige kao int
	 */
	public int getIzdanje() {
		return izdanje;
	}




	/**
	 * postavlja atribut izdanje na zeljenu vrednost
	 * @param izdanje knjige kao integer
	 * @throws java.lang.IllegalArgumentException ukoliko nije poslata pozitivna vrednost
	 */
	public void setIzdanje(int izdanje) {
		if(izdanje<=0) {
			throw new IllegalArgumentException("izdanje mora biti vece od 0");
		}
		this.izdanje = izdanje;
	}




	/**
	 * vraca trenutnu vrednost atributa opis
	 * @return opis knjige kao String
	 */
	public String getOpis() {
		return opis;
	}




	/**
	 * postavlja opis knjige na zeljenu vrednost
	 * @param opis knjige kao String
	 * @throws java.lang.NullPointerException ukoliko je prosledjena null vrednost
	 * @throws java.lang.IllegalArgumentException ukoliko je prosledjen prazan string
	 */
	public void setOpis(String opis) {
		if(opis==null) {
			throw new NullPointerException("opis ne sme biti null");
		}
		if(opis.equals("")) {
			throw new IllegalArgumentException("opis ne sme biti prazan string");
		}
		this.opis = opis;
	}

	



	/**
	 * vraca listu autora konkretne knjige
	 * @return autori -listu autora konkretne knjige
	 */
	public ArrayList<Autor> getAutori() {
		return autori;
	}




	/**
	 * postavlja autore konkretne knjige na zeljenu vrednost
	 * @param autori knjige kao Lista
	 * @throws java.lang.NullPointerException ukoliko je prosledjena null vrednost
	 */
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







	







	/**
	 * poredi dve knjige po svim atributima
	 * @param obj knjiga sa kojim se poredi
	 * @return true ukoliko druga knjiga ima sve iste atribute
	 * @return false u svim ostalim sitaucijama
	 */
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

