package com.example.bmicalc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etHeight, etWeight;
    Button calculate_btn, ReCalculate;
    TextView bmi, status, bmi_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etHeight = findViewById(R.id.etHeight);
        etWeight = findViewById(R.id.etWeight);
        calculate_btn = findViewById(R.id.calculate_btn);
        ReCalculate = findViewById(R.id.ReCalculate);
        bmi = findViewById(R.id.bmi);
        status = findViewById(R.id.status);
        bmi_tv = findViewById(R.id.bmi_tv);

        calculate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etHeight.getText().length() > 0 && etWeight.getText().length() > 0) {
                    int height = Integer.parseInt(etHeight.getText().toString());
                    int weight = Integer.parseInt(etWeight.getText().toString());

                    float BMI = calculateBMI(height, weight);

                    bmi.setText(String.valueOf(BMI));
                    bmi.setVisibility(View.VISIBLE);

                    if (BMI < 18.5) {
                        status.setText("Under Weight");
                    } else if (BMI >= 18.5 && BMI < 24.9) {
                        status.setText("Healthy");
                    } else if (BMI >= 24.9 && BMI < 30) {
                        status.setText("Overweight");
                    } else if (BMI >= 30) {
                        status.setText("Suffering from Obesity");
                    }

                    bmi_tv.setVisibility(View.VISIBLE);
                    status.setVisibility(View.VISIBLE);

                    ReCalculate.setVisibility(View.VISIBLE);
                    calculate_btn.setVisibility(View.GONE);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter valid height and weight", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ReCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetEverything();
            }
        });
    }

    private void ResetEverything() {
        calculate_btn.setVisibility(View.VISIBLE);
        ReCalculate.setVisibility(View.GONE);

        etHeight.getText().clear();
        etWeight.getText().clear();
        status.setText("");
        bmi.setText("");
        bmi_tv.setVisibility(View.GONE);
    }

    private float calculateBMI(int height, int weight) {
        float Height_in_metre = (float) height / 100;
        return (float) weight / (Height_in_metre * Height_in_metre);
    }
}
