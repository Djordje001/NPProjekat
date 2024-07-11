package org.serverP.so.porudzbina;

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
import org.zajednickiP.domain.Administrator;
import org.zajednickiP.domain.Kupac;
import org.zajednickiP.domain.Porudzbina;

class SOFindAllPorudzbinaTest extends AbstractSOTest {

	SOFindAllPorudzbina so;
	public AbstractSO getInstance() {
		return new SOFindAllPorudzbina();
	}
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		so=new SOFindAllPorudzbina();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		so=null;
	}

	
	@Test
	void testTemplateExecuteNeuspesno() {
		assertThrows(java.lang.NullPointerException.class,()->so.templateExecute(null));
		assertThrows(java.lang.IllegalArgumentException.class,()->so.templateExecute(new Kupac(null, "x", "x", "x", "x")));
		
	}
	@Test
	void testTemplateExecute() throws Exception {
		Kupac kupac=new Kupac(null,"Djordje","Djordjevic","email","free");
		Administrator administrator=new Administrator(null,"hehe", "23", "xd", "qwe");
		Porudzbina p=new Porudzbina(null, new Date(), new Date(),"123", "12", 1, 2, 3, kupac, administrator, new ArrayList<>());
		
		assertTrue(so.getLista()==null);
		so.templateExecute(p);
		assertTrue(so.getLista()!=null);
		ArrayList<Porudzbina> porudzbineKupca=so.getLista();

		System.out.println(porudzbineKupca);
	}

}
