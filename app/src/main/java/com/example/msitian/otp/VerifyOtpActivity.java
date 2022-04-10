package com.example.msitian.otp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.msitian.MainActivity;
import com.example.msitian.R;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class VerifyOtpActivity extends AppCompatActivity {

    Button btnVerifyOtp;
    SpinKitView progressBar;
    String getBackendOtp;
    TextView resendOtp, resendOtpAgain;
    PinView otpView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        initViews();

        TextView textView = findViewById(R.id.showMobileNumber);
        textView.setText(String.format("+91-%s", getIntent().getStringExtra("mobileNumber")));

        getBackendOtp = getIntent().getStringExtra("backendOtp");

        btnVerifyOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!otpView.getText().toString().isEmpty()) {
                    String enterOtpCode = otpView.getText().toString();
                    if (getBackendOtp != null) {
                        progressBar.setVisibility(View.VISIBLE);
                        btnVerifyOtp.setVisibility(View.INVISIBLE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(getBackendOtp, enterOtpCode);

                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        progressBar.setVisibility(View.GONE);
                                        btnVerifyOtp.setVisibility(View.VISIBLE);

                                        if (task.isSuccessful()) {
                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                            Toast.makeText(VerifyOtpActivity.this, "OTP Verified Successfully", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(VerifyOtpActivity.this, "Some Error Occurred", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    } else {
                        Toast.makeText(VerifyOtpActivity.this, "Some Error Occurred", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(VerifyOtpActivity.this, "Please Enter All Number", Toast.LENGTH_SHORT).show();
                }
            }
        });


        resendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + textView,
                        60,
                        TimeUnit.SECONDS,
                        VerifyOtpActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(VerifyOtpActivity.this, "Some Error Occurred", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newOtp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                getBackendOtp = newOtp;
                                Toast.makeText(VerifyOtpActivity.this, "OTP resend successfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
        });

        new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long l) {
                resendOtpAgain.setText(""+l/1000);
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    private void initViews() {
        otpView = findViewById(R.id.otpView);
        btnVerifyOtp = findViewById(R.id.btnVerifyOtp);
        progressBar = findViewById(R.id.progressBarRecieveOtp);
        resendOtp = findViewById(R.id.txtViewResendOtp);
        resendOtpAgain = findViewById(R.id.txtViewResendOtpAgain);
    }
}