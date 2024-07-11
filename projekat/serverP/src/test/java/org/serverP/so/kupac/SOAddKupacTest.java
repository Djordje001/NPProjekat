package org.serverP.so.kupac;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Statement;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.serverP.db.DBBroker;
import org.serverP.so.AbstractSO;
import org.serverP.so.AbstractSOTest;
import org.zajednickiP.domain.Kupac;
import org.zajednickiP.domain.Pisac;

class SOAddKupacTest extends AbstractSOTest{

	SOAddKupac so;
	SOGetAllKupac pomocna1;
	
	public AbstractSO getInstance() {
		return new SOAddKupac();
	}
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		so=new SOAddKupac();
		pomocna1=new SOGetAllKupac();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		so=null;
		pomocna1=null;
		
	}

	@Test
	void testTemplateExecuteNeuspesno() throws Exception {
		assertThrows(java.lang.NullPointerException.class,()->so.templateExecute(null));
		assertThrows(java.lang.IllegalArgumentException.class,()->so.templateExecute(new Pisac(1L,"ime","prezime", "email")));
		
		Kupac kupac=new Kupac(null, "Pedja", "Bandic", "pedja@gmail.com", "FREE");
		
		assertThrows(java.lang.IllegalArgumentException.class, ()->so.templateExecute(kupac));
		
	}
	
	@Test
	void testTemplateExecuteUspesno() throws Exception {
		Kupac kupac=new Kupac(null, "Milos", "Obilic", "obilic@gmail.com", "FREE");
		pomocna1.templateExecute(kupac);
		ArrayList<Kupac> kupci=pomocna1.getLista();
		
		assertFalse(kupci.contains(kupac));
		
		so.templateExecute(kupac);
		pomocna1.templateExecute(kupac);
		ArrayList<Kupac> novi=pomocna1.getLista();
		assertTrue(novi.size()==kupci.size()+1);
		
		
		assertTrue(novi.contains(kupac));

		System.out.println(kupac.getKupacID());
	
		try {
        String upit="DELETE FROM kupac WHERE KupacID = "+kupac.getKupacID();
		Statement s=DBBroker.getInstance().getConnection().createStatement();
		s.executeUpdate(upit);
		System.out.println(upit);
		DBBroker.getInstance().getConnection().commit();
		}catch(Exception e) {
			DBBroker.getInstance().getConnection().rollback();
		}
		
		
		
		
	}
	
	

}
