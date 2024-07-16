package org.serverP.so.autor;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.serverP.so.AbstractSO;
import org.serverP.so.AbstractSOTest;
import org.serverP.so.proizvod.SOGetAllProizvod;
import org.zajednickiP.domain.Autor;
import org.zajednickiP.domain.Knjiga;
import org.zajednickiP.domain.Kupac;
import org.zajednickiP.domain.Pisac;
import org.zajednickiP.domain.Proizvod;

class SOFindAllAutorTest extends AbstractSOTest {

	SOFindAllAutor so;
	SOGetAllProizvod soGAP;
	public AbstractSO getInstance() {
		return new SOFindAllAutor();
	}
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		so=new SOFindAllAutor();
		soGAP=new SOGetAllProizvod();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		so=null;
		soGAP=null;
	}

	@Test
	void testTemplateExecuteNeuspesno() {
		assertThrows(java.lang.NullPointerException.class,()->so.templateExecute(null));
		assertThrows(java.lang.IllegalArgumentException.class,()->so.templateExecute(new Kupac(2L, "ime", "prezime", "email", "tipkupca")));
	}
	
	@Test
	void testTemplateExecuteUspesno() throws Exception {
		soGAP.templateExecute(new Proizvod(null, 23,"naziv", 2));
		ArrayList<Proizvod> proizvodi=soGAP.getLista();
		for(Proizvod p : proizvodi) {
			if(p.getTip()==2) {
				Knjiga knjiga=(Knjiga) p;
				Autor autor=new Autor(knjiga, 0, new Pisac(null, "x", "x", "x"));
				assertTrue(so.getLista()==null);
				so.templateExecute(autor);
				ArrayList<Autor> autori=so.getLista();
				
				assertTrue(autori!=null);
				
				System.out.println(autori);
				break;
			}
		}
	}

}
