package com.pakersite.example.app.utils.dialog;

import android.app.Dialog;

import java.util.LinkedList;
import java.util.Queue;

public class MyQueue {
    private Queue<Dialog> mDialogQueue = new LinkedList<>();

    /**
     * 进队
     *
     * @param dialog
     */
    public void offer(Dialog dialog) {
        mDialogQueue.offer(dialog);
    }

    /**
     * 出队
     *
     * @return
     */
    public Dialog poll() {
        return mDialogQueue.poll();
    }
}
