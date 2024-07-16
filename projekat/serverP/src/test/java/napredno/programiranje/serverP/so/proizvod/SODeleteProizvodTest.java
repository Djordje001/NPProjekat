package napredno.programiranje.serverP.so.proizvod;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import napredno.programiranje.serverP.db.DBBroker;
import napredno.programiranje.serverP.so.AbstractSO;
import napredno.programiranje.serverP.so.AbstractSOTest;
import napredno.programiranje.serverP.so.autor.SOFindAllAutor;
import napredno.programiranje.serverP.so.proizvod.SOAddProizvod;
import napredno.programiranje.serverP.so.proizvod.SODeleteProizvod;
import napredno.programiranje.serverP.so.proizvod.SOGetAllProizvod;
import napredno.programiranje.zajednickiP.domain.AbstractDomainObject;
import napredno.programiranje.zajednickiP.domain.Administrator;
import napredno.programiranje.zajednickiP.domain.Autor;
import napredno.programiranje.zajednickiP.domain.KancelarijskiProizvod;
import napredno.programiranje.zajednickiP.domain.Knjiga;
import napredno.programiranje.zajednickiP.domain.Kupac;
import napredno.programiranje.zajednickiP.domain.Pisac;
import napredno.programiranje.zajednickiP.domain.Porudzbina;
import napredno.programiranje.zajednickiP.domain.Proizvod;
import napredno.programiranje.zajednickiP.domain.StavkaPorudzbine;

class SODeleteProizvodTest extends AbstractSOTest {

	SODeleteProizvod so;
	SOGetAllProizvod soGAP;
	SOFindAllAutor soFAA;
	SOAddProizvod soAP;
	public AbstractSO getInstance() {
		return new SODeleteProizvod();
	}
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		so=new SODeleteProizvod();
		soGAP=new SOGetAllProizvod();
		soFAA=new SOFindAllAutor();
		soAP=new SOAddProizvod();
	}

	@AfterEach
	void tearDown() throws Exception {
		so=null;
		soGAP=null;
		soFAA=null;
		soAP=null;
	}
	
	@Test
	void testTemplateExecutePogresno() {
		assertThrows(java.lang.NullPointerException.class,()->so.templateExecute(null));
		assertThrows(java.lang.IllegalArgumentException.class,()->so.templateExecute(new Kupac(null, "x", "x", "x", "x")));
	}

	@Test
	void testTemplateExecute() throws Exception {
		soGAP.templateExecute(new Proizvod(null, 1, "1", 1));
		ArrayList<Proizvod> proizvodi=soGAP.getLista();
		for(Proizvod p : proizvodi) {
			
			Kupac kupac=new Kupac(1L, "x", "x", "x", "x");
			Administrator administrator=new Administrator(2L,"x","X","x","x");
			Porudzbina porudzbina=new Porudzbina(2L, new Date(), new Date(), "12","123", 1, 2, 3, kupac, administrator, new ArrayList<>());
			ArrayList<StavkaPorudzbine> stavke=(ArrayList<StavkaPorudzbine>) (ArrayList<?>)select(new StavkaPorudzbine(porudzbina, 1, 2,3, p), p.getProizvodID());
			if(stavke.size()>0) {
				System.out.println("ne mozemo da obrisemo proizvod ukoliko postoji barem jedna stavka porudzbine koja sadrzi taj proizvod");
				continue;
			}
			
			
			if(p.getTip()==1) {
				
				
				
				
				KancelarijskiProizvod kp=(KancelarijskiProizvod) p;
				
				assertTrue(proizvodi.contains(kp));
				so.templateExecute(kp);
				
				
				soGAP.templateExecute(new Proizvod(null, 1, "1", 1));
				ArrayList<Proizvod> novi=soGAP.getLista();
				assertTrue(!novi.contains(kp));
				assertTrue(novi.size()==proizvodi.size()-1);
				
				
				
				//ponistavam efekat
				soAP.templateExecute(kp);
			}
			if(p.getTip()==2) {
				
				
				
				
				Knjiga k=(Knjiga) p;
				Pisac pomocni=new Pisac(null, "1", "1", "1");
				Autor autor=new Autor(k, 0, pomocni);
				soFAA.templateExecute(autor);
				ArrayList<Autor> autori=soFAA.getLista();
				k.setAutori(autori);
				
				
				
				
				
	
				
				assertTrue(proizvodi.contains(k));
				so.templateExecute(k);
				
				soGAP.templateExecute(new Proizvod(null, 1, "1", 1));
				ArrayList<Proizvod> novi=soGAP.getLista();
				assertTrue(!novi.contains(k));
				assertTrue(novi.size()==proizvodi.size()-1);
				
				//ponistavam efekeat
				soAP.templateExecute(k);
				
				
			}
			break;
		}
		
	}
	
	//pomocna funkcija
	public ArrayList<AbstractDomainObject> select(AbstractDomainObject ado,Long id) throws SQLException {
       
        String upit = "SELECT * FROM " + ado.nazivTabele() + " " + ado.alijas()
        + " " + ado.join() + " "+"where sp.ProizvodID="+id+" ";
               
        System.out.println(upit);
        Statement s = DBBroker.getInstance().getConnection().createStatement();
        ResultSet rs = s.executeQuery(upit);
        return ado.vratiListu(rs);
    }

}
