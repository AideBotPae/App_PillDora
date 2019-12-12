package com.example.aidebot.configuration;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.aidebot.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class Account extends Fragment {

    AlertDialog alertDialog;
    View mcontainer;
    private static final String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mcontainer = inflater.inflate(R.layout.account, container, false);

        Button change_password = mcontainer.findViewById(R.id.change_password);
        Button change_language = mcontainer.findViewById(R.id.change_language);
        Button change_postalcode = mcontainer.findViewById(R.id.change_postalcode);
        Button user_email = mcontainer.findViewById(R.id.user_email);
        Button user_email_tutor = mcontainer.findViewById(R.id.user_email_tutor);
        Button delete_account = mcontainer.findViewById(R.id.delete_account);
        final Button button_to_home = mcontainer.findViewById(R.id.button_to_home);


        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_password();
            }
        });
        change_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_language();
            }
        });
        change_postalcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_postalcode();
            }
        });
        user_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_email();
            }
        });
        user_email_tutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_email_tutor();
            }
        });
        delete_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete_account();
            }
        });
        button_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_to_home.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("");
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new ConfigurationSystem()).commit();

                    }
                });
            }
        });

        return mcontainer;
    }

    private void change_password() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mcontainer.getContext());
        builder.setView(getLayoutInflater().inflate(R.layout.change_password, null));

        alertDialog = builder.create();
        // To prevent a dialog from closing when the positive button clicked, set onShowListener to
        // the AlertDialog
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialogInterface) {

                final EditText current_password = alertDialog.findViewById(R.id.current_password);
                final EditText new_password_1 = alertDialog.findViewById(R.id.new_password_1);
                final EditText new_password_2 = alertDialog.findViewById(R.id.new_password_2);
                final Button submit_password = alertDialog.findViewById(R.id.submit_password);

                submit_password.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(checkCurrentPassword(current_password.getText().toString())){
                            String new_password_1_str=new_password_1.getText().toString();
                            String new_password_2_str=new_password_2.getText().toString();
                            if(new_password_1_str.equals(new_password_2_str)){
                                if (!matches(new_password_1_str)) {
                                    new_password_1.setError("Not a Valid Password. Enter Password with at least 8 characters and  at least one uppercase, lowercase, number and special character");
                                } else {
                                    new_password_1.setError(null);
                                    alertDialog.cancel();
                                    //PASSWORD IS CORRECT, CHANGE
                                    //CODE CHANGING PASSWORD
                                }
                            }
                        }
                    }
                });

            }
        });
        alertDialog.show();
    }
    private Boolean checkCurrentPassword(String current_password){
        //CHANGE AFTERWARDS
        return Boolean.TRUE;
    }
    public boolean matches(String pwd) {
        //returns:
        //      - true - if password is valid.
        //      - false - if password not valid.
        return (pwd.matches(pattern));
    }

    private void change_language() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mcontainer.getContext());
        builder.setView(getLayoutInflater().inflate(R.layout.change_language, null));

        alertDialog = builder.create();
        // To prevent a dialog from closing when the positive button clicked, set onShowListener to
        // the AlertDialog
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialogInterface) {

                final Spinner new_language = alertDialog.findViewById(R.id.new_language);
                final Button submit_language = alertDialog.findViewById(R.id.submit_language);
                submit_language.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String language = new_language.getSelectedItem().toString();
                        TextView errorTextview = (TextView) new_language.getSelectedView();

                        if (language.isEmpty()) {
                            errorTextview.setError("Choose one of these languages");
                        } else {
                            //used to clear the error
                            errorTextview.setError(null);
                            alertDialog.cancel();
                        }
                    }
                });

            }
        });
        alertDialog.show();
    }

    private void change_postalcode() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mcontainer.getContext());
        builder.setView(getLayoutInflater().inflate(R.layout.change_postalcode, null));

        alertDialog = builder.create();
        // To prevent a dialog from closing when the positive button clicked, set onShowListener to
        // the AlertDialog
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialogInterface) {

                final EditText new_postal_code = alertDialog.findViewById(R.id.new_postal_code);
                final Button submit_postal_code = alertDialog.findViewById(R.id.submit_postal_code);
                submit_postal_code.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String postal_code = new_postal_code.getText().toString();

                        if (postal_code.isEmpty() || postal_code.length() < 3) {
                            new_postal_code.setError("Enter a valid PostCode");
                        } else {
                            //used to clear the error
                            new_postal_code.setError(null);
                            alertDialog.cancel();

                        }
                    }
                });

            }
        });
        alertDialog.show();
    }

    private void user_email() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mcontainer.getContext());
        builder.setView(getLayoutInflater().inflate(R.layout.change_email, null));

        alertDialog = builder.create();
        // To prevent a dialog from closing when the positive button clicked, set onShowListener to
        // the AlertDialog
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialogInterface) {

                final EditText new_email = alertDialog.findViewById(R.id.new_email);
                final Button submit_postal_code = alertDialog.findViewById(R.id.submit_postal_code);
                submit_postal_code.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String email = new_email.getText().toString();

                        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                            new_email.setError("Enter a valid email address");
                        } else {
                            new_email.setError(null);
                            alertDialog.cancel();
                        }
                    }
                });

            }
        });
        alertDialog.show();
    }

    private void user_email_tutor() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mcontainer.getContext());
        builder.setView(getLayoutInflater().inflate(R.layout.change_email_tutor, null));

        alertDialog = builder.create();
        // To prevent a dialog from closing when the positive button clicked, set onShowListener to
        // the AlertDialog
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialogInterface) {

                final EditText new_email = alertDialog.findViewById(R.id.new_email);
                final Button submit_postal_code = alertDialog.findViewById(R.id.submit_postal_code);
                submit_postal_code.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String email = new_email.getText().toString();

                        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                            new_email.setError("Enter a valid email address");
                        } else {
                            new_email.setError(null);
                            alertDialog.cancel();
                        }
                    }
                });

            }
        });
        alertDialog.show();
    }

    private void delete_account() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mcontainer.getContext());
        builder.setView(getLayoutInflater().inflate(R.layout.delete_account, null));

        alertDialog = builder.create();
        // To prevent a dialog from closing when the positive button clicked, set onShowListener to
        // the AlertDialog
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialogInterface) {

                final Button YesDelete = alertDialog.findViewById(R.id.YesDelete);
                final Button NotDelete = alertDialog.findViewById(R.id.NotDelete);

                YesDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //DELETE ACCOUNT
                        alertDialog.cancel();
                    }
                });
                NotDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.cancel();
                    }
                });

            }
        });
        alertDialog.show();
    }


}
