package com.example.nasa;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

// Volley Singleton Class

public class VolleySingleton {

    // Instance of Volley Singleton
    private static VolleySingleton mInstance;
    // Instance of Request Queue
    private RequestQueue requestQueue;

    // Private Constructor of Singleton Class (Other classes cannot create instance)
    // Get Application context so that its available for whole application and not just one class
    private VolleySingleton(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    // Synchronized used if two threads access this method they won't be able to create two instance/object of this class
    public static synchronized VolleySingleton getmInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleySingleton(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }
}
