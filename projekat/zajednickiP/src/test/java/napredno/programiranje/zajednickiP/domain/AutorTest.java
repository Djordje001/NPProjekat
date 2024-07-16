package napredno.programiranje.zajednickiP.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import napredno.programiranje.zajednickiP.domain.AbstractDomainObject;
import napredno.programiranje.zajednickiP.domain.Autor;
import napredno.programiranje.zajednickiP.domain.Knjiga;
import napredno.programiranje.zajednickiP.domain.Pisac;

class AutorTest extends AbstractDomainObjectTest{
	Knjiga k;
	Pisac p;
	Autor a;
	public AbstractDomainObject getInstance() {
		Knjiga k=new Knjiga(null,1200, "x", 2, 1, "dobar", new ArrayList<>());
		Pisac p=new Pisac(null,"Desanka", "Maksimovic", "desanka.maksimovic@gmail.com");
		return new Autor(k, 0, p);
	}

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		 k=new Knjiga(null,1200, "x", 2, 1, "dobar", new ArrayList<>());
		 p=new Pisac(null,"Desanka", "Maksimovic", "desanka.maksimovic@gmail.com");
		 a=new Autor(k, 0, p);
	}

	@AfterEach
	void tearDown() throws Exception {
		k=null;
		p=null;
		a=null;
		
	}

	@Test
	void testNazivTabele() {
		assertEquals(" Autor ", a.nazivTabele());
	}

	@Test
	void testAlijas() {
		assertEquals(" a ", a.alijas());
	}

	@Test
	void testJoin() {
		assertEquals(" JOIN KNJIGA KNJ ON (KNJ.PROIZVODID = A.PROIZVODID) "
                + "JOIN PISAC P ON (P.PISACID = A.PISACID) "
        		+"JOIN PROIZVOD PR ON (PR.PROIZVODID = A.PROIZVODID)", a.join());
	}

	

	@Test
	void testKoloneZaInsert() {
		assertEquals(" (proizvodID,pisacID) ",a.koloneZaInsert());
	}

	@Test
	void testUslov() {
		assertEquals(" ProizvodID = " + k.getProizvodID(),a.uslov());
	}

	@Test
	void testVrednostiZaInsert() {
		assertEquals(k.getProizvodID()+", "+p.getPisacID(),a.vrednostiZaInsert());
	}

	@Test
	void testVrednostiZaUpdate() {
		assertEquals("",a.vrednostiZaUpdate());
	}

	@Test
	void testUslovZaSelect() {
		assertEquals("WHERE A.PROIZVODID = " + k.getProizvodID(),a.uslovZaSelect());
	}

	@Test
	void testAutorOk() {
		Knjiga knjiga=new Knjiga(null,20,"maximus", 2, 1,"dobar", new ArrayList<>());
		Pisac pisac=new Pisac(null,"Ivo","Andric","ivo.andric@gmail.com");
		a=new Autor(knjiga, 3, pisac);
		
		assertEquals(knjiga, a.getKnjiga());
		assertEquals(pisac, a.getPisac());
		assertEquals(3,a.getRb());
		
		
	}
	
	@Test
	void testAutorLose() {
		assertThrows(java.lang.NullPointerException.class, ()->new Autor(null,1,p));
		assertThrows(java.lang.NullPointerException.class, ()->new Autor(k,1,null));
	}

	@Test
	void testGetKnjiga() {
		Knjiga knjiga=new Knjiga(null,1200,"x",2,1,"dobar opis",new ArrayList<Autor>());
		a.setKnjiga(knjiga);
		assertEquals(knjiga, a.getKnjiga());
	}
	
	@Test
	void testSetKnjiga() {
		Knjiga knjiga=new Knjiga(null,1200,"x",2,1,"dobar opis",new ArrayList<Autor>());
		a.setKnjiga(knjiga);
		assertEquals(knjiga, a.getKnjiga());
	}
	
	@Test
	void testSetKnjigaLose() {
		assertThrows(java.lang.NullPointerException.class, ()->a.setKnjiga(null));
		
	}

	@Test
	void testGetRb() {
		a.setRb(1200);
		assertEquals(1200,a.getRb());
	}

	@Test
	void testSetRb() {
		a.setRb(1200);
		assertEquals(1200,a.getRb());
	}

	@Test
	void testGetPisac() {
		Pisac pisac=new Pisac(null,"Marko","Markovic","marko.markovic@gmail.com");
		a.setPisac(pisac);
		assertEquals(pisac, a.getPisac());
	}

	@Test
	void testSetPisac() {
		Pisac pisac=new Pisac(null,"Marko","Markovic","marko.markovic@gmail.com");
		a.setPisac(pisac);
		assertEquals(pisac, a.getPisac());
	}
	
	@Test
	void testSetPisacLose() {
		
		assertThrows(java.lang.NullPointerException.class, ()->a.setPisac(null));
	}

	@Test
	void testToString() {
		String tekst=a.toString();
		assertEquals("Autor [ + , rb=" + a.getRb() + ", pisac=" + a.getPisac().getPisacID() + "]", tekst);
	}

	
	
	@Test
	void testEqualsObjectIsti() {
		Autor b=a;
		assertEquals(true, a.equals(b));
	}
	
	@Test
	void testEqualsObjectNull() {
		
		assertEquals(false, a.equals(null));
	}
	
	@Test
	void testEqualsObjectRazlKlasa() {
		
		assertEquals(false, a.equals("123"));
	}
	
	@Test
	void testEqualsObjectSveOk() {
		Knjiga knjiga=new Knjiga(null, 3000, "abc", 2, 1, "opis", new ArrayList<>());
		Pisac pisac=new Pisac(null,"Pisac 1","Prezime pisca 1","email ");
		Autor autor=new Autor(knjiga, 5, pisac);
		
		a.setKnjiga(knjiga);
		a.setPisac(pisac);
		a.setRb(5);
		assertEquals(true, a.equals(autor));
		
		a.setKnjiga(k);
		assertEquals(false,a.equals(autor));
		a.setKnjiga(knjiga);
		a.setPisac(p);
		assertEquals(false, a.equals(autor));
		a.setKnjiga(knjiga);
		a.setPisac(pisac);
		a.setRb(10);
		assertEquals(false, a.equals(autor));
	}


}
