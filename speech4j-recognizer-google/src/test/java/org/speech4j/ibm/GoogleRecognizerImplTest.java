package org.speech4j.ibm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.speech4j.recognizer.RecognitionProperties;
import org.speech4j.recognizer.RecognizeProperty;
import org.speech4j.recognizer.Recognizer;

import java.net.URI;
import java.net.URISyntaxException;

public class GoogleRecognizerImplTest {

//    @Test
    public void recognizeAudioByURI() throws URISyntaxException {
        Recognizer recognizer = new GoogleRecognizerImpl();

        // change uri using google store
        URI uri = new URI("https://ucc81ad1c3fd2df7299885396bfe.dl.dropboxusercontent.com/cd/0/get/A0ZPd8yxGpA5uV4JMoaYHtCoZK_61284HzJEDg4l3gSmakCA3FQSSo12zFHAQmXzdZywuz0sRa73CaawiytVeIBSuRiQXKpsBmn7A-fg-CxecByH6JfUqGkLqtydxw1WBck/file?_download_id=10241367085572751665251126729638826031022621648367407921355450822&_notify_domain=www.dropbox.com&dl=1");
        RecognitionProperties recognitionProperties = new RecognitionProperties();
        recognitionProperties.setProperty(RecognizeProperty.LANGUAGE, "en-US");

        Assertions.assertFalse(recognizer.recognize(uri, recognitionProperties).isEmpty());
    }

//    @Test
//    public void recognizeAudioByInputStream() {
//        Recognizer recognizer = new GoogleRecognizerImpl();
//
//        RecognitionProperties recognitionProperties = new RecognitionProperties();
//        recognitionProperties.setProperty("Language", "en-US");
//
//        Assertions.assertFalse(recognizer.recognize(<InputStream>, recognitionProperties).isEmpty());
//    }
}
