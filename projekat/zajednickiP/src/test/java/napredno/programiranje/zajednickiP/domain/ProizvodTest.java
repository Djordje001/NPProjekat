package org.zajednickiP.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProizvodTest extends AbstractDomainObjectTest{

	Proizvod p;
	public AbstractDomainObject getInstance() {
		return new Proizvod(20L,2000,"ne zna se", 1);
	}
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		p=new Proizvod(20L,2000, "ne zna se", 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		p=null;
	}

	@Test
	void testNazivTabele() {
		assertEquals(" Proizvod ", p.nazivTabele());
	}

	@Test
	void testAlijas() {
		assertEquals(" p ", p.alijas());
	}

	@Test
	void testJoin() {
		assertEquals(" LEFT JOIN KANCELARIJSKIPROIZVOD KM ON (P.TIP = 1 AND KM.PROIZVODID = P.PROIZVODID) "
                +"LEFT JOIN KNJIGA KJ ON (P.TIP = 2 AND KJ.PROIZVODID = P.PROIZVODID) ", p.join());
	}

	

	@Test
	void testKoloneZaInsert() {
		assertEquals(" (cena,naziv,tip) ", p.koloneZaInsert());
	}

	@Test
	void testUslov() {
		assertEquals(" proizvodID = " +p.getProizvodID(), p.uslov());
	}

	@Test
	void testVrednostiZaInsert() {
		assertEquals(p.getCena()+", '"+p.getNaziv()+"', "+p.getTip(), p.vrednostiZaInsert());
	}

	@Test
	void testVrednostiZaUpdate() {
		assertEquals(" Cena = "+p.getCena()+", Naziv = '"+p.getNaziv()+"', Tip = "+p.getTip()+" ", p.vrednostiZaUpdate());
	}

	@Test
	void testUslovZaSelect() {
		assertEquals("WHERE P.NAZIV = '" + p.getNaziv() + "'", p.uslovZaSelect());
	}

	@Test
	void testProizvod() {
		p=new Proizvod(100L,10000, "naziv", 1);
		assertEquals(100L, p.getProizvodID());
		assertEquals(10000, p.getCena());
		assertEquals("naziv", p.getNaziv());
		assertEquals(1, p.getTip());
	}
	
	@Test
	void testProizvodLose() {
		assertThrows(java.lang.NullPointerException.class, ()->new Proizvod(100L, 20, null, 2));
		
		assertThrows(java.lang.IllegalArgumentException.class, ()->new Proizvod(100L, 0, "abc", 2));
		assertThrows(java.lang.IllegalArgumentException.class, ()->new Proizvod(100L, 2, "", 2));
		assertThrows(java.lang.IllegalArgumentException.class, ()->new Proizvod(100L, 2, "abc", 3));
	}

	@Test
	void testGetProizvodID() {
		p.setProizvodID(999L);
		assertEquals(999L, p.getProizvodID());
	}

	@Test
	void testSetProizvodID() {
		p.setProizvodID(999L);
		assertEquals(999L, p.getProizvodID());
	}

	@Test
	void testGetCena() {
		p.setCena(101);
		assertEquals(101, p.getCena());
	}

	@Test
	void testSetCena() {
		p.setCena(101);
		assertEquals(101, p.getCena());
	}
	
	@Test
	void testSetCenaLose() {
		assertThrows(java.lang.IllegalArgumentException.class,()->p.setCena(-1));
	}

	@Test
	void testGetNaziv() {
		p.setNaziv("naziv123");
		assertEquals("naziv123", p.getNaziv());
	}

	@Test
	void testSetNaziv() {
		p.setNaziv("naziv123");
		assertEquals("naziv123", p.getNaziv());
	}
	
	@Test
	void testSetNazivLose() {
		assertThrows(java.lang.NullPointerException.class,()->p.setNaziv(null));
		assertThrows(java.lang.IllegalArgumentException.class,()->p.setNaziv(""));
	}

	@Test
	void testGetTip() {
		p.setTip(1);
		assertEquals(1, p.getTip());
	}

	@Test
	void testSetTip() {
		p.setTip(1);
		assertEquals(1, p.getTip());
	}
	
	@Test
	void testSetTipLose() {
		
		assertThrows(java.lang.IllegalArgumentException.class,()->p.setTip(0));
	}

	@Test
	void testToString() {
		assertEquals("Proizvod [proizvodID=" + p.getProizvodID() + ", cena=" + p.getCena() + ", naziv=" + p.getNaziv() + ", tip=" + p.getTip() + "]", p.toString());
	}

	
	@Test
	void testEqualsObjectIsti() {
		Proizvod drugi=p;
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
	
	@Test
	void testEqualsObjectSveOk() {
		Proizvod drugi=new Proizvod(20L, 2000, "naziv", 1);
		p.setProizvodID(20L);
		
		assertTrue(p.equals(drugi));
		p.setProizvodID(30L);
		assertFalse(p.equals(drugi));
	}

}
