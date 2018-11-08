package edu.eci.pdsw.tests;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Calendar;

import javax.management.j2ee.statistics.StatefulSessionBeanStats;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.google.inject.Inject;

import edu.eci.pdsw.samples.entities.Area;
import edu.eci.pdsw.samples.entities.Initiative;
import edu.eci.pdsw.samples.entities.InitiativeStatus;
import edu.eci.pdsw.samples.entities.Role;
import edu.eci.pdsw.samples.entities.User;
import edu.eci.pdsw.samples.entities.UserStatus;
import edu.eci.pdsw.samples.services.ExceptionServiciosBancoIniciativas;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativasFactory;


public class ServiciosBancoDeIniciativasTest {
	
	@Inject
	private ServiciosBancoIniciativas servicios;
	
	public ServiciosBancoDeIniciativasTest() {
    	this.servicios = ServiciosBancoIniciativasFactory.getInstance().getServiciosBancoIniciativasTesting();
    }

	
	@Test
	public void registrarUsuarioConsultarUsuarioEliminar(){
		Area area;
		try {
			area = servicios.consultarArea(1);
			User user = new User("sneitder", "merchan","sneitder.merchan@mail.escuelaing.edu.co", 2133542, UserStatus.ACTIVO, Role.SIN_ASIGNAR,area);
			servicios.registrarUsuario(user);
			User userConsult = servicios.consultarUsuario("sneitder.merchan@mail.escuelaing.edu.co");
			assertEquals(user,userConsult);
			
		} catch (ExceptionServiciosBancoIniciativas e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void registrarIniciativaConsultarIniciativa(){
		try {
			User user = servicios.consultarUsuario("willson.melo@mail.escuelaing.edu.co");
			int id = servicios.consultarIdIniciativa();
			Initiative iniciativa = new Initiative(id, "nuevo edificio Sistemas",  "nuevo edificio Sistemas",  new Date(Calendar.getInstance().getTime().getTime()), null, "edificio,sistemas", user,new InitiativeStatus(1, "En espera de revisión")); 
			Initiative iniciativaConsult = servicios.consultarIniciativa(id);
			assertEquals(iniciativa, iniciativaConsult);
		} catch (ExceptionServiciosBancoIniciativas e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	@Test
	public void modficarEstadoDeIniciativa(){
		try {
			User user = servicios.consultarUsuario("willson.melo@mail.escuelaing.edu.co");
			int id = servicios.consultarIdIniciativa();
			Initiative iniciativa = new Initiative(id, "nuevo edificio Sistemas",  "nuevo edificio Sistemas",  new Date(Calendar.getInstance().getTime().getTime()), null, "edificio,sistemas", user,new InitiativeStatus(1, "En espera de revisión")); 
			InitiativeStatus status1 = iniciativa.getIniciativeStatus();
			servicios.updateInitiativeStatus(id,2);
			InitiativeStatus status2 = servicios.consultarIniciativa(id).getIniciativeStatus();
			assertEquals(status1,status2);
		} catch (ExceptionServiciosBancoIniciativas e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
}
