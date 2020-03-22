package org.speech4j.ibm;

import org.junit.jupiter.api.Test;

public class FreeTTSSpeechSynthesizerImplTest {

    @Test
    public void testTTSWithoutAssertions() {
        FreeTTSSpeechSynthesizerImpl speech = new FreeTTSSpeechSynthesizerImpl();
        speech.speak("Hello World");
    }
}
