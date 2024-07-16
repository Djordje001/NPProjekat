package org.serverP.so.porudzbina;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.serverP.db.DBBroker;
import org.serverP.so.AbstractSO;
import org.serverP.so.AbstractSOTest;
import org.serverP.so.administrator.SOGetAllAdministrator;
import org.serverP.so.kupac.SOGetAllKupac;
import org.serverP.so.proizvod.SOGetAllProizvod;
import org.serverP.so.stavkaPorudzbine.SOFindAllStavkaPorudzbine;
import org.zajednickiP.domain.Administrator;
import org.zajednickiP.domain.KancelarijskiProizvod;
import org.zajednickiP.domain.Knjiga;
import org.zajednickiP.domain.Kupac;
import org.zajednickiP.domain.Porudzbina;
import org.zajednickiP.domain.Proizvod;
import org.zajednickiP.domain.StavkaPorudzbine;

class SOAddPorudzbinaTest extends AbstractSOTest{

	SOAddPorudzbina so;
	SOGetAllPorudzbina pomocna1;
	SOGetAllKupac soGAK;
	SOGetAllAdministrator soGAA;
	SOGetAllProizvod soGAP;
	SOFindAllStavkaPorudzbine soFASP;
	
	Porudzbina p;
	Proizvod p1;
	Proizvod p2;
	StavkaPorudzbine sp1;
	StavkaPorudzbine sp2;
	Administrator a;
	Kupac k;
	public AbstractSO getInstance() {
		return new SOAddPorudzbina();
	}
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		so=new SOAddPorudzbina();
		pomocna1=new SOGetAllPorudzbina();
		soGAK=new SOGetAllKupac();
		soGAA=new SOGetAllAdministrator();
		soGAP=new SOGetAllProizvod();
		soFASP=new SOFindAllStavkaPorudzbine();
	}

	@AfterEach
	void tearDown() throws Exception {
		so=null;
		pomocna1=null;
		soGAK=null;
		soGAA=null;
		soGAP=null;
		soFASP=null;
	}

	@Test
	void testTemplateExecuteNeuspesno() {
		assertThrows(java.lang.NullPointerException.class,()->so.templateExecute(null));
		assertThrows(java.lang.IllegalArgumentException.class,()->so.templateExecute(new Kupac(null, "x", "x", "x", "x")));
		Kupac kupac=new Kupac(1L, "x", "x", "x", "x");
		Administrator administrator=new Administrator(2L,"x","X","x","x");
		try {
			
			  SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		        Date datumIsporuke = sdf.parse("21.05.2014.");
			assertThrows(java.lang.IllegalArgumentException.class,()->so.templateExecute(new Porudzbina(2L, new Date(),datumIsporuke, "12","123", 1, 2, 3, kupac, administrator, new ArrayList<>())));
		}catch(Exception e) {
			System.out.println(e);
		}
		assertThrows(java.lang.IllegalArgumentException.class,()->so.templateExecute(new Porudzbina(2L, new Date(),new Date(), "12","123", 1, 2, 3, kupac, administrator, new ArrayList<>())));
	}
	
	@Test
	void testTemplateExecuteUspesno() throws Exception {
		Kupac kupac=new Kupac(1L, "x", "x", "x", "x");
		Administrator administrator=new Administrator(2L,"x","X","x","x");
		
		
		
		pomocna1.templateExecute(new Porudzbina(2L, new Date(), new Date(), "12","123", 1, 2, 3, kupac, administrator, new ArrayList<>()));
		ArrayList<Porudzbina> porudzbine=pomocna1.getLista();
		int brojPorudzbina=porudzbine.size();
		
		
		
		
		soGAP.templateExecute(new Proizvod(null, 2, "123", 1));
		ArrayList<Proizvod> proizvodi=soGAP.getLista();
		if(proizvodi.size()>2) {
		p1=proizvodi.get(0);
	    p2=proizvodi.get(1);
		
		soGAA.templateExecute(new Administrator(null, "1","1", "1", "1"));
		ArrayList<Administrator> administratori=soGAA.getLista();
		if(administratori.size()>0) {
		 a=administratori.get(0);
	   
	   
		 soGAK.templateExecute(new Kupac(null, "1", "2", "2", "2"));
		 ArrayList<Kupac> kupci=soGAK.getLista();
		 if(kupci.size()>0) {
			 k=kupci.get(0);
			  SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		        Date datumIsporuke = sdf.parse("21.05.2025.");
		p=new Porudzbina(20L, new Date(), datumIsporuke, "Grad", "Adresa", 20, 10,18, k,a, new ArrayList<>());
		
		
		 sp1=new StavkaPorudzbine(p,20, 3, 5000, p1);
		 sp2=new StavkaPorudzbine(p,30, 5, 6000, p2);
		p.getStavkePorudzbine().add(sp1);
		p.getStavkePorudzbine().add(sp2);
		
		assertFalse(porudzbine.contains(p));
		
		so.templateExecute(p);
		System.out.println(p.getPorudzbinaID());
		
		pomocna1.templateExecute(new Porudzbina(2L, new Date(), new Date(), "12","123", 1, 2, 3, kupac, administrator, new ArrayList<>()));
		ArrayList<Porudzbina> nove=pomocna1.getLista();
		assertTrue(nove.size()==brojPorudzbina+1);
		
		assertTrue(nove.contains(p));
		
		StavkaPorudzbine pomocna=new StavkaPorudzbine(p, 1, 3,2, new Proizvod(null, 1, "x", 1));
		soFASP.templateExecute(pomocna);
		ArrayList<StavkaPorudzbine> stavke=soFASP.getLista();
		
		assertEquals(stavke,p.getStavkePorudzbine());
		
		
		try {
	        String upit="DELETE FROM porudzbina WHERE PorudzbinaID = "+p.getPorudzbinaID();
			Statement s=DBBroker.getInstance().getConnection().createStatement();
			s.executeUpdate(upit);
			System.out.println(upit);
			DBBroker.getInstance().getConnection().commit();
			}catch(Exception e) {
				DBBroker.getInstance().getConnection().rollback();
			}
		
		}
		}
		}
	}

}
