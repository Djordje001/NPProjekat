package napredno.programiranje.zajednickiP.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import napredno.programiranje.zajednickiP.domain.AbstractDomainObject;
import napredno.programiranje.zajednickiP.domain.Pisac;

class PisacTest extends AbstractDomainObjectTest {

	Pisac p;
	public AbstractDomainObject getInstance() {
		return new Pisac(1L, "Djordje", "Djordjevic","djordjevic.dj01@gmail.com");
	}
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		p=new Pisac(1L,"Djordje","Djordjevic","djordjevic.dj01@gmail.com");
	}

	@AfterEach
	void tearDown() throws Exception {
		p=null;
	}

	@Test
	void testNazivTabele() {
		assertEquals(" Pisac ", p.nazivTabele());
	}

	@Test
	void testAlijas() {
		assertEquals(" p ", p.alijas());
	}

	@Test
	void testJoin() {
		assertEquals("", p.join());
	}

	

	@Test
	void testKoloneZaInsert() {
		assertEquals("", p.koloneZaInsert());
	}

	@Test
	void testUslov() {
		assertEquals(" PisacID = " + p.getPisacID(), p.uslov());
	}

	@Test
	void testVrednostiZaInsert() {
		assertEquals("", p.vrednostiZaInsert());
	}

	@Test
	void testVrednostiZaUpdate() {
		assertEquals("", p.vrednostiZaUpdate());
	}

	@Test
	void testUslovZaSelect() {
		assertEquals("", p.uslovZaSelect());
	}

	@Test
	void testToString() {
	    assertEquals(p.getIme() + " " + p.getPrezime(), p.toString());
	}

	@Test
	void testPisac() {
		p=new Pisac(89L, "Marlon", "Makic", "maki@gmail.com");
		
		assertEquals(89L, p.getPisacID());
		assertEquals("Marlon", p.getIme());
		assertEquals("Makic", p.getPrezime());
		assertEquals("maki@gmail.com", p.getEmail());
	}
	
	@Test
	void testPisacLose() {
		assertThrows(java.lang.NullPointerException.class, ()->new Pisac(89L,null,"x","x"));
		assertThrows(java.lang.NullPointerException.class, ()->new Pisac(89L,"x",null,"x"));
		assertThrows(java.lang.NullPointerException.class, ()->new Pisac(89L,"x","x",null));
		
		assertThrows(java.lang.IllegalArgumentException.class, ()->new Pisac(89L,"","x","x"));
		assertThrows(java.lang.IllegalArgumentException.class, ()->new Pisac(89L,"x","","x"));
		assertThrows(java.lang.IllegalArgumentException.class, ()->new Pisac(89L,"x","x",""));
	}

	@Test
	void testGetPisacID() {
		p.setPisacID(100L);
		assertEquals(100L, p.getPisacID());
	}

	@Test
	void testSetPisacID() {
		p.setPisacID(100L);
		assertEquals(100L, p.getPisacID());
	}

	@Test
	void testGetIme() {
		p.setIme("Marko");
		assertEquals("Marko", p.getIme());
	}

	@Test
	void testSetIme() {
		p.setIme("Marko");
		assertEquals("Marko", p.getIme());
	}
	
	@Test
	void testSetImeLose() {
		assertThrows(java.lang.NullPointerException.class,()->p.setIme(null));
		assertThrows(java.lang.IllegalArgumentException.class,()->p.setIme(""));
	}

	@Test
	void testGetPrezime() {
		p.setPrezime("Prezime1");
		assertEquals("Prezime1", p.getPrezime());
	}

	@Test
	void testSetPrezime() {
		p.setPrezime("Prezime1");
		assertEquals("Prezime1", p.getPrezime());
	}
	
	@Test
	void testSetPrezimeLose() {
		assertThrows(java.lang.NullPointerException.class,()->p.setPrezime(null));
		assertThrows(java.lang.IllegalArgumentException.class,()->p.setPrezime(""));
	}

	@Test
	void testGetEmail() {
		p.setEmail("1123");
		assertEquals("1123", p.getEmail());
	}

	@Test
	void testSetEmail() {
		p.setEmail("1123");
		assertEquals("1123", p.getEmail());
	}
	
	@Test
	void testSetEmailLose() {
		assertThrows(java.lang.NullPointerException.class,()->p.setEmail(null));
		assertThrows(java.lang.IllegalArgumentException.class,()->p.setEmail(""));
	}

	

	@Test
	void testEqualsObjectIsti() {
		Pisac drugi=p;
		assertTrue(p.equals(drugi));
	}
	
	@Test
	void testEqualsObjectNull() {
		assertFalse(p.equals(null));
	}
	
	@Test
	void testEqualsObjectRazlKlase() {
		assertFalse(p.equals("123"));
	}
	
	
	@ParameterizedTest
	@CsvSource({
		    "1,1,email,email,true",
		    "1,2,email,email,false",
		    "1,1,email,emai1,false",
		    "1,2,email,emai2,false",
	})
	void testEqualsObject(Long id1,Long id2,String email1,String email2,boolean indikator) {
		Pisac drugi=new Pisac(id2, "x", "x", email2);
		p.setPisacID(id1);
		p.setEmail(email1);
		
		assertEquals(indikator, p.equals(drugi));
	}

}
