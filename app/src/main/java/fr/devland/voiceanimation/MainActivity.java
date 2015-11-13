package fr.devland.voiceanimation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/*
 * Holy Grail. We put everything together.
 * (If you don't quite get it it's the equivalent of
 * private static void main(String []args) now you get it huh ?)
 */
public class MainActivity extends Activity implements OnRecordListener {

    private static final String TAG = MainActivity.class.getName();

    private TextView mTextView;
    private VoiceView mVoiceView;
    private SpeechRecognizer recognizer;
    private String[] voiceTextArray;
    private VoiceRecognitionListener recognitionListener;

    private boolean mIsRecording = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.text);
        mVoiceView = (VoiceView) findViewById(R.id.voiceView);
        mVoiceView.setOnRecordListener(this);
        recognitionListener = new VoiceRecognitionListener(this);

        if (!SpeechRecognizer.isRecognitionAvailable(this))
            Toast.makeText(this, "No voice recognition available.", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onRecordStart() {
        Log.d(TAG, "onRecordStart");
        mIsRecording = true;

        recognizer = SpeechRecognizer.createSpeechRecognizer(this);
        recognizer.setRecognitionListener(recognitionListener);

        Toast.makeText(this, "Recording...", Toast.LENGTH_SHORT).show();
        recognizer.startListening(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH));
    }

    @Override
    public void onRecordFinish() {
        Log.d(TAG, "onRecordFinish");
        mIsRecording = false;
        recognizer.stopListening();
        voiceTextArray = recognitionListener.getTextContent().split(" ");
        Toast.makeText(this, "Final : " + voiceTextArray[0], Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        if (mIsRecording) {
            mIsRecording = false;
        }
        recognizer.destroy();
        super.onDestroy();
    }

    public VoiceView getVoiceView() {
        return mVoiceView;
    }


}