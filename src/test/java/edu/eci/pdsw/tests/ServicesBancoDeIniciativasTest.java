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

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.google.inject.Inject;

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

    @Test
    public void pruebaCeroTest() throws SQLException, ExceptionServiciosBancoIniciativas {
        try {
            serviciosBancoIniciativas.registrarArea(new Area(1, "Sistemas"));
            System.out.println(serviciosBancoIniciativas.consultarAreas());
            User user = new User("Carlos Andrés", "Medina Rivas", "carlos.medina-ri@mail.escuelaing.edu.co", 2125262, UserStatus.ACTIVO, Role.ADMINISTRADOR, new Area(1, "Decanatura de Ingenería de Sistemas"));
            serviciosBancoIniciativas.registrarUsuario(user);
            System.out.println(serviciosBancoIniciativas.consultarUsuario("carlos.medina-ri@mail.escuelaing.edu.co"));
            assertTrue(true);
        } catch (ExceptionServiciosBancoIniciativas ex) {
            ex.printStackTrace();

        }

    }
}
