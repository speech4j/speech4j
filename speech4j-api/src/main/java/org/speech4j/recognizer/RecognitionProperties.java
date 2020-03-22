package org.speech4j.recognizer;

import java.util.HashMap;
import java.util.Map;

public class RecognitionProperties {
    private Map<String, String> props = new HashMap<>();

    public void setProperty(String key, String value) {
        props.put(key, value);
    }

    public String getProperty(String key) {
        return props.get(key);
    }
}
