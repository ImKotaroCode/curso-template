package pe.idat.template.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String mensaje) {
        super(mensaje);
    }
}
