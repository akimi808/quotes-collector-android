package com.akimi808.quotescollector.db.versions;

import android.database.sqlite.SQLiteDatabase;

import com.akimi808.quotescollector.db.Migration;

/**
 * Created by akimi808 on 16/07/2017.
 */

public class Migration7 implements Migration {
    @Override
    public void doUpgrade(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE quotes (\n" +
                "        id INTEGER PRIMARY KEY,\n" +
                "        text TEXT,\n" +
                "        author_id INTEGER,\n" +
                "        source_id INTEGER,\n" +
                "        external_id TEXT, \n" +
                "        application TEXT)");
        db.execSQL("CREATE TABLE authors (\n" +
                "        id INTEGER PRIMARY KEY,\n" +
                "        name TEXT);\n");
        db.execSQL("CREATE TABLE sources (\n" +
                "        id INTEGER PRIMARY KEY,\n" +
                "        title TEXT, \n" +
                "        type TEXT, \n" +
                "        external_id TEXT, \n" +
                "        application TEXT);\n");

    }
}
