package com.mystatscloud.onpoint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ScheduleTestFormActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_form);
    }

    public void submitRequest(View button)
    {
        final EditText nameField = (EditText) findViewById(R.id.EditTextName);
        String name = nameField.getText().toString();

        final EditText phoneField = (EditText) findViewById(R.id.EditTextPhone);
        String phone = phoneField.getText().toString();

        final EditText emailField = (EditText) findViewById(R.id.EditTextEmail);
        String email = emailField.getText().toString();

        final EditText companyField = (EditText) findViewById(R.id.EditTextCompany);
        String company = companyField.getText().toString();

        final EditText commentField = (EditText) findViewById(R.id.EditTextMessage);
        String message = commentField.getText().toString();

        Toast.makeText(this, "Your request was successfully submitted.", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getApplicationContext(),MainMenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
