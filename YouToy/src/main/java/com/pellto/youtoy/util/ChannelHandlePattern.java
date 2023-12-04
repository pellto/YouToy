package com.pellto.youtoy.util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChannelHandlePattern {
    private static final Pattern HANDLE_PATTERN = Pattern.compile("@(.*?) ");

    public static Pattern getPattern() {
        return HANDLE_PATTERN;
    }

    public static Matcher getMatcher(String content) {
        return HANDLE_PATTERN.matcher(content);
    }

    public static boolean hasPattern(String content) {
        return getMatcher(content).find();
    }

    public static ArrayList<String> extractChannelHandle(String content) {
        var matcher = ChannelHandlePattern.getMatcher(content);
        ArrayList<String> results = new ArrayList<>();
        while (matcher.find()) {
            results.add(matcher.group().strip());
        }
        return results;
    }
}
