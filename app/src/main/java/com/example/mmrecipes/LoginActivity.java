package com.example.mmrecipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private final LoginActivity activity = LoginActivity.this;
    private NestedScrollView nestedScrollView;

    private TextInputLayout layout_Email;
    private TextInputLayout layout_Password;

    private EditText editText_Email;
    private EditText editText_Password;

    private Button btn_login;
    private TextView textView_registerNow;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialView();
        initialListener();
        initialObject();
    }

    @SuppressLint("WrongViewCast")
    private void initialView() {
        nestedScrollView = findViewById(R.id.nestedScrollView_login);
        layout_Email = findViewById(R.id.textInputLayout_login_email);
        layout_Password = findViewById(R.id.textInputLayout_login_password);

        editText_Email = findViewById(R.id.editText_login_email);
        editText_Password = findViewById(R.id.editText_login_password);

        btn_login = findViewById(R.id.btn_login);
        textView_registerNow = findViewById(R.id.textView_login_registerNow);
    }

    private void initialListener() {
        btn_login.setOnClickListener(this);
        textView_registerNow.setOnClickListener(this);
    }

    private void initialObject() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                verifyFromSqlite();
                break;
            case R.id.textView_login_registerNow:
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                break;
        }
    }

    private void verifyFromSqlite() {
        if (!inputValidation.isEdittextFilled(editText_Email, layout_Email, getString(R.string.error_email))) {
            return;
        }
        if (!inputValidation.isEdittextEmail(editText_Email, layout_Email, getString(R.string.error_email))) {
            return;
        }
        if (!inputValidation.isEdittextFilled(editText_Password, layout_Password, getString(R.string.error_password))) {
            return;
        }
        if (databaseHelper.checkUser(editText_Email.getText().toString().trim(), editText_Password.getText().toString().trim())) {
            //Intent intent = new Intent(activity, MainActivity.class);
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("Email", editText_Email.getText().toString().trim());
            emptyInputEdittext();
            startActivity(intent);
        }
        else {
            Snackbar.make(nestedScrollView, getString(R.string.error_email_password), Snackbar.LENGTH_LONG).show();
        }
    }

    private void emptyInputEdittext() {
        editText_Email.setText(null);
        editText_Password.setText(null);
    }
}
