package com.lwk.contactlist;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        final EditText name = (EditText) findViewById(R.id.nameEditText);
        final EditText phone = (EditText) findViewById(R.id.phoneEditText);
        final EditText nick = (EditText) findViewById(R.id.nickEditText3);
        final EditText email = (EditText) findViewById(R.id.emailEditText);
        final EditText address = (EditText) findViewById(R.id.addrEditText);
        final ToggleButton male = (ToggleButton) findViewById(R.id.maleBtn);
        Button addBtn = (Button) findViewById(R.id.addBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEmpty(name)){
                    Toast.makeText(EditActivity.this,"Please enter name",Toast.LENGTH_LONG).show();
                }else if(isEmpty(phone)){
                    Toast.makeText(EditActivity.this,"Please enter phone", Toast.LENGTH_LONG).show();
                }else{
                    Person person = new Person(name.getText().toString(), Integer.parseInt(phone.getText().toString()));
                    person.setNickname(nick.getText().toString());
                    person.setEmail(email.getText().toString());
                    person.setAddress(address.getText().toString());
                    person.setMale(male.isChecked());

                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("result",person);
                    setResult(Activity.RESULT_OK,returnIntent);
                    finish();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();

        setResult(Activity.RESULT_CANCELED, intent);
        finish();
        super.onBackPressed();
    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
}
