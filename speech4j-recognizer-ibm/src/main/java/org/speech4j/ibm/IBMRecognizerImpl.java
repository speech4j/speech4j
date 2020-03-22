package org.speech4j.ibm;

import org.speech4j.recognizer.RecognitionProperties;
import org.speech4j.recognizer.Recognizer;

import java.io.InputStream;
import java.net.URI;

public class IBMRecognizerImpl implements Recognizer {
    @Override
    public String recognize(InputStream source, RecognitionProperties recognitionProperties) {
        return null;
    }

    @Override
    public String recognize(URI uriSource, RecognitionProperties recognitionProperties) {
        return null;
    }
}
