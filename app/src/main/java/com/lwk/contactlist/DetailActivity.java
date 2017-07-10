package com.lwk.contactlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView icon = (ImageView) findViewById(R.id.imageView);
        TextView nickname = (TextView) findViewById(R.id.nick);
        TextView name = (TextView) findViewById(R.id.name);
        TextView phone = (TextView) findViewById(R.id.phone);
        TextView address = (TextView) findViewById(R.id.address);
        TextView email = (TextView) findViewById(R.id.email);



        Person person = (Person) getIntent().getSerializableExtra("person");


        nickname.setText(person.getNickname());
        name.setText(person.getName());
        phone.setText(String.valueOf(person.getPhone()));
        address.setText(person.getAddress());
        email.setText(person.getEmail());

        if(person.isMale()){
            icon.setImageResource(R.drawable.male);

        }else {
            icon.setImageResource(R.drawable.female);
        }

    }
}
