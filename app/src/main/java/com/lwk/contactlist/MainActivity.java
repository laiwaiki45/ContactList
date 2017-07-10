package com.lwk.contactlist;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public final static int REQ_CODE_CHILD = 1;

    private ArrayList<Person> persons;
    private ArrayAdapter<Person> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        persons = new ArrayList<>();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent child = new Intent(MainActivity.this, EditActivity.class);
                startActivityForResult(child, REQ_CODE_CHILD);
            }
        });

        final Person ales = new Person("Ales", true, 3345);
        ales.setEmail("ales@ales.com");
        ales.setNickname("A");
        ales.setAddress("Cz");
        final Person tomas = new Person("Tomas", true, 1234);
        tomas.setAddress("T address");
        tomas.setNickname("T");
        tomas.setEmail("tomas@tomas.com");
        final Person sarah = new Person("Sarah", false, 2234);
        sarah.setAddress("S address");
        sarah.setEmail("sarah@sarah.com");
        sarah.setNickname("S");

        persons.add(ales);
        persons.add(tomas);
        persons.add(sarah);
        persons.add(new Person("Tom", true, 4456));

        final ListView personsListView = (ListView) findViewById(R.id.listViewPersons);
        adapter = new ArrayAdapter<Person>(this, android.R.layout.simple_list_item_1, persons);
        personsListView.setAdapter(adapter);

        personsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("person", persons.get(i));
                startActivity(intent);
            }
        });

        personsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int i, long l) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                //Do your Yes progress
                                persons.remove(i);
                                adapter.notifyDataSetChanged();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //Do your No progress
                                break;
                        }
                    }
                };
                AlertDialog.Builder ab = new AlertDialog.Builder(MainActivity.this);
                ab.setMessage("Are you sure to delete " + persons.get(i) + "?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
                return true;
            }
        });


        /*
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("person", persons.get(0));
                startActivity(intent);
            }
        });
        Button tomasBtn = (Button) findViewById(R.id.tomasBtn);
        tomasBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("person", persons.get(1));
                startActivity(intent);
            }
        });

        Button sarahBtn = (Button) findViewById(R.id.sarahBtn);
        sarahBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("person", persons.get(2));
                startActivity(intent);
            }
        });*/


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQ_CODE_CHILD) {
            if (resultCode == Activity.RESULT_OK) {
                Person newPerson = (Person) data.getSerializableExtra("result");
                persons.add(newPerson);
                adapter.notifyDataSetChanged();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
}
