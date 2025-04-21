import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class CepApi {

    public Endereco consultaCep(String cep) {
        System.out.println("Consultando API Via CEP...");
        String urlAddress = "https://viacep.com.br/ws/" + cep + "/json/";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlAddress))
                .build();

        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 400) {
                throw new CepApiException("O CEP digitado é inválido!");
            }
            String json = response.body();
            if (json.contains("\"erro\": \"true\"")) {
                throw new CepApiException("CEP Inexistente!");
            }
            Gson gson = new GsonBuilder().create();
            return gson.fromJson(json, Endereco.class);
        } catch (ConnectException e) {
            throw new CepApiException("Erro ao conectar à API.");
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
