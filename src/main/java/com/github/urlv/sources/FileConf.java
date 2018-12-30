package com.github.urlv.sources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class FileConf implements SrcConf {
    private String filepath;

    public FileConf(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public Map<String, String> load(Set<String> keys) {
        Map<String, String> loaded = new HashMap<>();

        try (FileInputStream fileInput = new FileInputStream(filepath)){
            Properties props = new Properties();
            props.load(fileInput);

            Optional.ofNullable(keys).ifPresent(keysCopy -> keys.forEach(k -> Optional.ofNullable(props.getProperty(k)).ifPresent(v -> loaded.put(k, v))));
        } catch (IOException e) {
            //
        }

        return loaded;
    }
}
