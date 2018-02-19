package com.kiselev.matchmaker.api.network.vk.utils;

import java.util.List;
import java.util.stream.Collectors;

public class VKUtils {

    public static void timeout() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static List<String> toStringList(List<Integer> integers) {
        return integers.stream()
                .map(Object::toString)
                .collect(Collectors.toList());
    }
}
