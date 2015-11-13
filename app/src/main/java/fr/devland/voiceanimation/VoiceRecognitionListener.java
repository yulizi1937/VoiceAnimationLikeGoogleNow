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

    }

    /*
     * Process the result of the SpeechRecognition and
     * tell the view to update itself for beauty sake.
     */
    @Override
    public void onResults(Bundle results) {

        textContent = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION).get(0);
        Toast.makeText(activity, "Recording wtf " + textContent, Toast.LENGTH_SHORT).show();
        Log.d("ONRESULTS", textContent.toString());

        ((MainActivity) activity).getVoiceView().endRecording();

    }

    @Override
    public void onError(int error) {
    }

    /*
     * Animate whenever the speaker changes tonality.
     * You can play with the denominator of variable act to fit
     * until you find it reasonable.
     * Who cares by the way, you can leave it as it is.
     */
    @Override
    public void onRmsChanged(float rmsdB) {

        int act = ScreenUtils.dp2px(activity, 20) / 5;
        int min = ScreenUtils.dp2px(activity, 68) / 2;

        ((MainActivity) activity).getVoiceView().animateRadius(Math.max(min, min + rmsdB * act));

    }

    @Override
    public void onBeginningOfSpeech() {
        // textContent = "";
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
