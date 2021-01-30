package me.oktop.javastudy.week11;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

public class EnumPractice {

    private void printLanguageEnum(Language language) {
        switch (language) {
            case JAVASCRIPT:
                System.out.println(language.getDescription());
                break;
            case JAVA:
                System.out.println(language.getDescription());
                break;
            case KOTLIN:
                System.out.println(language.getDescription());
                break;
        }
    }

    public static void main(String[] args) {
//        EnumPractice enumPractice = new EnumPractice();
//        enumPractice.printLanguageEnum(Language.JAVA);
//        System.out.println("JAVA");
//        System.out.println(Language.JAVA);
//        System.out.println("JAVA".equals(Language.JAVA));
//
//        Arrays.stream(Language.values())
//                .forEach(o -> System.out.println(o.getDescription()));
//
//        System.out.println(Language.valueOf("JAVA"));
//        System.out.println(Language.JAVA.name());
//        System.out.println(Language.JAVA.ordinal());
//
//        Set<Language> languages = EnumSet.allOf(Language.class);
//        languages.forEach(System.out::println);
//        System.out.println("###############################");
//
//        Language language = Language.JAVA;
//        System.out.println("실행결과");
//        language.print();
//
        EnumSet<Language> set1, set2, set3, set4, set5;

        set1 = EnumSet.allOf(Language.class);
        set2 = EnumSet.of(Language.JAVA);
        set3 = EnumSet.complementOf(set2);
        set4 = EnumSet.range(Language.JAVASCRIPT, Language.KOTLIN);
        set5 = EnumSet.noneOf(Language.class);
        set5.add(Language.KOTLIN);
        set5.add(Language.JAVASCRIPT);
        set5.remove(Language.JAVASCRIPT);

        System.out.println("set1 = " + set1);
        System.out.println("set2 = " + set2);
        System.out.println("set3 = " + set3);
        System.out.println("set4 = " + set4);
        System.out.println("set5 = " + set5);
        System.out.println(set5.contains(Language.JAVASCRIPT));

        Set<Language> allLanguageSet = Arrays.stream(Language.values()).collect(Collectors.toSet());
        Set<Language> enumSetLanguage = EnumSet.allOf(Language.class);

    }
}
