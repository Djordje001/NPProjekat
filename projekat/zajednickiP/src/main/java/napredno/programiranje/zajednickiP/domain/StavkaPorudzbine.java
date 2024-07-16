/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napredno.programiranje.zajednickiP.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;



/**
 *  predstavlja konkretnu stavku Porudzbine sa atributima:rb,kolicina,cena,proizvod
 * @author Djordje Djordjevic
 */
public class StavkaPorudzbine extends AbstractDomainObject {

	/**
	 * porudzbina kojoj pripada
	 */
    private  Porudzbina porudzbina;
    
    /**
     * redni broj stavek
     */
    private int rb;
    
    /**
     * kolicina konkretnog proizvoda
     */
    private int kolicina;
    
    /**
     * cena konkretne stavke
     */
    private double cena;
    
    /**
     * proizvod od kojeg se sastoji stavka
     */
    private Proizvod proizvod;

    /**
     * postavlja sve atribute na zeljene vrednost
     * @param porudzbina kojoj pripada stavka 
     * @param rb redni broj stavke kao integer
     * @param kolicina proizvoda kao integer
     * @param cena stavke kao double
     * @param proizvod od kojeg se sastoji stavka
     * @throws java.lang.NullPointerException ukoliko je prosledjena null vrednost za porudzbinu ili proizvod
     * @throws java.lang.IllegalArgumentException ukoliko nije prosledjena pozitivna vrednnost za kolicinu ili cenu 
     */
    public StavkaPorudzbine(Porudzbina porudzbina, int rb, int kolicina, double cena, Proizvod proizvod) {
       
        setPorudzbina(porudzbina);
       
        setRb(rb);
        
        setKolicina(kolicina);
      
        setCena(cena);
      
        setProizvod(proizvod);
    }

    

    @Override
    public String nazivTabele() {
        return " StavkaPorudzbine ";
    }

    @Override
    public String alijas() {
        return " sp ";
    }

    @Override
    public String join() {
        return " JOIN PROIZVOD PR ON (PR.PROIZVODID = SP.PROIZVODID) "
                + "JOIN PORUDZBINA P ON (P.PORUDZBINAID = SP.PORUDZBINAID) "
                + "JOIN KUPAC K ON (K.KUPACID = P.KUPACID) "
                + "JOIN ADMINISTRATOR A ON (A.ADMINISTRATORID = P.ADMINISTRATORID) "
                +"LEFT JOIN KANCELARIJSKIPROIZVOD KM ON (PR.TIP = 1 AND KM.PROIZVODID = SP.PROIZVODID) "
                +"LEFT JOIN KNJIGA KJ ON (PR.TIP = 2 AND KJ.PROIZVODID = SP.PROIZVODID) ";
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
            
            //public Knjiga(Long proizvodID, double cena, String naziv,int tip,int izdanje,String opis,ArrayList<Autor> autori)
          //  public KancelarijskiProizvod(Long proizvodID, double cena, String naziv,int tip,String vrsta,String proizvodjac,double visina,double sirina,double duzina)
           
            Proizvod proizvod=new Proizvod(null, 2000,"pravim ovako samo zato jer nemam bezparam konstruktor",1);
            StavkaPorudzbine sp=new StavkaPorudzbine(p, 0, 2, 2500,proizvod);
            if(rs.getInt("tip")==1) {
            	KancelarijskiProizvod kp=new KancelarijskiProizvod(rs.getLong("proizvodID"), rs.getDouble("cena"), rs.getString("naziv"),
            			rs.getInt("tip"), rs.getString("vrsta"), rs.getString("proizvodjac"), rs.getDouble("visina"),rs.getDouble("sirina"), rs.getDouble("duzina"));
            	sp=new StavkaPorudzbine(p, rs.getInt("rb"), rs.getInt("kolicina"), rs.getDouble("cena"), kp);
            }else {
            	 Knjiga knj = new Knjiga(rs.getLong("proizvodID"),
                         rs.getDouble("cena"), rs.getString("naziv"),
                         rs.getInt("tip"), rs.getInt("izdanje"),rs.getString("opis"), new ArrayList<>());
            	 sp = new StavkaPorudzbine(p, rs.getInt("rb"), 
                         rs.getInt("kolicina"), rs.getDouble("cena"), knj);
            }
            
            lista.add(sp);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (porudzbinaID, kolicina, cena, proizvodID) ";
    }

    @Override
    public String uslov() {
        return " porudzbinaID = " + porudzbina.getPorudzbinaID();
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + porudzbina.getPorudzbinaID() + ", "
                + " " + kolicina + ", " + cena + ", " + proizvod.getProizvodID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslovZaSelect() {
        return " WHERE P.PORUDZBINAID = " + porudzbina.getPorudzbinaID();
    }

    
    /**
     * vraca porudzbinu kojoj pripada stavka
     * @return porudzbina konkretne stavke
     */
    public Porudzbina getPorudzbina() {
        return porudzbina;
    }
    
    /**
     * postavlja atribut porudzbina na zeljenu vrednost
     * @param porudzbina kojoj pripada stavka
     * @throws java.lang.NullPointerException ukoliko je prosledjena null vrednost
     */
    public void setPorudzbina(Porudzbina porudzbina) {
    	if(porudzbina==null) {
    		throw new NullPointerException("porudzbina ne sme biti null");
    	}
        this.porudzbina = porudzbina;
    }

    
    /**
     * vraca trenutnu vrednost atributa rb
     * @return rb konkretne stavke
     */
    public int getRb() {
        return rb;
    }

    /**
     * postavlja atribut rb na zeljenu vrednost
     * @param rb rednibroj stavke kao integer
     */
    public void setRb(int rb) {
    	//nemam proveru vrednosti jer mi je rb autoincrement u bazi znaci ja ne zadajem vrednost
        this.rb = rb;
    }

    /**
     * vraca trenutnu vrednost atributa kolicina
     * @return kolicina proizvoda kao integer
     */
    public int getKolicina() {
        return kolicina;
    }

    /**
     * postavlja atribut kolicina na zeljenu vrednost
     * @param kolicina konkretnog proizvoda stavke kao integer
     * @throws java.lang.IllegalArgumentException ukoliko nije prosledjen pozitivan broj
     */
    public void setKolicina(int kolicina) {
    	if(kolicina<=0) {
    		throw new IllegalArgumentException("kolicina mora biti pozitivna");
    	}
        this.kolicina = kolicina;
    }

    
    /**
     * vraca trenutnu vrednost atributa cena
     * @return cena konkretne stavke kao double
     */
    public double getCena() {
        return cena;
    }

    /**
     * postavlja vrednost atributa cena konkretne stavke 
     * @param cena stavke kao double
     * @throws java.lang.IllegalArgumentException ukoliko nije prosledjena pozitivna vrednost
     */
    public void setCena(double cena) {
    	if(cena<=0) {
    		throw new IllegalArgumentException("cena mora biti pozitivna");
    	}
        this.cena = cena;
    }

    
    /**
     * vraca Proizvod od kojeg se sastoji sama stavka
     * @return proizvod stavke
     */
    public Proizvod getProizvod() {
        return proizvod;
    }

    /**
     * postavlja proizvod stavke
     * @param proizvod stavke 
     * @throws java.lang.IllegalArgumentException ukoliko je prosledjena null vrednost
     */
    public void setProizvod(Proizvod proizvod) {
    	if(proizvod==null) {
    		throw new NullPointerException("proizvod ne sme biit null");
    	}
        this.proizvod = proizvod;
    }

	@Override
	public String toString() {
		return "StavkaPorudzbine [porudzbina=" + porudzbina + ", rb=" + rb + ", kolicina=" + kolicina + ", cena=" + cena
				+ ", proizvod=" + proizvod + "]";
	}



	



	/**
	 * poredi dve stavke po atributima:Porudzbina i rb
	 * @param obj  StavkaPorudzbine sa kojom se poredi
	 * @return true - ukoliko su im svi atributi jednaki
	 * @return false - u svim suprotnim situacijama
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StavkaPorudzbine other = (StavkaPorudzbine) obj;
		return 
				Objects.equals(porudzbina, other.porudzbina)
				&& rb == other.rb;
	}
    
	
	
	
    
    
    

}
