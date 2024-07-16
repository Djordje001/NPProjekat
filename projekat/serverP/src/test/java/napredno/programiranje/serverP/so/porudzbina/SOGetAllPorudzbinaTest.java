package napredno.programiranje.serverP.so.porudzbina;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import napredno.programiranje.serverP.so.AbstractSO;
import napredno.programiranje.serverP.so.AbstractSOTest;
import napredno.programiranje.serverP.so.porudzbina.SOGetAllPorudzbina;
import napredno.programiranje.zajednickiP.domain.Administrator;
import napredno.programiranje.zajednickiP.domain.Kupac;
import napredno.programiranje.zajednickiP.domain.Porudzbina;

class SOGetAllPorudzbinaTest extends AbstractSOTest{

	SOGetAllPorudzbina so;
	public AbstractSO getInstance() {
		return new SOGetAllPorudzbina();
	}
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		so=new SOGetAllPorudzbina();
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
		
		System.out.println(so.getLista());
		
		
	}

}
