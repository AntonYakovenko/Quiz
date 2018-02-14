package com.company.quiz.filter.security;

public class UrlCodec {
    private UrlCodec() {}

    public static String encode(String original) {
        return original
                .replace("=", "*")
                .replace("?", "!")
                .replace("&", "@");
    }

    public static String decode(String encoded) {
        return encoded
                .replace("*", "=")
                .replace("!", "?")
                .replace("@", "&");
    }
}
