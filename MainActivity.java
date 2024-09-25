package com.example.presensi;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private TimePicker timePicker;
    private AutoCompleteTextView attendanceType;
    private TextInputEditText description;
    private MaterialButton submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datePicker = findViewById(R.id.date_picker);
        timePicker = findViewById(R.id.time_picker);
        attendanceType = findViewById(R.id.attendance_type);
        description = findViewById(R.id.description);
        submitButton = findViewById(R.id.submit_button);

        setupAttendanceTypeDropdown();
        setupSubmitButton();
    }

    private void setupAttendanceTypeDropdown() {
        String[] attendanceTypes = new String[]{"Hadir Tepat Waktu", "Sakit", "Terlambat", "Izin"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, attendanceTypes);
        attendanceType.setAdapter(adapter);
    }

    private void setupSubmitButton() {
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitAttendance();
            }
        });
    }

    private void submitAttendance() {
        int year = datePicker.getYear();
        int month = datePicker.getMonth();
        int day = datePicker.getDayOfMonth();
        int hour = timePicker.getCurrentHour();
        int minute = timePicker.getCurrentMinute();

        String type = attendanceType.getText().toString();
        String desc = description.getText().toString();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, minute);

        // Here you would typically save this data to a database or send it to a server
        // For this example, we'll just show a toast message
        String message = String.format("Presensi berhasil: %s pada %tF %tT\nJenis: %s\nKeterangan: %s",
                type, calendar, calendar, type, desc);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}