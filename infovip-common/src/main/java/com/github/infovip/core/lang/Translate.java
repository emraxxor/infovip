/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.infovip.core.lang;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author attila
 */
public class Translate {

	private final Properties dictionary = new Properties();

    private static final Translate translatorInstance = new Translate();

    static {
		try {
			load("lang/hu_HU.UTF8.properties");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    public static void load(String path) throws IOException {
        InputStream is = Translate.class.getClassLoader().getResourceAsStream(path);
        translatorInstance.dictionary.clear();
        translatorInstance.dictionary.load(is);
    }

    public static String tr(String s) {
        return translatorInstance.translate(s);
    }

    public Properties getDictionary() {
		return dictionary;
	}
    
    public String translate(String s) {
        if (s.length() > 0) {
            if (dictionary.getProperty(s) != null) {
                return dictionary.getProperty(s);
            }

            if (s.charAt(s.length() - 1) == ':') {
                return translate(s.substring(0, s.length() - 1)) + ':';
            }
        }
        return s;
    }

    public static Properties properties() {
    	return translatorInstance.dictionary;
    }
}
