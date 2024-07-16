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
import napredno.programiranje.zajednickiP.domain.Kupac;

class KupacTest extends AbstractDomainObjectTest {

	Kupac k;
	public AbstractDomainObject getInstance() {
		return new Kupac(null, "proba", "probic", "gmail.com","tip");
	}
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		k=new Kupac(20L, "Djordje", "Djordjevic", "djordjevic.dj01@gmail.com", "PREMIUM ULTRA");
	}

	@AfterEach
	void tearDown() throws Exception {
		k=null;
	}

	@Test
	void testNazivTabele() {
		assertEquals(" Kupac ", k.nazivTabele());
	}

	@Test
	void testAlijas() {
		assertEquals(" k ", k.alijas());
	}

	@Test
	void testJoin() {
		assertEquals("", k.join());
	}

	

	@Test
	void testKoloneZaInsert() {
		assertEquals(" (Ime, Prezime, email, tipKupca) ", k.koloneZaInsert());
	}

	@Test
	void testUslov() {
		assertEquals(" KupacID = " + k.getKupacID(), k.uslov());
	}

	@Test
	void testVrednostiZaInsert() {
		assertEquals("'" + k.getIme() + "', '" + k.getPrezime() + "', "
                + "'" + k.getEmail() + "', '" + k.getTipKupca() + "'", k.vrednostiZaInsert());
	}

	@Test
	void testVrednostiZaUpdate() {
		assertEquals(" Ime = '" + k.getIme() + "', Prezime = '" + k.getPrezime() + "', "
                + "email = '" + k.getEmail() + "', tipKupca = '" + k.getTipKupca() + "' ", k.vrednostiZaUpdate());
	}

	@Test
	void testUslovZaSelect() {
		assertEquals("",k.uslovZaSelect());
	}

	@Test
	void testToString() {
		assertEquals(k.getIme() + " " + k.getPrezime() + " (Tip: " + k.getTipKupca() + ")", k.toString());
	}

	@Test
	void testKupacSveOk() {
		k=new Kupac(20L, "Djordje", "Djordjevic", "djordjevic.dj01@gmail.com", "FREE");
		
		assertEquals(20L,k.getKupacID());
		assertEquals("Djordje",k.getIme());
		assertEquals("Djordjevic",k.getPrezime());
		assertEquals("djordjevic.dj01@gmail.com",k.getEmail());
		assertEquals("FREE",k.getTipKupca());
	}
	
	
	@Test
	void testKupacNeValja() {
		
		assertThrows(java.lang.NullPointerException.class,()->new Kupac(20L, null, "Djordjevic", "djordjevic.dj01@gmail.com", "FREE") );
		assertThrows(java.lang.NullPointerException.class,()->new Kupac(20L, "Djordje", null, "djordjevic.dj01@gmail.com", "FREE") );
		assertThrows(java.lang.NullPointerException.class,()->new Kupac(20L, "Djordje", "Djordjevic", null, "FREE") );
		assertThrows(java.lang.NullPointerException.class,()->new Kupac(20L, "Djordje", "Djordjevic", "djordjevic.dj01@gmail.com", null) );
		
		assertThrows(java.lang.IllegalArgumentException.class,()->new Kupac(20L, "", "Djordjevic", "djordjevic.dj01@gmail.com", "FREE") );
		assertThrows(java.lang.IllegalArgumentException.class,()->new Kupac(20L, "Djordje", "", "djordjevic.dj01@gmail.com", "FREE") );
		assertThrows(java.lang.IllegalArgumentException.class,()->new Kupac(20L, "Djordje", "Djordjevic", "", "FREE") );
		assertThrows(java.lang.IllegalArgumentException.class,()->new Kupac(20L, "Djordje", "Djordjevic", "djordjevic.dj01@gmail.com","") );
	}

	@Test
	void testGetKupacID() {
		k.setKupacID(100L);
		assertEquals(100L, k.getKupacID());
	}

	@Test
	void testSetKupacID() {
		k.setKupacID(100L);
		assertEquals(100L, k.getKupacID());
	}

	@Test
	void testGetIme() {
		k.setIme("Bojan");
		assertEquals("Bojan", k.getIme());
	}

	@Test
	void testSetIme() {
		k.setIme("Bojan");
		assertEquals("Bojan", k.getIme());
	}
	
	@Test
	void testSetImeLose() {
		assertThrows(java.lang.NullPointerException.class,()->k.setIme(null));
		
		assertThrows(java.lang.IllegalArgumentException.class,()->k.setIme(""));
	}

	@Test
	void testGetPrezime() {
		k.setPrezime("Nikolic");
		assertEquals("Nikolic", k.getPrezime());
	}

	@Test
	void testSetPrezime() {
		k.setPrezime("Nikolic");
		assertEquals("Nikolic", k.getPrezime());
	}
	
	@Test
	void testSetPrezimeLose() {
		assertThrows(java.lang.NullPointerException.class,()->k.setPrezime(null));
		
		assertThrows(java.lang.IllegalArgumentException.class,()->k.setPrezime(""));
	}

	@Test
	void testGetEmail() {
		k.setEmail("x@gmail.com");
		assertEquals("x@gmail.com", k.getEmail());
	}

	@Test
	void testSetEmail() {
		k.setEmail("x@gmail.com");
		assertEquals("x@gmail.com", k.getEmail());
	}
	
	@Test
	void testSetEmailLose() {
		assertThrows(java.lang.NullPointerException.class,()->k.setEmail(null));
		
		assertThrows(java.lang.IllegalArgumentException.class,()->k.setEmail(""));
	}

	@Test
	void testGetTipKupca() {
		k.setTipKupca("FREE");
		assertEquals("FREE", k.getTipKupca());
	}

	@Test
	void testSetTipKupca() {
		k.setTipKupca("FREE");
		assertEquals("FREE", k.getTipKupca());
	}
	
	@Test
	void testSetTipKupcaLose() {
		assertThrows(java.lang.NullPointerException.class,()->k.setTipKupca(null));
		
		assertThrows(java.lang.IllegalArgumentException.class,()->k.setTipKupca(""));
	}

	@Test
	void testEqualsObjectIsti() {
		Kupac drugi=k;
		assertTrue(k.equals(drugi));
	}
	
	@Test
	void testEqualsObjectNull() {
		
		assertFalse(k.equals(null));
	}
	@Test
	void testEqualsObjectRazlKlase() {
		
		assertFalse(k.equals("123"));
	}
	
	
	@ParameterizedTest
	@CsvSource({
		    "1,1,email,email,true",
		    "1,2,email,email,false",
		    "1,1,email,emai1,false",
		    "1,2,email,emai2,false",
	})
	void testEqualsObject(Long id1,Long id2,String email1,String email2,boolean indikator) {
		Kupac drugi=new Kupac(id2, "x", "x", email2, "x");
		k.setKupacID(id1);
		k.setEmail(email1);
		
		assertEquals(indikator, k.equals(drugi));
	}

}
