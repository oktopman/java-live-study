package me.oktop.javastudy.week11;

public enum Language {
    JAVA("java") {
        @Override
        void print() {
            System.out.println("java 입니다.");
        }
    },
    JAVASCRIPT("javascript") {
        @Override
        void print() {
            System.out.println("javascript 입니다.");
        }
    },
    KOTLIN("kotlin") {
        @Override
        void print() {
            System.out.println("kotlin 입니다.");
        }
    };

    private String description;

    Language(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    abstract void print();
}
