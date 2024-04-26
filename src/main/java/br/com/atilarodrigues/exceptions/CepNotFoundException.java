package br.com.atilarodrigues.exceptions;

public class CepNotFoundException extends Exception {
    public CepNotFoundException() {
        super("Address not found!");
    }
}
