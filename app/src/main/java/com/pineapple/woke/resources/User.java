package com.pineapple.woke.resources;

import android.util.Log;

import com.pineapple.woke.StudySession.SavedSession;
import com.pineapple.woke.StudySession.StudySession;

import java.util.ArrayList;

public class User {

    private String name;
    private int wokeInterval;
    private ArrayList<SavedSession> studySessions;

    public User(){ }
    public User(String name, int wokeInterval){
        this.name = name;
        this.wokeInterval = wokeInterval;
        studySessions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getWokeInterval() {
        return wokeInterval;
    }
    public void setWokeInterval(int wokeInterval) {
        this.wokeInterval = wokeInterval;
    }

    public ArrayList<SavedSession> getStudySessions() {
        return studySessions;
    }
    public void addStudySession(SavedSession s){
        studySessions.add(s);
        Log.d("User", "Study Session added to: "+name);
        Log.d("User", "Study Session duration: " + Long.toString(s.getDuration()));
    }
}
