package com.example.mmrecipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private final RegisterActivity activity = RegisterActivity.this;
    private NestedScrollView nestedScrollView;

    private TextInputLayout layout_Fullname, layout_Email, layout_Password, layout_ConfirmPassword;

    private EditText editText_Fullname, editText_Email, editText_Password, editText_ConfirmPassword;

    private Button btn_register;
    private TextView textView_loginNow;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        initialView();
        initialListener();
        initialObject();
    }

    @SuppressLint("WrongViewCast")
    private void initialView() {
        nestedScrollView = findViewById(R.id.nestedScrollView_register);
        layout_Fullname = findViewById(R.id.textInputLayout_register_fullname);
        layout_Email = findViewById(R.id.textInputLayout_register_email);
        layout_Password = findViewById(R.id.textInputLayout_register_password);
        layout_ConfirmPassword = findViewById(R.id.textInputLayout_register_confirmPassword);

        editText_Fullname = findViewById(R.id.editText_register_fullname);
        editText_Email = findViewById(R.id.editText_register_email);
        editText_Password = findViewById(R.id.editText_register_password);
        editText_ConfirmPassword = findViewById(R.id.editText_register_confirmPassword);

        btn_register = findViewById(R.id.btn_register);
        textView_loginNow = findViewById(R.id.textView_register_loginNow);
    }

    private void initialListener() {
        btn_register.setOnClickListener(this);
        textView_loginNow.setOnClickListener(this);
    }

    private void initialObject() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(this);
        user = new User();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                PostDataToSqlite();

                //startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                break;
            case R.id.textView_register_loginNow:
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                //finish();
                break;
        }
    }

    private void PostDataToSqlite() {
        if (!inputValidation.isEdittextFilled(editText_Fullname, layout_Fullname, getString(R.string.error_fullname))) {
            return;
        }
        if (!inputValidation.isEdittextFilled(editText_Email, layout_Email, getString(R.string.error_email))) {
            return;
        }
        if (!inputValidation.isEdittextEmail(editText_Email, layout_Email, getString(R.string.error_email))) {
            return;
        }
        if (!inputValidation.isEdittextFilled(editText_Password, layout_Password, getString(R.string.error_password))) {
            return;
        }
        if (!inputValidation.isEdittextMatches(editText_Password, editText_ConfirmPassword, layout_ConfirmPassword, getString(R.string.error_password_match))) {
            return;
        }

        if(!databaseHelper.checkUser(editText_Email.getText().toString().trim())) {
            user.setFullname(editText_Fullname.getText().toString().trim());
            user.setEmail(editText_Email.getText().toString().trim());
            user.setPassword(editText_Password.getText().toString().trim());
            databaseHelper.addUser(user);

            Snackbar.make(nestedScrollView, getString(R.string.register_success), Snackbar.LENGTH_LONG).show();
            emptyInputEdittext();

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("Email", editText_Email.getText().toString().trim());
            startActivity(intent);
        }
        else {
            Snackbar.make(nestedScrollView, getString(R.string.register_email_exists), Snackbar.LENGTH_LONG).show();
        }
    }

    private void emptyInputEdittext() {
        editText_Fullname.setText(null);
        editText_Email.setText(null);
        editText_Password.setText(null);
        editText_ConfirmPassword.setText(null);
    }


}
