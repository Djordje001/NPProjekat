package napredno.programiranje.serverP.so.proizvod;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import napredno.programiranje.serverP.db.DBBroker;
import napredno.programiranje.serverP.so.AbstractSO;
import napredno.programiranje.zajednickiP.domain.AbstractDomainObject;
import napredno.programiranje.zajednickiP.domain.Autor;
import napredno.programiranje.zajednickiP.domain.KancelarijskiProizvod;
import napredno.programiranje.zajednickiP.domain.Knjiga;
import napredno.programiranje.zajednickiP.domain.Kupac;
import napredno.programiranje.zajednickiP.domain.Porudzbina;
import napredno.programiranje.zajednickiP.domain.Proizvod;
import napredno.programiranje.zajednickiP.domain.StavkaPorudzbine;

/**
 *  ova klasa predstavlja sistemsku operaciju za dodavanje proizvoda
 *  dodace se red u tabeli Proizvod i jedan red u tabeli Knjiga ili KancelarijskiProizvod u zavisnosti od tipa,
 *  ukoliko se dodaje red u tabeli Knjiga dodace se i redovi u tabeli Autori(ako postoje autori za unetu knjigu)
 * @author Djordje Djordjevic
 */
public class SOAddProizvod extends AbstractSO {

	
	/**
     * ova metoda ce ispitati da li je apstraktni domenski objekat validan
     * @param ado apstraktni domenski objekat nad kojim vrsimo validaciju
     * @throws java.lang.NullPointerException ukoliko je prosledjena null vrednost
     * @throws java.lang.IllegalArgumentException ukoliko nije prosledjena instanca klase Proizvod
     */
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
    	if(ado==null) {
    		throw new NullPointerException("Prosledjeni objekat je null");
    	}
        if (!(ado instanceof Proizvod)) {
            throw new IllegalArgumentException("Prosledjeni objekat nije instanca klase Proizvod!");
        }

     

    }

    
    /**
     * ova metoda treba da izvrsi sistemsku operaciju dodavanja proizvoda u bazu pozivanjem insert metode naseg DBBrokera,
     * takodje dodace se i red u tabeli Knjiga ili KancelarijskiMaterijal u zavinosti od tipa
     * ukoliko se dodaje red u tabeli knjiga dodace se i redovi u tabeli Autor(ukoliko postoje autori za konkretnu knjigu)
     * @param ado apstraktni domenski objekat nad kojim se izvrsava SO
     * @throws java.lang.Exception ukoliko dodje do kreske prilikom izvrsavanja upita u bazi
     */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
    	Proizvod proizvod=(Proizvod)ado;
    	//System.out.println(p);
        //Proizvod dodat=DBBroker.getInstance().insert(p);
    	Proizvod p=new Proizvod(null, proizvod.getCena(), proizvod.getNaziv(), proizvod.getTip());
        
        
        System.out.println("nadotac:"+p);
        
         PreparedStatement ps = DBBroker.getInstance().insert(p);
        
        
        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        Long proizvodID = tableKeys.getLong(1);
        
        
        
        
     //  Porudzbina p = (Porudzbina) ado;
       // p.setPorudzbinaID(porudzbinaID);
        
        if(p.getTip()==1) {
        	System.out.println("kancelarijski je u pitanju");
    		KancelarijskiProizvod kp=(KancelarijskiProizvod)ado;
    	//	System.out.println(kp);
    		
    		kp.setProizvodID(proizvodID);
    		System.out.println(kp);
    		 DBBroker.getInstance().insert(kp);
    	}
        if(p.getTip()==2)
        {
    		System.out.println("knjiga je u pitanju");
    		Knjiga k=(Knjiga)ado;
    		
        		//System.out.println(k);
        		k.setProizvodID(proizvodID);
        		
        		//System.out.println(k);
        		DBBroker.getInstance().insert(k);
        		
        		ArrayList<Autor> autori=k.getAutori();
        		for(Autor autor : autori) {
        			 autor.setKnjiga(k);
        			 PreparedStatement kec=  DBBroker.getInstance().insert(autor);
        		     
        		     
        		     
        		        
        		        
        		        ResultSet forica = kec.getGeneratedKeys();
        		        forica.next();
        		        long rb = forica.getLong(1);
        		     int x=(int) rb;
        		        autor.setRb(x);
        		}
    		
    		
    		
    		
    		 //DBBroker.getInstance().insert(k);
    	}
        
        
        /*for (StavkaPorudzbine stavkaPorudzbine : p.getStavkePorudzbine()) {
            stavkaPorudzbine.setPorudzbina(p);
            DBBroker.getInstance().insert(stavkaPorudzbine);
        }*/
        
        
        
    }

}
