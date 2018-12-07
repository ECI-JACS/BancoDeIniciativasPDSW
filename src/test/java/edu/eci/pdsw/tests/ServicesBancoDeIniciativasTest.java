/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.pdsw.tests;



import static org.quicktheories.QuickTheory.qt;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.google.inject.Inject;

import edu.eci.pdsw.generators.UserGenerator;
import edu.eci.pdsw.samples.entities.Area;
import edu.eci.pdsw.samples.entities.Role;
import edu.eci.pdsw.samples.entities.User;
import edu.eci.pdsw.samples.entities.UserStatus;
import edu.eci.pdsw.samples.services.ExceptionServiciosBancoIniciativas;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;

/**
 *
 * @author hcadavid
 * @author fchaves
 */
public class ServicesBancoDeIniciativasTest extends TestBase {

    @Inject
    private ServiciosBancoIniciativas serviciosBancoIniciativas;
    
    private User userTest = new User("userTest", "userTest", "userTest@escuelaing.edu.co", 12345678, UserStatus.ACTIVO, Role.PROPONENTE, new Area(5, "pruebas"));
    
    @Test
    public void registrarConsultarAreas(){
    	try {
    		System.out.println("areas");
    		Area area1 = new Area(1, "Decanatura de Ingenería de Sistemas");
    		Area area2 = new Area(2, "Unidad de Proyectos");
    		Area area3 = new Area(3, "Vicerrectoría");
    		Area area4 = new Area(4, "Compras");
			serviciosBancoIniciativas.registrarArea(area1);
			serviciosBancoIniciativas.registrarArea(area2);
	    	serviciosBancoIniciativas.registrarArea(area3);
	    	serviciosBancoIniciativas.registrarArea(area4);
	    	boolean test = serviciosBancoIniciativas.consultarArea(1).equals(area1) &&
	    					serviciosBancoIniciativas.consultarArea(2).equals(area2) &&
	    					serviciosBancoIniciativas.consultarArea(3).equals(area3) &&
	    					serviciosBancoIniciativas.consultarArea(4).equals(area4);
	    	
	    	System.out.println("resultado: " + test);
	    	assertTrue(test);
	    			
		} catch (ExceptionServiciosBancoIniciativas e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    @Test
    public void ConsultarAreas(){
    	try {
    		System.out.println("consultarAreas");
			List<Area> areasActuales = serviciosBancoIniciativas.consultarAreas();
			Area prueba1 = new Area(6,"prueba1");
			Area prueba2 = new Area(7,"prueba2");
			areasActuales.add(prueba1);
			areasActuales.add(prueba2);
			serviciosBancoIniciativas.registrarArea(prueba1);
			serviciosBancoIniciativas.registrarArea(prueba2);
			List<Area> areasNuevas = serviciosBancoIniciativas.consultarAreas();
			boolean test = true;
			for(int i = 0 ; i < areasActuales.size(); i++){
				test = test && areasActuales.get(i).equals(areasNuevas.get(i));
				
			}
			System.out.println("resultado: " + test);
			assertTrue(test);
		} catch (ExceptionServiciosBancoIniciativas e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
   
    /*
    @Test
    public void registrarUsuarioTest(){
    	System.out.println("userTest");
    	try {
			serviciosBancoIniciativas.registrarUsuario(userTest);
			System.out.println("userTestFin");
			assertTrue(userTest.equals(serviciosBancoIniciativas.consultarUsuario("userTest@escuelaing.edu.co")));
		} catch (ExceptionServiciosBancoIniciativas e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }*/

    
    @Test
    public void actualizarRolUsuario(){
    	try {
    		System.out.println("Roles");
    		boolean test = true;
    		User user = serviciosBancoIniciativas.consultarUsuario(userTest.getEmail());
    		Area area = serviciosBancoIniciativas.consultarArea(5);
    		if (null == user || area == null){
    			serviciosBancoIniciativas.registrarArea(new Area(5, "pruebas"));
    			serviciosBancoIniciativas.registrarUsuario(userTest);
    			userTest = serviciosBancoIniciativas.consultarUsuario(userTest.getEmail());		
    		}
			serviciosBancoIniciativas.actualizarRolUsuario(userTest.getEmail(), Role.ADMINISTRADOR.toString());
			userTest = serviciosBancoIniciativas.consultarUsuario(userTest.getEmail());			
			test = test && userTest.getRole().toString().equals(Role.ADMINISTRADOR.toString());
			serviciosBancoIniciativas.actualizarRolUsuario(userTest.getEmail(), Role.PMO.toString());
			userTest = serviciosBancoIniciativas.consultarUsuario(userTest.getEmail());			
			test = test && userTest.getRole().toString().equals(Role.PMO.toString());
			serviciosBancoIniciativas.actualizarRolUsuario(userTest.getEmail(), Role.PUBLICO.toString());
			userTest = serviciosBancoIniciativas.consultarUsuario(userTest.getEmail());			
			test = test && userTest.getRole().toString().equals(Role.PUBLICO.toString());
			System.out.println("resultado: " + test);
			assertTrue(test);
		} catch (ExceptionServiciosBancoIniciativas e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
    
    @Test
    public void registrarConsultarUsers() throws SQLException, ExceptionServiciosBancoIniciativas {
        try {
        	System.out.println("Usuarios");
        	qt().forAll(UserGenerator.userGenerator()).check(user -> {
        		//System.out.println(user.toString());
        		try {
        			user.setArea(new Area(1, "Decanatura de Ingenería de Sistemas"));
					serviciosBancoIniciativas.registrarUsuario(user);
					User user2 = serviciosBancoIniciativas.consultarUsuario(user.getEmail());
					boolean test = user.equals(user2);
					//System.out.println("resultado: " + test);
					assertTrue(test);
					
				} catch (ExceptionServiciosBancoIniciativas e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		return true;
        	});
        } catch (Exception ex) {
            ex.printStackTrace();

        }

    }
    
    
}
