package br.com.atilarodrigues.exceptions;

public class CepInvalidExpection extends Exception{
    public CepInvalidExpection() {
        super("Invalid CEP! The CEP must have 8 digits.");
    }
}
