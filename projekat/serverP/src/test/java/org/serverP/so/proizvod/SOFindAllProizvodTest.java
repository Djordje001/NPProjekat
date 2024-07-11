package org.serverP.so.proizvod;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.serverP.so.AbstractSO;
import org.serverP.so.AbstractSOTest;
import org.zajednickiP.domain.Kupac;
import org.zajednickiP.domain.Proizvod;

class SOFindAllProizvodTest extends AbstractSOTest{

	SOFindAllProizvod so;
	public AbstractSO getInstance() {
		return new SOFindAllProizvod();
	}
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		so=new SOFindAllProizvod();
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
	void testTemplateExecute() throws Exception {
		assertTrue(so.getLista()==null);
		so.templateExecute(new Proizvod(null,2000,"Padobran",1));
		assertTrue(so.getLista()!=null);
		assertTrue(so.getLista().size()==1);
		assertTrue(so.getLista().get(0).getNaziv().equals("Padobran"));
		System.out.println(so.getLista());
	}

}
