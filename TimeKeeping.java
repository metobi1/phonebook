package com.linkedinlearning.phonebook;

public class TimeKeeping {

    protected int ms = 0;
    protected int sec = 0;
    protected int min = 0;
    protected long difference = 0;

    // convert miliseconds to mins and secs
    protected void timeConv(long difference) {
        ms = (int) difference % 1000;
        sec = (int) difference / 1000;
        min = sec / 60;
    }
}
