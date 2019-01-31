package com.github.infovip.util;

import java.io.IOException;
import java.io.InputStream;

public class ResourceLoader {

    public static InputStream load(String path) throws IOException {
        return ResourceLoader.class.getClassLoader().getResourceAsStream(path);
    }

}
