package org.zajednickiP.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

class AdministratorTest extends AbstractDomainObjectTest {
	
	public AbstractDomainObject getInstance() {
		return new Administrator(1L,"Djordje","Djordjevic","123","123");
	}

	Administrator a;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		a=new Administrator(1L, "Djordje", "Djordjevic", "djole", "djole");
	}

	@AfterEach
	void tearDown() throws Exception {
		a=null;
	}

	@Test
	void testNazivTabele() {
		assertEquals(" administrator ", a.nazivTabele() );
	}

	@Test
	void testAlijas() {
		assertEquals(" a ", a.alijas() );
	}

	@Test
	void testJoin() {
		assertEquals("", a.join() );
	}

	

	@Test
	void testKoloneZaInsert() {
		assertEquals(" (Ime, Prezime, Username, Password) ",a.koloneZaInsert());
	}

	@Test
	void testUslov() {
		assertEquals(" AdministratorID = "+a.getAdministratorID(),a.uslov());
	}

	@Test
	void testVrednostiZaInsert() {
		assertEquals("'" + a.getIme() + "', '" + a.getPrezime() + "', "
                + "'" + a.getUsername() + "', '" + a.getPassword() + "'",a.vrednostiZaInsert());
	}

	@Test
	void testVrednostiZaUpdate() {
		assertEquals(" Ime = '" + a.getIme() + "', Prezime = '" + a.getPrezime() + "', "
                + "Username = '" + a.getUsername() + "', Password = '" + a.getPassword() + "' ",a.vrednostiZaUpdate());
	}

	@Test
	void testUslovZaSelect() {
		assertEquals("", a.uslovZaSelect());
	}

	@Test
	void testAdministratorOk() {
		a=new Administrator(1000L, "Marko", "Kraljevic", "mare", "kralj");
	    assertEquals(1000L, a.getAdministratorID());
	    assertEquals("Marko", a.getIme());
	    assertEquals("Kraljevic", a.getPrezime());
	    assertEquals("mare", a.getUsername());
	    assertEquals("kralj", a.getPassword());
	}
	
	@Test
	void testAdministratorLose() {
		a=new Administrator(1000L, "Marko", "Kraljevic", "mare", "kralj");
	    assertThrows(java.lang.NullPointerException.class, ()->new Administrator(1000L, null, "Kraljevic", "mare", "kralj"));
	    assertThrows(java.lang.NullPointerException.class, ()->new Administrator(1000L, "Marko", null, "mare", "kralj"));
	    assertThrows(java.lang.NullPointerException.class, ()->new Administrator(1000L, "Marko", "Kraljevic", null, "kralj"));
	    assertThrows(java.lang.NullPointerException.class, ()->new Administrator(1000L, "Marko", "Kraljevic", "mare", null));
	    
	    assertThrows(java.lang.IllegalArgumentException.class, ()->new Administrator(1000L, "", "Kraljevic", "mare", "kralj"));
	    assertThrows(java.lang.IllegalArgumentException.class, ()->new Administrator(1000L, "Marko", "", "mare", "kralj"));
	    assertThrows(java.lang.IllegalArgumentException.class, ()->new Administrator(1000L, "Marko", "Kraljevic", "", "kralj"));
	    assertThrows(java.lang.IllegalArgumentException.class, ()->new Administrator(1000L, "Marko", "Kraljevic", "mare", ""));
	}

	@Test
	void testGetAdministratorID() {
		//isti test kao i sledeci 
		a.setAdministratorID(30L);
		assertEquals(30L, a.getAdministratorID());
	}

	@Test
	void testSetAdministratorID() {
		a.setAdministratorID(25L);
		assertEquals(25L, a.getAdministratorID());
	}

	@Test
	void testGetUsername() {
		a.setUsername("Anci");
		assertEquals("Anci", a.getUsername());
	}

	@Test
	void testSetUsername() {
		a.setUsername("Anci");
		assertEquals("Anci", a.getUsername());
	}

	@Test
	void testGetPassword() {
		a.setPassword("123");
		assertEquals("123", a.getPassword());
	}

	@Test
	void testSetPassword() {
		a.setPassword("123");
		assertEquals("123", a.getPassword());
	}

	@Test
	void testGetIme() {
		a.setIme("Milos");
		assertEquals("Milos", a.getIme());
	}

	@Test
	void testSetIme() {
		a.setIme("Milos");
		assertEquals("Milos", a.getIme());
	}

	@Test
	void testGetPrezime() {
		a.setPrezime("Milosevic");
		assertEquals("Milosevic", a.getPrezime());
	}

	@Test
	void testSetPrezime() {
		a.setPrezime("Milosevic");
		assertEquals("Milosevic", a.getPrezime());
	}

	@Test
	void testToString() {
		a.setIme("Lazar");
		a.setPrezime("Hrebeljanovic");
		String tekst=a.toString();
		assertEquals("Lazar Hrebeljanovic",tekst);
	}

	@Test
	void testEqualsObjectIsti() {
		Administrator b=a;
		assertTrue(a.equals(b));
	}
	
	@Test
	void testEqualsObjectNull() {
		assertFalse(a.equals(null));
	}
	
	@Test
	void testEqualsObjectRazlKlasa() {
		assertFalse(a.equals("123"));
	}
	
	@ParameterizedTest
	@CsvSource({
		"1,1,Djordje,Djordje,Djordjevic,Djordjevic,true",
		"1,2,Djordje,Djordje,Djordjevic,Djordjevic,false",
		"1,1,Djordje,Djordje1,Djordjevic,Djordjevic,false",
		"1,1,Djordje,Djordje,Djordjevic,Djordjevic1,false",
		"1,2,Djordje,Djordje1,Djordjevic,Djordjevic,false",
		"1,2,Djordje,Djordje,Djordjevic,Djordjevic2,false",
		"1,1,Djordje,Djordje1,Djordjevic,Djordjevic2,false",
		"1,2,Djordje,Djordje1,Djordjevic,Djordjevic2,false",
	})
	void testEqualsObjectSveOk(Long administratorID1,Long administratorID2,String ime1,String ime2,String prezime1,String prezime2,Boolean indikator) {
		a=new Administrator(administratorID1, ime1, prezime1, "x", "x");
		Administrator b=new Administrator(administratorID2, ime2, prezime2, "x", "x");
		assertEquals(indikator, a.equals(b));
	}

	

}
