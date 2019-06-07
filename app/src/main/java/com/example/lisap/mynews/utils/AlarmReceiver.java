package com.example.lisap.mynews.utils;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.example.lisap.mynews.R;
import com.example.lisap.mynews.activities.SearchResultActivity;
import com.example.lisap.mynews.adapters.SearchResultAdapter;
import com.example.lisap.mynews.entities.Root;

import java.util.Calendar;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlarmReceiver extends BroadcastReceiver {

    public static final String CHANNEL_ID = "channelNbArticles";
    private Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;

        String q = SharedPreferencesManager.getString(context, Constants.SEARCH_QUERY_KEY);
        String fq = SharedPreferencesManager.getString(context, Constants.CATEGORIES_QUERY_KEY);

        Calendar c = Calendar.getInstance();
        String date = c.get(Calendar.YEAR) + String.format("%02d", c.get(Calendar.MONTH)) + String.format("%02d", c.get(Calendar.DATE));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final NewYorkTimesService service = retrofit.create(NewYorkTimesService.class);

        Call<Root> rootCall = service.getSearchWithCategories(q, fq, date, date);
        rootCall.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                Root root = response.body();
                if (root != null) {
                    if (!root.getResponse().getDocs().isEmpty()) {
                        createNotification(mContext, root.getResponse().getDocs().size());
                    }
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {}
        });
    }

    private void createNotification(Context context, int nbArticles) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Nouveaux articles")
                .setContentText(getNotifText(nbArticles))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Nouveaux articles";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription("Recevez au quotidien les articles correspondant à vos critères de recherche");
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            Objects.requireNonNull(notificationManager).createNotificationChannel(channel);
        }

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1, builder.build());
    }
    public String getNotifText(int nbArticles){
        if (nbArticles == 0)
            return "Aucun article n'a été publié aujourd'hui";
        else if(nbArticles == 1)
            return nbArticles + " nouvel article a été publié aujourd'hui";
        else
            return nbArticles + " nouveaux articles ont été publiés aujourd'hui";

    }
}


