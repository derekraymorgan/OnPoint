package com.mystatscloud.onpoint;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ScheduleTestFormActivity extends ActionBarActivity {

    private static final String username = "onpointexample@gmail.com";
    private static final String password = "seunr2015";
    private static final String subjectMessage = "Schedule a Test Request";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_form);
    }

    public void submitRequest(View button)
    {
        sendMail();
    }

    private void sendMail() {
        Session session = createSessionObject();

        try {
            Message message = createMessage(session);
            new SendMailTask().execute(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private Message createMessage(Session session) throws MessagingException, UnsupportedEncodingException {
        String name, phone, email, company, message;
        String PHONE_REGEX = "^(?:(?:\\+?1\\s*(?:[.-]\\s*)?)?(?:\\(\\s*([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\\s*\\)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\\s*(?:[.-]\\s*)?)?([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\\s*(?:[.-]\\s*)?([0-9]{4})(?:\\s*(?:#|x\\.?|ext\\.?|extension)\\s*(\\d+))?$";

        final EditText nameField = (EditText) findViewById(R.id.EditTextName);
        final EditText phoneField = (EditText) findViewById(R.id.EditTextPhone);
        final EditText emailField = (EditText) findViewById(R.id.EditTextEmail);
        final EditText companyField = (EditText) findViewById(R.id.EditTextCompany);
        final EditText commentField = (EditText) findViewById(R.id.EditTextMessage);

        phoneField.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        name = nameField.getText().toString();
        if(TextUtils.isEmpty(name))
        {
            Toast.makeText(ScheduleTestFormActivity.this, "Please fill out your name.", Toast.LENGTH_LONG).show();
            throw new MessagingException();
        }

        email = emailField.getText().toString();
        phone = phoneField.getText().toString();
        if(TextUtils.isEmpty(phone) && TextUtils.isEmpty(email))
        {
            Toast.makeText(ScheduleTestFormActivity.this, "Please provide a method of contact (Phone or E-mail).", Toast.LENGTH_LONG).show();
            throw new MessagingException();
        }
        else if(TextUtils.isEmpty(phone))
        {
            if(Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                phone = "N/A";
            }
            else
            {
                Toast.makeText(ScheduleTestFormActivity.this, "Please provide a valid e-mail address.", Toast.LENGTH_LONG).show();
                throw new MessagingException();
            }
        }
        else if(TextUtils.isEmpty(email))
        {
            if(phone.matches(PHONE_REGEX))
            {
                email = "N/A";
            }
            else
            {
                Toast.makeText(ScheduleTestFormActivity.this, "Please provide a valid phone number.", Toast.LENGTH_LONG).show();
                throw new MessagingException();
            }
        }

        company = companyField.getText().toString();
        if(TextUtils.isEmpty(company))
        {
            company = "N/A";
        }

        message = commentField.getText().toString();
        if(TextUtils.isEmpty(message))
        {
            message = "N/A";
        }

        StringBuilder buildMessage = new StringBuilder();
        buildMessage.append("A request to schedule a test has been received from the following:");
        buildMessage.append("\r\n\r\n");
        buildMessage.append("Name \r\n" + name);
        buildMessage.append("\r\n\r\n");
        buildMessage.append("Phone number \r\n" + phone);
        buildMessage.append("\r\n\r\n");
        buildMessage.append("E-mail \r\n" + email);
        buildMessage.append("\r\n\r\n");
        buildMessage.append("Company \r\n" + company);
        buildMessage.append("\r\n\r\n");
        buildMessage.append("Additional Message \r\n" + message);

        String messageBody = buildMessage.toString();

        Message e_message = new MimeMessage(session);
        e_message.setFrom(new InternetAddress(email, name));
        e_message.addRecipient(Message.RecipientType.TO, new InternetAddress(username));
        e_message.setSubject(subjectMessage);
        e_message.setText(messageBody);
        return e_message;
    }

    private Session createSessionObject() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        return Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    private class SendMailTask extends AsyncTask<Message, Void, Void> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(ScheduleTestFormActivity.this, "Please wait...", "Sending request.", true, false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();

            Toast.makeText(ScheduleTestFormActivity.this, "Your request was successfully submitted.", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(getApplicationContext(),MainMenuActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

        @Override
        protected Void doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
