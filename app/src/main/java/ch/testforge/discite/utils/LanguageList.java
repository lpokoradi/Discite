package ch.testforge.discite.utils;

import java.util.Locale;
import java.util.Vector;

public class LanguageList {
    private static final String[] languageList;

    static {
        try {
            Vector<String> vLanguageList = new Vector<String>();

            for (Locale l : Locale.getAvailableLocales()) {
                if (!vLanguageList.contains(l.getDisplayLanguage()))
                    vLanguageList.add(l.getDisplayLanguage());
            }
            languageList = new String[vLanguageList.size()];

            vLanguageList.toArray(languageList);

        } catch (Exception e) {
            throw new RuntimeException("Darn, an error occurred!", e);
        }
    }

    private LanguageList() {

    }

    public static String[] getLanguageList() {
        return languageList;
    }
}
