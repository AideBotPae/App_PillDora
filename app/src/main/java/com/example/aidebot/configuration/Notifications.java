package com.example.aidebot.configuration;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.example.aidebot.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class Notifications extends Fragment {

    AlertDialog alertDialog;
    View mcontainer;
    String vibration_notification,  light_notification, priority_notification;
    Boolean tones_notification, email_notification;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mcontainer = inflater.inflate(R.layout.notifications, container, false);

        Switch notification_tones = (Switch) mcontainer.findViewById(R.id.notification_tones);
        Switch notification_email = (Switch) mcontainer.findViewById(R.id.notification_email);

        Button button_to_home = mcontainer.findViewById(R.id.button_to_home);
        Button notification_vibration = mcontainer.findViewById(R.id.notification_vibration);
        Button notification_light = mcontainer.findViewById(R.id.notification_light);
        Button notification_priority = mcontainer.findViewById(R.id.notification_priority);

        //DEFAULT VALUES;
        vibration_notification="Predetermined"; //"Predetermined","Default","Long","Short"
        light_notification="CYAN";  //"NONE", "CYAN", "RED", "YELLOW"
        priority_notification="DEFAULT";   //"DEFAULT", "HIGH", "LOW", "MAX", "MIN"

        tones_notification=Boolean.TRUE;
        email_notification=Boolean.FALSE;

        button_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ConfigurationSystem()).commit();

            }
        });
        notification_vibration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibration();

            }
        });

        notification_light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                light();

            }
        });

        notification_priority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priority();

            }
        });

        notification_tones.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tones_notification=isChecked;
            }
        });
        notification_email.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                email_notification=isChecked;
            }
        });
        return mcontainer;
    }

    private void vibration(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mcontainer.getContext());
        builder.setView(getLayoutInflater().inflate(R.layout.vibration_dialog, null));

        alertDialog = builder.create();
        // To prevent a dialog from closing when the positive button clicked, set onShowListener to
        // the AlertDialog
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialogInterface) {
                LinearLayout longVibration = alertDialog.findViewById(R.id.longVibration);
                LinearLayout shortVibration = alertDialog.findViewById(R.id.shortVibration);
                LinearLayout predeterminedVibration = alertDialog.findViewById(R.id.predeterminedVibration);
                LinearLayout disabledVibration = alertDialog.findViewById(R.id.disabledVibration);

                longVibration.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vibration_notification="Long";
                        alertDialog.cancel();
                    }
                });
                shortVibration.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vibration_notification="Short";
                        alertDialog.cancel();
                    }
                });
                predeterminedVibration.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vibration_notification="Predetermined";
                        alertDialog.cancel();
                    }
                });
                disabledVibration.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vibration_notification="Default";
                        alertDialog.cancel();
                    }
                });

            }
        });
        alertDialog.show();
    }

    private void light(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mcontainer.getContext());
        builder.setView(getLayoutInflater().inflate(R.layout.light_dialog, null));

        alertDialog = builder.create();
        // To prevent a dialog from closing when the positive button clicked, set onShowListener to
        // the AlertDialog
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialogInterface) {
                LinearLayout light_yellow = alertDialog.findViewById(R.id.light_yellow);
                LinearLayout light_cyan = alertDialog.findViewById(R.id.light_cyan);
                LinearLayout light_red = alertDialog.findViewById(R.id.light_red);
                LinearLayout light_none = alertDialog.findViewById(R.id.light_none);

                light_none.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vibration_notification="NONE";
                        alertDialog.cancel();
                    }
                });
                light_red.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vibration_notification="RED";
                        alertDialog.cancel();
                    }
                });
                light_cyan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vibration_notification="CYAN";
                        alertDialog.cancel();
                    }
                });
                light_yellow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vibration_notification="YELLOW";
                        alertDialog.cancel();
                    }
                });

            }
        });

        alertDialog.show();
    }

    private void priority(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mcontainer.getContext());
        builder.setView(getLayoutInflater().inflate(R.layout.priority_dialog, null));

        alertDialog = builder.create();
        // To prevent a dialog from closing when the positive button clicked, set onShowListener to
        // the AlertDialog
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialogInterface) {
                LinearLayout max_priority = alertDialog.findViewById(R.id.max_priority);
                LinearLayout high_priority = alertDialog.findViewById(R.id.high_priority);
                LinearLayout default_priority = alertDialog.findViewById(R.id.default_priority);
                LinearLayout low_priority = alertDialog.findViewById(R.id.low_priority);
                LinearLayout min_priority = alertDialog.findViewById(R.id.min_priority);


                max_priority.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vibration_notification="MAX";
                        alertDialog.cancel();
                    }
                });
                high_priority.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vibration_notification="HIGH";
                        alertDialog.cancel();
                    }
                });
                default_priority.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vibration_notification="DEFAULT";
                        alertDialog.cancel();
                    }
                });
                low_priority.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vibration_notification="LOW";
                        alertDialog.cancel();
                    }
                });
                min_priority.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vibration_notification="MIN";
                        alertDialog.cancel();
                    }
                });

            }
        });

        alertDialog.show();
    }
}
