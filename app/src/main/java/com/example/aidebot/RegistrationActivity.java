package com.example.aidebot;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aidebot.Storage.InternalStorage;

import java.util.HashMap;

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
    String username, password,language, email;

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
        Button signUp_btn = findViewById(R.id.next_button);
        signUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });
    }

    //Handles actual login
    private void createAccount() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignUPFailed();
            return;
        }

        Button signUp_btn = findViewById(R.id.next_button);
        signUp_btn.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(RegistrationActivity.this,
                R.style.NewDialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Still some parameters to introduce...");
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
            //onLoginSuccess();  //theorically only this line
            Handler handler = new Handler();

            //in order to check correct behaviour of Progress dialog
            handler.postDelayed(new Runnable() {
                public void run() {
                    onSignUPSuccess();
                    progressDialog.dismiss();
                }
            }, 3000);

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


    private boolean validate() {
        boolean valid = true;

        EditText username_txt = findViewById(R.id.new_username);
        EditText email_address_txt = findViewById(R.id.new_email_address);
        EditText password_txt = findViewById(R.id.new_password);
        Spinner language_txt = findViewById(R.id.new_language);

        this.username = username_txt.getText().toString();
        this.password = password_txt.getText().toString();
        this.email = email_address_txt.getText().toString();
        this.language = language_txt.getSelectedItem().toString();

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
        TextView errorTextview = (TextView) language_txt.getSelectedView();
        if (language.isEmpty()) {
            errorTextview.setError("Choose one of these languages");
            valid = false;
        } else {
            //used to clear the error
            errorTextview.setError(null);
        }
        return valid;
    }

    private boolean matches(String pwd) {
        //returns:
        //      - true - if password is valid.
        //      - false - if password not valid.
        return (pwd.matches(pattern));
    }

    private void onSignUPFailed() {
        Toast.makeText(getBaseContext(), "Unable to create account. Try again!", Toast.LENGTH_LONG).show();
        Button signUp_btn = findViewById(R.id.next_button);
        signUp_btn.setEnabled(true);
    }

    private void onSignUPSuccess() {
        Button signUp_btn = findViewById(R.id.next_button);
        signUp_btn.setEnabled(true);
        newUser();
        startActivity(new Intent(this, RegistrationActivityOptional.class));
        finish();
    }

    private void newUser(){
        InternalStorage in = new InternalStorage(RegistrationActivity.this);
        in.setUsername(username);
        HashMap<String, String> new_user = new HashMap<>();
        new_user.put("username", username);
        new_user.put("password", password);
        new_user.put("language", language);
        new_user.put("email_user", email);
        in.createUser(new_user);
    }

}