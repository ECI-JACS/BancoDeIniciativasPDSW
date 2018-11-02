
package edu.eci.pdsw.samples.services;
/**
 * @author 2106913
 */
public class ExcepcionServiciosBancoIniciativas extends Exception {

    public ExcepcionServiciosBancoIniciativas() {
    }

    public ExcepcionServiciosBancoIniciativas(String message) {
        super(message);
    }

    public ExcepcionServiciosBancoIniciativas(String message, Throwable cause) {
        super(message, cause);
    }

    public ExcepcionServiciosBancoIniciativas(Throwable cause) {
        super(cause);
    }
    
}
