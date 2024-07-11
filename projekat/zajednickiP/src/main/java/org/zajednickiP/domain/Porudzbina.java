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
 *predstavlja porudzbinu sa atributima:porudzbinaId,datumVreme,datumIsporuke,grad,adresa,cena,popust,konacnaCena,kupac,administrator,stavkePorudzbine
 * @author Lenovo
 */
public class Porudzbina extends AbstractDomainObject {

	/**
	 * id porudzbine kao Long
	 */
    private Long porudzbinaID;
    /**
     * datum i vreme kada je nastala porudzbina
     */
    private Date datumVreme;
    
    /**
     * datum isporuke konkretne porudzbine
     */
    private Date datumIsporuke;
    
    /**
     * grad u kome treba izvrsiti isporuku
     */
    private String grad;
    
    /**
     * konkretna adresa u samom gradu gde treba izvrsiti isporuku
     */
    private String adresa;
    
    /**
     * osnovna cena porudzbine bez uracunatog popusta
     */
    private double cena;
    
    /**
     * ostvareni popust u procentima
     */
    private double popust;
    
    /**
     * konacna cena porudzbine kada se uracuna popust na osnovnu cenu
     */
    private double konacnaCena;
    
    /**
     * kupac koji je porucio porudzbinu
     */
    private Kupac kupac;
    
    /**
     * administrator koji je kreirao porudzbinu
     */
    private Administrator administrator;
    
    /**
     * lista stavki porudzbina
     */
    private ArrayList<StavkaPorudzbine> stavkePorudzbine;

    /**
     * postavlja  sve atribute porudzbine na zeljene vrednost
     * @param porudzbinaID id porudbzine kao long
     * @param datumVreme kao util.Date
     * @param datumIsporuke kao util.Date
     * @param grad kao String
     * @param adresa kao String
     * @param cena kao String
     * @param popust kao double
     * @param konacnaCena kao double
     * @param kupac konkretne porudzbine
     * @param administrator koji je kreirao porudzbinu
     * @param stavkePorudzbine lista stavki porudzbina
     * @throws java.lang.NullPointerException ukoliko se prosledi null vrednost za datumVreme ili datumIsporuke ili grad ili adresu ili kupca ili administratora ili za stavke
     * @throws java.lang.IllegalArgumentException ukoliko se prosledi  prazan string za grad ili adresu ili se za cenu ne prosledi pozitivan broj ili se za popust prosledi negativan broj ili se za konacnu cenu ne prosledi pozitivan broj
     */
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

    
    /**
     * vraca trenutnu vrednost atributa porudzbinaID
     * @return porudzbinaID kao long
     */
    public Long getPorudzbinaID() {
        return porudzbinaID;
    }

    /**
     * postavlja atribut porudzbinaID na zeljenu vrednost
     * @param porudzbinaID id porudzbine kao Long
     */
    public void setPorudzbinaID(Long porudzbinaID) {
        this.porudzbinaID = porudzbinaID;
    }

    /**
     * vraca trenutnu vrednost atributa datumVreme
     * @return datumVreme kao util.Date
     */
    public Date getDatumVreme() {
        return datumVreme;
    }

    
    /**
     * postavlja atribut datumVreme na zeljenu vrednost
     * @param datumVreme porudzbine kad je nastala
     * @throws java.lang.NullPointerException ukoliko je prosledjena null vrednost
     */
    public void setDatumVreme(Date datumVreme) {
    	if(datumVreme==null) {
    		throw new NullPointerException("datumVreme ne smeju biti null");
    	}
        this.datumVreme = datumVreme;
    }

    
    /**
     * vraca trenutnu vrednost atributa datumIsporuke konkretne porudzbine
     * @return datumIsporuke kao util.Date
     */
    public Date getDatumIsporuke() {
        return datumIsporuke;
    }

    
    /**
     * postavlja atribut datumIsporuke na zeljenu vrednost
     * @param datumIsporuke kad porudzbina treba biti isporucena
     * @throws java.lang.NullPointerException ukoliko je prosledjena null vrednost
     * 
     */
    public void setDatumIsporuke(Date datumIsporuke) {
    	if(datumIsporuke==null) {
    		throw new NullPointerException("datumIsporuke ne sme biti null");
    		
    	}
    	
    	/*Date trenutniDatum=new Date();
    	if(datumIsporuke.before(trenutniDatum)) {
    		throw new IllegalArgumentException("datumIsporuke mora biti u buducnosti");
    	}*/
        this.datumIsporuke = datumIsporuke;
    }

    
    /**
     * vraca trenutnu vrednost atributa grad
     * @return grad u kome treba biti isporucena porudzbina kao String
     */
    public String getGrad() {
        return grad;
    }

    /**
     * postavlja atribut grad na zeljenu vrednost
     * @param grad u kome treba biti isporucena porudzbina kao String
     * @throws java.lang.NullPointerException ukoliko je prosledjena null vrednost
     * @throws java.lang.IllegalArgumentException ukoliko je prosledjen prazan string
     */
    public void setGrad(String grad) {
    	if(grad==null) {
    		throw new NullPointerException("grad ne sme biti null");
    	}
    	if(grad.equals("")) {
    		throw new IllegalArgumentException("grad ne sme biti prazan string");
    	}
        this.grad = grad;
    }

    
    /**
     * vraca trenutnu vrednost atributa adresa
     * @return adresa na kojoj treba biti isporucena porudzbina
     */
    public String getAdresa() {
        return adresa;
    }

    /**
     * postavlja atribut adresa na zeljenu vrednost
     * @param adresa na kojoj treba biti isporucena porudzbina kao String
     * @throws java.lang.NullPointerException ukoliko je prosledjena null vrednost
     * @throws java.lang.IllegalArgumentException ukoliko je prosledjen prazan String
     */
    public void setAdresa(String adresa) {
    	if(adresa==null) {
    		throw new NullPointerException("adresa ne sme biti null");
    	}
    	if(adresa.equals("")) {
    		throw new IllegalArgumentException("adresa ne sme biti prazan string");
    	}
        this.adresa = adresa;
    }

    /**
     * vraca trenutnu vrednost atributa cena
     * @return cena porudzbine kao double
     */
    public double getCena() {
        return cena;
    }

   /**
    * postavlja atribut cena na zeljenu vrednost
    * @param cena porudzbine kao double
    * @throws java.lang.IllegalArgumentException ukoliko nije prosledjena pozitivna vrednost
    */
    public void setCena(double cena) {
    	if(cena<=0) {
    		throw new IllegalArgumentException("cena mora biti veca od 0");
    	}
    	
        this.cena = cena;
    }

    /**
     * vraca trenutnu vrednost atributa popust
     * @return popust ostvaren za porudzbinu kao double
     */
    public double getPopust() {
        return popust;
    }

    /**
     * postavlja atribut popust na zeljenu vrednost
     * @param popust ostvaren na porudzbinu kao double
     * @throws java.lang.IllegalArgumentException ukoliko je prosledjena negativna vrednost
     */
    public void setPopust(double popust) {
    	if(popust<0) {
    		throw new IllegalArgumentException("popust ne sme biti negativan");
    	}
    	
        this.popust = popust;
    }

    /**
     * vraca trenutnu vrednost atributa konacna cena
     * @return konacnaCena porudzbine kao double
     */
    public double getKonacnaCena() {
        return konacnaCena;
    }

    
    
    /**
     * postavlja atribut konacnaCena na zeljenu vrednost
     * @param konacnaCena porudzbine kao double
     * @throws java.lang.IllegalArgumentException ukoliko nije prosledjena pozitivna vrednost
     */
    public void setKonacnaCena(double konacnaCena) {
    	if(konacnaCena<=0) {
    		throw new IllegalArgumentException("konacnaCena mora biti pozitivna");
    	}
    	
        this.konacnaCena = konacnaCena;
    }

    
    /**
     * vraca kupca konkretne porudzbine
     * @return kupac porudzbine
     */
    public Kupac getKupac() {
        return kupac;
    }

    /**
     * postavlja kupca konkretne porudzbine 
     * @param kupac porudzbine
     * @throws java.lang.NullPointerException ukoliko je prosledjena null vrednost
     */
    public void setKupac(Kupac kupac) {
    	if(kupac==null) {
    		throw new NullPointerException("kupac ne sme biti null");
    	}
        this.kupac = kupac;
    }

    /**
     * vraca administratora konkretne porudzbine
     * @return administrator porudzbine
     */
    public Administrator getAdministrator() {
        return administrator;
    }

    /**
     * postavlja administratora konkretne porudzbine 
     * @param administrator porudzbine
     * @throws java.lang.NullPointerException ukoliko je prosledjena null vrednost
     */
    public void setAdministrator(Administrator administrator) {
    	if(administrator==null) {
    		throw new NullPointerException("administrator ne sme biti null");
    	}
        this.administrator = administrator;
    }

    /**
     * vraca sve stavke konkretne porudzbine
     * @return stavkePorudzbine kao lista
     */
    public ArrayList<StavkaPorudzbine> getStavkePorudzbine() {
        return stavkePorudzbine;
    }

    
    /**
     * postavlja stavkePorudzbine konkretne porudzbine
     * @param stavkePorudzbine porudzbine kao lista
     * @throws java.lang.NullPointerException ukoliko je prosledjena null vrednost
     */
    public void setStavkePorudzbine(ArrayList<StavkaPorudzbine> stavkePorudzbine) {
    	if(stavkePorudzbine==null) {
    		throw new NullPointerException("stavke ne smeju biti null");
    	}
    	/*if(stavkePorudzbine.isEmpty()) {   ovo sam ipak dozvolio a onda cu kod sistemskih operacija za porudzbinu da proveravam da li je prazna lista
    		throw new IllegalArgumentException("porudzbina mora sadrzati barem jednu stavku");
    	}*/
        this.stavkePorudzbine = stavkePorudzbine;
    }



	



    /**
     * poredi dva porudzbine po atributima:administrator,kupac i porudzbina ID
     * @param obj porudbzina sa kojom se poredi
     * @return true - ukoliko su im svi atributi jednaki
     * @return false - u svim surpotnim situacijama
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Porudzbina other = (Porudzbina) obj;
		return Objects.equals(administrator, other.administrator) 
				&& Objects.equals(kupac, other.kupac)
				&& Objects.equals(porudzbinaID, other.porudzbinaID);
	}
    
    
    

}