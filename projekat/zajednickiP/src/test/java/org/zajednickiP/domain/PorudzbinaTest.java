package org.zajednickiP.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PorudzbinaTest extends AbstractDomainObjectTest{

	Kupac k;
	Administrator a;
	Porudzbina p;
	
	StavkaPorudzbine sp1;
	StavkaPorudzbine sp2;
	StavkaPorudzbine sp3;
	
	Proizvod p1;
	Proizvod p2;
	Proizvod p3;

	public AbstractDomainObject getInstance() {
		k=new Kupac(20L, "ime", "prezime", "kupac@gmail.com", "FREE");
		a=new Administrator(30L, "Marko", "Markovic", "username", "password");
		
		return new Porudzbina(20L, new Date(), new Date(), "Grad", "Adresa", 20, 10,18, k,a, new ArrayList<>());
	}
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		
		p1=new KancelarijskiProizvod(20L, 30, "Naziv", 1, "vrsta","Proizvodjac", 1, 2, 3);
		p2=new Knjiga(50L, 300, "Naziv 1", 2, 3, "opisic", new ArrayList<>());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
      // Date datumIsporuke = sdf.parse("21.05.3025.");
       
	     
	   
		
		k=new Kupac(20L, "ime", "prezime", "kupac@gmail.com", "FREE");
		a=new Administrator(30L, "Marko", "Markovic", "username", "password");
		
	   
	   
		
		p=new Porudzbina(20L, new Date(), new Date(), "Grad", "Adresa", 20, 10,18, k,a, new ArrayList<>());
		
		 sp1=new StavkaPorudzbine(p,20, 3, 5000, p1);
		 sp2=new StavkaPorudzbine(p,30, 5, 6000, p2);
		p.getStavkePorudzbine().add(sp1);
		p.getStavkePorudzbine().add(sp2);
	}

	@AfterEach
	void tearDown() throws Exception {
		k=null;
		a=null;
		p=null;
		
		sp1=null;
		sp2=null;
		p1=null;
		p2=null;
	}

	@Test
	void testNazivTabele() {
		assertEquals(" Porudzbina ", p.nazivTabele());
	}

	@Test
	void testAlijas() {
		assertEquals(" p ", p.alijas());
	}

	@Test
	void testJoin() {
		assertEquals(" JOIN KUPAC K ON (K.KUPACID = P.KUPACID) "
                + "JOIN ADMINISTRATOR A ON (A.ADMINISTRATORID = P.ADMINISTRATORID) ", p.join());
	}

	
	@Test
	void testKoloneZaInsert() {
		assertEquals(" (datumVreme, datumIsporuke, grad, adresa, cena, popust, konacnaCena, "
                + "kupacID, administratorID) ", p.koloneZaInsert());
	}

	@Test
	void testUslov() {
		assertEquals(" porudzbinaID = " + p.getPorudzbinaID(), p.uslov());
	}

	@Test
	void testVrednostiZaInsert() {
		assertEquals("'" + new Timestamp(p.getDatumVreme().getTime()) + "', "
                + "'" + new java.sql.Date(p.getDatumIsporuke().getTime()) + "', "
                + "'" + p.getGrad() + "', '" + p.getAdresa() + "', " + p.getCena() + ", " + p.getPopust() + ", "
                + p.getKonacnaCena() + ", " + p.getKupac().getKupacID() + ", " + p.getAdministrator().getAdministratorID(), p.vrednostiZaInsert());
	}

	@Test
	void testVrednostiZaUpdate() {
		assertEquals(" datumIsporuke = '" + new java.sql.Date(p.getDatumIsporuke().getTime()) + "', "
                + "grad = '" + p.getGrad() + "', "
                + "adresa = '" + p.getAdresa() + "', cena = " + p.getCena() + ", "
                + "konacnaCena = " + p.getKonacnaCena(), p.vrednostiZaUpdate());
	}

	@Test
	void testUslovZaSelect() {
		assertEquals("WHERE CONCAT(K.IME, ' ', K.PREZIME) = '" + p.getKupac().getIme() + " " + p.getKupac().getPrezime() + "'", p.uslovZaSelect());
	}

	@Test
	void testPorudzbina() {
		k=new Kupac(20L, "Kupac 1", "prezime 1", "kupac1@gmail.com", "PREMIUM");
		a=new Administrator(30L, "Admin 1", "Markovic", "username", "password");
		
		p=new Porudzbina(20L, new Date(), new Date(), "Beograd", "Vozdovac", 20, 10,18, k,a, new ArrayList<>());
		
		
		
		assertEquals(20L, p.getPorudzbinaID());
		assertEquals(new Date(), p.getDatumVreme());
		assertEquals(new Date(), p.getDatumIsporuke());
		assertEquals("Beograd",p.getGrad());
		assertEquals("Vozdovac", p.getAdresa());
		assertEquals(20, p.getCena());
		assertEquals(10, p.getPopust());
		assertEquals(18, p.getKonacnaCena());
		assertEquals(k, p.getKupac());
		assertEquals(a, p.getAdministrator());
		assertEquals(new ArrayList<>(), p.getStavkePorudzbine());
		
		
		sp1=new StavkaPorudzbine(p,20, 3, 5000, p1);
		 sp2=new StavkaPorudzbine(p,30, 5, 6000, p2);
		p.getStavkePorudzbine().add(sp1);
		p.getStavkePorudzbine().add(sp2);
		
		ArrayList<StavkaPorudzbine> stavke=new ArrayList<>();
		stavke.add(sp1);
		stavke.add(sp2);
		assertEquals(stavke, p.getStavkePorudzbine());
	}
	
	
	@Test
	void testPorudzbinaLose() {
		k=new Kupac(20L, "Kupac 1", "prezime 1", "kupac1@gmail.com", "PREMIUM");
		a=new Administrator(30L, "Admin 1", "Markovic", "username", "password");
		
		p=new Porudzbina(20L, new Date(), new Date(), "Beograd", "Vozdovac", 20, 10,18, k,a, new ArrayList<>());
		
		
		
		assertThrows(java.lang.NullPointerException.class, ()->new Porudzbina(20L, null, new Date(), "Beograd", "Vozdovac", 20, 10,18, k,a, new ArrayList<>()));
		assertThrows(java.lang.NullPointerException.class, ()->new Porudzbina(20L, new Date(), null, "Beograd", "Vozdovac", 20, 10,18, k,a, new ArrayList<>()));
		assertThrows(java.lang.NullPointerException.class, ()->new Porudzbina(20L, new Date(), new Date(), null, "Vozdovac", 20, 10,18, k,a, new ArrayList<>()));
		assertThrows(java.lang.NullPointerException.class, ()->new Porudzbina(20L, new Date(), new Date(), "Beograd", null, 20, 10,18, k,a, new ArrayList<>()));
		assertThrows(java.lang.NullPointerException.class, ()->new Porudzbina(20L, new Date(), new Date(), "Beograd", "Vozdovac", 20, 10,18, null,a, new ArrayList<>()));
		assertThrows(java.lang.NullPointerException.class, ()->new Porudzbina(20L, new Date(), new Date(), "Beograd", "Vozdovac", 20, 10,18, k,null, new ArrayList<>()));
		assertThrows(java.lang.NullPointerException.class, ()->new Porudzbina(20L, new Date(), new Date(), "Beograd", "Vozdovac", 20, 10,18, k,a, null));
		
		
		
	
		assertThrows(java.lang.IllegalArgumentException.class, ()->new Porudzbina(20L, new Date(), new Date(), "", "Vozdovac", 20, 10,18, k,a, new ArrayList<>()));
		assertThrows(java.lang.IllegalArgumentException.class, ()->new Porudzbina(20L, new Date(), new Date(), "Beorgad", "", 20, 10,18, k,a, new ArrayList<>()));
		assertThrows(java.lang.IllegalArgumentException.class, ()->new Porudzbina(20L, new Date(), new Date(), "Beograd", "Vozdovac", -1, 10,18, k,a, new ArrayList<>()));
		assertThrows(java.lang.IllegalArgumentException.class, ()->new Porudzbina(20L, new Date(), new Date(), "Beograd", "Vozdovac", 20, -1,18, k,a, new ArrayList<>()));
		assertThrows(java.lang.IllegalArgumentException.class, ()->new Porudzbina(20L, new Date(), new Date(), "Beograd", "Vozdovac", 20, 10,-1, k,a, new ArrayList<>()));

	}

	@Test
	void testToString() {
		assertEquals("Porudzbina{" + "datumVreme=" + p.getDatumVreme() + ", datumIsporuke=" + p.getDatumIsporuke() + ", kupac=" + p.getKupac() + '}', p.toString());
    }

	@Test
	void testGetPorudzbinaID() {
		p.setPorudzbinaID(22L);
		assertEquals(22L, p.getPorudzbinaID());
	}

	@Test
	void testSetPorudzbinaID() {
		p.setPorudzbinaID(22L);
		assertEquals(22L, p.getPorudzbinaID());
	}

	@Test
	void testGetDatumVreme() {
		p.setDatumVreme(new Date());
		assertEquals(new Date(), p.getDatumVreme());
	}

	@Test
	void testSetDatumVreme() {
		p.setDatumVreme(new Date());
		assertEquals(new Date(), p.getDatumVreme());
	}
	@Test
	void testSetDatumVremeLose() {
		assertThrows(java.lang.NullPointerException.class, ()->p.setDatumVreme(null));
	}

	@Test
	void testGetDatumIsporuke() {
		p.setDatumIsporuke(new Date());
		assertEquals(new Date(), p.getDatumIsporuke());
	}

	@Test
	void testSetDatumIsporuke() {
		p.setDatumIsporuke(new Date());
		assertEquals(new Date(), p.getDatumIsporuke());
	}
	
	@Test
	void testSetDatumIsporukeLose() {
		assertThrows(java.lang.NullPointerException.class, ()->p.setDatumIsporuke(null));
		
		
		
	    
	}

	@Test
	void testGetGrad() {
		p.setGrad("grad123");
		assertEquals("grad123",p.getGrad());
	}

	@Test
	void testSetGrad() {
		p.setGrad("grad123");
		assertEquals("grad123",p.getGrad());
	}
	
	@Test
	void testSetGradLose() {
		assertThrows(java.lang.NullPointerException.class, ()->p.setGrad(null));
	    assertThrows(java.lang.IllegalArgumentException.class, ()->p.setGrad(""));
		
	    
	}

	@Test
	void testGetAdresa() {
		p.setAdresa("adresa123");
		assertEquals("adresa123",p.getAdresa());
	}

	@Test
	void testSetAdresa() {
		p.setAdresa("adresa123");
		assertEquals("adresa123",p.getAdresa());
	}
	
	@Test
	void testSetAdresaLose() {
		assertThrows(java.lang.NullPointerException.class, ()->p.setAdresa(null));
	    assertThrows(java.lang.IllegalArgumentException.class, ()->p.setAdresa(""));
		
	    
	}

	@Test
	void testGetCena() {
		p.setCena(23);
		assertEquals(23, p.getCena());
	}

	@Test
	void testSetCena() {
		p.setCena(23);
		assertEquals(23, p.getCena());
	}

	@Test
	void testSetCenaLose() {

	    assertThrows(java.lang.IllegalArgumentException.class, ()->p.setCena(-1));
		
	    
	}
	@Test
	void testGetPopust() {
		p.setPopust(20);
		assertEquals(20, p.getPopust());
	}

	@Test
	void testSetPopust() {
		p.setPopust(20);
		assertEquals(20, p.getPopust());
	}
	@Test
	void testSetPopustLose() {

	    assertThrows(java.lang.IllegalArgumentException.class, ()->p.setPopust(-1));
		
	    
	}

	@Test
	void testGetKonacnaCena() {
		p.setKonacnaCena(100);
		assertEquals(100, p.getKonacnaCena());
	}

	@Test
	void testSetKonacnaCena() {
		p.setKonacnaCena(100);
		assertEquals(100, p.getKonacnaCena());
	}
	
	@Test
	void testSetKonacnaCenaLose() {

	    assertThrows(java.lang.IllegalArgumentException.class, ()->p.setKonacnaCena(-1));
		
	    
	}

	@Test
	void testGetKupac() {
		Kupac kupac=new Kupac(23L, "Kupac1", "Prezime1", "email1","tip1");
		p.setKupac(kupac);
		assertEquals(kupac, p.getKupac());
	}

	@Test
	void testSetKupac() {
		Kupac kupac=new Kupac(23L, "Kupac1", "Prezime1", "email1","tip1");
		p.setKupac(kupac);
		assertEquals(kupac, p.getKupac());
	}
	
	@Test
	void testSetKupacLose() {

	    assertThrows(java.lang.NullPointerException.class, ()->p.setKupac(null));
		
	    
	}

	@Test
	void testGetAdministrator() {
		Administrator administrator=new Administrator(100L, "admin1", "adminic", "123", "456");
		p.setAdministrator(administrator);
		assertEquals(administrator, p.getAdministrator());
	}

	@Test
	void testSetAdministrator() {
		Administrator administrator=new Administrator(100L, "admin1", "adminic", "123", "456");
		p.setAdministrator(administrator);
		assertEquals(administrator, p.getAdministrator());
	}
	
	@Test
	void testSetAdministratorLose() {

	    assertThrows(java.lang.NullPointerException.class, ()->p.setAdministrator(null));
		
	    
	}

	@Test
	void testGetStavkePorudzbine() {
		p1=new KancelarijskiProizvod(50L, 40, "Naziv 123", 1, "gumice","Novi sad fabrika", 1, 2, 3);
		p2=new Knjiga(60L, 500, "Proizvodic", 2, 4, "opisic123", new ArrayList<>());
		p3=new KancelarijskiProizvod(60L, 50, "Naziv 1234", 1, "fascikle","Suboticka fabrika", 1, 5, 7);
		 sp1=new StavkaPorudzbine(p,20, 3, 5000, p1);
		 sp2=new StavkaPorudzbine(p,30, 5, 6000, p2);
		 sp3=new StavkaPorudzbine(p, 40, 10, 8500, p3);
		 
		 ArrayList<StavkaPorudzbine> stavke=new ArrayList<StavkaPorudzbine>();
		 stavke.add(sp1);
		 stavke.add(sp2);
		 stavke.add(sp3);
		 
		 p.setStavkePorudzbine(stavke);
		 assertEquals(stavke, p.getStavkePorudzbine());
	}

	@Test
	void testSetStavkePorudzbine() {
		p1=new KancelarijskiProizvod(50L, 40, "Naziv 123", 1, "gumice","Novi sad fabrika", 1, 2, 3);
		p2=new Knjiga(60L, 500, "Proizvodic", 2, 4, "opisic123", new ArrayList<>());
		p3=new KancelarijskiProizvod(60L, 50, "Naziv 1234", 1, "fascikle","Suboticka fabrika", 1, 5, 7);
		 sp1=new StavkaPorudzbine(p,20, 3, 5000, p1);
		 sp2=new StavkaPorudzbine(p,30, 5, 6000, p2);
		 sp3=new StavkaPorudzbine(p, 40, 10, 8500, p3);
		 
		 ArrayList<StavkaPorudzbine> stavke=new ArrayList<StavkaPorudzbine>();
		 stavke.add(sp1);
		 stavke.add(sp2);
		 stavke.add(sp3);
		 
		 p.setStavkePorudzbine(stavke);
		 assertEquals(stavke, p.getStavkePorudzbine());
	}
	
	@Test
	void testSetStavkePorudzbineLose() {

	    assertThrows(java.lang.NullPointerException.class, ()->p.setStavkePorudzbine(null));
		
	}
	
	

	@Test
	void testEqualsObjectIsti() {
		Porudzbina druga=p;
		assertTrue(p.equals(druga));
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
		Porudzbina druga=new Porudzbina(p.getPorudzbinaID(), p.getDatumVreme(),p.getDatumIsporuke(),p.getGrad(),p.getAdresa(), p.getCena(), p.getPopust(), p.getKonacnaCena(),p.getKupac(), p.getAdministrator(), p.getStavkePorudzbine());
		
		assertTrue(p.equals(druga));
		
		druga.setPorudzbinaID(p.getPorudzbinaID()-1);
		assertFalse(p.equals(druga));
		
		druga.setPorudzbinaID(p.getPorudzbinaID());
		druga.setAdministrator(new Administrator(3L, "123", "prezime", "x", "x"));
		assertFalse(p.equals(druga));
		druga.setAdministrator(p.getAdministrator());
		druga.setKupac(new Kupac(856L, "ime","Prezime1", "ad", "cm"));
		
		assertFalse(p.equals(druga));
		
	}
	
	

}
