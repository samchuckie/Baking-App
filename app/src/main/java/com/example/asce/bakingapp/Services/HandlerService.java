package com.example.asce.bakingapp.Services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

public class HandlerService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public HandlerService(String name) {
        super(name);
    }

    public HandlerService() {
        super("HandlerService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            Log.e("sam", "action is " + action);
        }
        else {
            Log.e("sam", "action is " );

        }
    }
}
