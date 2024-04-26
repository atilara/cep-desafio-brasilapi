package br.com.atilarodrigues.service;

import br.com.atilarodrigues.model.CepModel;
import br.com.atilarodrigues.repository.CepRepository;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CepService {

    private CepRepository cepRepository;

    public CepService(CepRepository cepRepository) {
        this.cepRepository = cepRepository;
    }

    public void getCepRequest(String cep) throws URISyntaxException, IOException, InterruptedException {
        // Importar CepRepository
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://brasilapi.com.br/api/cep/v1/" + cep))
                .version(HttpClient.Version.HTTP_2)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();

        cepRepository.saveAddress(gson.fromJson(response.body(), CepModel.class));

    }

}
