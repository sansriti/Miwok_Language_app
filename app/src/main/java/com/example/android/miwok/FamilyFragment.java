package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FamilyFragment extends Fragment {
    MediaPlayer mediaPlayer;
    private AudioManager myAudioManager ;
    AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT|| focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };

    MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.word_list,container,false);
        myAudioManager = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("father","әpә",R.drawable.family_father,R.raw.family_father));
        words.add(new Word("mother","әṭa",R.drawable.family_mother,R.raw.family_mother));
        words.add(new Word("son","angsi",R.drawable.family_son,R.raw.family_son));
        words.add(new Word("daughter","tune",R.drawable.family_daughter,R.raw.family_daughter));
        words.add(new Word("older brother","taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        words.add(new Word("younger brother","chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        words.add(new Word("older sister","teṭe",R.drawable.family_older_sister,R.raw.family_older_sister));
        words.add(new Word("younger sister","kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        words.add(new Word("grandmother","ama",R.drawable.family_grandmother,R.raw.family_grandmother));
        words.add(new Word("grandfather","paapa",R.drawable.family_grandfather,R.raw.family_grandfather));
        WordAdapter itemsAdapter =
                new WordAdapter(getActivity(), words,R.color.category_family);
        ListView listView = (ListView) rootview.findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                                Word W = words.get(i);
                                                int song = W.getAudioResourceId();
                                                releaseMediaPlayer();
                                                int result = myAudioManager.requestAudioFocus(audioFocusChangeListener,
                                                        // Use the music stream.
                                                        AudioManager.STREAM_MUSIC,
                                                        // Request permanent focus.
                                                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                                                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                                                    // Start playback

                                                    mediaPlayer = MediaPlayer.create(getActivity(), song);
                                                    mediaPlayer.start();
                                                    mediaPlayer.setOnCompletionListener(onCompletionListener);
                                                }
                                            }
                                        }
        );
    return rootview;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer(){
        if(mediaPlayer!=null)
        {
            mediaPlayer.release();
            mediaPlayer=null;
            myAudioManager.abandonAudioFocus(audioFocusChangeListener);
        }
    }
}
