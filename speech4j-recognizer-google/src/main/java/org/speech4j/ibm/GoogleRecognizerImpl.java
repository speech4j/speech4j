package org.speech4j.ibm;

import com.google.api.gax.longrunning.OperationFuture;
import com.google.cloud.speech.v1.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.speech4j.recognizer.RecognitionProperties;
import org.speech4j.recognizer.RecognizeProperty;
import org.speech4j.recognizer.Recognizer;

import java.io.InputStream;
import java.net.URI;

public class GoogleRecognizerImpl implements Recognizer {

    private static Logger log = LoggerFactory.getLogger(GoogleRecognizerImpl.class);
    private final static int MOST_PROBABLE_VERSION_INDEX = 0;
    private final static int FLAC_SAMPLE_RATE_HERTZ = 44100;

    @Override
    public String recognize(InputStream source, RecognitionProperties recognitionProperties) {
        // retrieve all required properties
        String languageCode = recognitionProperties.getProperty(RecognizeProperty.LANGUAGE);

        StringBuilder googleSpeechOutput = new StringBuilder();

        try (SpeechClient speechClient = SpeechClient.create()) {

            // Encoding of audio data sent. This sample sets this explicitly.
            // This field is optional for FLAC and WAV audio formats.
            RecognitionConfig.AudioEncoding encoding = RecognitionConfig.AudioEncoding.LINEAR16;
            RecognitionConfig config =
                    RecognitionConfig.newBuilder()
                            .setSampleRateHertz(FLAC_SAMPLE_RATE_HERTZ)
                            .setLanguageCode(languageCode)
                            .setEncoding(encoding)
                            .build();
            RecognitionAudio audio = RecognitionAudio.parseFrom(source);
            LongRunningRecognizeRequest request =
                    LongRunningRecognizeRequest.newBuilder().setConfig(config).setAudio(audio).build();
            OperationFuture<LongRunningRecognizeResponse, LongRunningRecognizeMetadata> future =
                    speechClient.longRunningRecognizeAsync(request);

            log.debug("Waiting for operation to complete...");
            LongRunningRecognizeResponse response = future.get();
            for (SpeechRecognitionResult result : response.getResultsList()) {
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(MOST_PROBABLE_VERSION_INDEX);
                googleSpeechOutput.append(alternative.getTranscript());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        return googleSpeechOutput.toString();
    }

    @Override
    public String recognize(URI uriSource, RecognitionProperties recognitionProperties) {

        // retrieve all required properties
        String languageCode = recognitionProperties.getProperty(RecognizeProperty.LANGUAGE);

        StringBuilder googleSpeechOutput = new StringBuilder();
        try (SpeechClient speechClient = SpeechClient.create()) {
            // Encoding of audio data sent. This sample sets this explicitly.
            // This field is optional for FLAC and WAV audio formats.
            RecognitionConfig.AudioEncoding encoding = RecognitionConfig.AudioEncoding.LINEAR16;
            RecognitionConfig config =
                    RecognitionConfig.newBuilder()
                            .setSampleRateHertz(FLAC_SAMPLE_RATE_HERTZ)
                            .setLanguageCode(languageCode)
                            .setEncoding(encoding)
                            .build();
            RecognitionAudio audio = RecognitionAudio.newBuilder().setUri(uriSource.getPath()).build();
            LongRunningRecognizeRequest request =
                    LongRunningRecognizeRequest.newBuilder().setConfig(config).setAudio(audio).build();
            OperationFuture<LongRunningRecognizeResponse, LongRunningRecognizeMetadata> future =
                    speechClient.longRunningRecognizeAsync(request);

            log.debug("Waiting for operation to complete...");
            LongRunningRecognizeResponse response = future.get();
            for (SpeechRecognitionResult result : response.getResultsList()) {
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(MOST_PROBABLE_VERSION_INDEX);
                googleSpeechOutput.append(alternative.getTranscript());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        return googleSpeechOutput.toString();
    }
}
