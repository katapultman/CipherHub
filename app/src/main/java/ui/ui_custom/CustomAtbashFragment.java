package ui.ui_custom;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cipherhub.R;

import managers.CipherCallerManager;

public class CustomAtbashFragment extends CustomCipherFragment implements CustomCipherDialogFragment.OnInputSelected {

    @Override
    public void sendInput(String input) {
        atbashKey = input;
        Log.d("atbash key", atbashKey);
        CipherCallerManager.instantiateAtbashCipher();
    }

    public CustomAtbashFragment() {

    }

    static private String atbashKey = "abcdefghijklmnopqrstuvwxyz";

    static public String getAtbashKey() {return atbashKey;}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        final Resources resources = getResources();

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomCipherDialogFragment customCipherDialogFragment = new CustomAtbashDialogFragment(resources.getString(R.string.custom_atbash_dialog_message),
                        resources.getString(R.string.custom_dialog_hint), resources.getString(R.string.custom_dialog_positive_button_message),
                        resources.getString(R.string.custom_dialog_negative_button_message));
                customCipherDialogFragment.setTargetFragment(CustomAtbashFragment.this, 2);
                customCipherDialogFragment.show(getFragmentManager(), "Custom Atbash Dialog");
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendInput("abcdefghijklmnopqrstuvwxyz");
                Toast.makeText(getActivity().getApplicationContext(), "Atbash cipher successfully reset!", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

}
