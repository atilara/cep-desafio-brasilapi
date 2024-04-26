package br.com.atilarodrigues.exceptions;

public class CepAlreadyStoredException extends Exception {
    public CepAlreadyStoredException() {
        super("CEP already stored in the database!");
    }
}
