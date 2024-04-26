package br.com.atilarodrigues.service;

import br.com.atilarodrigues.exceptions.CepInvalidExpection;
import br.com.atilarodrigues.exceptions.CepNotFoundException;
import br.com.atilarodrigues.model.CepModel;
import br.com.atilarodrigues.repository.CepRepository;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class CepService {

    private final CepRepository cepRepository;

    public CepService() {
        cepRepository = new CepRepository();
    }

    public CepModel saveAddressRequest(String cep) {
        try {
            if (cep.length() != 8) {
                throw new CepInvalidExpection();
            }

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://brasilapi.com.br/api/cep/v1/" + cep))
                    .version(HttpClient.Version.HTTP_2)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 404) {
                throw new CepNotFoundException();
            }

            Gson gson = new Gson();
            return cepRepository.saveAddress(gson.fromJson(response.body(), CepModel.class));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<CepModel> findAllAddresses() {
        return cepRepository.findAllAddresses();
    }


}
