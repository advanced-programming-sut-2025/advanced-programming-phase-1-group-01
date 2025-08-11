package com.stardew_valley.network;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.IdentityHashMap;

public class JsonUtils {

    private static final Gson gson = new GsonBuilder()
        .serializeNulls() // nullها رو ذخیره کن
        .enableComplexMapKeySerialization() // کلیدهای پیچیده map
        .setExclusionStrategies(new ExclusionStrategy() { // حذف حلقه‌ها
            private final ThreadLocal<IdentityHashMap<Object, Boolean>> visited =
                ThreadLocal.withInitial(IdentityHashMap::new);

            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                Object parent = f.getDeclaringClass();
                if (visited.get().containsKey(parent)) {
                    return true; // اگر قبلا دیده شده حذفش کن
                }
                visited.get().put(parent, true);
                return false;
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        })
        // Adapter عمومی برای کلاس‌هایی که final فیلد دارن
        .registerTypeAdapterFactory(new SafeTypeAdapterFactory())
        .create();

    public static Gson getInstance() {
        return gson;
    }

    /**
     * Adapter Factory برای اینکه Gson بتونه کلاس‌های final/پیچیده رو هم بخونه.
     */
    private static class SafeTypeAdapterFactory implements TypeAdapterFactory {
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
            return new TypeAdapter<>() {
                @Override
                public void write(JsonWriter out, T value) throws IOException {
                    delegate.write(out, value);
                }

                @Override
                public T read(JsonReader in) throws IOException {
                    try {
                        return delegate.read(in);
                    } catch (Exception e) {
                        // اگر Gson نتونست بسازه، null بده
                        return null;
                    }
                }
            };
        }
    }
}
