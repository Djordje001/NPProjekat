package org.serverP.so.stavkaPorudzbine;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.serverP.so.AbstractSO;
import org.serverP.so.AbstractSOTest;
import org.serverP.so.porudzbina.SOGetAllPorudzbina;
import org.zajednickiP.domain.Administrator;
import org.zajednickiP.domain.Kupac;
import org.zajednickiP.domain.Pisac;
import org.zajednickiP.domain.Porudzbina;
import org.zajednickiP.domain.Proizvod;
import org.zajednickiP.domain.StavkaPorudzbine;

class SOFindAllStavkaPorudzbineTest extends AbstractSOTest {

	public AbstractSO getInstance() {
		return new SOFindAllStavkaPorudzbine();
	}
	SOFindAllStavkaPorudzbine so;
	SOGetAllPorudzbina soGAP;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		so=new SOFindAllStavkaPorudzbine();
		soGAP=new SOGetAllPorudzbina();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testTemplateExecuteNeuspesno() {
		assertThrows(java.lang.NullPointerException.class,()->so.templateExecute(null));
		assertThrows(java.lang.IllegalArgumentException.class,()->so.templateExecute(new Pisac(1L,"ime","prezime", "email")));
		
		
	}
	
	@Test
	void testTemplateExecuteUspesno() throws Exception {
		Kupac kupac=new Kupac(null,"Djordje","Djordjevic","email","free");
		Administrator administrator=new Administrator(null,"hehe", "23", "xd", "qwe");
		Porudzbina p=new Porudzbina(null, new Date(), new Date(),"123", "12", 1, 2, 3, kupac, administrator, new ArrayList<>());
		
		assertTrue(soGAP.getLista()==null);
		soGAP.templateExecute(p);
		assertTrue(soGAP.getLista()!=null);
		
		for(Porudzbina por : soGAP.getLista()) {
			assertTrue(so.getLista()==null);
			StavkaPorudzbine pomocna=new StavkaPorudzbine(por, 2, 3, 5, new Proizvod(null, 232, "123", 1));
			so.templateExecute(pomocna);
			assertTrue(so.getLista()!=null);
			
			System.out.println(so.getLista());
			
			
			
			break;
		}
		
		
		
	}

}
