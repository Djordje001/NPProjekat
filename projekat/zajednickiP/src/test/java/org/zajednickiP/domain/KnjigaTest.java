package org.zajednickiP.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnjigaTest extends AbstractDomainObjectTest {

	
	Knjiga k;
	Autor a1;
	Autor a2;
	Pisac p1;
	Pisac p2;
	
	public AbstractDomainObject getInstance() {
		return new Knjiga(null, 10000, "Naziv", 2, 2, "Opis", new ArrayList<>());
	}
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		ArrayList<Autor> autori=new ArrayList<>();
		k=new Knjiga(null, 10000, "Naziv", 2, 2, "Opis",autori);
		Pisac p1=new Pisac(null, "Milos", "Obilic", "x@gmail.com");
		a1=new Autor(k, 0, p1);
		Pisac p2=new Pisac(null, "Marko", "Kraljevic", "y@gmail.com");
		a2=new Autor(k,0,p2);
		autori.add(a1);
		autori.add(a2);
		
		k.setAutori(autori);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		k=null;
		a1=null;
		a2=null;
		p1=null;
		p2=null;
	}

	@Test
	void testNazivTabele() {
		assertEquals(" Knjiga ", k.nazivTabele());
	}

	@Test
	void testAlijas() {
		assertEquals(" k ", k.alijas());
	}

	@Test
	void testJoin() {
		assertEquals(" JOIN PROIZVOD P ON (P.PROIZVODID = K.PROIZVODID) ", k.join());
	}

	

	@Test
	void testKoloneZaInsert() {
		assertEquals(" (proizvodID,izdanje,opis) ", k.koloneZaInsert());
	}

	@Test
	void testUslov() {
		assertEquals(" proizvodID = " +k.getProizvodID(),k.uslov());
	}

	@Test
	void testVrednostiZaInsert() {
		assertEquals(k.getProizvodID()+", "+k.getIzdanje()+", '"+k.getOpis()+"'",k.vrednostiZaInsert());
	}

	@Test
	void testVrednostiZaUpdate() {
		assertEquals(" Izdanje = " + k.getIzdanje() + ", Opis = '" + k.getOpis() + "' ", k.vrednostiZaUpdate());
	}

	@Test
	void testUslovZaSelect() {
		assertEquals("",k.uslovZaSelect());
	}

	@Test
	void testToString() {
		Proizvod p=new Proizvod(k.getProizvodID(), k.getCena(), k.getNaziv(), k.getTip());
		assertEquals(p.toString()+ "Knjiga [ izdanje=" + k.getIzdanje() + ", opis=" + k.getOpis() + ", autori=" + k.getAutori() + "]", k.toString());
	}

	@Test
	void testEqualsObjectIsti() {
		Knjiga druga=k;
		assertTrue(k.equals(druga));
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
		    "1,1,1,1,gumica,gumica,true",
	        "1,2,1,1,gumica,gumica,false",
	        "1,1,1,2,gumica,gumica,false",
	        "1,1,1,1,gumica,gumica1,false",
	        "1,1,1,2,gumica,gumica1,false",
	        "1,2,1,2,gumica,gumica,false",
	        "1,2,1,1,gumica,gumica1,false",
	        "1,2,1,2,gumica,gumica2,false",
	})
	void testEqualsObject(Long id1,Long id2,int izdanje1,int izdanje2,String opis1,String opis2,boolean indikator) {
		
		
		Knjiga drugi=new Knjiga(id2, 1000, "Naziv", 2, izdanje2, opis2, new ArrayList<>());
		
		k.setProizvodID(id1);
		k.setIzdanje(izdanje1);
		k.setOpis(opis1);
		
		assertEquals(indikator, k.equals(drugi));
		
	}

	@Test
	void testKnjigaOk() {
		
		k=new Knjiga(1L, 10000, "Naziv", 2, 3, "Opis", new ArrayList<>());
		
		assertEquals(1L, k.getProizvodID());
		assertEquals(10000, k.getCena());
		assertEquals("Naziv", k.getNaziv());
		assertEquals(2, k.getTip());
		assertEquals(3, k.getIzdanje());
		assertEquals("Opis", k.getOpis());
		assertEquals(new ArrayList<>(), k.getAutori());
	}
	
	@Test
	void testKnjigaLose() {
		
		assertThrows(java.lang.NullPointerException.class,()-> new Knjiga(1L,10000,null,2,3,"opis",new ArrayList<>()));
		assertThrows(java.lang.NullPointerException.class,()-> new Knjiga(1L,10000,"hehe",2,3,null,new ArrayList<>()));
		assertThrows(java.lang.NullPointerException.class,()-> new Knjiga(1L,10000,"hehe",2,3,"opis",null));
		
		assertThrows(java.lang.IllegalArgumentException.class,()-> new Knjiga(1L,10000,"hehe",5,3,"opis",new ArrayList<>()));

		assertThrows(java.lang.IllegalArgumentException.class,()-> new Knjiga(1L,-1,"hehe",2,3,"opis",new ArrayList<>()));
		assertThrows(java.lang.IllegalArgumentException.class,()-> new Knjiga(1L,2000,"hehe",2,-5,"opis",new ArrayList<>()));
	}

	@Test
	void testGetIzdanje() {
		k.setIzdanje(20);
		assertEquals(20,k.getIzdanje());
	}

	@Test
	void testSetIzdanje() {
		k.setIzdanje(20);
		assertEquals(20,k.getIzdanje());
	}
	
	@Test
	void testSetIzdanjeLose() {
		assertThrows(java.lang.IllegalArgumentException.class,()->k.setIzdanje(-1));
	}
	

	@Test
	void testGetOpis() {
		k.setOpis("opis123");
		assertEquals("opis123",k.getOpis());
	}

	@Test
	void testSetOpis() {
		k.setOpis("opis123");
		assertEquals("opis123",k.getOpis());
	}
	
	@Test
	void testSetOpisLose() {
		assertThrows(java.lang.NullPointerException.class,()-> k.setOpis(null));
		
		assertThrows(java.lang.IllegalArgumentException.class,()-> k.setOpis(""));
	}

	@Test
	void testGetAutori() {
		ArrayList<Autor> autori=new ArrayList<>();
		k=new Knjiga(null, 10000, "Naziv", 2, 2, "Opis",autori);
		Pisac p1=new Pisac(null, "Milos", "Obilic", "x@gmail.com");
		a1=new Autor(k, 0, p1);
		Pisac p2=new Pisac(null, "Marko", "Kraljevic", "y@gmail.com");
		a2=new Autor(k,0,p2);
		autori.add(a1);
		autori.add(a2);
		
		k.setAutori(autori);
		
		
		assertEquals(autori,k.getAutori());
	}

	@Test
	void testSetAutori() {
		ArrayList<Autor> autori=new ArrayList<>();
		k=new Knjiga(null, 10000, "Naziv", 2, 2, "Opis",autori);
		Pisac p1=new Pisac(null, "Milos", "Obilic", "x@gmail.com");
		a1=new Autor(k, 0, p1);
		Pisac p2=new Pisac(null, "Marko", "Kraljevic", "y@gmail.com");
		a2=new Autor(k,0,p2);
		autori.add(a1);
		autori.add(a2);
		
		k.setAutori(autori);
		
		
		assertEquals(autori,k.getAutori());
	}
	
	@Test
	void testSetAutoriLose() {
		assertThrows(java.lang.NullPointerException.class,()-> k.setAutori(null));
		
		
	}

}
