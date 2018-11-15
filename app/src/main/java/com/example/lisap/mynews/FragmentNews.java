package com.example.lisap.mynews;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FragmentNews extends Fragment {

    private static final String DRAWABLE = "image";
    private static final String MY_COLOR_KEY = "color";

    private int mImage;
    private int mColor;

    private RecyclerView mRecyclerView;


    // PASS DATA TO CONSTRUCTOR //
    public static FragmentNews newInstance(int image, int color) {
        FragmentNews f = new FragmentNews();
        Bundle args = new Bundle();
        args.putInt(DRAWABLE, image);
        args.putInt(MY_COLOR_KEY, color);
        f.setArguments(args);
        return f;
    }

    // ASSOCIATE DATA WITH LOCAL VARIABLE //
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
        mRecyclerView = v.findViewById(R.id.fragment_news_recycler_view);

        Article article = new Article("https://www.maxizoo.fr/wp-content/uploads/2016/06/aliments-Lapin.jpg", "Lapin mignon", "Le lapin le plus mignon du monde ", "04/11/18");
        Article article1 = new Article("https://www.vulgaris-medical.com/sites/default/files/styles/big-lightbox/public/field/image/actualites/2018/02/26/le-chat-source-de-bienfaits-pour-votre-sante_1.jpg?itok=839wZP-t", "Un petit chat", "Les chats vont dominer le monde", "14/10/18");
        Article article2 = new Article("https://www.google.com/url?sa=i&rct=j&q=&esrc=s&source=imgres&cd=&cad=rja&uact=8&ved=2ahUKEwih9d-An7veAhVCqxoKHYfQABYQjRx6BAgBEAU&url=https%3A%2F%2Ffr.wikipedia.org%2Fwiki%2FSoleil&psig=AOvVaw1ZcVwInQKsvWSoBSGUXNIz&ust=1541438225645031", "Réchauffement climatique", "Le soleil", "08/10/18");

        List<Article> articleList = new ArrayList<>();
        articleList.add(article);
        articleList.add(article1);
        articleList.add(article2);

       // Article article3 = articleList.get(0);

        //CREATE RECYCLER ADAPTER//
        NewsAdapter newsAdapter = new NewsAdapter(articleList);
        //ASSOCIATE ADAPTER WITH RECYCLER//
        mRecyclerView.setAdapter(newsAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));




        return v;
    }
}
