package napredno.programiranje.serverP.so.porudzbina;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import napredno.programiranje.serverP.so.AbstractSO;
import napredno.programiranje.serverP.so.AbstractSOTest;
import napredno.programiranje.serverP.so.porudzbina.SOGetAllPorudzbina;
import napredno.programiranje.serverP.so.porudzbina.SOUpdatePorudzbina;
import napredno.programiranje.serverP.so.proizvod.SOGetAllProizvod;
import napredno.programiranje.serverP.so.stavkaPorudzbine.SOFindAllStavkaPorudzbine;
import napredno.programiranje.zajednickiP.domain.Administrator;
import napredno.programiranje.zajednickiP.domain.Kupac;
import napredno.programiranje.zajednickiP.domain.Porudzbina;
import napredno.programiranje.zajednickiP.domain.Proizvod;
import napredno.programiranje.zajednickiP.domain.StavkaPorudzbine;

class SOUpdatePorudzbinaTest extends AbstractSOTest {

	SOUpdatePorudzbina so;
	SOGetAllPorudzbina soGAP;
	SOFindAllStavkaPorudzbine soFASP;
	SOGetAllProizvod soGAP1;
	
	Proizvod p1;
	StavkaPorudzbine sp1;
	Porudzbina p;
	
	
	
	public AbstractSO getInstance() {
		return new SOUpdatePorudzbina();
	}
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		so=new SOUpdatePorudzbina();
		soGAP=new SOGetAllPorudzbina();
		soFASP=new SOFindAllStavkaPorudzbine();
		soGAP1=new SOGetAllProizvod();
	}

	@AfterEach
	void tearDown() throws Exception {
		so=null;
		soGAP=null;
		soFASP=null;
		soGAP1=null;
	}

	
	@Test
	void testTemplateExecuteNeuspesno() {
		assertThrows(java.lang.NullPointerException.class,()->so.templateExecute(null));
		assertThrows(java.lang.IllegalArgumentException.class,()->so.templateExecute(new Kupac(null, "x", "x", "x", "x")));
		Kupac kupac=new Kupac(1L, "x", "x", "x", "x");
		Administrator administrator=new Administrator(2L,"x","X","x","x");
		try {
			
			  SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		        Date datumIsporuke = sdf.parse("21.05.2014.");
			assertThrows(java.lang.IllegalArgumentException.class,()->so.templateExecute(new Porudzbina(2L, new Date(),datumIsporuke, "12","123", 1, 2, 3, kupac, administrator, new ArrayList<>())));
		}catch(Exception e) {
			System.out.println(e);
		}
		assertThrows(java.lang.IllegalArgumentException.class,()->so.templateExecute(new Porudzbina(2L, new Date(),new Date(), "12","123", 1, 2, 3, kupac, administrator, new ArrayList<>())));
	}
	@Test
	void testTemplateExecute() throws Exception {
		Kupac kupac=new Kupac(null,"Djordje","Djordjevic","email","free");
		Administrator administrator=new Administrator(null,"hehe", "23", "xd", "qwe");
	     p=new Porudzbina(null, new Date(), new Date(),"123", "12", 1, 2, 3, kupac, administrator, new ArrayList<>());
		
		assertTrue(soGAP.getLista()==null);
		soGAP.templateExecute(p);
		assertTrue(soGAP.getLista()!=null);
		ArrayList<Porudzbina> porudzbine=soGAP.getLista();
		for(Porudzbina por : porudzbine) {
			Porudzbina stara=new Porudzbina(por.getPorudzbinaID(), por.getDatumVreme(), por.getDatumIsporuke(), por.getGrad(),por.getAdresa(), por.getCena(),por.getPopust(), por.getKonacnaCena(), por.getKupac(), por.getAdministrator(), new ArrayList<>());
			System.out.println(stara.getDatumIsporuke());
			Proizvod pomocni=new Proizvod(2L, 30, "naziv", 1);
			StavkaPorudzbine pomocna=new StavkaPorudzbine(stara, 2, 3, 4, pomocni);
			soFASP.templateExecute(pomocna);
			ArrayList<StavkaPorudzbine> stavke=soFASP.getLista();
			stara.setStavkePorudzbine(stavke);
			
			
			soGAP1.templateExecute(new Proizvod(1L,3,"1",1));
			ArrayList<Proizvod> proizvodi=soGAP1.getLista();
			
			if(proizvodi.size()>0) {
			StavkaPorudzbine nova=new StavkaPorudzbine(por,2,3,5,proizvodi.get(0));
			por.setAdresa("ivanova ulica");
			por.setCena(20);
			por.setGrad("Valjevo");
			por.setKonacnaCena(2300);
			
			por.setStavkePorudzbine(new ArrayList<>());
			por.getStavkePorudzbine().add(nova);
			
			so.templateExecute(por);
			
			soGAP.templateExecute(stara);
			ArrayList<Porudzbina> nove=soGAP.getLista();
			assertTrue(nove.contains(por));
			assertTrue(nove.size()==porudzbine.size());
			for(Porudzbina x : nove) {
				if(x.getPorudzbinaID()==stara.getPorudzbinaID()) {
					assertTrue(x.getAdresa().equals("ivanova ulica"));
					assertTrue(x.getCena()==20);
					assertTrue(x.getGrad().equals("Valjevo"));
					assertTrue(x.getKonacnaCena()==2300);
				     break;
				}
			}
			
			soFASP.templateExecute(nova);
			ArrayList<StavkaPorudzbine> izmenjeneStavke=soFASP.getLista();
			/*assertTrue(izmenjeneStavke.size()==1);
			assertTrue(izmenjeneStavke.get(0).getKolicina()==3);
			assertTrue(izmenjeneStavke.get(0).getCena()==5);
			assertTrue(izmenjeneStavke.get(0).getProizvod().equals(proizvodi.get(0)));*/
			
			
			assertTrue(izmenjeneStavke.equals(por.getStavkePorudzbine()));
			//rb ne poredim jer sigurno nije isti jer je autoincrementalan u bazi
			System.out.println(izmenjeneStavke);
			System.out.println(por.getStavkePorudzbine());
			
			
			System.out.println(stara.getDatumIsporuke());
			//ponisavam efekat testa
			so.templateExecute(stara);
			
			}
			
			break;
		}
		
	}

}
