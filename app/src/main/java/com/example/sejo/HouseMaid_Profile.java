package com.example.sejo;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.TimeZone;

public class HouseMaid_Profile extends AppCompatActivity {

    TextView textView;
    boolean[] selectedLanguage;
    ArrayList<Integer> langList = new ArrayList<>();
    String[] langArray = {"Sweeping", "Mopping", "Dusting", "Vacuuming", "Washing utensils","Washing cloths", "Cleaning Windows", "Cleaning bathroom", "Cooking", "Watering plants"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_maid_profile);

        Button button = findViewById(R.id.button5);

        button.setOnClickListener(view -> {
            Intent intent = new Intent(HouseMaid_Profile.this, HouseMaid_JobsSearch.class);
            startActivity(intent);
        });

        TextView textView12 = findViewById(R.id.textView12);
        Button button1 = findViewById(R.id.button14);
        button1.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int hours = calendar.get(Calendar.HOUR_OF_DAY);
            int mins = calendar.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(HouseMaid_Profile.this, androidx.appcompat.R.style.Theme_AppCompat_Dialog, (view, hourOfDay, minute) -> {
                Calendar c = Calendar.getInstance();
                c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                c.set(Calendar.MINUTE, minute);
                c.setTimeZone(TimeZone.getDefault());
                SimpleDateFormat format = new SimpleDateFormat("h:mm a", Locale.getDefault());
                String time = format.format(c.getTime());
                textView12.setText(time);
            }
                    ,hours,mins, false);
            timePickerDialog.show();
        });


        Button button2 = findViewById(R.id.button15);
        button2.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int hours = calendar.get(Calendar.HOUR_OF_DAY);
            int mins = calendar.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(HouseMaid_Profile.this, androidx.appcompat.R.style.Theme_AppCompat_Dialog, (view, hourOfDay, minute) -> {
                Calendar c = Calendar.getInstance();
                c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                c.set(Calendar.MINUTE, minute);
                c.setTimeZone(TimeZone.getDefault());
                SimpleDateFormat format = new SimpleDateFormat("h:mm a", Locale.getDefault());
                String time = format.format(c.getTime());
                textView12.setText(time);
            }
                    ,hours,mins, false);
            timePickerDialog.show();
        });

        textView = findViewById(R.id.textView);

        // initialize selected language array
        selectedLanguage = new boolean[langArray.length];

        textView.setOnClickListener(view -> {

            // Initialize alert dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(HouseMaid_Profile.this);

            // set title
            builder.setTitle("Select the works");

            // set dialog non cancelable
            builder.setCancelable(false);

            builder.setMultiChoiceItems(langArray, selectedLanguage, (dialogInterface, i, b) -> {
                // check condition
                if (b) {
                    // when checkbox selected
                    // Add position  in lang list
                    langList.add(i);
                    // Sort array list
                    Collections.sort(langList);
                } else {
                    // when checkbox unselected
                    // Remove position from langList
                    langList.remove(Integer.valueOf(i));
                }
            });

            builder.setPositiveButton("OK", (dialogInterface, i) -> {
                // Initialize string builder
                StringBuilder stringBuilder = new StringBuilder();
                // use for loop
                for (int j = 0; j < langList.size(); j++) {
                    // concat array value
                    stringBuilder.append(langArray[langList.get(j)]);
                    // check condition
                    if (j != langList.size() - 1) {
                        // When j value  not equal
                        // to lang list size - 1
                        // add comma
                        stringBuilder.append(", ");
                    }
                }
                // set text on textView
                textView.setText(stringBuilder.toString());
            });

            builder.setNegativeButton("Cancel", (dialogInterface, i) -> {
                // dismiss dialog
                dialogInterface.dismiss();
            });
            builder.setNeutralButton("Clear All", (dialogInterface, i) -> {
                // use for loop
                for (int j = 0; j < selectedLanguage.length; j++) {
                    // remove all selection
                    selectedLanguage[j] = false;
                    // clear language list
                    langList.clear();
                    // clear text view value
                    textView.setText("");
                }
            });
            // show dialog
            builder.show();
        });
    }
}
