package ch.testforge.discite.utils;

import android.app.IntentService;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import ch.testforge.discite.database.DisciteDbContract;
import ch.testforge.discite.database.DisciteDbHelper;

/**
 * Created by laszl on 2015. 11. 30..
 */
public class DisciteDbService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public DisciteDbService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
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
            int id = c.getInt(c.getColumnIndexOrThrow(DisciteDbContract.DictionaryEntry.COLUMN_NAME_DICTIONARY_ID));
            String name = c.getString(c.getColumnIndexOrThrow(DisciteDbContract.DictionaryEntry.COLUMN_NAME_DICTIONARY_NAME));
        }

        db.close();
    }
}
