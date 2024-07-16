package org.zajednickiP.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StavkaPorudzbineTest extends AbstractDomainObjectTest{
	Porudzbina p;
	Kupac k;
	Administrator a;
	Proizvod proizvod;
	StavkaPorudzbine sp;
	
	public AbstractDomainObject getInstance() {
		proizvod=new KancelarijskiProizvod(20L, 30, "Naziv", 1, "vrsta","Proizvodjac", 1, 2, 3);
		 k=new Kupac(30L, "Djordje", "Djordje", "Djordjevic", "FREE");
		a=new Administrator(40L, "Anastasija", "Madic", "maki", "maki");
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
       // Date datumIsporuke = sdf.parse("21.05.3025.");
        
	     p=new Porudzbina(20L, new Date(), new Date(), "grad","adresa", 2000, 20, 1600, k, a, new ArrayList<>());
	     sp=new StavkaPorudzbine(p,20, 3, 5000, proizvod);
		return sp;
	}

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		proizvod=new KancelarijskiProizvod(20L, 30, "Naziv", 1, "vrsta","Proizvodjac", 1, 2, 3);
		 k=new Kupac(30L, "Djordje", "Djordje", "Djordjevic", "FREE");
		a=new Administrator(40L, "Anastasija", "Madic", "maki", "maki");
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
      // Date datumIsporuke = sdf.parse("21.05.3025.");
       
	     p=new Porudzbina(20L, new Date(), new Date(), "grad","adresa", 2000, 20, 1600, k, a, new ArrayList<>());
	     sp=new StavkaPorudzbine(p,20, 3, 5000, proizvod);
	}

	@AfterEach
	void tearDown() throws Exception {
		
		sp=null;
		p=null;
		a=null;
		k=null;
		proizvod=null;
		
		
	}

	@Test
	void testNazivTabele() {
		assertEquals(" StavkaPorudzbine ", sp.nazivTabele());
	}

	@Test
	void testAlijas() {
		assertEquals(" sp ", sp.alijas());
	}

	@Test
	void testJoin() {
		assertEquals(" JOIN PROIZVOD PR ON (PR.PROIZVODID = SP.PROIZVODID) "
                + "JOIN PORUDZBINA P ON (P.PORUDZBINAID = SP.PORUDZBINAID) "
                + "JOIN KUPAC K ON (K.KUPACID = P.KUPACID) "
                + "JOIN ADMINISTRATOR A ON (A.ADMINISTRATORID = P.ADMINISTRATORID) "
                +"LEFT JOIN KANCELARIJSKIPROIZVOD KM ON (PR.TIP = 1 AND KM.PROIZVODID = SP.PROIZVODID) "
                +"LEFT JOIN KNJIGA KJ ON (PR.TIP = 2 AND KJ.PROIZVODID = SP.PROIZVODID) ", sp.join());
	}

	

	@Test
	void testKoloneZaInsert() {
		assertEquals(" (porudzbinaID, kolicina, cena, proizvodID) ", sp.koloneZaInsert());
	}

	@Test
	void testUslov() {
		assertEquals(" porudzbinaID = " + sp.getPorudzbina().getPorudzbinaID(), sp.uslov());
	}

	@Test
	void testVrednostiZaInsert() {
		assertEquals(" " + sp.getPorudzbina().getPorudzbinaID() + ", "
                + " " + sp.getKolicina() + ", " + sp.getCena() + ", " + sp.getProizvod().getProizvodID(),sp.vrednostiZaInsert());
	}

	@Test
	void testVrednostiZaUpdate() {
		assertEquals("",sp.vrednostiZaUpdate());
	}

	@Test
	void testUslovZaSelect() {
		assertEquals(" WHERE P.PORUDZBINAID = " + sp.getPorudzbina().getPorudzbinaID(),sp.uslovZaSelect());
	}

	@Test
	void testStavkaPorudzbine() {
		proizvod=new KancelarijskiProizvod(20L, 30, "Naziv", 1, "vrsta","Proizvodjac", 1, 2, 3);
		 k=new Kupac(30L, "Djordje", "Djordje", "Djordjevic", "FREE");
		a=new Administrator(40L, "Anastasija", "Madic", "maki", "maki");
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
      // Date datumIsporuke = sdf.parse("21.05.3025.");
       
	     p=new Porudzbina(20L, new Date(), new Date(), "grad","adresa", 2000, 20, 1600, k, a, new ArrayList<>());
	     sp=new StavkaPorudzbine(p,20, 3, 5000, proizvod);
	     
	     assertEquals(p, sp.getPorudzbina());
	     assertEquals(20, sp.getRb());
	     assertEquals(3,sp.getKolicina());
	     assertEquals(5000, sp.getCena());
	     assertEquals(proizvod,sp.getProizvod());
	     
	     
	}
	
	@Test
	void testStavkaPorudzbineLose() {
		
		proizvod=new KancelarijskiProizvod(20L, 30, "Naziv", 1, "vrsta","Proizvodjac", 1, 2, 3);
		 k=new Kupac(30L, "Djordje", "Djordje", "Djordjevic", "FREE");
		a=new Administrator(40L, "Anastasija", "Madic", "maki", "maki");
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
     // Date datumIsporuke = sdf.parse("21.05.3025.");
      
	     p=new Porudzbina(20L, new Date(), new Date(), "grad","adresa", 2000, 20, 1600, k, a, new ArrayList<>());
	     sp=new StavkaPorudzbine(p,20, 3, 5000, proizvod);
	     
	     
		assertThrows(java.lang.NullPointerException.class,()->new StavkaPorudzbine(null, 20,3, 5000,proizvod));
		assertThrows(java.lang.NullPointerException.class,()->new StavkaPorudzbine(p, 20,3, 5000,null));
		
		assertThrows(java.lang.IllegalArgumentException.class,()->new StavkaPorudzbine(p, 20,-1, 5000,proizvod));
		
		assertThrows(java.lang.IllegalArgumentException.class,()->new StavkaPorudzbine(p, 20,2, -1,proizvod));
	}

	@Test
	void testGetPorudzbina() {
		proizvod=new KancelarijskiProizvod(30L, 40, "Naziv 123", 1, "vrsta 123","Proizvodjac 123", 1, 2, 3);
		 k=new Kupac(40L, "Djordje 123", "Djordje 123", "Djordjevic 123", "FREE 123");
		a=new Administrator(50L, "Anastasija123 ", "Madic 123", "maki 123", "maki 123");
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    // Date datumIsporuke = sdf.parse("21.05.3025.");
     
	     p=new Porudzbina(50L, new Date(), new Date(), "nova grad","nova adresa", 3000, 10, 1700, k, a, new ArrayList<>());
	     
	     
	     sp.setPorudzbina(p);
	     
	     assertEquals(p, sp.getPorudzbina());
	     
	     
	     
	}

	@Test
	void testSetPorudzbina() {
		proizvod=new KancelarijskiProizvod(30L, 40, "Naziv 123", 1, "vrsta 123","Proizvodjac 123", 1, 2, 3);
		 k=new Kupac(40L, "Djordje 123", "Djordje 123", "Djordjevic 123", "FREE 123");
		a=new Administrator(50L, "Anastasija123 ", "Madic 123", "maki 123", "maki 123");
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
   // Date datumIsporuke = sdf.parse("21.05.3025.");
    
	     p=new Porudzbina(50L, new Date(), new Date(), "nova grad","nova adresa", 3000, 10, 1700, k, a, new ArrayList<>());
	     
	     
	     sp.setPorudzbina(p);
	     
	     assertEquals(p, sp.getPorudzbina());
	}
	
	@Test
	void testSetPorudzbinaLose() {
		
		
		assertThrows(java.lang.NullPointerException.class,()->sp.setPorudzbina(null));
	}

	@Test
	void testGetRb() {
		sp.setRb(999);
		assertEquals(999, sp.getRb());
	}

	@Test
	void testSetRb() {
		sp.setRb(999);
		assertEquals(999, sp.getRb());
	}

	@Test
	void testGetKolicina() {
		sp.setKolicina(20);
		assertEquals(20, sp.getKolicina());
	}

	@Test
	void testSetKolicina() {
		sp.setKolicina(20);
		assertEquals(20, sp.getKolicina());
	}
	
	@Test
	void testSetKolicinaLose() {
		assertThrows(java.lang.IllegalArgumentException.class,()->sp.setKolicina(-1));
	}

	@Test
	void testGetCena() {
		sp.setCena(250);
		assertEquals(250, sp.getCena());
	}

	@Test
	void testSetCena() {
		sp.setCena(250);
		assertEquals(250, sp.getCena());
	}
	
	@Test
	void testSetCenaLose() {
		assertThrows(java.lang.IllegalArgumentException.class,()->sp.setCena(-1));
	}

	@Test
	void testGetProizvod() {
		Proizvod novi=new KancelarijskiProizvod(777L, 30, "abc", 1, "hehe", "proizvodjac", 2, 4, 6);
		sp.setProizvod(novi);
		assertEquals(novi, sp.getProizvod());
	}

	@Test
	void testSetProizvod() {
		Proizvod novi=new KancelarijskiProizvod(777L, 30, "abc", 1, "hehe", "proizvodjac", 2, 4, 6);
		sp.setProizvod(novi);
		assertEquals(novi, sp.getProizvod());
	}
	@Test
	void testSetProizvodLose() {
		assertThrows(java.lang.NullPointerException.class,()->sp.setProizvod(null));
	}

	@Test
	void testToString() {
		assertEquals("StavkaPorudzbine [porudzbina=" + sp.getPorudzbina() + ", rb=" + sp.getRb() + ", kolicina=" + sp.getKolicina() + ", cena=" + sp.getCena()
				+ ", proizvod=" + proizvod + "]", sp.toString());
	}

	@Test
	void testEqualsObjectIsti() {
		StavkaPorudzbine druga=sp;
		assertTrue(sp.equals(druga));
	}
	
	@Test
	void testEqualsObjectNull() {
		
		assertFalse(sp.equals(null));
	}
	
	@Test
	void testEqualsObjectRazlKlasa() {
		
		assertFalse(sp.equals("123"));
	}
	
	@Test
	void testEqualsObjectSveOk() {
		
		StavkaPorudzbine druga=new StavkaPorudzbine(sp.getPorudzbina(),sp.getRb(),sp.getKolicina(), sp.getCena(), sp.getProizvod());
		druga.setRb(230);
		
		assertFalse(sp.equals(druga));
		druga.setRb(sp.getRb());
		assertTrue(sp.equals(druga));
		
		
		
		Proizvod proizvodnovi=new KancelarijskiProizvod(30L, 30, "kanc", 1, "vrstica","Niska fabrika", 1, 2, 3);
		Kupac kupacnovi=new Kupac(30L, "Bojan", "Tomic", "email", "FREE");
		Administrator administratornovi=new Administrator(40L, "Dusanka", "Djordjevic", "dusica", "dusa");
		
     
	    Porudzbina porudbzinanova=new Porudzbina(100L, new Date(), new Date(), "momcilov grad","adresa 123", 5000, 30, 1700, k, a, new ArrayList<>());
		
	    
	    druga.setPorudzbina(porudbzinanova);
	    assertFalse(sp.equals(druga));
	    
	    druga.setRb(234);
	    assertFalse(sp.equals(druga));
	    
	    druga.setPorudzbina(sp.getPorudzbina());
	    assertFalse(sp.equals(druga));
		
	}

}
