package org.speech4j.recognizer;

import java.io.InputStream;
import java.net.URI;

public interface Recognizer {
    String recognize(InputStream source, RecognitionProperties recognitionProperties);
    String recognize(URI uriSource, RecognitionProperties recognitionProperties);
}
