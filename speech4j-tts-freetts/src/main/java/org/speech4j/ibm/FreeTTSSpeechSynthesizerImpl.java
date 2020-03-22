package org.speech4j.ibm;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import org.speech4j.tts.SpeechSynthesizer;

public class FreeTTSSpeechSynthesizerImpl implements SpeechSynthesizer {
    @Override
    public void speak(String text) {

        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
        System.out.println("Name: " + voice.getName());
        System.out.println("Description: " + voice.getDescription());
        System.out.println("Organization: " + voice.getOrganization());
        System.out.println("Age: " + voice.getAge());
        System.out.println("Gender: " + voice.getGender());
        System.out.println("Rate: " + voice.getRate());
        System.out.println("Pitch: " + voice.getPitch());
        System.out.println("Style: " + voice.getStyle());
        System.out.println();

        voice.allocate();
        voice.speak(text);
        voice.deallocate();
    }
}
