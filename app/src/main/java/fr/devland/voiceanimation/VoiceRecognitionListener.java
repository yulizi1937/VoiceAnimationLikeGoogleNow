package fr.devland.voiceanimation;

import android.os.Bundle;
import android.speech.RecognitionListener;

import java.util.ArrayList;
import java.util.List;

public class VoiceRecognitionListener implements RecognitionListener {

    private List<String> textContentList;

    public VoiceRecognitionListener() {
        textContentList = new ArrayList<>();
    }

    @Override
    public void onReadyForSpeech(Bundle params) {}

    @Override
    public void onEndOfSpeech() {}

    @Override
    public void onResults(Bundle results) {}

    @Override
    public void onError(int error) {}

    @Override
    public void onRmsChanged(float rmsdB) {}

    @Override
    public void onBeginningOfSpeech() {}

    @Override
    public void onBufferReceived(byte[] buffer) {}

    @Override
    public void onEvent(int eventType, Bundle params) {}

    @Override
    public void onPartialResults(Bundle partialResults) {}

    public List<String> getTextContentList() {
        return textContentList;
    }
}
