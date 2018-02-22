package com.kiselev.matchmaker.api.network.vk.converter;

import com.google.common.collect.Maps;

import java.util.Map;

public class VKPropertyMapper {

    private static final Map<String, String> smokingAndAlcohol = Maps.newHashMap();
    private static final Map<String, String> politicalViews = Maps.newHashMap();
    private static final Map<String, String> importantInOthers = Maps.newHashMap();
    private static final Map<String, String> personalPriority = Maps.newHashMap();
    private static final Map<String, String> relation = Maps.newHashMap();

    static {
        smokingAndAlcohol.put("1", "Very negative");
        smokingAndAlcohol.put("2", "Negative");
        smokingAndAlcohol.put("3", "Compromisable");
        smokingAndAlcohol.put("4", "Neutral");
        smokingAndAlcohol.put("5", "Positive");
        smokingAndAlcohol.put("", "");
    }

    static {
        politicalViews.put("1", "Communist");
        politicalViews.put("2", "Socialist");
        politicalViews.put("3", "Moderate");
        politicalViews.put("4", "Liberal");
        politicalViews.put("5", "Conservative");
        politicalViews.put("6", "Monarchist");
        politicalViews.put("7", "Ultraconservative");
        politicalViews.put("8", "Apathetic");
        politicalViews.put("9", "Libertarian");
        politicalViews.put("", "");
    }

    static {
        importantInOthers.put("1", "Intellect and creativity");
        importantInOthers.put("2", "Kindness and honesty");
        importantInOthers.put("3", "Health and beauty");
        importantInOthers.put("4", "Wealth and power");
        importantInOthers.put("5", "Courage and persistence");
        importantInOthers.put("6", "Humor and love for life");
        importantInOthers.put("", "");
    }

    static {
        personalPriority.put("1", "Family and children");
        personalPriority.put("2", "Career and money");
        personalPriority.put("3", "Entertainment and leisure");
        personalPriority.put("4", "Science and research");
        personalPriority.put("5", "Improving the world");
        personalPriority.put("6", "Personal development");
        personalPriority.put("7", "Beauty and art");
        personalPriority.put("8", "Fame and influence");
        personalPriority.put("", "");
    }

    static {
        // TODO: Missed status: "In a civil union"
        relation.put("1", "Single");
        relation.put("2", "In a relationship");
        relation.put("3", "Engaged");
        relation.put("4", "Married");
        relation.put("5", "It's complicated");
        relation.put("6", "Actively searching");
        relation.put("7", "In love");
        relation.put("", "");
    }

    public static String getSmokingAndAlcohol(String key) {
        return smokingAndAlcohol.get(key);
    }

    public static String getPoliticalViews(String key) {
        return politicalViews.get(key);
    }

    public static String getImportantInOthers(String key) {
        return importantInOthers.get(key);
    }

    public static String getPersonalPriority(String key) {
        return personalPriority.get(key);
    }

    public static String getRelation(String key) {
        return relation.get(key);
    }
}
