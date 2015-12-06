package ch.testforge.discite.activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.Comparator;

import ch.testforge.discite.R;
import ch.testforge.discite.database.DisciteDbContract;
import ch.testforge.discite.database.DisciteDbHelper;
import ch.testforge.discite.utils.LanguageList;

public class AddDictionaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dictionary);

        loadLanguageListToSpinner(R.id.learningLanguageSpinner);
        loadLanguageListToSpinner(R.id.knownLanguageSpinner);
        loadLanguageListToSpinner(R.id.languageSpinner);

        ((RadioButton) findViewById(R.id.bilanguageRadioButton)).setChecked(true);
        onClickLanguageRadioButton(findViewById(R.id.bilanguageRadioButton));
    }

    private void loadLanguageListToSpinner(int resource) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item);

        Spinner s = (Spinner) findViewById(resource);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.addAll(LanguageList.getLanguageList());
        adapter.sort(new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                return lhs.compareTo(rhs);
            }
        });

        s.setAdapter(adapter);
    }

    public void onClickCreateDictionaryButton(View view) {
        // TODO: elmenteni az adatbazisba.
        DisciteDbHelper dbHelper = new DisciteDbHelper(this);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        int dictionaryType = ((RadioButton) findViewById(R.id.bilanguageRadioButton)).isChecked() ? 2 : 1;
        values.put(DisciteDbContract.DictionaryEntry.COLUMN_NAME_DICTIONARY_TYPE, dictionaryType);

        String learningLanguage = ((Spinner) findViewById(R.id.learningLanguageSpinner)).getSelectedItem().toString();
        values.put(DisciteDbContract.DictionaryEntry.COLUMN_NAME_DICTIONARY_LEARNING_LANGUAGE, learningLanguage);

        String knownLanguage = (dictionaryType == 2) ? ((Spinner) findViewById(R.id.knownLanguageSpinner)).getSelectedItem().toString() : learningLanguage;
        values.put(DisciteDbContract.DictionaryEntry.COLUMN_NAME_DICTIONARY_KNOWN_LANGUAGE, knownLanguage);

        values.put(DisciteDbContract.DictionaryEntry.COLUMN_NAME_DICTIONARY_NAME, "Learning " + learningLanguage + " in " + knownLanguage);

        db.insert(DisciteDbContract.DictionaryEntry.TABLE_NAME, null, values);

        db.close();

        onClickCancelCreateDictionaryButton(view);
    }

    public void onClickLanguageRadioButton(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.bilanguageRadioButton:
                if (checked) {
                    findViewById(R.id.learningLanguageText).setEnabled(true);

                    findViewById(R.id.learningLanguageSpinner).setEnabled(true);

                    findViewById(R.id.knownLanguageText).setEnabled(true);

                    findViewById(R.id.knownLanguageSpinner).setEnabled(true);

                    findViewById(R.id.languageText).setEnabled(false);

                    findViewById(R.id.languageSpinner).setEnabled(false);
                    break;
                }
            case R.id.monoLanguageRadioButton:
                if (checked) {
                    findViewById(R.id.learningLanguageText).setEnabled(false);

                    findViewById(R.id.learningLanguageSpinner).setEnabled(false);

                    findViewById(R.id.knownLanguageText).setEnabled(false);

                    findViewById(R.id.knownLanguageSpinner).setEnabled(false);

                    findViewById(R.id.languageText).setEnabled(true);

                    findViewById(R.id.languageSpinner).setEnabled(true);
                    break;
                }
        }
    }

    public void onClickCancelCreateDictionaryButton(View view) {
        Intent intent = new Intent(this, DictionaryListActivity.class);
        startActivity(intent);
    }
}
