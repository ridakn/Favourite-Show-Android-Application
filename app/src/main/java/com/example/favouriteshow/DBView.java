package com.example.favouriteshow;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class DBView extends Activity {

    Intent goBack;
    private DBManager dbManager;
    private ListView list;
    private SimpleCursorAdapter adapter;

    Button back;

    final String[] from = new String[] {
            DBHelper._ID, DBHelper.NAME, DBHelper.EMAIL, DBHelper.SHOW
    };

    final int[] to = new int[] {
            R.id.id__row, R.id.name_row, R.id.email_row, R.id.show_row
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shows_list);

        goBack = new Intent(this, MainActivity.class);
        back = (Button) findViewById(R.id.button2);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        list = (ListView) findViewById(R.id.list_view);

        adapter = new SimpleCursorAdapter(this, R.layout.list_row, cursor, from, to, 0);

        list.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(goBack);
            }
        });
    }
}
