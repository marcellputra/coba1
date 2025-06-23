/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author USER
 */
public class I18n {

    private static Locale locale = new Locale("id"); // default Indonesia
    private static ResourceBundle bundle = loadBundle();

    public static void setLocale(Locale newLocale) {
        locale = newLocale;
        bundle = loadBundle();
    }

    private static ResourceBundle loadBundle() {
        return ResourceBundle.getBundle("i18n.messages", locale);
    }

    public static String get(String key) {
        return bundle.getString(key);
    }
}
