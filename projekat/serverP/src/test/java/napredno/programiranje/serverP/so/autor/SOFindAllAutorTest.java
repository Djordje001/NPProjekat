package napredno.programiranje.serverP.so.autor;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import napredno.programiranje.serverP.so.AbstractSO;
import napredno.programiranje.serverP.so.AbstractSOTest;
import napredno.programiranje.serverP.so.autor.SOFindAllAutor;
import napredno.programiranje.serverP.so.proizvod.SOGetAllProizvod;
import napredno.programiranje.zajednickiP.domain.Autor;
import napredno.programiranje.zajednickiP.domain.Knjiga;
import napredno.programiranje.zajednickiP.domain.Kupac;
import napredno.programiranje.zajednickiP.domain.Pisac;
import napredno.programiranje.zajednickiP.domain.Proizvod;

class SOFindAllAutorTest extends AbstractSOTest {

	SOFindAllAutor so;
	SOGetAllProizvod soGAP;
	public AbstractSO getInstance() {
		return new SOFindAllAutor();
	}
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		so=new SOFindAllAutor();
		soGAP=new SOGetAllProizvod();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		so=null;
		soGAP=null;
	}

	@Test
	void testTemplateExecuteNeuspesno() {
		assertThrows(java.lang.NullPointerException.class,()->so.templateExecute(null));
		assertThrows(java.lang.IllegalArgumentException.class,()->so.templateExecute(new Kupac(2L, "ime", "prezime", "email", "tipkupca")));
	}
	
	@Test
	void testTemplateExecuteUspesno() throws Exception {
		soGAP.templateExecute(new Proizvod(null, 23,"naziv", 2));
		ArrayList<Proizvod> proizvodi=soGAP.getLista();
		for(Proizvod p : proizvodi) {
			if(p.getTip()==2) {
				Knjiga knjiga=(Knjiga) p;
				Autor autor=new Autor(knjiga, 0, new Pisac(null, "x", "x", "x"));
				assertTrue(so.getLista()==null);
				so.templateExecute(autor);
				ArrayList<Autor> autori=so.getLista();
				
				assertTrue(autori!=null);
				
				System.out.println(autori);
				break;
			}
		}
	}

}
