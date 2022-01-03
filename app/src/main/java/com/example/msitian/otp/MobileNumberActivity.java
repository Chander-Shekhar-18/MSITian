package com.example.msitian.otp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.msitian.MainActivity;
import com.example.msitian.R;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MobileNumberActivity extends AppCompatActivity {

    EditText mobileNumber;
    Button btnGetOtp;
    ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_number);

        firebaseAuth = FirebaseAuth.getInstance();

        initViews();

        btnGetOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mobileNumber.getText().toString().trim().isEmpty()) {
                    if ((mobileNumber.getText().toString().trim()).length() == 10) {
                        progressBar.setVisibility(View.VISIBLE);
                        btnGetOtp.setVisibility(View.INVISIBLE);

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91" + mobileNumber.getText().toString().trim(),
                                60,
                                TimeUnit.SECONDS,
                                MobileNumberActivity.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                        progressBar.setVisibility(View.GONE);
                                        btnGetOtp.setVisibility(View.VISIBLE);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        progressBar.setVisibility(View.GONE);
                                        btnGetOtp.setVisibility(View.VISIBLE);
                                        Toast.makeText(MobileNumberActivity.this, "Some Error Occurred", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        progressBar.setVisibility(View.GONE);
                                        btnGetOtp.setVisibility(View.VISIBLE);

                                        Intent intent = new Intent(MobileNumberActivity.this, VerifyOtpActivity.class);
                                        intent.putExtra("mobileNumber", mobileNumber.getText().toString().trim());
                                        intent.putExtra("backendOtp", s);
                                        startActivity(intent);
                                    }
                                }
                        );
                    } else {
                        Toast.makeText(MobileNumberActivity.this, "Please Enter Correct Mobile Number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MobileNumberActivity.this, "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initViews() {
        mobileNumber = findViewById(R.id.enterMobileNumber);
        btnGetOtp = findViewById(R.id.btnGetOtp);
        progressBar = findViewById(R.id.progressBarSendingOtp);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            //user already logged in
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
}