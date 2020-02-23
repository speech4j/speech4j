package org.speech4j;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class MediaConverterImplTest {

    @Test
    public void failWhenAudioNotConverted() {
        Assertions.assertEquals("stub value", new MediaConverterImpl().convertToText(new InputStream() {
            @Override
            public int read() {
                return 0;
            }
        }));
    }
}
