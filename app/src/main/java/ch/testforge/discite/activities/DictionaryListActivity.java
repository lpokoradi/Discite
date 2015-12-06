package ch.testforge.discite.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Vector;

import ch.testforge.discite.R;
import ch.testforge.discite.database.DisciteDbContract;
import ch.testforge.discite.database.DisciteDbHelper;

public class DictionaryListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item);

        ListView lv = (ListView) findViewById(R.id.dictionaryListView);

        adapter.setDropDownViewResource(android.R.layout.simple_selectable_list_item);
        adapter.addAll(getDictionaryNameList());

        lv.setAdapter(adapter);


    }

    public void onClickAddDictionaryButton(View v) {
        Intent intent = new Intent(this, AddDictionaryActivity.class);
        startActivity(intent);
    }

    private String[] getDictionaryNameList() {
        Vector<String> dictionaryNamesVector = new Vector<String>();
        DisciteDbHelper dbHelper = new DisciteDbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                DisciteDbContract.DictionaryEntry.COLUMN_NAME_DICTIONARY_ID,
                DisciteDbContract.DictionaryEntry.COLUMN_NAME_DICTIONARY_NAME
        };

        String sortOrder =
                DisciteDbContract.DictionaryEntry.COLUMN_NAME_DICTIONARY_LAST_USED + " DESC";

        Cursor c = db.query(
                DisciteDbContract.DictionaryEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        if (c.moveToFirst()) {
            do {
                dictionaryNamesVector.add(c.getString(c.getColumnIndexOrThrow(DisciteDbContract.DictionaryEntry.COLUMN_NAME_DICTIONARY_NAME)));
                c.moveToNext();
            } while (!c.isLast());
        }

        db.close();

        String[] dictionaryNamesArray = new String[dictionaryNamesVector.size()];
        return dictionaryNamesVector.toArray(dictionaryNamesArray);
    }
}
