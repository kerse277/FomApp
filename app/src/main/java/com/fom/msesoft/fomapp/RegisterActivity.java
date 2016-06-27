package com.fom.msesoft.fomapp;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.lang.reflect.Field;

@EActivity(R.layout.activity_register)
public class RegisterActivity extends Activity {


    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @ViewById(R.id.input_layout_name)
    TextInputLayout inputLayoutName;

    @ViewById(R.id.input_layout_email)
    TextInputLayout inputLayoutEmail;

    @ViewById(R.id.input_layout_password)
    TextInputLayout inputLayoutPassword;

    @ViewById(R.id.input_name)
    EditText inputName;

    @ViewById(R.id.input_email)
    EditText inputEmail;

    @ViewById(R.id.input_password)
    EditText inputPassword;

    @AfterTextChange(R.id.input_name)
    void onTextChancedInputName(){
        validateName();
    }

    @AfterTextChange(R.id.input_email)
    void onTextChancedInputEmail(){
        validateEmail();
    }

    @AfterTextChange(R.id.input_password)
    void onTextChancedInputPassword(){
        validatePassword();
    }

    @ViewById
    FloatingActionButton floatingActionButton;

    @Click(R.id.btn_signin_register)
    void btn_signin_register(){
        Intent intent = new Intent(this,LoginActivity_.class);
        startActivity(intent);
    }

    @Click
    void floatingActionButton (View view) {
        final PopupMenu popup = new PopupMenu(RegisterActivity.this, floatingActionButton);
        //Inflating the Popup using xml file
        popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
        Object menuHelper;
        Class[] argTypes;

        try {
            Field fMenuHelper = PopupMenu.class.getDeclaredField("mPopup");
            fMenuHelper.setAccessible(true);
            menuHelper = fMenuHelper.get(popup);
            argTypes = new Class[] { boolean.class };
            menuHelper.getClass().getDeclaredMethod("setForceShowIcon", argTypes).invoke(menuHelper, true);
        } catch (Exception e) {

            popup.show();
            return;
        }


        popup.show();//showing popup menu
    }


    private void submitForm() {
        if (!validateName()) {
            return;
        }

        if (!validateEmail()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }

        Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_SHORT).show();
    }

    private boolean validateName() {
        if (inputName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError(getString(R.string.err_msg_name));
            requestFocus(inputName);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (inputPassword.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(inputPassword);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
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
