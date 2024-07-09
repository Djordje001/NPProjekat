package org.klijentP;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.event.CaretListener;

import org.klijentP.controller.ClientController;
import org.zajednickiP.domain.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
       // addProizvod();
       // deleteProizvod();
        //getStavke();
        //getAllProizvod();
       // findAllProizvod();
        //login();
       // updateProizvod();
       // getAllPisac();
       // getAllKupac();
       // addKupac();
        //addPorudzbina();
        //findAllPorudzbina();
      //  deletePorudzbina();
   //     updatePorudzbina();
        //findAllStavke();
        
    }
    public static void login() {
    	try {
    		Administrator a=new Administrator(null, "djole", "djole123", "123", "123");
    		
        	Administrator pronadjen=ClientController.getInstance().login(a);
        	System.out.println(pronadjen);
        	//ClientController.getInstance().deleteProizvod(proizvodi.get(2));
        	}
        	catch(Exception e) {
        		System.out.println(e.getMessage());
        	}
    }
    
    
    
    public static void addKupac() {
    	try {
    		ArrayList<Kupac> kupci=ClientController.getInstance().getAllKupac();
    		System.out.println(kupci);
    		Kupac k=new Kupac(null,"Milojko","Pantic","milojko.pantic@gmail.com","FREE");
    		
    		ClientController.getInstance().addKupac(k);
    		
    		kupci=ClientController.getInstance().getAllKupac();
    		System.out.println(kupci);
        	}
        	catch(Exception e) {
        		System.out.println(e.getMessage());
        	}
    }
    public static void addProizvod()  {
    	try {
    	ArrayList<Pisac> pisci=ClientController.getInstance().getAllPisac();
    	System.out.println(pisci);
    	//Proizvod p=new Knjiga(null,10000, "Leglo", 2, 1, "Predobro", null)
    	ArrayList<Autor> autori=new ArrayList<>();
        for(Pisac pisac : pisci) {
        	autori.add(new Autor(null,0,pisac));
        }
    	Proizvod p=new Knjiga(null,10000,"Leglo",2,1,"Predobro",autori);
    	//p=new KancelarijskiProizvod(null,3000,"Ne znam ni ja",1,"lego kokcica","deda mile",0.2,0.4,0.6);
    	p=new Knjiga(null,5000,"Naziv proizvoda",2,2,"Harry Poter",autori);
    	System.out.println("proslo");
    	ClientController.getInstance().addProizvod(p);
    	}catch(Exception e) {
    		System.out.println(e.getMessage());
    		System.out.println("ovde");
    	}
    }
    public static void addPorudzbina() {
    	try {
        	
        	
        	ArrayList<Proizvod> proizvodi=ClientController.getInstance().getAllProizvod();
        	ArrayList<Kupac> kupci=ClientController.getInstance().getAllKupac();
         	ArrayList<Administrator> administratori=ClientController.getInstance().getAllAdministrator();
        	ArrayList<StavkaPorudzbine> stavke=new ArrayList<>();
        	
        	if(proizvodi.size()>1 && administratori.size()>0 && kupci.size()>0) {
        	Administrator administrator=administratori.get(0);
        	Kupac kupac=kupci.get(0);
        	Proizvod proizvod=proizvodi.get(0);
        	Proizvod proizvod1=proizvodi.get(2);
        	
        
        	
        	
        	Date datumVreme=new Date();
        	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	        Date datumIsporuke = sdf.parse("21.05.2025.");
	        String grad="Ljuba";
	        String adresa="Adresa 100";
	        double cena=18300;
	        double popust=50;
	        double konacnaCena=cena-(cena/popust);
	        
        	//Long porudzbinaID, Date datumVreme, Date datumIsporuke, String grad, String adresa, double cena, double popust, double konacnaCena, Kupac kupac, Administrator administrator, ArrayList<StavkaPorudzbine> stavkePorudzbine
        	Porudzbina p=new Porudzbina(null,datumVreme,datumIsporuke,grad,adresa,cena,popust,konacnaCena,kupac, administrator,stavke);
        	stavke.add(new StavkaPorudzbine(p, 1, 2, 10000, proizvod));
         	stavke.add(new StavkaPorudzbine(p, 2, 3, 9999, proizvod1));
        	ClientController.getInstance().addPorudzbina(p);
        	
        	}
        	}catch(Exception e) {
        		System.out.println(e.getMessage());
        		System.out.println("ovde");
        	}
    }
    
    
    
    
    public static void deleteProizvod() {
    	try {
    	ArrayList<Proizvod> proizvodi=ClientController.getInstance().getAllProizvod();
    	System.out.println(proizvodi);
    	if(proizvodi.size()>0) {
    	ClientController.getInstance().deleteProizvod(proizvodi.get(0));
    	}
    	}
    	catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    	
    }
    public static void deletePorudzbina() {
    	try {
    		ArrayList<Porudzbina> porudzbine=ClientController.getInstance().getAllPorudzbina();
    		if(porudzbine.size()>0) {
    			ClientController.getInstance().deletePorudzbina(porudzbine.get(0));
    		}
    
        	}
        	catch(Exception e) {
        		System.out.println(e.getMessage());
        	}
    }
    
  
    public static void updateProizvod() {
    	try {
    		ArrayList<Pisac> pisci=ClientController.getInstance().getAllPisac();
        	System.out.println(pisci);
        	
        	
        	ArrayList<Autor> autori=new ArrayList<>();
            for(Pisac pisac : pisci) {
            	autori.add(new Autor(null,0,pisac));
            	
            }
    	 
            ArrayList<Autor> autoriNovi=new ArrayList<>();
            if(autori.size()>0) {
            autoriNovi.add(autori.get(0));
            ArrayList<Proizvod> proizvodi=ClientController.getInstance().getAllProizvod();
            if(proizvodi.size()>0) {
            	for(Proizvod p : proizvodi) {
            		if(p.getTip()==2) {
            	 Proizvod proizvod=p;
        		 ClientController.getInstance().updateProizvod(proizvod);
            		}
            	}
            }
    		
            }
        	}
        	catch(Exception e) {
        		System.out.println(e.getMessage());
        	}
    }
    public static void updatePorudzbina() {
    	try {
    		ArrayList<Porudzbina> porudzbine=ClientController.getInstance().getAllPorudzbina();
    		if(porudzbine.size()>0) {
    		System.out.println(porudzbine);
    		System.out.println(porudzbine.get(0));
    		 Porudzbina p=porudzbine.get(0);
    		 p.setAdresa("meraklije");
    		
    		 p.setGrad("Bela Palanka");
    	    
    		 double cena=0;
    		 ArrayList<Proizvod> proizvodi=ClientController.getInstance().getAllProizvod();
    		 if(proizvodi.size()>0) {
    		 ArrayList<StavkaPorudzbine> stavke=new ArrayList<StavkaPorudzbine>();
    		 stavke.add(new StavkaPorudzbine(p,1,3,3*proizvodi.get(0).getCena(),proizvodi.get(0)));
    		// stavke.add(new StavkaPorudzbine(p,2,4,4*proizvodi.get(1).getCena(),proizvodi.get(1)));
    		 p.setStavkePorudzbine(stavke);
    		 cena+=stavke.get(0).getCena();
    		// cena+=stavke.get(1).getCena();
    		 p.setKonacnaCena(cena-(p.getPopust()/100)*cena);
    		 p.setCena(cena);
    		   SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	            Date datumIsporuke = sdf.parse("23.12.2026.");
	            p.setDatumIsporuke(datumIsporuke);
    		 ClientController.getInstance().updatePorudzbina(p);
    		 }
    		}
    	    //    public Porudzbina(Long porudzbinaID, Date datumVreme, Date datumIsporuke, String grad, String adresa, double cena, double popust, double konacnaCena, Kupac kupac, Administrator administrator, ArrayList<StavkaPorudzbine> stavkePorudzbine)
        	}
        	catch(Exception e) {
        		System.out.println(e.getMessage());
        	}
    }
    
    
    public static void findAllProizvod() {
    	try {
    		Proizvod p=new Proizvod(0L,2,"Hari poter",1);
    		
        	ArrayList<Proizvod> proizvodi=ClientController.getInstance().findAllProizvod(p);
        	System.out.println(proizvodi);
        	//ClientController.getInstance().deleteProizvod(proizvodi.get(2));
        	}
        	catch(Exception e) {
        		System.out.println(e.getMessage());
        	}
    }
    public static void findAllPorudzbina() {
    	try {
    		ArrayList<Porudzbina> porudzbine=ClientController.getInstance().getAllPorudzbina();
    		Kupac k=new Kupac(null,"Dusanka","Djordjevic","dusanka@gmail.com","PREMIUM");
    		if(porudzbine.size()>0) {
    			Porudzbina p=porudzbine.get(0);
    			p.setKupac(k);
    			ArrayList<Porudzbina> porudzbineKonkretnogKupca=ClientController.getInstance().findAllPorudzbina(p);
        		System.out.println(porudzbineKonkretnogKupca);
    		}
    		
    		
    		
        	}
        	catch(Exception e) {
        		System.out.println(e.getMessage());
        	}
    }
    public static void findAllStavke() {
    	try {
    		
    		ArrayList<Porudzbina> porudzbine=ClientController.getInstance().getAllPorudzbina();
    		if(porudzbine.size()>0) {
    			ArrayList<StavkaPorudzbine> stavke=ClientController.getInstance().findAllStavkaPorudzbine(porudzbine.get(0));
        		System.out.println(stavke);
    		}
    		
    	}catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    }
    public static void findAllAutori() {
    	try {
    		ArrayList<Proizvod> proizvodi=ClientController.getInstance().getAllProizvod();
    		Knjiga k=new Knjiga(null,1,"proba",2,2,"hehe",new ArrayList());
    		for(Proizvod p : proizvodi) {
    			if(p.getTip()==2) {
    				k=(Knjiga) p;
    				ArrayList<Autor> autori=ClientController.getInstance().findAllAutor(k);
    	    		System.out.println(autori);
    			}
    		}
    		
    	}catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    public static void getAllAdministrator() {
    	try {
    		ArrayList<Administrator> administratori=ClientController.getInstance().getAllAdministrator();
    		System.out.println(administratori);
    	}catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    }
    public static void getAllKupac() {
    	try {
    		ArrayList<Kupac> kupci=ClientController.getInstance().getAllKupac();
    		System.out.println(kupci);
    	}catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    }
    public static void getAllPisac() {
    	try {
    		ArrayList<Pisac> pisci=ClientController.getInstance().getAllPisac();
    		System.out.println(pisci);
    	}catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    }
    public static void getAllProizvod() {
    	try {
        	ArrayList<Proizvod> proizvodi=ClientController.getInstance().getAllProizvod();
        	System.out.println(proizvodi);
        	//ClientController.getInstance().deleteProizvod(proizvodi.get(2));
        	}
        	catch(Exception e) {
        		System.out.println(e.getMessage());
        	}
    }
    public static void getAllPorudzbina() {
    	try {
        	ArrayList<Porudzbina> porudzbine=ClientController.getInstance().getAllPorudzbina();
        	System.out.println(porudzbine);
        	//ClientController.getInstance().deleteProizvod(proizvodi.get(2));
        	}
        	catch(Exception e) {
        		System.out.println(e.getMessage());
        	}
    }
    
    
  
    
}
