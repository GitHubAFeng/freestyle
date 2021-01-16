package com.afeng.web.common.util;

import java.util.HashMap;

@SuppressWarnings("unchecked")
public class MParams extends HashMap<String, Object> {
    public static MParams load() {
        return new MParams();
    }


    @Override
    public MParams put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    @Override
    public MParams remove(Object key) {
        super.remove(key);
        return this;
    }
}
