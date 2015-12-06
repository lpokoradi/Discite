package ch.testforge.discite.database;

import android.provider.BaseColumns;

/**
 * Created by laszl on 2015. 11. 29..
 */
public final class DisciteDbContract {
    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DictionaryEntry.TABLE_NAME;
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String TEXT_TYPE = " TEXT";
    private static final String BLOB_TYPE = " BLOB";
    private static final String REAL_TYPE = " REAL";
    private static final String NUMERIC_TYPE = " NUMERIC";


    private static final String COMMA_SEP = ",";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DictionaryEntry.TABLE_NAME + " (" +
                    DictionaryEntry.COLUMN_NAME_DICTIONARY_ID + DictionaryEntry.COLUMN_TYPE_DICTIONARY_ID + " PRIMARY KEY" + COMMA_SEP +
                    DictionaryEntry.COLUMN_NAME_DICTIONARY_TYPE + DictionaryEntry.COLUMN_TYPE_DICTIONARY_TYPE + COMMA_SEP +
                    DictionaryEntry.COLUMN_NAME_DICTIONARY_NAME + DictionaryEntry.COLUMN_TYPE_DICTIONARY_NAME + COMMA_SEP +
                    DictionaryEntry.COLUMN_NAME_DICTIONARY_LEARNING_LANGUAGE + DictionaryEntry.COLUMN_TYPE_DICTIONARY_LEARNING_LANGUAGE + COMMA_SEP +
                    DictionaryEntry.COLUMN_NAME_DICTIONARY_KNOWN_LANGUAGE + DictionaryEntry.COLUMN_TYPE_DICTIONARY_KNOWN_LANGUAGE + COMMA_SEP +
                    DictionaryEntry.COLUMN_NAME_DICTIONARY_LAST_USED + DictionaryEntry.COLUMN_TYPE_DICTIONARY_LAST_USED +
                    " )";

    public DisciteDbContract() {
    }

    /* Inner class that defines the table contents */
    public static abstract class DictionaryEntry implements BaseColumns {
        public static final String TABLE_NAME = "dictionaries";
        public static final String COLUMN_NAME_DICTIONARY_ID = "dictionary_id";
        public static final String COLUMN_TYPE_DICTIONARY_ID = INTEGER_TYPE;
        public static final String COLUMN_NAME_DICTIONARY_NAME = "dictionary_name";
        public static final String COLUMN_TYPE_DICTIONARY_NAME = TEXT_TYPE;
        public static final String COLUMN_NAME_DICTIONARY_TYPE = "dictionary_type";
        public static final String COLUMN_TYPE_DICTIONARY_TYPE = INTEGER_TYPE;
        public static final String COLUMN_NAME_DICTIONARY_LEARNING_LANGUAGE = "dictionary_lang1";
        public static final String COLUMN_TYPE_DICTIONARY_LEARNING_LANGUAGE = TEXT_TYPE;
        public static final String COLUMN_NAME_DICTIONARY_KNOWN_LANGUAGE = "dictionary_lang2";
        public static final String COLUMN_TYPE_DICTIONARY_KNOWN_LANGUAGE = TEXT_TYPE;
        public static final String COLUMN_NAME_DICTIONARY_LAST_USED = "last_used";
        public static final String COLUMN_TYPE_DICTIONARY_LAST_USED = NUMERIC_TYPE;
    }


}
