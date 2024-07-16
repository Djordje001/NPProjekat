/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napredno.programiranje.zajednickiP.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Predstavlja apstraktni domenski objekat koji ima metode neophodne za manipulaciju bazom podataka
 * Sve domenske klase nasledjuju ovu apstraktnu klasu
 *
 * @author Djordje Djordjevic 
 */
public abstract class AbstractDomainObject implements Serializable {

	/**
	 * Vraca naziv tabele domenske klase u bazi
	 * @return String reprezentaciju naziva tabele zeljene domenske klase u kontekstu baze podataka
	 */
    public abstract String nazivTabele();
    
    /**
     * Vraca alijas tabele domenske klase u bazi
     * @return Alijas kao string u kontekstu baza podataka
     */
    public abstract String alijas();
    
    /**
     * Vraca string koji odredjuje join-ove koje treba obaviti da bi se prikupili svi podaci zeljenih tabela
     * @return string reprezentaciju join-ova u kontekstu baza podataka
     */
    public abstract String join();
    
    
    /**
     * Ova metoda ce napraviti listu na osnovu prethodno kreiranog rs operacijom select nad bazom podataka
     * @param rs ResultSet kroz koji ce iterirati i za svaki red rs-a ce praviti novi objekat zeljene domenske klase
     * @return listu objekata zeljene domenske klase
     * @throws java.sql.SQLException ako tip podataka u bazi ne odgovara tipu podatka koji ocekujete u javi,ako se pokusa pristupiti koloni preko pogresnog imena,ili ako ako je resultSet vec zatvoren
     */
    public abstract ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException;
    
    
    /**
     * Vraca moguce kolone koje ce moci da se popunjavaju vrednostima kod operacije insert nad zeljenom tabelom u bazi podataka
     * @return spisak kolona kao String u konstekstu baze podataka
     */
    public abstract String koloneZaInsert();
    
    /**
     * Vraca uslov koji se proverava prilikom operacije delete/update nad zeljenom tabelom u bazi podataka
     * @return uslov kao String u kontekstu baze podataka
     */
    public abstract String uslov();
    
    /**
     * Vraca vrednosti koje treba insertovati u zeljenu tabelu u bazi podataka
     * @return vrednosti kao String u kontekstu baze podataka
     */
    public abstract String vrednostiZaInsert();
    
    
    /**
     * Vraca vrednosti koje treba azurirati nad zeljenom tabelom u bazi podataka
     * @return vrednosti kao String u kontekstu baze podataka
     */
    public abstract String vrednostiZaUpdate();
    
    /**
     * Vraca uslov pri obavljanju operacije select nad zeljenom tabelom u bazi podataka
     * @return string reprezentaciju uslov za select
     */
    public abstract String uslovZaSelect();
    
}
