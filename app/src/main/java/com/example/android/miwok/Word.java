
package com.example.android.miwok;
import android.widget.ArrayAdapter;
/**
 * Created by sansriti on 24-07-2018.
 */

public class Word {
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private  int   imageResourceId=NO_IMAGE_VIEW;
    private int audioResourceId;
    private static int NO_IMAGE_VIEW=-1;
    public Word(String defaultTranslation,String miwokTranslation,int imageId,int audioId){
        mDefaultTranslation= defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        imageResourceId=imageId;
        audioResourceId=audioId;
    }
    public Word(String defaultTranslation,String miwokTranslation,int audioId){
        mDefaultTranslation= defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        audioResourceId=audioId;
    }
    public  String getDefaultTranslation(){
        return mDefaultTranslation;
    }
    public  String getMiwokTranslation(){
        return mMiwokTranslation;
    }
    public int  getImageResourceId(){
        return imageResourceId;
    }
    public boolean hasImage(){
        return (imageResourceId!=NO_IMAGE_VIEW);
    }
    public int getAudioResourceId(){return audioResourceId;}
}
