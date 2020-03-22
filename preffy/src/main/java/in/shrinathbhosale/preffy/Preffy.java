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

    public void putBooleanAsync(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public boolean putBoolean(String key, boolean value) {
        return sharedPreferences.edit().putBoolean(key, value).commit();
    }

    public void putFloatAsync(String key, float value) {
        sharedPreferences.edit().putFloat(key, value).apply();
    }

    public boolean putFloat(String key, float value) {
        return sharedPreferences.edit().putFloat(key, value).commit();
    }

    public void putIntAsync(String key, int value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    public boolean putInt(String key, int value) {
        return sharedPreferences.edit().putInt(key, value).commit();
    }

    public void putLongAsync(String key, long value) {
        sharedPreferences.edit().putLong(key, value).apply();
    }

    public boolean putLong(String key, long value) {
        return sharedPreferences.edit().putLong(key, value).commit();
    }

    public void putDoubleAsync(String key, double value) {
        putLongAsync(key, Double.doubleToRawLongBits(value));
    }

    public boolean putDouble(String key, double value) {
        return putLong(key, Double.doubleToRawLongBits(value));
    }

    public void putStringAsync(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public boolean putString(String key, String value) {
        return sharedPreferences.edit().putString(key, value).commit();
    }

    public void putStringSetAsync(String key, Set<String> value) {
        sharedPreferences.edit().putStringSet(key, value).apply();
    }

    public boolean putStringSet(String key, Set<String> value) {
        return sharedPreferences.edit().putStringSet(key, value).commit();
    }

    public void removeAsync(String key) {
        sharedPreferences.edit().remove(key).apply();
    }

    public boolean remove(String key) {
        return sharedPreferences.edit().remove(key).commit();
    }

    public void removeAllAsync() {
        sharedPreferences.edit().clear().apply();
    }

    public boolean removeAll() {
        return sharedPreferences.edit().clear().commit();
    }

    public boolean contains(String key) {
        return sharedPreferences.contains(key);
    }
}
