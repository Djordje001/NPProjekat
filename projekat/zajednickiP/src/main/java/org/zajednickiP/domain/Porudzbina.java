/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zajednickiP.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Lenovo
 */
public class Porudzbina extends AbstractDomainObject {

    private Long porudzbinaID;
    private Date datumVreme;
    private Date datumIsporuke;
    private String grad;
    private String adresa;
    private double cena;
    private double popust;
    private double konacnaCena;
    private Kupac kupac;
    private Administrator administrator;
    private ArrayList<StavkaPorudzbine> stavkePorudzbine;

    public Porudzbina(Long porudzbinaID, Date datumVreme, Date datumIsporuke, String grad, String adresa, double cena, double popust, double konacnaCena, Kupac kupac, Administrator administrator, ArrayList<StavkaPorudzbine> stavkePorudzbine) {
      
        setPorudzbinaID(porudzbinaID);
     
        setDatumVreme(datumVreme);
    
        setDatumIsporuke(datumIsporuke);
   
        setGrad(grad);
   
        setAdresa(adresa);
    
        setCena(cena);
    
        setPopust(popust);
       
        setKonacnaCena(konacnaCena);
       
        setKupac(kupac);
      
        setAdministrator(administrator);
    
        setStavkePorudzbine(stavkePorudzbine);
    }

    

    @Override
    public String toString() {
        return "Porudzbina{" + "datumVreme=" + datumVreme + ", datumIsporuke=" + datumIsporuke + ", kupac=" + kupac + '}';
    }

    
   
    
    @Override
    public String nazivTabele() {
        return " Porudzbina ";
    }

    @Override
    public String alijas() {
        return " p ";
    }

    @Override
    public String join() {
        return " JOIN KUPAC K ON (K.KUPACID = P.KUPACID) "
                + "JOIN ADMINISTRATOR A ON (A.ADMINISTRATORID = P.ADMINISTRATORID) ";
        
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<AbstractDomainObject>();

        while (rs.next()) {
            Administrator a = new Administrator(rs.getLong("AdministratorID"),
                    rs.getString("a.Ime"), rs.getString("a.Prezime"),
                    rs.getString("Username"), rs.getString("Password"));

            Kupac k = new Kupac(rs.getLong("KupacID"),
                    rs.getString("k.Ime"), rs.getString("k.Prezime"),
                    rs.getString("email"), rs.getString("tipKupca"));

            Porudzbina p = new Porudzbina(rs.getLong("porudzbinaID"),
                    rs.getTimestamp("datumVreme"), rs.getDate("datumIsporuke"),
                    rs.getString("grad"), rs.getString("adresa"),
                    rs.getDouble("cena"), rs.getDouble("popust"),
                    rs.getDouble("konacnaCena"), k, a, new ArrayList<>());

            lista.add(p);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (datumVreme, datumIsporuke, grad, adresa, cena, popust, konacnaCena, "
                + "kupacID, administratorID) ";
    }

    @Override
    public String uslov() {
        return " porudzbinaID = " + porudzbinaID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + new Timestamp(datumVreme.getTime()) + "', "
                + "'" + new java.sql.Date(datumIsporuke.getTime()) + "', "
                + "'" + grad + "', '" + adresa + "', " + cena + ", " + popust + ", "
                + konacnaCena + ", " + kupac.getKupacID() + ", " + administrator.getAdministratorID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " datumIsporuke = '" + new java.sql.Date(datumIsporuke.getTime()) + "', "
                + "grad = '" + grad + "', "
                + "adresa = '" + adresa + "', cena = " + cena + ", "
                + "konacnaCena = " + konacnaCena;
    }

    @Override
    public String uslovZaSelect() {
       
       /* if(kupac==null){
            return "";
        }
        return "WHERE K.KUPACID= "+kupac.getKupacID();*/
    	//return "WHERE CONCAT(K.IME,K.PREZIME)= "+kupac.getIme()+kupac.getPrezime();
    	return "WHERE CONCAT(K.IME, ' ', K.PREZIME) = '" + kupac.getIme() + " " + kupac.getPrezime() + "'";
        
       
    }

    public Long getPorudzbinaID() {
        return porudzbinaID;
    }

    public void setPorudzbinaID(Long porudzbinaID) {
        this.porudzbinaID = porudzbinaID;
    }

    public Date getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(Date datumVreme) {
    	if(datumVreme==null) {
    		throw new NullPointerException("datumVreme ne smeju biti null");
    	}
        this.datumVreme = datumVreme;
    }

    public Date getDatumIsporuke() {
        return datumIsporuke;
    }

    public void setDatumIsporuke(Date datumIsporuke) {
    	if(datumIsporuke==null) {
    		throw new NullPointerException("datumIsporuke ne sme biti null");
    		
    	}
    	
    	Date trenutniDatum=new Date();
    	if(datumIsporuke.before(trenutniDatum)) {
    		throw new IllegalArgumentException("datumIsporuke mora biti u buducnosti");
    	}
        this.datumIsporuke = datumIsporuke;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
    	if(grad==null) {
    		throw new NullPointerException("grad ne sme biti null");
    	}
    	if(grad.equals("")) {
    		throw new IllegalArgumentException("grad ne sme biti prazan string");
    	}
        this.grad = grad;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
    	if(adresa==null) {
    		throw new NullPointerException("adresa ne sme biti null");
    	}
    	if(adresa.equals("")) {
    		throw new IllegalArgumentException("adresa ne sme biti prazan string");
    	}
        this.adresa = adresa;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
    	if(cena<=0) {
    		throw new IllegalArgumentException("cena mora biti veca od 0");
    	}
    	
        this.cena = cena;
    }

    public double getPopust() {
        return popust;
    }

    public void setPopust(double popust) {
    	if(popust<0) {
    		throw new IllegalArgumentException("popust ne sme biti negativan");
    	}
    	
        this.popust = popust;
    }

    public double getKonacnaCena() {
        return konacnaCena;
    }

    public void setKonacnaCena(double konacnaCena) {
    	if(konacnaCena<=0) {
    		throw new IllegalArgumentException("konacnaCena mora biti pozitivna");
    	}
    	
        this.konacnaCena = konacnaCena;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
    	if(kupac==null) {
    		throw new NullPointerException("kupac ne sme biti null");
    	}
        this.kupac = kupac;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
    	if(administrator==null) {
    		throw new NullPointerException("administrator ne sme biti null");
    	}
        this.administrator = administrator;
    }

    public ArrayList<StavkaPorudzbine> getStavkePorudzbine() {
        return stavkePorudzbine;
    }

    public void setStavkePorudzbine(ArrayList<StavkaPorudzbine> stavkePorudzbine) {
    	if(stavkePorudzbine==null) {
    		throw new NullPointerException("stavke ne smeju biti null");
    	}
    	/*if(stavkePorudzbine.isEmpty()) {   ovo sam ipak dozvolio a onda cu kod sistemskih operacija za porudzbinu da proveravam da li je prazna lista
    		throw new IllegalArgumentException("porudzbina mora sadrzati barem jednu stavku");
    	}*/
        this.stavkePorudzbine = stavkePorudzbine;
    }



	



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Porudzbina other = (Porudzbina) obj;
		return Objects.equals(administrator, other.administrator) && Objects.equals(adresa, other.adresa)
				&& Double.doubleToLongBits(cena) == Double.doubleToLongBits(other.cena)
				&& Objects.equals(datumIsporuke, other.datumIsporuke) && Objects.equals(datumVreme, other.datumVreme)
				&& Objects.equals(grad, other.grad)
				&& Double.doubleToLongBits(konacnaCena) == Double.doubleToLongBits(other.konacnaCena)
				&& Objects.equals(kupac, other.kupac)
				&& Double.doubleToLongBits(popust) == Double.doubleToLongBits(other.popust)
				&& Objects.equals(porudzbinaID, other.porudzbinaID);
	}
    
    
    

}