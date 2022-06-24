package com.example.lesson08;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//1-фишки
//2-сохранение списка после рестарта
public class InfoActivity extends AppCompatActivity {
    public static final String KEY_LIST_VALUE = "list";

    private TextView allDigits;
    private TextView currentDigit;

    private Button btnSave;
    private Button btnCalculator;

    private static final List<Integer> listNumber = new ArrayList<>();

    private String digit;
    private String refactorLine;
    private final String txtNoDigit = "Вы не ввели число";

    public void findViewById() {
        allDigits = findViewById(R.id.allDigit);
        currentDigit = findViewById(R.id.currentDigit);

        btnSave = findViewById(R.id.btnSave);
        btnCalculator = findViewById(R.id.btnCalculator);
    }

    public void validate(String digit) {
        if (listNumber.size() == 5) {
            listNumber.remove(0);
        }
        listNumber.add(Integer.parseInt(digit));
        refactorLine = TextUtils.join("\n", listNumber);

        updateValues(refactorLine);
    }
    ////////////////
    public void savePreviousList() {
        if (listNumber.size() == 0) {
            try {
                String[] txtNumbers = getValues().split("\n");
                for (String num : txtNumbers) {
                    listNumber.add(Integer.parseInt(num));
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }


    public String getValues() {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(this);

        return sp.getString(KEY_LIST_VALUE, "");
    }

    private void updateValues(String refactorLine) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = sp.edit();
        editor.putString(KEY_LIST_VALUE, String.valueOf(refactorLine));
        editor.apply();
    }

    public void openSomeActivityForResult() {
        Intent intent = new Intent(this, CalculatorActivity.class);
        someActivityResultLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        assert data != null;

                        digit = data.getStringExtra("currentDigit");
                        currentDigit.setText(digit);
                        allDigits.setText(getValues());
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        findViewById();

        savePreviousList();

        allDigits.setText(getValues());

        allDigits.setMovementMethod(new ScrollingMovementMethod());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (digit == null || digit.equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            txtNoDigit,
                            Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    validate(digit);
                }
            }
        });

        btnCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSomeActivityForResult();
            }
        });
    }

    /*@SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        String name = data.getStringExtra("name");
        currentDigit.setText(name);
    }*/


    @Override
    protected void onDestroy() {
        super.onDestroy();

        allDigits = null;
        currentDigit = null;

        btnSave = null;
        btnCalculator = null;
    }
}