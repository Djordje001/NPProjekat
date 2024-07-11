package org.serverP.so.proizvod;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.serverP.so.AbstractSO;
import org.serverP.so.AbstractSOTest;
import org.serverP.so.autor.SOFindAllAutor;
import org.serverP.so.pisac.SOGetAllPisac;
import org.zajednickiP.domain.Autor;
import org.zajednickiP.domain.KancelarijskiProizvod;
import org.zajednickiP.domain.Knjiga;
import org.zajednickiP.domain.Kupac;
import org.zajednickiP.domain.Pisac;
import org.zajednickiP.domain.Proizvod;

class SOUpdateProizvodTest extends AbstractSOTest {

	SOUpdateProizvod so;
	SOGetAllProizvod soGAP;
	SOFindAllAutor soFAA;
	SOGetAllPisac soGAP1;
	public AbstractSO getInstance() {
		return new SOUpdateProizvod();
	}
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		so=new SOUpdateProizvod();
		soGAP=new SOGetAllProizvod();
		soFAA=new SOFindAllAutor();
		soGAP1=new SOGetAllPisac();
	}

	@AfterEach
	void tearDown() throws Exception {
		so=null;
		soGAP=null;
		soFAA=null;
		soGAP1=null;
	}

	
	@Test
	void testTemplateExecutePogresno() {
		assertThrows(java.lang.NullPointerException.class,()->so.templateExecute(null));
		assertThrows(java.lang.IllegalArgumentException.class,()->so.templateExecute(new Kupac(null, "x", "x", "x", "x")));
	}
	@Test
	void testTemplateExecute() throws Exception {
		soGAP.templateExecute(new Proizvod(null, 1, "1", 1));
		ArrayList<Proizvod> proizvodi=soGAP.getLista();
		for(Proizvod p : proizvodi) {
			
			
			
			
			if(p.getTip()==1) {
				KancelarijskiProizvod kp=(KancelarijskiProizvod) p;
				KancelarijskiProizvod stari=new KancelarijskiProizvod(kp.getProizvodID(),kp.getCena(), kp.getNaziv(), kp.getTip(), kp.getVrsta(), kp.getProizvodjac(), kp.getVisina(),kp.getSirina(),kp.getDuzina());
				
				
				
				kp.setCena(1200);
				kp.setNaziv("novo");
				kp.setVrsta("vrsta");
				kp.setProizvodjac("proizvodjac");
				kp.setVisina(1);
				kp.setDuzina(2);
				kp.setSirina(3);
				
				
				
				so.templateExecute(kp);
				
				soGAP.templateExecute(new Proizvod(null, 1, "1", 1));
				ArrayList<Proizvod> novi=soGAP.getLista();
				assertTrue(novi.contains(kp));
				assertTrue(novi.size()==proizvodi.size());
				
				for(Proizvod x : novi) {
					if(x.getProizvodID()==stari.getProizvodID()) {
						KancelarijskiProizvod y=(KancelarijskiProizvod) x;
						assertEquals(y.getCena(),kp.getCena());
						assertEquals(y.getDuzina(),kp.getDuzina());
						assertEquals(y.getNaziv(),kp.getNaziv());
						assertEquals(y.getProizvodjac(),kp.getProizvodjac());
						assertEquals(y.getSirina(),kp.getSirina());
						assertEquals(y.getVisina(),kp.getVisina());
						assertEquals(y.getVrsta(),kp.getVrsta());
					    break;
					}
				}
				
				
				//ponistavanje efekta testa
				so.templateExecute(stari);
				
			}
			if(p.getTip()==2) {
				
				Knjiga k=(Knjiga) p;
				Knjiga stara=new Knjiga(k.getProizvodID(),k.getCena(), k.getNaziv(), k.getTip(),k.getIzdanje(),k.getOpis(), k.getAutori());
				Pisac pomocni=new Pisac(null, "1", "1", "1");
				Autor autor=new Autor(k, 0, pomocni);
				soFAA.templateExecute(autor);
				ArrayList<Autor> autori=soFAA.getLista();
				stara.setAutori(autori);
				
				
				soGAP1.templateExecute(new Pisac(null, "1", "1", "1"));
				ArrayList<Pisac> pisci=soGAP1.getLista();
				if(pisci.size()>1) {
					
				   Autor a1=new Autor(k, 1, pisci.get(0));
				   Autor a2=new Autor(k,2,pisci.get(1));
				   ArrayList<Autor> lista=new ArrayList<>();
				   lista.add(a1);
				   lista.add(a2);
				   
				   k.setAutori(lista);
				   k.setCena(2000);
				   k.setIzdanje(2);
				   k.setNaziv("hehhe123");
				   k.setOpis("Opis");
				
				so.templateExecute(k);
				
				soGAP.templateExecute(new Proizvod(null, 1, "1", 1));
				ArrayList<Proizvod> novi=soGAP.getLista();
				assertTrue(novi.contains(k));
				assertTrue(novi.size()==proizvodi.size());
				
				for(Proizvod x : novi) {
					if(x.getProizvodID()==stara.getProizvodID()) {
						Knjiga y=(Knjiga) x;
						
						
						assertEquals(y.getCena(),k.getCena() );
						assertEquals(y.getIzdanje(),k.getIzdanje() );
						assertEquals(y.getNaziv(),k.getNaziv() );
						assertEquals(y.getOpis(),k.getOpis() );
						
						
						
					
						 pomocni=new Pisac(null, "1", "1", "1");
						 autor=new Autor(y, 0, pomocni);
						soFAA.templateExecute(autor);
						 autori=soFAA.getLista();
						 
						 assertEquals(autori,lista);
						
					    break;
					}
				}
				
				
				//ponistavam efekeat
				so.templateExecute(stara);
				}
				
			}
			break;
		}
		
	}
	

}
