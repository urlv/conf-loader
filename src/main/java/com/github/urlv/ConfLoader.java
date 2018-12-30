package com.github.urlv;

import com.github.urlv.sources.EnvConf;
import com.github.urlv.sources.FileConf;
import com.github.urlv.sources.SrcConf;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.Stack;
import java.util.stream.Collectors;

public class ConfLoader {
    private Stack<SrcConf> sources = new Stack<>();

    public static ConfLoader load() {
        return new ConfLoader();
    }

    public ConfLoader fromFile(String filepath) {
        sources.add(new FileConf(filepath));
        return this;
    }

    public ConfLoader fromEnv() {
        sources.add(new EnvConf());
        return this;
    }

    public <T> T toClass(Class<T> clazz) {
        try {
            T obj = clazz.getDeclaredConstructor().newInstance();
            toClass(obj);
            return obj;
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void toClass(Object obj) {
        Map<String, Field> fields = Arrays.stream(obj.getClass().getDeclaredFields()).collect(Collectors.toMap(Field::getName, f -> f));

        while (!sources.isEmpty() && !fields.isEmpty()) {
            Map<String, String> loaded = sources.pop().load(fields.keySet());
            loaded.forEach((k, v) -> {
                Object convertedVal = Converter.convert(v, fields.get(k).getType());
                Optional.ofNullable(convertedVal).ifPresent(val -> {
                    try {
                        Field f = obj.getClass().getDeclaredField(k);
                        f.setAccessible(true);
                        f.set(obj, convertedVal);
                        fields.remove(k);
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        //
                    }
                });
            });
        }
    }
}
