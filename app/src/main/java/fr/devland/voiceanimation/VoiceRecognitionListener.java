package fr.devland.voiceanimation;

import android.app.Activity;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.widget.Toast;

public class VoiceRecognitionListener implements RecognitionListener {

    private String textContent = "";
    Activity activity;

    public VoiceRecognitionListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onReadyForSpeech(Bundle params) {
    }

    @Override
    public void onEndOfSpeech() {
        textContent = "";
    }

    @Override
    public void onResults(Bundle results) {
        textContent = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION).get(0);
        Toast.makeText(activity, "Recording : " + textContent, Toast.LENGTH_SHORT).show();
        Log.d("ONRESULTS", textContent.toString());
    }

    @Override
    public void onError(int error) {
    }

    @Override
    public void onRmsChanged(float rmsdB) {
        Toast.makeText(activity, "rmsdB : " + rmsdB, Toast.LENGTH_SHORT).show();
        float radius = (float) Math.log10(Math.max(1, rmsdB - 500)) * ScreenUtils.dp2px(activity, 20);
        ((MainActivity) activity).getVoiceView().animateRadius(radius);
    }

    @Override
    public void onBeginningOfSpeech() {
    }

    @Override
    public void onBufferReceived(byte[] buffer) {
    }

    @Override
    public void onEvent(int eventType, Bundle params) {
    }

    @Override
    public void onPartialResults(Bundle partialResults) {
    }

    public String getTextContent() {
        return textContent;
    }
}
