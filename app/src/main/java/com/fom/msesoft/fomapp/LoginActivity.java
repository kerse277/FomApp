package com.fom.msesoft.fomapp;

import android.app.Activity;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_login)
public class LoginActivity extends Activity {

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @ViewById(R.id.input_layout_id)
    TextInputLayout inputLayoutId;

    @ViewById(R.id.input_layout_login_password)
    TextInputLayout inputLayoutLoginPassword;

    @ViewById(R.id.input_id)
    EditText inputId;

    @ViewById(R.id.input_login_password)
    EditText inputLoginPassword;

    @Click(R.id.btn_signin)
    void btn_signin(){

    }

    @AfterTextChange(R.id.input_id)
    void onTextChancedInputName(){
        validateName();
    }


    @AfterTextChange(R.id.input_login_password)
    void onTextChancedInputPassword(){
        validatePassword();
    }

    private void submitForm() {
        if (!validateName()) {
            return;
        }


        if (!validatePassword()) {
            return;
        }


    }

    private boolean validateName() {
        if (inputId.getText().toString().trim().isEmpty()) {
            inputLayoutId.setError(getString(R.string.err_msg_name));
            requestFocus(inputId);
            return false;
        } else {
            inputLayoutId.setErrorEnabled(false);
        }

        return true;
    }


    private boolean validatePassword() {
        if (inputLoginPassword.getText().toString().trim().isEmpty()) {
            inputLayoutLoginPassword.setError(getString(R.string.err_msg_password));
            requestFocus(inputLoginPassword);
            return false;
        } else {
            inputLayoutLoginPassword.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

}
