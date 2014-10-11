// MainActivity.java
//
// Project: InputEventLogger
// Copyright (c) 2014 Sangwhan Moon. Refer to license.txt for full license.

package com.example.InputEventLogger;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mBackLog = (TextView) findViewById(R.id.inputLog);
        appendToLog("Press menu to clear log.");
        appendSeparator();

        mReady = true;
    }

    @Override
    public boolean dispatchKeyEvent(android.view.KeyEvent event) {
        if (mReady) {
            if (event.getKeyCode() == KeyEvent.KEYCODE_MENU) {
                if (isLogEmpty()) {
                    return super.dispatchKeyEvent(event);
                } else {
                    clearLog();
                    return true;
                }
            } else {
                appendToLog("dispatchKeyEvent(): keycode = " + event.getKeyCode() + ", "  + event.toString());
                appendSeparator();
                return true;
            }
        }

        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean dispatchGenericMotionEvent(android.view.MotionEvent ev) {
        if (mReady) {
            appendToLog("dispatchGenericMotionEvent(): " + ev.toString());
            appendSeparator();
        }

        return super.dispatchGenericMotionEvent(ev);
    }

    private void appendSeparator() {
        appendToLog(kLineSeperator);
    }

    private void appendToLog(String message) {
        mBackLog.append(message + "\n");
    }

    private void clearLog() {
        mBackLog.setText("");
    }

    private boolean isLogEmpty() {
        String log = mBackLog.getText().toString();
        return log.isEmpty();
    }

    private static String kLineSeperator = "========================================";

    private boolean mReady = false;
    private TextView mBackLog;
}
