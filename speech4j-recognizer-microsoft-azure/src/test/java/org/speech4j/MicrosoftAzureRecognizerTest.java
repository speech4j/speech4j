package org.speech4j;

import org.junit.jupiter.api.Test;
import org.speech4j.recognizer.RecognitionProperties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MicrosoftAzureRecognizerTest {

    @Test
    public void recognizeAudioByURI() throws FileNotFoundException {
        MicrosoftAzureRecognizer recognizer = new MicrosoftAzureRecognizer();

        String uri = "/Users/mmokh/Projects/pet/recognition-rating/src/main/java/microsoftV2/audios/audio1.mp3";

        RecognitionProperties recognitionProperties = new RecognitionProperties();
        recognitionProperties.setProperty(MicrosoftAzureRecognizer.SUBSCRIPTION_KEY, "17843c3f87884eaaa279784cdec479db");

        InputStream is = new FileInputStream(uri);

        String result = recognizer.recognize(is, recognitionProperties);


        System.out.println(result);
        //assertEquals("Are you doing?" , result);
    }

}
