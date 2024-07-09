package org.serverP.so.proizvod;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.serverP.db.DBBroker;
import org.serverP.so.AbstractSO;
import org.zajednickiP.domain.AbstractDomainObject;
import org.zajednickiP.domain.Autor;
import org.zajednickiP.domain.KancelarijskiProizvod;
import org.zajednickiP.domain.Knjiga;
import org.zajednickiP.domain.Kupac;
import org.zajednickiP.domain.Porudzbina;
import org.zajednickiP.domain.Proizvod;
import org.zajednickiP.domain.StavkaPorudzbine;

/**
 *
 * @author Lenovo
 */
public class SOAddProizvod extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
    	if(ado==null) {
    		throw new NullPointerException("Prosledjeni objekat je null");
    	}
        if (!(ado instanceof Proizvod)) {
            throw new IllegalArgumentException("Prosledjeni objekat nije instanca klase Proizvod!");
        }

     

    }

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
        		     DBBroker.getInstance().insert(autor);
        		}
    		
    		
    		
    		
    		 //DBBroker.getInstance().insert(k);
    	}
        
        
        /*for (StavkaPorudzbine stavkaPorudzbine : p.getStavkePorudzbine()) {
            stavkaPorudzbine.setPorudzbina(p);
            DBBroker.getInstance().insert(stavkaPorudzbine);
        }*/
        
        
        
    }

}