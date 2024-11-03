package com.mtaparenka.mortygallery.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Base64;

@Component
public class CDNClient {
    private final RestClient restClient;
    private final CDNClientProperties properties;

    public CDNClient(CDNClientProperties properties) {
        this.properties = properties;
        this.restClient = RestClient.create(this.properties.url);
    }

    public String uploadImage(byte[] imageData) {
        CDNUploadData uploadData = new CDNUploadData(Base64.getEncoder().encodeToString(imageData));

        String fileName = restClient.post()
                .uri(getUrl() + "/content/images/upload")
                .contentType(MediaType.APPLICATION_JSON)
                .body(uploadData)
                .retrieve()
                .body(String.class);

        return getUrl() + properties.imagesEndpoint + fileName;
    }

    @ConfigurationProperties("client.cdn")
    public record CDNClientProperties(String url, String imagesEndpoint) {
    }

    public String getUrl() {
        return properties.url;
    }
}
