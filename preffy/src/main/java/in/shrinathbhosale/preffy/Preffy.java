package in.shrinathbhosale.preffy;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;
import java.util.Set;

public final class Preffy {
    private static Preffy instance;
    private SharedPreferences sharedPreferences;

    private Preffy(Context context) {
        sharedPreferences = context.getSharedPreferences(
                context.getPackageName() + "_preferences",
                Context.MODE_PRIVATE
        );
    }

    public static Preffy getInstance(Context context) {
        if (instance == null) {
            instance = new Preffy(context.getApplicationContext());
        }

        return instance;
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public float getFloat(String key, float defaultValue) {
        return sharedPreferences.getFloat(key, defaultValue);
    }

    public int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    public long getLong(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    public double getDouble(String key, double defaultValue) {
        if(!contains(key))
            return defaultValue;

        return Double.longBitsToDouble(getLong(key, 0L));
    }

    public String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public Set<String> getStringSet(String key, Set<String> defaultValue) {
        return sharedPreferences.getStringSet(key, defaultValue);
    }

    public Map<String, ?> getAll() {
        return sharedPreferences.getAll();
    }

    public void putBoolean(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public boolean putBooleanSync(String key, boolean value) {
        return sharedPreferences.edit().putBoolean(key, value).commit();
    }

    public void putFloat(String key, float value) {
        sharedPreferences.edit().putFloat(key, value).apply();
    }

    public boolean putFloatSync(String key, float value) {
        return sharedPreferences.edit().putFloat(key, value).commit();
    }

    public void putInt(String key, int value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    public boolean putIntSync(String key, int value) {
        return sharedPreferences.edit().putInt(key, value).commit();
    }

    public void putLong(String key, long value) {
        sharedPreferences.edit().putLong(key, value).apply();
    }

    public boolean putLongSync(String key, long value) {
        return sharedPreferences.edit().putLong(key, value).commit();
    }

    public void putDouble(String key, double value) {
        putLong(key, Double.doubleToRawLongBits(value));
    }

    public boolean putDoubleSync(String key, double value) {
        return putLongSync(key, Double.doubleToRawLongBits(value));
    }

    public void putString(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public boolean putStringSync(String key, String value) {
        return sharedPreferences.edit().putString(key, value).commit();
    }

    public void putStringSet(String key, Set<String> value) {
        sharedPreferences.edit().putStringSet(key, value).apply();
    }

    public boolean putStringSetSync(String key, Set<String> value) {
        return sharedPreferences.edit().putStringSet(key, value).commit();
    }

    public void remove(String key) {
        sharedPreferences.edit().remove(key).apply();
    }

    public boolean removeSync(String key) {
        return sharedPreferences.edit().remove(key).commit();
    }

    public void removeAll() {
        sharedPreferences.edit().clear().apply();
    }

    public boolean removeAllSync() {
        return sharedPreferences.edit().clear().commit();
    }

    public boolean contains(String key) {
        return sharedPreferences.contains(key);
    }
}
