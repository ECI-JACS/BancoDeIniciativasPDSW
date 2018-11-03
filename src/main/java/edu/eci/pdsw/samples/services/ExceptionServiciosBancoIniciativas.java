package edu.eci.pdsw.samples.services;

/**
 * @author ECI-FACS
 */
public class ExceptionServiciosBancoIniciativas extends Exception {

    public ExceptionServiciosBancoIniciativas() {
    }

    public ExceptionServiciosBancoIniciativas(String message) {
        super(message);
    }

    public ExceptionServiciosBancoIniciativas(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionServiciosBancoIniciativas(Throwable cause) {
        super(cause);
    }

}
