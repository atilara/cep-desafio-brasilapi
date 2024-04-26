package br.com.atilarodrigues;

import br.com.atilarodrigues.service.CepService;

public class Main {
    public static void main(String[] args) {
        CepService cepService = new CepService();
        if (args.length > 0) {
            cepService.saveAddressRequest(args[0]);
        }
        System.out.println(cepService.findAllAddresses());
    }
} 