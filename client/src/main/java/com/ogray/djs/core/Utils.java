package com.ogray.djs.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {
    public static byte[] loadFile(String inputFile) throws IOException {
        return Files.readAllBytes(Paths.get(inputFile));
    }
}
