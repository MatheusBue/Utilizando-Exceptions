import br.com.projeto.consultagithub.ErroConsultaGitHubException;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scan = new Scanner(System.in);

        System.out.println("Insira o username do usuario que voce deseja visitar: ");
        var busca = scan.nextLine();

        String endereco = "https://api.github.com/users/" + busca;

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endereco))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() == 404) {
                throw new ErroConsultaGitHubException("Erro ao consultar o usuario informado! Tente " +
                        "novamente mais tarde.");
            }

            String json = response.body();
            System.out.println(json);

        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Parece que houve um erro na hora de informa o username...");
            System.out.println("Erro na busca: " + e.getMessage());
        } catch (ErroConsultaGitHubException e) {
            System.out.println(e.getMessage());
        }
    }
}