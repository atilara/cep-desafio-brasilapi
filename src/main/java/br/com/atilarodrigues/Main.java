package br.com.atilarodrigues;

import br.com.atilarodrigues.service.CepService;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        CepService cepService = new CepService();
        cepService.getCepRequest("50010020");
    }
}