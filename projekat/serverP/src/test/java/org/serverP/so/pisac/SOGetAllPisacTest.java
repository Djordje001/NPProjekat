package org.serverP.so.pisac;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.serverP.so.AbstractSO;
import org.serverP.so.AbstractSOTest;
import org.zajednickiP.domain.Kupac;
import org.zajednickiP.domain.Pisac;

class SOGetAllPisacTest  extends AbstractSOTest{

    SOGetAllPisac so;
	public AbstractSO getInstance() {
		return new SOGetAllPisac();
	}
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		so=new SOGetAllPisac();
	}

	@AfterEach
	void tearDown() throws Exception {
		so=null;
		
	}

	@Test
	void testTemplateExecutePogresno() {
		assertThrows(java.lang.NullPointerException.class,()->so.templateExecute(null));
		assertThrows(java.lang.IllegalArgumentException.class,()->so.templateExecute(new Kupac(null, "x", "x", "x", "x")));
	}
	
	@Test
	void testTemplateExecuteUspesno() throws Exception {
		assertTrue(so.getLista()==null);
		
		so.templateExecute(new Pisac(null,"x","x","x"));
		assertTrue(so.getLista()!=null);
		
		ArrayList<Pisac> pisci=so.getLista();
		Pisac pisac=new Pisac(2L,"Mesa","Selimovic","mesa.selimovic@gmail.com");
		assertTrue(pisci.contains(pisac));
	}

}
