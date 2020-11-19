package org.speech4j;

import org.json.JSONObject;
import org.speech4j.recognizer.RecognitionProperties;
import org.speech4j.recognizer.Recognizer;

import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Supplier;

public class MicrosoftAzureRecognizer implements Recognizer  {

    public static final String MODE = "mode";
    public static final String LANGUAGE = "language";
    public static final String RECOGNITION_FORMAT = "recognitionFormat";
    public static final String SUBSCRIPTION_KEY = "subscriptionKey";

    public static final String MODE_DEFAULT_VALUE = "INTERACTIVE";
    public static final String LANGUAGE_DEFAULT_VALUE = "en-US";
    public static final String RECOGNITION_FORMAT_DEFAULT_VALUE = "SIMPLE";
    //public static final String SUBSCRIPTION_KEY_DEFAULT_VALUE = "17843c3f87884eaaa279784cdec479db";

    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    @Override
    public String recognize(InputStream source, RecognitionProperties recognitionProperties) {

        String mode = recognitionProperties.getProperty(MODE);
        if (mode == null) {
            mode = MODE_DEFAULT_VALUE;
        }
        String language = recognitionProperties.getProperty(LANGUAGE);
        if (language == null) {
            language = LANGUAGE_DEFAULT_VALUE;
        }
        String recognitionFormat = recognitionProperties.getProperty(RECOGNITION_FORMAT);
        if (recognitionFormat == null) {
            recognitionFormat = RECOGNITION_FORMAT_DEFAULT_VALUE;
        }
        String subscriptionKey = recognitionProperties.getProperty(SUBSCRIPTION_KEY);
        if (subscriptionKey == null) {
            throw new RuntimeException("No subscription Key");
        }

        String uri = String.format("https://speech.platform.bing.com/speech/recognition/%s/cognitiveservices/v1?language=%s&format=%s", mode, language, recognitionFormat);

        Supplier<InputStream> supplier = () -> source;

        HttpRequest request = null;
        HttpResponse<String> response = null;
        try {
            request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofInputStream(supplier))
                    .uri(URI.create(uri))
                    .setHeader("Ocp-Apim-Subscription-Key", subscriptionKey)
                    .setHeader("Accept", "application/json")//optional header
                    //you can use mp3 but header should be audio/wav
                    .setHeader("Content-Type", "audio/wav")
                    .build();

            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject(response.body());

        return jsonObject.getString("DisplayText");
    }

    @Override
    public String recognize(URI uriSource, RecognitionProperties recognitionProperties) {

        String mode = recognitionProperties.getProperty(MODE);
        if (mode == null) {
            mode = MODE_DEFAULT_VALUE;
        }
        String language = recognitionProperties.getProperty(LANGUAGE);
        if (language == null) {
            language = LANGUAGE_DEFAULT_VALUE;
        }
        String recognitionFormat = recognitionProperties.getProperty(RECOGNITION_FORMAT);
        if (recognitionFormat == null) {
            recognitionFormat = RECOGNITION_FORMAT_DEFAULT_VALUE;
        }
        String subscriptionKey = recognitionProperties.getProperty(SUBSCRIPTION_KEY);
        if (subscriptionKey == null) {
            throw new RuntimeException("No subscription Key");
        }

        String uri = String.format("https://speech.platform.bing.com/speech/recognition/%s/cognitiveservices/v1?language=%s&format=%s", mode, language, recognitionFormat);

        Path audio = Paths.get(uriSource);

        HttpRequest request = null;
        HttpResponse<String> response = null;
        try {
            request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofFile(audio))
                    .uri(URI.create(uri))
                    .setHeader("Ocp-Apim-Subscription-Key", subscriptionKey)
                    .setHeader("Accept", "application/json")//optional header
                    //you can use mp3 but header should be audio/wav
                    .setHeader("Content-Type", "audio/wav")
                    .build();

            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject(response.body());

        return jsonObject.getString("DisplayText");
    }
}
