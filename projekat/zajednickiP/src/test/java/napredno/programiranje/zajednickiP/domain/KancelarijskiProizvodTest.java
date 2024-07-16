package org.zajednickiP.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KancelarijskiProizvodTest  extends AbstractDomainObjectTest{

	KancelarijskiProizvod kp;
	public AbstractDomainObject getInstance() {
	    return new KancelarijskiProizvod(null,1000, "123", 1, "hehe", "ne zna se", 1, 2, 3);
	}
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		kp=new KancelarijskiProizvod(null,1000, "123", 1, "hehe", "ne zna se", 1, 2, 3);
	}

	@AfterEach
	void tearDown() throws Exception {
		kp=null;
	}

	@Test
	void testNazivTabele() {
		assertEquals(" KancelarijskiProizvod ",kp.nazivTabele());
	}

	@Test
	void testAlijas() {
		assertEquals(" kp ",kp.alijas());
	}

	@Test
	void testJoin() {
		assertEquals(" JOIN PROIZVOD P ON (P.PROIZVODID = KP.PROIZVODID) ",kp.join());
	}

	

	@Test
	void testKoloneZaInsert() {
		assertEquals(" (proizvodID,vrsta,proizvodjac,visina,sirina,duzina) ",kp.koloneZaInsert());
	}

	@Test
	void testUslov() {
		assertEquals(" proizvodID = " +kp.getProizvodID(),kp.uslov());
	}

	@Test
	void testVrednostiZaInsert() {
		assertEquals(kp.getProizvodID()+", '"+kp.getVrsta()+"', '"+kp.getProizvodjac()+"', "
				 +kp.getVisina()+", "+kp.getSirina()+", "+kp.getDuzina(),kp.vrednostiZaInsert());
	}

	@Test
	void testVrednostiZaUpdate() {
		assertEquals(" Vrsta = '" + kp.getVrsta() + "', Proizvodjac = '" + kp.getProizvodjac() + "', "
          + "Visina = " + kp.getVisina() + ", Sirina = " + kp.getSirina() + ", "+"Duzina = "+kp.getDuzina()+" ",kp.vrednostiZaUpdate());
	}

	@Test
	void testUslovZaSelect() {
		assertEquals("",kp.uslovZaSelect());
	}

	@Test
	void testToString() {
		Proizvod p=new Proizvod(kp.getProizvodID(), kp.getCena(), kp.getNaziv(),kp.getTip());
		
		assertEquals(p.toString()+
				"KancelarijskiProizvod [ vrsta=" + kp.getVrsta() + ", proizvodjac=" + kp.getProizvodjac() + ", visina=" + kp.getVisina()
				+ ", sirina=" + kp.getSirina() + ", duzina=" + kp.getDuzina() + "]",kp.toString());
	}

	@Test
	void testEqualsObjectIsti() {
		KancelarijskiProizvod drugi=kp;
		assertTrue(kp.equals(drugi));
	}
	
	@Test
	void testEqualsObjectNull() {
		
		assertFalse(kp.equals(null));
	}
	
	@Test
	void testEqualsObjectRazlKlasa() {
		
		assertFalse(kp.equals("123"));
	}
	
	
	@ParameterizedTest
	@CsvSource({
		    "1,1,prosek,prosek,gumica,gumica,true",
	        "1,2,prosek,prosek,gumica,gumica,false",
	        "1,1,prosek,prosek1,gumica,gumica,false",
	        "1,1,prosek,prosek,gumica,gumica1,false",
	        "1,1,prosek,prosek1,gumica,gumica1,false",
	        "1,2,prosek,prosek1,gumica,gumica,false",
	        "1,2,prosek,prosek,gumica,gumica1,false",
	        "1,2,prosek,prosek1,gumica,gumica2,false",
	})
	void testEqualsObject(Long id1,Long id2,String proizvodjac1,String proizvodjac2,String vrsta1,String vrsta2,boolean indikator) {
		KancelarijskiProizvod drugi=new KancelarijskiProizvod(id2, 1000, "123", 1,vrsta2, proizvodjac2, 1, 2, 3);
		kp.setProizvodjac(proizvodjac1);
		kp.setVrsta(vrsta1);
		kp.setProizvodID(id1);
		
		assertEquals(indikator, kp.equals(drugi));
		
	}
	
	

	@Test
	void testKancelarijskiProizvod() {
		
		
	   kp=new KancelarijskiProizvod(30L, 20, "Naziv 1", 1, "dobra vrsta","dobar proizvodjac", 1, 2, 3);
	   assertEquals(30L, kp.getProizvodID());
	   assertEquals(20, kp.getCena());
	   assertEquals("Naziv 1",kp.getNaziv());
	   assertEquals(1,kp.getTip());
	   assertEquals("dobra vrsta",kp.getVrsta());
	   assertEquals("dobar proizvodjac",kp.getProizvodjac());
	   assertEquals(1,kp.getVisina());
	   assertEquals(2,kp.getSirina());
	   assertEquals(3, kp.getDuzina());
	}
	
	@Test
	void testKancelarijskiProizvodLose() {
		assertThrows(java.lang.NullPointerException.class, ()->new KancelarijskiProizvod(30L, 20, null, 1, "dobra vrsta","dobar proizvodjac", 1, 2, 3));

		assertThrows(java.lang.NullPointerException.class, ()->new KancelarijskiProizvod(30L, 20, "Naziv 1", 1, null,"dobar proizvodjac", 1, 2, 3));
		
		assertThrows(java.lang.NullPointerException.class, ()->new KancelarijskiProizvod(30L, 20, "Naziv 1", 1, "dobra vrsta",null, 1, 2, 3));
		
		
		assertThrows(java.lang.IllegalArgumentException.class, ()->new KancelarijskiProizvod(30L,0, "Naziv 1", 1, "dobra vrsta","dobar proizvodjac", 1, 2, 3));
		
		assertThrows(java.lang.IllegalArgumentException.class, ()->new KancelarijskiProizvod(30L,1, "Naziv 1", 2, "dobra vrsta","dobar proizvodjac", 1, 2, 3));
		
		assertThrows(java.lang.IllegalArgumentException.class, ()->new KancelarijskiProizvod(30L,1, "Naziv 1", 1, "dobra vrsta","dobar proizvodjac", 0, 2, 3));
		
		assertThrows(java.lang.IllegalArgumentException.class, ()->new KancelarijskiProizvod(30L,1, "Naziv 1", 1, "dobra vrsta","dobar proizvodjac", 1, 0, 3));
		
		assertThrows(java.lang.IllegalArgumentException.class, ()->new KancelarijskiProizvod(30L,1, "Naziv 1", 1, "dobra vrsta","dobar proizvodjac", 1, 2,0));
	}

	@Test
	void testGetVrsta() {
	     kp.setVrsta("vrsta123");
	     assertEquals("vrsta123",kp.getVrsta());
	}

	@Test
	void testSetVrsta() {
		kp.setVrsta("vrsta123");
	     assertEquals("vrsta123",kp.getVrsta());
	}
	
	@Test
	void testSetVrstaLose() {
		assertThrows(java.lang.NullPointerException.class,()-> kp.setVrsta(null));
		
		assertThrows(java.lang.IllegalArgumentException.class,()-> kp.setVrsta(""));
	}

	@Test
	void testGetProizvodjac() {
		kp.setProizvodjac("proizvodjac");
	     assertEquals("proizvodjac",kp.getProizvodjac());
	}

	@Test
	void testSetProizvodjac() {

		kp.setProizvodjac("proizvodjac");
	     assertEquals("proizvodjac",kp.getProizvodjac());
	}
	
	@Test
	void testSetProizvodjacLose() {
		assertThrows(java.lang.NullPointerException.class,()-> kp.setProizvodjac(null));
		
		assertThrows(java.lang.IllegalArgumentException.class,()-> kp.setProizvodjac(""));
	}

	@Test
	void testGetVisina() {
		kp.setVisina(2);
	     assertEquals(2,kp.getVisina());
	}

	@Test
	void testSetVisina() {
		kp.setVisina(2);
	     assertEquals(2,kp.getVisina());
	}
	
	@Test
	void testSetVisinaLose() {
		
		
		assertThrows(java.lang.IllegalArgumentException.class,()-> kp.setVisina(-1));
	}

	@Test
	void testGetSirina() {
		kp.setSirina(3);
	     assertEquals(3,kp.getSirina());
	}

	@Test
	void testSetSirina() {
		kp.setSirina(3);
	     assertEquals(3,kp.getSirina());
	}
	
	@Test
	void testSetSirinaLose() {
		
		
		assertThrows(java.lang.IllegalArgumentException.class,()-> kp.setSirina(-2));
	}

	@Test
	void testGetDuzina() {
		kp.setDuzina(4);
	     assertEquals(4,kp.getDuzina());
	}

	@Test
	void testSetDuzina() {
		kp.setDuzina(4);
	     assertEquals(4,kp.getDuzina());
	}
	
	@Test
	void testSetDuzinaLose() {
		
		
		assertThrows(java.lang.IllegalArgumentException.class,()-> kp.setDuzina(-3));
	}

}
