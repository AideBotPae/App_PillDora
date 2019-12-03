package com.example.aidebot;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class RegistrationActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    /*      PATTERN MATCHING --> used to check password complexity
             ^                 # start-of-string
            (?=.*[0-9])       # a digit must occur at least once
            (?=.*[a-z])       # a lower case letter must occur at least once
            (?=.*[A-Z])       # an upper case letter must occur at least once
            (?=.*[@#$%^&+=])  # a special character must occur at least once
            (?=\S+$)          # no whitespace allowed in the entire string
            .{8,}             # anything, at least eight places though
            $                 # end-of-string
    */
    private static final String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //In case user does have an account, return to SIGN IN display
        TextView signIn_text = findViewById(R.id.new_signIn_text);
        signIn_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                finish();
            }
        });

        //In case user press SIGN UP button, create new account
        Button signUp_btn = findViewById(R.id.new_signUp);
        signUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });
    }

    //Handles actual login
    public void createAccount() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignUPFailed();
            return;
        }

        Button signUp_btn = findViewById(R.id.new_signUp);
        signUp_btn.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(RegistrationActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        EditText username_txt = findViewById(R.id.new_username);
        EditText email_address_txt = findViewById(R.id.new_email_address);
        EditText password_txt = findViewById(R.id.new_password);
        Spinner language_txt = findViewById(R.id.new_language);

        String username = username_txt.getText().toString();
        String password = password_txt.getText().toString();
        String email = email_address_txt.getText().toString();
        String language = language_txt.getSelectedItem().toString();

        // TODO: Implement your own signup logic here. Call to presenter methods.
        if (true) {
            onSignUPSuccess();
            return;
        }


        //If happens 3 seconds without verification, we suppose Failure in SignUp.
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignUPFailed();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public boolean validate() {
        boolean valid = true;

        EditText username_txt = findViewById(R.id.new_username);
        EditText email_address_txt = findViewById(R.id.new_email_address);
        EditText password_txt = findViewById(R.id.new_password);
        Spinner language_txt = findViewById(R.id.new_language);

        String username = username_txt.getText().toString();
        String password = password_txt.getText().toString();
        String email = email_address_txt.getText().toString();
        String language = language_txt.getSelectedItem().toString();

        if (username.isEmpty() || username.length() < 3) {
            username_txt.setError("At least 3 characters");
            valid = false;
        } else {
            //used to clear the error
            username_txt.setError(null);
        }
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            email_address_txt.setError("Enter a valid email address");
            valid = false;
        } else {
            email_address_txt.setError(null);
        }

        if (!matches(password)) {
            password_txt.setError("Not a Valid Password. Enter Password with at least 8 characters and  at least one uppercase, lowercase, number and special character");
            valid = false;
        } else {
            password_txt.setError(null);
        }

        if (language.isEmpty()) {
            username_txt.setError("Choose one of these languages");
            valid = false;
        } else {
            //used to clear the error
            username_txt.setError(null);
        }
        return valid;
    }

    public boolean matches(String pwd) {
        //returns:
        //      - true - if password is valid.
        //      - false - if password not valid.
        return (pwd.matches(pattern));
    }

    public void onSignUPFailed() {
        Toast.makeText(getBaseContext(), "Unable to create account. Try again!", Toast.LENGTH_LONG).show();
        Button signUp_btn = findViewById(R.id.new_signUp);
        signUp_btn.setEnabled(true);
    }

    public void onSignUPSuccess() {
        Button signUp_btn = findViewById(R.id.new_signUp);
        signUp_btn.setEnabled(true);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}