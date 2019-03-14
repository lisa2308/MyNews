package com.example.lisap.mynews.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.lisap.mynews.R;

import java.util.Calendar;

public class SearchSettingsActivity extends AppCompatActivity {
    LinearLayout linearNotifications;
    LinearLayout linearSearch;
    TableLayout tableLayout;
    Button button;
    LinearLayout linearDate;
    EditText editTextQuery;
    CheckBox cbArts, cbBusiness, cbEntrepreneurs, cbPolitics, cbSports, cbTravel;
    TextInputEditText beginDate, endDate;

    Calendar calendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener beginDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            beginDate.setText(year+ String.format("%02d", month) + String.format("%02d", dayOfMonth));
        }
    };
    DatePickerDialog.OnDateSetListener endDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            endDate.setText(year+ String.format("%02d", month) + String.format("%02d", dayOfMonth));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_settings);
        initViews();
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
        }
    }

    private void setSearchButtonListener(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String q = editTextQuery.getText().toString();
                String fq = "";
                if(cbArts.isChecked()){
                    fq += "arts+";
                }
                if(cbBusiness.isChecked()){
                    fq += "business+";
                }
                if(cbEntrepreneurs.isChecked()){
                    fq += "entrepreneurs+";
                }
                if(cbPolitics.isChecked()){
                    fq += "politics+";
                }
                if(cbSports.isChecked()){
                    fq += "sports+";
                }
                if(cbTravel.isChecked()){
                    fq += "travel+";
                }

                String begin = beginDate.getText().toString();
                String end =  endDate.getText().toString();

                if(!q.isEmpty()) {
                    Intent i = new Intent(SearchSettingsActivity.this, SearchResultActivity.class);
                    i.putExtra("q", q);
                    i.putExtra("fq", fq);
                    i.putExtra("begin", begin);
                    i.putExtra("end", end);
                    startActivity(i);
                }
                else {
                    Toast.makeText(SearchSettingsActivity.this,"Please enter a query",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
