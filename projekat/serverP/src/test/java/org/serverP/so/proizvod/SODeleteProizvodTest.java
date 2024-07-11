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
import org.zajednickiP.domain.Autor;
import org.zajednickiP.domain.KancelarijskiProizvod;
import org.zajednickiP.domain.Knjiga;
import org.zajednickiP.domain.Kupac;
import org.zajednickiP.domain.Pisac;
import org.zajednickiP.domain.Proizvod;

class SODeleteProizvodTest extends AbstractSOTest {

	SODeleteProizvod so;
	SOGetAllProizvod soGAP;
	SOFindAllAutor soFAA;
	SOAddProizvod soAP;
	public AbstractSO getInstance() {
		return new SODeleteProizvod();
	}
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		so=new SODeleteProizvod();
		soGAP=new SOGetAllProizvod();
		soFAA=new SOFindAllAutor();
		soAP=new SOAddProizvod();
	}

	@AfterEach
	void tearDown() throws Exception {
		so=null;
		soGAP=null;
		soFAA=null;
		soAP=null;
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
				
				assertTrue(proizvodi.contains(kp));
				so.templateExecute(kp);
				
				
				soGAP.templateExecute(new Proizvod(null, 1, "1", 1));
				ArrayList<Proizvod> novi=soGAP.getLista();
				assertTrue(!novi.contains(kp));
				assertTrue(novi.size()==proizvodi.size()-1);
				
				
				
				//ponistavam efekat
				soAP.templateExecute(kp);
			}
			if(p.getTip()==2) {
				Knjiga k=(Knjiga) p;
				Pisac pomocni=new Pisac(null, "1", "1", "1");
				Autor autor=new Autor(k, 0, pomocni);
				soFAA.templateExecute(autor);
				ArrayList<Autor> autori=soFAA.getLista();
				k.setAutori(autori);
				
				
				
				
				
	
				
				assertTrue(proizvodi.contains(k));
				so.templateExecute(k);
				
				soGAP.templateExecute(new Proizvod(null, 1, "1", 1));
				ArrayList<Proizvod> novi=soGAP.getLista();
				assertTrue(!novi.contains(k));
				assertTrue(novi.size()==proizvodi.size()-1);
				
				//ponistavam efekeat
				soAP.templateExecute(k);
				
				
			}
			break;
		}
		
	}

}
