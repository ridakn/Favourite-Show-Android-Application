package com.example.favouriteshow;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText name, show, email, up_id, del_id;
    private Button add, view, delete, update;

    private DBManager dbManager;

    Intent goTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager = new DBManager(this);
        dbManager.open();

        name = (EditText) findViewById(R.id.name);
        show = (EditText) findViewById(R.id.show);
        email = (EditText) findViewById(R.id.email);
        up_id = (EditText) findViewById(R.id.update_id);
        del_id = (EditText) findViewById(R.id.del_id);
        add = (Button) findViewById(R.id.add);
        view = (Button) findViewById(R.id.view);
        delete = (Button) findViewById(R.id.delete);
        update = (Button) findViewById(R.id.update);

        goTo = new Intent(this, DBView.class);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String n = name.getText().toString();
                final String e = email.getText().toString();
                final String s = show.getText().toString();

                if(TextUtils.isEmpty(n) || TextUtils.isEmpty(e) || TextUtils.isEmpty(s))
                {
                    Toast.makeText(getApplicationContext(),
                            "Please fill in all the fields!", Toast.LENGTH_SHORT).show();
                }
                else {
                    dbManager.insert(n, e, s);
                    Toast.makeText(getApplicationContext(),
                            "Added!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(goTo);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String n = name.getText().toString();
                final String e = email.getText().toString();
                final String s = show.getText().toString();

                if(TextUtils.isEmpty(n) || TextUtils.isEmpty(e) || TextUtils.isEmpty(s))
                {
                    Toast.makeText(getApplicationContext(),
                            "Please fill in all the fields!", Toast.LENGTH_SHORT).show();
                }

                else if(TextUtils.isEmpty(up_id.getText().toString())) {
                    Toast.makeText(getApplicationContext(),
                            "Please provide an ID", Toast.LENGTH_SHORT).show();
                }
                else {
                    final Long _id = Long.parseLong(up_id.getText().toString());
                    dbManager.update(_id, n, e, s);
                    Toast.makeText(getApplicationContext(),
                            "Updated!", Toast.LENGTH_SHORT).show();
                }


            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(del_id.getText().toString())) {
                    Toast.makeText(getApplicationContext(),
                            "Please provide an ID", Toast.LENGTH_SHORT).show();
                }
                else {
                    final Long _id = Long.parseLong(del_id.getText().toString());
                    dbManager.delete(_id);
                    Toast.makeText(getApplicationContext(),
                            "Deleted!", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
