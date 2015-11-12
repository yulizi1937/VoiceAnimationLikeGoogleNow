package fr.devland.voiceanimation;

/*
 * This interface is required by the VoiceView for the animation.
 * (When to start dancing and when not)
 * MainActivity implements this Interface. 
 */

public interface OnRecordListener {

    void onRecordStart();

    void onRecordFinish();

}
