import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class CatFactsFetcher {

    public static void main(String[] args) {
        CatFactsFetcher fetcher = new CatFactsFetcher();
        fetcher.fetchAndPrintFilteredFacts();
    }

    public void fetchAndPrintFilteredFacts() {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build()) {
            HttpGet request = new HttpGet("https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats");
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(response.getEntity().getContent());
                List<CatFact> catFacts = new ArrayList<>();
                for (JsonNode node : rootNode) {
                    CatFact catFact = objectMapper.treeToValue(node, CatFact.class);
                    if (catFact.getUpvotes() != null) {
                        catFacts.add(catFact);
                    }
                }
                catFacts.forEach(System.out::println);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
