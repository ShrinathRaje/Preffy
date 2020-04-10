package in.shrinathbhosale.preffy;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.Set;

/**
 * Preffy is the singleton class that wraps over the android's native EncryptedSharedPreferences implementation and gives you a clean API to persist simple key value pairs.
 * @author Shrinath Bhosale
 */
public final class Preffy {
    private static Preffy instance;
    private SharedPreferences sharedPreferences;

    private static final boolean DEFAULT_BOOLEAN_VAL  = false;
    private static final float DEFAULT_FLOAT_VAL = 0.0F;
    private static final int DEFAULT_INT_VAL = -1;
    private static final long DEFAULT_LONG_VAL = -1L;
    private static final double DEFAULT_DOUBLE_VAL = 0.0;
    private static final String DEFAULT_STRING_VAL = "";
    private static final Set<String> DEFAULT_SET_VAL = null;

    private Preffy(Context context) {
        try {
            String masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);

            sharedPreferences = EncryptedSharedPreferences.create(
                    context.getPackageName() + "_preferences",
                    masterKeyAlias,
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param context the application/activity context
     * @return a singleton instance of Preffy
     */
    public static Preffy getInstance(Context context) {
        if (instance == null) {
            instance = new Preffy(context.getApplicationContext()); //getApplicationContext to avoid memory leaks
        }

        return instance;
    }

    /**
     * Read the boolean value saved in the shared prefs
     * @param key the name of the preference to read
     * @return the value if key is present, else return the DEFAULT_BOOLEAN_VAL
     * @see #DEFAULT_BOOLEAN_VAL
     */
    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, DEFAULT_BOOLEAN_VAL);
    }

    /**
     * Read the boolean value saved in the shared prefs
     * @param key the name of the preference to read
     * @param defaultValue value to return if the key does not exist
     * @return the value if it exists, or defaultValue
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    /**
     * Read the float value saved in the shared prefs
     * @param key the name of the preference to read
     * @return the value if key is present, else return the DEFAULT_FLOAT_VAL
     * @see #DEFAULT_FLOAT_VAL
     */
    public float getFloat(String key) {
        return sharedPreferences.getFloat(key, DEFAULT_FLOAT_VAL);
    }

    /**
     * Read the float value saved in the shared prefs
     * @param key the name of the preference to read
     * @param defaultValue value to return if the key does not exist
     * @return the value if it exists, or defaultValue
     */
    public float getFloat(String key, float defaultValue) {
        return sharedPreferences.getFloat(key, defaultValue);
    }

    /**
     * Read the integer value saved in the shared prefs
     * @param key the name of the preference to read
     * @return the value if key is present, else return the DEFAULT_INT_VAL
     * @see #DEFAULT_INT_VAL
     */
    public int getInt(String key) {
        return sharedPreferences.getInt(key, DEFAULT_INT_VAL);
    }

    /**
     * Read the integer value saved in the shared prefs
     * @param key the name of the preference to read
     * @param defaultValue value to return if the key does not exist
     * @return the value if it exists, or defaultValue
     */
    public int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    /**
     * Read the long value saved in the shared prefs
     * @param key the name of the preference to read
     * @return the value if key is present, else return the DEFAULT_LONG_VAL
     * @see #DEFAULT_LONG_VAL
     */
    public long getLong(String key) {
        return sharedPreferences.getLong(key, DEFAULT_LONG_VAL);
    }

    /**
     * Read the long value saved in the shared prefs
     * @param key the name of the preference to read
     * @param defaultValue value to return if the key does not exist
     * @return the value if it exists, or defaultValue
     */
    public long getLong(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    /**
     * Read the double value saved in the shared prefs
     * @param key the name of the preference to read
     * @return the value if key is present, else return the DEFAULT_DOUBLE_VAL
     * @see #DEFAULT_DOUBLE_VAL
     */
    public double getDouble(String key) {
        if(!contains(key))
            return DEFAULT_DOUBLE_VAL;

        return Double.longBitsToDouble(getLong(key));
    }

    /**
     * Read the double value saved in the shared prefs
     * @param key the name of the preference to read
     * @param defaultValue value to return if the key does not exist
     * @return the value if it exists, or defaultValue
     */
    public double getDouble(String key, double defaultValue) {
        if(!contains(key))
            return defaultValue;

        return Double.longBitsToDouble(getLong(key, DEFAULT_LONG_VAL));
    }

    /**
     * Read the string value saved in the shared prefs
     * @param key the name of the preference to read
     * @return the value if key is present, else return the DEFAULT_STRING_VAL
     * @see #DEFAULT_STRING_VAL
     */
    public String getString(String key) {
        return sharedPreferences.getString(key, DEFAULT_STRING_VAL);
    }

    /**
     * Read the string value saved in the shared prefs
     * @param key the name of the preference to read
     * @param defaultValue value to return if the key does not exist
     * @return the value if it exists, or defaultValue
     */
    public String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    /**
     * Read the string set value saved in the shared prefs
     * @param key the name of the preference to read
     * @return the value if key is present, else return the DEFAULT_SET_VAL
     * @see #DEFAULT_SET_VAL
     */
    public Set<String> getStringSet(String key) {
        return sharedPreferences.getStringSet(key, DEFAULT_SET_VAL);
    }

    /**
     * Read the string set value saved in the shared prefs
     * @param key the name of the preference to read
     * @param defaultValue value to return if the key does not exist
     * @return the value if it exists, or defaultValue
     */
    public Set<String> getStringSet(String key, Set<String> defaultValue) {
        return sharedPreferences.getStringSet(key, defaultValue);
    }

    /**
     * Read all the key value pairs saved in the shared prefs
     * @return the map of key key value pairs
     */
    public Map<String, ?> getAll() {
        return sharedPreferences.getAll();
    }

    /**
     * Save the boolean value asynchronously
     * @param key the name of the preference
     * @param value the value of the preference
     */
    public void putBoolean(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    /**
     * Save the boolean value synchronously
     * @param key the name of the preference
     * @param value the value of the preference
     * @return true if successfully saved, else false
     */
    public boolean putBooleanSync(String key, boolean value) {
        return sharedPreferences.edit().putBoolean(key, value).commit();
    }

    /**
     * Save the float value asynchronously
     * @param key the name of the preference
     * @param value the value of the preference
     */
    public void putFloat(String key, float value) {
        sharedPreferences.edit().putFloat(key, value).apply();
    }

    /**
     * Save the float value synchronously
     * @param key the name of the preference
     * @param value the value of the preference
     * @return true if successfully saved, else false
     */
    public boolean putFloatSync(String key, float value) {
        return sharedPreferences.edit().putFloat(key, value).commit();
    }

    /**
     * Save the integer value asynchronously
     * @param key the name of the preference
     * @param value the value of the preference
     */
    public void putInt(String key, int value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    /**
     * Save the integer value synchronously
     * @param key the name of the preference
     * @param value the value of the preference
     * @return true if successfully saved, else false
     */
    public boolean putIntSync(String key, int value) {
        return sharedPreferences.edit().putInt(key, value).commit();
    }

    /**
     * Save the long value asynchronously
     * @param key the name of the preference
     * @param value the value of the preference
     */
    public void putLong(String key, long value) {
        sharedPreferences.edit().putLong(key, value).apply();
    }

    /**
     * Save the long value synchronously
     * @param key the name of the preference
     * @param value the value of the preference
     * @return true if successfully saved, else false
     */
    public boolean putLongSync(String key, long value) {
        return sharedPreferences.edit().putLong(key, value).commit();
    }

    /**
     * Save the double value asynchronously
     * @param key the name of the preference
     * @param value the value of the preference
     */
    public void putDouble(String key, double value) {
        putLong(key, Double.doubleToRawLongBits(value));
    }

    /**
     * Save the double value synchronously
     * @param key the name of the preference
     * @param value the value of the preference
     * @return true if successfully saved, else false
     */
    public boolean putDoubleSync(String key, double value) {
        return putLongSync(key, Double.doubleToRawLongBits(value));
    }

    /**
     * Save the string value asynchronously
     * @param key the name of the preference
     * @param value the value of the preference
     */
    public void putString(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    /**
     * Save the string value synchronously
     * @param key the name of the preference
     * @param value the value of the preference
     * @return true if successfully saved, else false
     */
    public boolean putStringSync(String key, String value) {
        return sharedPreferences.edit().putString(key, value).commit();
    }

    /**
     * Save the string set value asynchronously
     * @param key the name of the preference
     * @param value the value of the preference
     */
    public void putStringSet(String key, Set<String> value) {
        sharedPreferences.edit().putStringSet(key, value).apply();
    }

    /**
     * Save the string set value synchronously
     * @param key the name of the preference
     * @param value the value of the preference
     * @return true if successfully saved, else false
     */
    public boolean putStringSetSync(String key, Set<String> value) {
        return sharedPreferences.edit().putStringSet(key, value).commit();
    }

    /**
     * Deletes the specific preference asynchronously
     * @param key the name of the preference to delete
     */
    public void remove(String key) {
        sharedPreferences.edit().remove(key).apply();
    }

    /**
     * Deletes the specific preference synchronously
     * @param key the name of the preference to delete
     * @return true if successfully deleted, else false
     */
    public boolean removeSync(String key) {
        return sharedPreferences.edit().remove(key).commit();
    }

    /**
     * Delete all preferences asynchronously
     */
    public void removeAll() {
        sharedPreferences.edit().clear().apply();
    }

    /**
     * Delete all preferences synchronously
     * @return true if successfully deleted, else false
     */
    public boolean removeAllSync() {
        return sharedPreferences.edit().clear().commit();
    }

    /**
     * Search a preference
     * @param key the name of the preference to search
     * @return true if key is found, else false
     */
    public boolean contains(String key) {
        return sharedPreferences.contains(key);
    }
}
