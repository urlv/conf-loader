package com.github.urlv.sources;

import java.util.Map;
import java.util.Set;

public interface SrcConf {
    Map<String, String> load(Set<String> keys);
}
