package napredno.programiranje.serverP.so.login;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import napredno.programiranje.serverP.so.AbstractSO;
import napredno.programiranje.serverP.so.AbstractSOTest;
import napredno.programiranje.serverP.so.login.SOLogin;
import napredno.programiranje.zajednickiP.domain.Administrator;
import napredno.programiranje.zajednickiP.domain.Pisac;

class SOLoginTest extends AbstractSOTest{

	SOLogin so;
	public AbstractSO getInstance() {
		return new SOLogin();
	}
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		so=new SOLogin();
	}

	@AfterEach
	void tearDown() throws Exception {
		so=null;
		
	}

	@Test
	void testTemplateExecutePogresno() {
		assertThrows(java.lang.NullPointerException.class,()->so.templateExecute(null));
		assertThrows(java.lang.IllegalArgumentException.class,()->so.templateExecute(new Pisac(1L,"ime","prezime", "email")));
	}
	
	@Test
	void testTemplateExecuteUspesno() throws Exception {
	     assertTrue(so.getUlogovani()==null);
	     
	     Administrator admin=new Administrator(null,"Anastasija","Madic","maki","makic");
	     so.templateExecute(admin);
	     
	     assertTrue(so.getUlogovani()!=null);
	     assertTrue(so.getUlogovani().getIme().equals("Anastasija"));
	     assertTrue(so.getUlogovani().getPrezime().equals("Madic"));
	     assertTrue(so.getUlogovani().getUsername().equals("maki"));
	     assertTrue(so.getUlogovani().getPassword().equals("makic"));
	     assertTrue(so.getUlogovani().getAdministratorID()!=null);
	     
	     Administrator los=new Administrator(null,"abc","123","keke","we");
	    
	     assertThrows(java.lang.IllegalArgumentException.class, ()->so.templateExecute(los));
	}

}
