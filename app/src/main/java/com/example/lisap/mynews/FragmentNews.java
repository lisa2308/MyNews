package com.example.lisap.mynews;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Calendar;

public class FragmentNews extends Fragment {

    private static final String DRAWABLE = "image";
    private static final String MY_COLOR_KEY = "color";

    private int mImage;
    private int mColor;


    // PASS DATA TO CONSTRUCTOR //
    public static FragmentNews newInstance(int image, int color) {
        FragmentNews f = new FragmentNews();
        Bundle args = new Bundle();
        args.putInt(DRAWABLE, image);
        args.putInt(MY_COLOR_KEY, color);
        f.setArguments(args);
        return f;
    }

    // SELECT MOOD //
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mImage = getArguments().getInt(DRAWABLE);
            mColor = getArguments().getInt(MY_COLOR_KEY);
        } else {
            mImage = 0;
            mColor = Color.BLACK;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news, container, false);
        return v;
    }
}
