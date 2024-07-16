/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napredno.programiranje.serverP.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import napredno.programiranje.serverP.so.administrator.SOGetAllAdministrator;
import napredno.programiranje.serverP.so.autor.SOFindAllAutor;
import napredno.programiranje.serverP.so.kupac.SOAddKupac;
import napredno.programiranje.serverP.so.kupac.SOGetAllKupac;
import napredno.programiranje.serverP.so.login.SOLogin;
import napredno.programiranje.serverP.so.pisac.SOGetAllPisac;
import napredno.programiranje.serverP.so.porudzbina.SOAddPorudzbina;
import napredno.programiranje.serverP.so.porudzbina.SODeletePorudzbina;
import napredno.programiranje.serverP.so.porudzbina.SOFindAllPorudzbina;
import napredno.programiranje.serverP.so.porudzbina.SOGetAllPorudzbina;
import napredno.programiranje.serverP.so.porudzbina.SOUpdatePorudzbina;
import napredno.programiranje.serverP.so.proizvod.SOAddProizvod;
import napredno.programiranje.serverP.so.proizvod.SODeleteProizvod;
import napredno.programiranje.serverP.so.proizvod.SOFindAllProizvod;
import napredno.programiranje.serverP.so.proizvod.SOGetAllProizvod;
import napredno.programiranje.serverP.so.proizvod.SOUpdateProizvod;
import napredno.programiranje.serverP.so.stavkaPorudzbine.SOFindAllStavkaPorudzbine;
import napredno.programiranje.zajednickiP.domain.Administrator;
import napredno.programiranje.zajednickiP.domain.Autor;
import napredno.programiranje.zajednickiP.domain.Knjiga;
import napredno.programiranje.zajednickiP.domain.Kupac;
import napredno.programiranje.zajednickiP.domain.Pisac;
import napredno.programiranje.zajednickiP.domain.Porudzbina;
import napredno.programiranje.zajednickiP.domain.Proizvod;
import napredno.programiranje.zajednickiP.domain.StavkaPorudzbine;




/**
 *
 * @author Lenovo
 */
public class ServerController {

    private static ServerController instance;

    private ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    
    public Administrator login(Administrator administrator) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(administrator);
        return so.getUlogovani();
    }
    
    

    public void addKupac(Kupac kupac) throws Exception {
        (new SOAddKupac()).templateExecute(kupac);
    }
    public void addProizvod(Proizvod proizvod) throws Exception {
    	(new SOAddProizvod()).templateExecute(proizvod);
    }

    public void addPorudzbina(Porudzbina porudzbina) throws Exception {
        (new SOAddPorudzbina()).templateExecute(porudzbina);
    }

   

    public void deleteProizvod(Proizvod proizvod) throws Exception {
    	(new SODeleteProizvod()).templateExecute(proizvod);
    }
    public void deletePorudzbina(Porudzbina porudzbina) throws Exception {
        (new SODeletePorudzbina()).templateExecute(porudzbina);
    }
   

    
    public void updateProizvod(Proizvod proizvod) throws Exception {
        (new SOUpdateProizvod()).templateExecute(proizvod);
    }
    public void updatePorudzbina(Porudzbina porudzbina) throws Exception {
    	System.out.println(porudzbina.getStavkePorudzbine());
        (new SOUpdatePorudzbina()).templateExecute(porudzbina);
    }
    
    
    
    public ArrayList<Proizvod> findAllProizvod(Proizvod p) throws Exception  {
    	SOFindAllProizvod so=new SOFindAllProizvod();
    	so.templateExecute(p);
    	return so.getLista();
    }
    public ArrayList<Porudzbina> findAllPorudzbina(Porudzbina p) throws Exception {
        SOFindAllPorudzbina so = new SOFindAllPorudzbina();
        so.templateExecute(p);
        return so.getLista();
    }
    public ArrayList<StavkaPorudzbine> findAllStavkaPorudzbine(Porudzbina p) throws Exception {
        SOFindAllStavkaPorudzbine so = new SOFindAllStavkaPorudzbine();
        
        //StavkaPorudzbine sp = new StavkaPorudzbine();
        Proizvod proizvod=new Proizvod(null, 1, "pravim ovako samo zato jer nemam bezparamteraski konstruktor", 1);
        StavkaPorudzbine sp=new StavkaPorudzbine(p, 0, 1, 1, proizvod);
       // sp.setPorudzbina(p);
        
        so.templateExecute(sp);
        return so.getLista();
    }
    public ArrayList<Autor> findAllAutor(Knjiga k) throws Exception {
        SOFindAllAutor so = new SOFindAllAutor();
        
        /*StavkaPorudzbine sp = new StavkaPorudzbine();
        sp.setPorudzbina(p);*/
        
      //  Autor autor=new Autor();
        Pisac pisac=new Pisac(null, "pravim ovako samo zato jer nemam bezparametarski konstruktor", "x", "x");
        Autor autor=new Autor(k, 0,pisac );
       // autor.setKnjiga(k);
        System.out.println(autor);
        
        so.templateExecute(autor);
        return so.getLista();
    }
    
    

    public ArrayList<Administrator> getAllAdministrator() throws Exception {
        SOGetAllAdministrator so = new SOGetAllAdministrator();
        Administrator administrator=new Administrator(null,"pravim ovako samo zato jer nemamo bezparametarski konstruktor", "x", "x", "x");
        so.templateExecute(administrator);
        return so.getLista();
    }
    public ArrayList<Kupac> getAllKupac() throws Exception {
        SOGetAllKupac so = new SOGetAllKupac();
        Kupac kupac=new Kupac(null, "pravim ovako samo zato jer nemam bezparametarski konstruktor","x", "x", "x");
        so.templateExecute(kupac);
        return so.getLista();
    }
    public ArrayList<Pisac> getAllPisac() throws Exception {
        SOGetAllPisac so = new SOGetAllPisac();
        Pisac pisac=new Pisac(null,"pravim ovako samo zato jer nemam bezparamteraski konstruktor","x","x");
        so.templateExecute(pisac);
        return so.getLista();
    }
    public ArrayList<Proizvod> getAllProizvod() throws Exception {
        SOGetAllProizvod so = new SOGetAllProizvod();
        Proizvod proizvod=new Proizvod(null, 1, "pravim ovako samo zato jer neamm bezparamteraski konstruktor", 1);
        so.templateExecute(proizvod);
        return so.getLista();
    }
    public ArrayList<Porudzbina> getAllPorudzbina() throws Exception {
        SOGetAllPorudzbina so = new SOGetAllPorudzbina();
        Administrator administrator=new Administrator(null, "x", "x", "x", "x");
        Kupac kupac=new Kupac(null,"x","x","x","x");
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date datumIsporuke = sdf.parse("21.05.3025.");
        Porudzbina porudzbina=new Porudzbina(null, new Date(), datumIsporuke, "pravim ovako samo zato jer nemam bezparametarski konstruktor", "x", 1, 10, 100,kupac,administrator, new ArrayList<>());
        so.templateExecute(porudzbina);
        return so.getLista();
    }
    
   
}
