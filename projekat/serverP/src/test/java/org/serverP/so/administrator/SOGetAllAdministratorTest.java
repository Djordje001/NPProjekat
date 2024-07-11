package org.serverP.so.administrator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.serverP.so.AbstractSO;
import org.serverP.so.AbstractSOTest;
import org.zajednickiP.domain.Administrator;
import org.zajednickiP.domain.Kupac;

class SOGetAllAdministratorTest  extends AbstractSOTest{
	
	SOGetAllAdministrator so;
	public AbstractSO getInstance() {
		return new SOGetAllAdministrator();
	}

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		so=new SOGetAllAdministrator();
	}

	@AfterEach
	void tearDown() throws Exception {
		so=null;
	}
	

	@Test
	void testTemplateExecuteLose() {
		assertThrows(java.lang.NullPointerException.class,()->so.templateExecute(null));
		assertThrows(java.lang.IllegalArgumentException.class,()->so.templateExecute(new Kupac(2L, "ime", "prezime", "email", "tipkupca")));
	}
	
	@Test
	void testTemplateExecuteUspesno() throws Exception {
		assertEquals(null,so.getLista());
		so.templateExecute(new Administrator(20L, "admin", "prezime", "username", "password"));
		
		ArrayList<Administrator> administratori=so.getLista();
		assertEquals(2, administratori.size());
		
		
		Administrator administrator=new Administrator(2L, "Dule", "Savic", "dule", "savic");
		assertTrue(administratori.contains(administrator));
		
	}

}
