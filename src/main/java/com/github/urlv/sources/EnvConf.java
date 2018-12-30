package com.github.urlv.sources;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class EnvConf implements SrcConf {
    @Override
    public Map<String, String> load(Set<String> keys) {
        Map<String, String> loaded = new HashMap<>();

        Optional.ofNullable(keys).ifPresent(keysCopy -> keys.forEach(k -> Optional.ofNullable(System.getenv(k)).ifPresent(v -> loaded.put(k, v))));

        return loaded;
    }
}
