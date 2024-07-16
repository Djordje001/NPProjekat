package napredno.programiranje.serverP.so.kupac;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import napredno.programiranje.serverP.so.AbstractSO;
import napredno.programiranje.serverP.so.AbstractSOTest;
import napredno.programiranje.serverP.so.kupac.SOGetAllKupac;
import napredno.programiranje.zajednickiP.domain.Kupac;
import napredno.programiranje.zajednickiP.domain.Pisac;

class SOGetAllKupacTest extends AbstractSOTest {

	SOGetAllKupac so;
	public AbstractSO getInstance() {
		return new SOGetAllKupac();
	}
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		so=new SOGetAllKupac();
	}

	@AfterEach
	void tearDown() throws Exception {
		so=null;
	}

	@Test
	void testTemplateExecuteNeuspesno() {
		assertThrows(java.lang.NullPointerException.class,()->so.templateExecute(null));
		assertThrows(java.lang.IllegalArgumentException.class,()->so.templateExecute(new Pisac(1L,"ime","prezime", "email")));
		
	}
	
	@Test
	void testTemplateExecuteUspesno() throws Exception {
		assertTrue(so.getLista()==null);
		so.templateExecute(new Kupac(null, "123", "123","123", "123"));
		ArrayList<Kupac> kupci=so.getLista();
		
		assertTrue(kupci.size()!=0);
		assertTrue(kupci.contains(new Kupac(1L, "Djordje", "Djordje", "djordjevic.dj01@gmail.com", "FREE")));
		
	}

}
