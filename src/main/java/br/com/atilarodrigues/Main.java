package br.com.atilarodrigues;

import br.com.atilarodrigues.service.CepService;

public class Main {
    public static void main(String[] args) {
        CepService cepService = new CepService();
        for (String arg : args) {
            cepService.saveAddressRequest(arg);
        }
        System.out.println(cepService.findAllAddresses());
    }
}