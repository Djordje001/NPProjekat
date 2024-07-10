package org.serverP;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.serverP.db.DBBroker;
import org.serverP.so.administrator.SOGetAllAdministrator;
import org.serverP.thread.ThreadServer;
import org.zajednickiP.domain.AbstractDomainObject;
import org.zajednickiP.domain.Administrator;
import org.zajednickiP.domain.Autor;
import org.zajednickiP.domain.KancelarijskiProizvod;
import org.zajednickiP.domain.Knjiga;
import org.zajednickiP.domain.Kupac;
import org.zajednickiP.domain.Pisac;
import org.zajednickiP.domain.Porudzbina;
import org.zajednickiP.domain.Proizvod;
import org.zajednickiP.domain.StavkaPorudzbine;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Hello world!
 *
 */
public class App 
{
	private static ThreadServer threadServer;
    public static void main( String[] args )
    {
    	if (threadServer == null || !threadServer.isAlive()) {
			System.out.println("zelimo da upalimo");
			threadServer = new ThreadServer();
			threadServer.start();
		}
    }
    
    
    public static void serijalizujPromenu() {
        try (FileWriter x=new FileWriter("src/test/resources/porudzbine.json")){
        	Gson gson=new GsonBuilder().setPrettyPrinting().create();
        	
        	//-----moram na ovaj nacin da napravim porudzbinu jer sam izbacio bezparametarske konstrukture kod svih domenskih klasa
        	Administrator administrator=new Administrator(null, "x", "x", "x", "x");
        	Kupac kupac=new Kupac(null, "x", "x", "x", "x");
        	ArrayList<StavkaPorudzbine> stavkePorudzbine=new ArrayList<>();
        	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Date datumIsporuke = sdf.parse("21.05.3025.");
        	Porudzbina porudzbinaPomocna=new Porudzbina(null, new Date(), datumIsporuke, "x", "x", 1, 1, 1, kupac, administrator, stavkePorudzbine);
        	//-----
        	
        	
        	 ArrayList<AbstractDomainObject> porudzbineADO = DBBroker.getInstance().select(porudzbinaPomocna);
             ArrayList<Porudzbina>lista = (ArrayList<Porudzbina>) (ArrayList<?>) porudzbineADO;
             
             JsonArray glavniNiz=new JsonArray();
             for(Porudzbina porudzbina : lista) {
            	 JsonObject porudzbinaObjekat=new JsonObject();
            	 porudzbinaObjekat.addProperty("PorudzbinaID",porudzbina.getPorudzbinaID());
            	 porudzbinaObjekat.addProperty("DatumVreme",porudzbina.getDatumVreme().toString());
            	 porudzbinaObjekat.addProperty("DatumIsporuke",porudzbina.getDatumIsporuke().toString());
            	 porudzbinaObjekat.addProperty("Grad", porudzbina.getGrad());
            	 porudzbinaObjekat.addProperty("Adresa", porudzbina.getAdresa());
            	 porudzbinaObjekat.addProperty("Cena", porudzbina.getCena());
            	 porudzbinaObjekat.addProperty("Popust", porudzbina.getPopust());
            	 porudzbinaObjekat.addProperty("KonacnaCena",porudzbina.getKonacnaCena());
            	 
            	 JsonObject administratorObjekat=new JsonObject();
            	 administratorObjekat.addProperty("AdministratorID", porudzbina.getAdministrator().getAdministratorID());
            	 administratorObjekat.addProperty("Ime", porudzbina.getAdministrator().getIme());
            	 administratorObjekat.addProperty("Prezime", porudzbina.getAdministrator().getPrezime());
            	 administratorObjekat.addProperty("Username", porudzbina.getAdministrator().getUsername());
            	 administratorObjekat.addProperty("Password", porudzbina.getAdministrator().getPassword());
            	 
            	 porudzbinaObjekat.add("Administrator", administratorObjekat);
            	 
            	 JsonObject kupacObjekat=new JsonObject();
            	 kupacObjekat.addProperty("KupacID", porudzbina.getKupac().getKupacID());
            	 kupacObjekat.addProperty("Ime", porudzbina.getKupac().getIme());
            	 kupacObjekat.addProperty("Prezime", porudzbina.getKupac().getPrezime());
            	 kupacObjekat.addProperty("Email", porudzbina.getKupac().getEmail());
            	 kupacObjekat.addProperty("TipKupca", porudzbina.getKupac().getTipKupca());
            	 
            	 porudzbinaObjekat.add("Kupac", kupacObjekat);
            	 
            	 Proizvod proizvod=new KancelarijskiProizvod(null, 1, "123", 1, "123", "123", 1, 1, 1);
            	 StavkaPorudzbine stavka=new StavkaPorudzbine(porudzbina, 0, 1, 1,proizvod);
            	 
            	 
            	 ArrayList<AbstractDomainObject> s=DBBroker.getInstance().find(stavka);
            	 ArrayList<StavkaPorudzbine>stavke = (ArrayList<StavkaPorudzbine>) (ArrayList<?>) s;
            	 
            	 JsonArray stavkeNiz=new JsonArray();
            	 for(StavkaPorudzbine sp : stavke) {
            		 JsonObject stavkaObjekat=new JsonObject();
            		 stavkaObjekat.addProperty("Rb", sp.getRb());
            		 stavkaObjekat.addProperty("Kolicina", sp.getKolicina());
            		 stavkaObjekat.addProperty("Cena", sp.getCena());
            		 
            		 JsonObject proizvodObjekat=new JsonObject();
            		 proizvodObjekat.addProperty("proizvodID", sp.getProizvod().getProizvodID());
            		 proizvodObjekat.addProperty("cena proizvoda", sp.getProizvod().getCena());
            		 proizvodObjekat.addProperty("naziv", sp.getProizvod().getNaziv());
            		 proizvodObjekat.addProperty("tip", sp.getProizvod().getTip());
            		 
            		 if(sp.getProizvod().getTip()==1) {
            			 KancelarijskiProizvod kmp=(KancelarijskiProizvod) sp.getProizvod();
            			 proizvodObjekat.addProperty("vrsta", kmp.getVrsta());
            			 proizvodObjekat.addProperty("proizvodjac", kmp.getProizvodjac());
            			 proizvodObjekat.addProperty("visina", kmp.getVisina());
            			 proizvodObjekat.addProperty("sirina", kmp.getSirina());
            			 proizvodObjekat.addProperty("duzina", kmp.getDuzina());
            		 }
            		 if(sp.getProizvod().getTip()==2) {
            			 Knjiga kp=(Knjiga) sp.getProizvod();
            			 proizvodObjekat.addProperty("izdanje", kp.getIzdanje());
            			 proizvodObjekat.addProperty("opis", kp.getOpis());
            			 
            		     Pisac pisac=new Pisac(null, "x", "x", "x");
            			 Proizvod pomocni=sp.getProizvod();
            			 Knjiga knjiga=(Knjiga) pomocni;
            			 Autor a=new Autor(knjiga, 0, pisac);
            		
            			 ArrayList<AbstractDomainObject> w=DBBroker.getInstance().find(a);
                    	 ArrayList<Autor> autori = (ArrayList<Autor>) (ArrayList<?>) w;
                    	 System.out.println("ovde");
                    	 knjiga.setAutori(autori);
                    	 System.out.println("ovde1");
                    	
                    	 sp.setProizvod(knjiga);
                    	 
                    	 JsonArray autoriNiz=new JsonArray();
                    	 for(Autor c : autori) {
                    		 JsonObject autorObjekat=new JsonObject();
                    		 autorObjekat.addProperty("Rb",c.getRb());
                    		 JsonObject pisacObjekat=new JsonObject();
                    		 pisacObjekat.addProperty("PisacID",c.getPisac().getPisacID());
                    		 pisacObjekat.addProperty("Ime",c.getPisac().getIme());
                    		 pisacObjekat.addProperty("Prezime",c.getPisac().getPrezime());
                    		 pisacObjekat.addProperty("Email",c.getPisac().getEmail());
                    		 
                    		 autorObjekat.add("Pisac", pisacObjekat);
                    		 autoriNiz.add(autorObjekat);
                    	 }
                    	 proizvodObjekat.add("Autori", autoriNiz);
                    	 
            		 }
            		 stavkaObjekat.add("Proizvod", proizvodObjekat);
            		 stavkeNiz.add(stavkaObjekat);
            	 }
            	 porudzbinaObjekat.add("StavkePorudzbine", stavkeNiz);
            	 porudzbina.setStavkePorudzbine(stavke);
            	 
            	 
            	 glavniNiz.add(porudzbinaObjekat);
             }
             System.out.println(lista);
             x.write(gson.toJson(glavniNiz));
        	
        	
        }catch(Exception e) {
        	System.out.println(e);
        }
}
    
}
