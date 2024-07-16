package napredno.programiranje.serverP.so.proizvod;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Statement;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import napredno.programiranje.serverP.db.DBBroker;
import napredno.programiranje.serverP.so.AbstractSO;
import napredno.programiranje.serverP.so.AbstractSOTest;
import napredno.programiranje.serverP.so.autor.SOFindAllAutor;
import napredno.programiranje.serverP.so.pisac.SOGetAllPisac;
import napredno.programiranje.serverP.so.proizvod.SOAddProizvod;
import napredno.programiranje.serverP.so.proizvod.SOGetAllProizvod;
import napredno.programiranje.zajednickiP.domain.Autor;
import napredno.programiranje.zajednickiP.domain.KancelarijskiProizvod;
import napredno.programiranje.zajednickiP.domain.Knjiga;
import napredno.programiranje.zajednickiP.domain.Kupac;
import napredno.programiranje.zajednickiP.domain.Pisac;
import napredno.programiranje.zajednickiP.domain.Proizvod;

class SOAddProizvodTest  extends AbstractSOTest{

	SOAddProizvod so;
	SOGetAllProizvod soGAP;
	SOFindAllAutor soFAA;
	SOGetAllPisac soGAP1;
	public AbstractSO getInstance() {
		return new SOAddProizvod();
	}
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		so=new SOAddProizvod();
		soGAP=new SOGetAllProizvod();
		soFAA=new SOFindAllAutor();
		soGAP1=new SOGetAllPisac();
	}

	@AfterEach
	void tearDown() throws Exception {
		so=null;
		soGAP=null;
		soFAA=null;
		soGAP1=null;
	}
	

	@Test
	void testTemplateExecutePogresno() {
		assertThrows(java.lang.NullPointerException.class,()->so.templateExecute(null));
		assertThrows(java.lang.IllegalArgumentException.class,()->so.templateExecute(new Kupac(null, "x", "x", "x", "x")));
	}

	@Test
	void testTemplateExecuteKancelarijskiProizvod() throws Exception {
		assertTrue(soGAP.getLista()==null);
		soGAP.templateExecute(new Proizvod(null, 2, "w", 1));
		assertTrue(soGAP.getLista()!=null);
		
		ArrayList<Proizvod> proizvodi=soGAP.getLista();
		
		Proizvod km=new KancelarijskiProizvod(null, 2, "123", 1, "hhe", "dada", 1, 2, 3);
		assertTrue(!proizvodi.contains(km));
		
		so.templateExecute(km);
		soGAP.templateExecute(new Proizvod(null, 2, "w", 1));
		ArrayList<Proizvod> novi=soGAP.getLista();
		assertTrue(novi.contains(km));
		assertTrue(novi.size()==proizvodi.size()+1);
		
		
		try {
			//ponistavam efekat testa
	        String upit="DELETE FROM proizvod WHERE ProizvodID = "+km.getProizvodID();
			Statement s=DBBroker.getInstance().getConnection().createStatement();
			s.executeUpdate(upit);
			System.out.println(upit);
			DBBroker.getInstance().getConnection().commit();
			}catch(Exception e) {
				DBBroker.getInstance().getConnection().rollback();
			}
		
	}
	
	@Test
	void testTemplateExecuteKnjiga() throws Exception {
		assertTrue(soGAP.getLista()==null);
		soGAP.templateExecute(new Proizvod(null, 2, "w", 1));
		assertTrue(soGAP.getLista()!=null);
		
		ArrayList<Proizvod> proizvodi=soGAP.getLista();
		
		soGAP1.templateExecute(new Pisac(null,"x","x","x"));
		ArrayList<Pisac> pisci=soGAP1.getLista();
		if(pisci.size()>1) {

		Knjiga k=new Knjiga(null, 3, "hhe", 2, 2, "naslov",new ArrayList<>());
		Autor a1=new Autor(k, 1, pisci.get(0));
		Autor a2=new Autor(k, 1, pisci.get(1));
		k.getAutori().add(a1);
		k.getAutori().add(a2);
		
		assertTrue(!proizvodi.contains(k));
		
		so.templateExecute(k);
		soGAP.templateExecute(new Proizvod(null, 2, "w", 1));
		ArrayList<Proizvod> novi=soGAP.getLista();
		assertTrue(novi.contains(k));
		assertTrue(novi.size()==proizvodi.size()+1);
		
		
		soFAA.templateExecute(a1);
		ArrayList<Autor> autori=soFAA.getLista();
		assertTrue(autori.size()==2);
		assertTrue(autori.equals(k.getAutori()));
		
		try {
			//ponistavam efekat testa
	        String upit="DELETE FROM proizvod WHERE ProizvodID = "+k.getProizvodID();
			Statement s=DBBroker.getInstance().getConnection().createStatement();
			s.executeUpdate(upit);
			System.out.println(upit);
			DBBroker.getInstance().getConnection().commit();
			}catch(Exception e) {
				DBBroker.getInstance().getConnection().rollback();
			}
		
		}
	}

}
