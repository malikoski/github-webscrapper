package br.com.malikoski.github.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
@RequiredArgsConstructor
public class HttpConnection {

    private HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();


    public HttpResponse<String> connect(String url)  {


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try {
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    public byte[] getBytesFromUrl(String url) throws IOException {
        try (InputStream in = new URL(url).openStream()) {
            var bytes = in.readAllBytes();
            return bytes;
        }
    }

    private String get(String url) throws IOException {
        final StringBuffer buffer = new StringBuffer(2048);
        String line = null;
        String message = new String();

        URL link = new URL(url);
        BufferedReader read = new BufferedReader(new InputStreamReader(link.openStream()));

        while ((line = read.readLine()) != null) {
            message += line;
        }

        return message;
    }
}
