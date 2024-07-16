package org.serverP.so.porudzbina;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.serverP.so.AbstractSO;
import org.serverP.so.AbstractSOTest;
import org.serverP.so.stavkaPorudzbine.SOFindAllStavkaPorudzbine;
import org.zajednickiP.domain.Administrator;
import org.zajednickiP.domain.Kupac;
import org.zajednickiP.domain.Porudzbina;
import org.zajednickiP.domain.Proizvod;
import org.zajednickiP.domain.StavkaPorudzbine;

class SODeletePorudzbinaTest extends AbstractSOTest {

	SODeletePorudzbina so;
	SOGetAllPorudzbina soGAP;
	SOAddPorudzbina soAP;
	SOFindAllStavkaPorudzbine soFASP;
	public AbstractSO getInstance() {
		return new SODeletePorudzbina();
	}
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		so=new SODeletePorudzbina();
		soGAP=new SOGetAllPorudzbina();
		soAP=new SOAddPorudzbina();
		soFASP=new SOFindAllStavkaPorudzbine();
	}

	@AfterEach
	void tearDown() throws Exception {
		so=null;
		soGAP=null;
		soAP=null;
		soFASP=null;
	}

	@Test
	void testTemplateExecuteNeuspesno() {
		assertThrows(java.lang.NullPointerException.class,()->so.templateExecute(null));
		assertThrows(java.lang.IllegalArgumentException.class,()->so.templateExecute(new Kupac(null, "x", "x", "x", "x")));
		
	}
	@Test
	void testTemplateExecuteUspesno() throws Exception {
		Kupac kupac=new Kupac(1L, "x", "x", "x", "x");
		Administrator administrator=new Administrator(2L,"x","X","x","x");
		
		
		
		soGAP.templateExecute(new Porudzbina(2L, new Date(), new Date(), "12","123", 1, 2, 3, kupac, administrator, new ArrayList<>()));
		ArrayList<Porudzbina> porudzbine=soGAP.getLista();
		int brojPorudzbina=porudzbine.size();
		
		for(Porudzbina p : porudzbine) {
			assertTrue(porudzbine.contains(p));
			StavkaPorudzbine pomocna=new StavkaPorudzbine(p,2,3,1,new Proizvod(2L, 2, "1", 1));
			soFASP.templateExecute(pomocna);
			ArrayList<StavkaPorudzbine> stavke=soFASP.getLista();
			Porudzbina zaBrisanje=p;
			zaBrisanje.setStavkePorudzbine(stavke);
			
		    so.templateExecute(p);
		    
		    
		    soGAP.templateExecute(new Porudzbina(2L, new Date(), new Date(), "12","123", 1, 2, 3, kupac, administrator, new ArrayList<>()));
			ArrayList<Porudzbina> nove=soGAP.getLista();
			assertTrue(nove.size()==brojPorudzbina-1);
			assertFalse(nove.contains(p));
		    
		    
			//ponistavam efekeat testa,vraca ovu porudzbinu u bazu sa njenim stavkama
		    soAP.templateExecute(zaBrisanje);
			break;
		}
	}
	
	

}
