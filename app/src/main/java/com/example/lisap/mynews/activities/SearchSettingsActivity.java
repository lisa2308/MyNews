package com.example.lisap.mynews.activities;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.lisap.mynews.R;
import com.example.lisap.mynews.utils.AlarmReceiver;
import com.example.lisap.mynews.utils.Constants;
import com.example.lisap.mynews.utils.SharedPreferencesManager;

import java.util.Calendar;

//Search, Notification//
public class SearchSettingsActivity extends AppCompatActivity {
    LinearLayout linearNotifications;
    LinearLayout linearSearch;
    TableLayout tableLayout;
    Button button;
    LinearLayout linearDate;
    EditText editTextQuery;
    CheckBox cbArts, cbBusiness, cbEntrepreneurs, cbPolitics, cbSports, cbTravel;
    TextInputEditText beginDate, endDate;
    String beginDateGoodFormat, endDateGoodFormat;
    Switch notifSwitch;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;


    //Listener when date is selected//
    Calendar calendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener beginDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            beginDate.setText(getDateTextFormat(year,month,dayOfMonth));
            beginDateGoodFormat = getDateNYTFormat(year,month,dayOfMonth);
        }
    };
    DatePickerDialog.OnDateSetListener endDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            endDate.setText(getDateTextFormat(year,month,dayOfMonth));
            endDateGoodFormat = getDateNYTFormat(year,month,dayOfMonth);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_settings);
        initViews();
        //Show or Hide layouts Search or notification//
        setupSearchVisibility();
        setSearchButtonListener();

        beginDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(
                        SearchSettingsActivity.this,
                        beginDateListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DATE)


                ).show();

            }
        });

        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(
                        SearchSettingsActivity.this,
                        endDateListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DATE)

                ).show();
            }
        });
    }

    private void initViews(){
        linearNotifications = findViewById(R.id.activity_search_settings_linear_notif);
        linearSearch = findViewById(R.id.activity_search_settings_linear_search);
        tableLayout = findViewById(R.id.activity_search_settings_table);
        button = findViewById(R.id.activity_search_settings_button);
        linearDate = findViewById(R.id.activity_search_settings_linear_date);
        editTextQuery = findViewById(R.id.activity_search_settings_query);
        beginDate = findViewById(R.id.activity_search_settings_begin_date);
        endDate = findViewById(R.id.activity_search_settings_end_date);
        cbArts = findViewById(R.id.ass_checkbox_arts);
        cbBusiness = findViewById(R.id.ass_checkbox_business);
        cbEntrepreneurs = findViewById(R.id.ass_checkbox_entrepreneurs);
        cbPolitics = findViewById(R.id.ass_checkbox_politics);
        cbSports = findViewById(R.id.ass_checkbox_sports);
        cbTravel = findViewById(R.id.ass_checkbox_travel);
        notifSwitch = findViewById(R.id.activity_search_setting_switch);

        editTextQuery.setText(SharedPreferencesManager.getString(this, Constants.SEARCH_QUERY_KEY));
        cbArts.setChecked(SharedPreferencesManager.getBoolean(this, Constants.CATEGORIES_ARTS_CHECKED_KEY));
        cbBusiness.setChecked(SharedPreferencesManager.getBoolean(this, Constants.CATEGORIES_BUSINESS_CHECKED_KEY));
        cbEntrepreneurs.setChecked(SharedPreferencesManager.getBoolean(this, Constants.CATEGORIES_ENTREPRENEURS_CHECKED_KEY));
        cbPolitics.setChecked(SharedPreferencesManager.getBoolean(this, Constants.CATEGORIES_POLITICS_CHECKED_KEY));
        cbSports.setChecked(SharedPreferencesManager.getBoolean(this, Constants.CATEGORIES_SPORTS_CHECKED_KEY));
        cbTravel.setChecked(SharedPreferencesManager.getBoolean(this, Constants.CATEGORIES_TRAVEL_CHECKED_KEY));
    }

    private void setupSearchVisibility(){
        boolean isSearch = getIntent().getExtras().getBoolean("isSearch");
        if (isSearch){
            setTitle("Search Articles");
            linearNotifications.setVisibility(View.GONE);
        }
        else {
            setTitle("Notifications");
            button.setVisibility(View.GONE);
            linearDate.setVisibility(View.GONE);
            notifSwitch.setChecked(SharedPreferencesManager.getBoolean(this, Constants.NOTIFICATIONS_CHECKED_KEY));
            setSearchQueryListener();
            setCheckBoxListener();

            alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(this, AlarmReceiver.class);
            alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

            notifSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // check if search query term is not empty
                    if(editTextQuery.getText().toString().trim().isEmpty()){
                        notifSwitch.setChecked(false);
                        Toast.makeText(SearchSettingsActivity.this, "Please enter a query", Toast.LENGTH_LONG).show();
                    }

                    // check if categories are not empty
                    if(getFqString().isEmpty()){
                        notifSwitch.setChecked(false);
                        Toast.makeText(SearchSettingsActivity.this, "Please enter at least one category", Toast.LENGTH_LONG).show();
                    }

                    // on enregistre la valeur du bouton switch (notifications ON ou OFF)
                    SharedPreferencesManager.putBoolean(
                            SearchSettingsActivity.this,
                            Constants.NOTIFICATIONS_CHECKED_KEY,
                            isChecked
                    );

                    if(isChecked) {
                        enableAlarmManager();
                    }
                    else {
                        disableAlarmManager();
                    }
                }
            });
        }
    }

    public String getDateTextFormat (int year, int month, int dayOfMonth) {
        return String.format("%02d", dayOfMonth) + "/" + String.format("%02d", month + 1) + "/" + year;
    }
    public String getDateNYTFormat (int year, int month, int dayOfMonth) {
        return year+ String.format("%02d", month + 1) + String.format("%02d", dayOfMonth);
    }

    private void enableAlarmManager() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 11);
        calendar.set(Calendar.MINUTE, 20);
        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);
    }

    private void disableAlarmManager() {
        alarmMgr.cancel(alarmIntent);
    }

    private void setSearchQueryListener() {
        editTextQuery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                SharedPreferencesManager.putString(SearchSettingsActivity.this, Constants.SEARCH_QUERY_KEY, s.toString());

                if(editTextQuery.getText().toString().isEmpty() && notifSwitch.isChecked()){
                    notifSwitch.setChecked(false);
                    Toast.makeText(SearchSettingsActivity.this, "Please enter a query", Toast.LENGTH_LONG).show();
                }

                if(notifSwitch.isChecked()) {
                    enableAlarmManager();
                }
                else {
                    disableAlarmManager();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setCheckBoxListener() {
        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch(buttonView.getId()) {
                    case R.id.ass_checkbox_arts:
                        SharedPreferencesManager.putBoolean(SearchSettingsActivity.this, Constants.CATEGORIES_ARTS_CHECKED_KEY, isChecked);
                        break;
                    case R.id.ass_checkbox_business:
                        SharedPreferencesManager.putBoolean(SearchSettingsActivity.this, Constants.CATEGORIES_BUSINESS_CHECKED_KEY, isChecked);
                        break;
                    case R.id.ass_checkbox_entrepreneurs:
                        SharedPreferencesManager.putBoolean(SearchSettingsActivity.this, Constants.CATEGORIES_ENTREPRENEURS_CHECKED_KEY, isChecked);
                        break;
                    case R.id.ass_checkbox_politics:
                        SharedPreferencesManager.putBoolean(SearchSettingsActivity.this, Constants.CATEGORIES_POLITICS_CHECKED_KEY, isChecked);
                        break;
                    case R.id.ass_checkbox_sports:
                        SharedPreferencesManager.putBoolean(SearchSettingsActivity.this, Constants.CATEGORIES_SPORTS_CHECKED_KEY, isChecked);
                        break;
                    case R.id.ass_checkbox_travel:
                        SharedPreferencesManager.putBoolean(SearchSettingsActivity.this, Constants.CATEGORIES_TRAVEL_CHECKED_KEY, isChecked);
                        break;
                }

                SharedPreferencesManager.putString(SearchSettingsActivity.this, Constants.CATEGORIES_QUERY_KEY, getFqString());

                if(getFqString().isEmpty() && notifSwitch.isChecked()){
                    notifSwitch.setChecked(false);
                    Toast.makeText(SearchSettingsActivity.this, "Please enter at least category", Toast.LENGTH_LONG).show();
                }

                if(notifSwitch.isChecked()) {
                    enableAlarmManager();
                }
                else {
                    disableAlarmManager();
                }
            }
        };
        cbArts.setOnCheckedChangeListener(listener);
        cbBusiness.setOnCheckedChangeListener(listener);
        cbEntrepreneurs.setOnCheckedChangeListener(listener);
        cbPolitics.setOnCheckedChangeListener(listener);
        cbSports.setOnCheckedChangeListener(listener);
        cbTravel.setOnCheckedChangeListener(listener);
    }

    private String getFqString() {
        String finalOutput = "";
        if(cbArts.isChecked()){
            finalOutput += "arts+";
        }
        if(cbBusiness.isChecked()){
            finalOutput += "business+";
        }
        if(cbEntrepreneurs.isChecked()){
            finalOutput += "entrepreneurs+";
        }
        if(cbPolitics.isChecked()){
            finalOutput += "politics+";
        }
        if(cbSports.isChecked()){
            finalOutput += "sports+";
        }
        if(cbTravel.isChecked()){
            finalOutput += "travel+";
        }
        return finalOutput;
    }

    private void setSearchButtonListener(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String q = editTextQuery.getText().toString();
                String fq = getFqString();

                if(!q.isEmpty()) {
                    Intent i = new Intent(SearchSettingsActivity.this, SearchResultActivity.class);
                    i.putExtra("q", q);
                    i.putExtra("fq", fq);
                    i.putExtra("begin", beginDateGoodFormat);
                    i.putExtra("end", endDateGoodFormat);
                    startActivity(i);
                }
                else {
                    Toast.makeText(SearchSettingsActivity.this,"Please enter a query",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
