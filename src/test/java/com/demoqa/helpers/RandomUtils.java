package com.demoqa.helpers;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Map;

public class RandomUtils {
    protected Faker fakerRu;
    protected Faker fakerEn;

    private final String[] genders = {"Male", "Female", "Other"};
    private final String[] state = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
    private final Map<String, String[]> citiesByState = Map.of(
            "NCR", new String[]{"Delhi", "Gurgaon", "Noida"},
            "Uttar Pradesh", new String[]{"Agra", "Lucknow", "Merrut"},
            "Haryana", new String[]{"Karnal", "Panipat"},
            "Rajasthan", new String[]{"Jaipur", "Jaiselmer"}
    );

    private final String[] hobbies = {"Sports", "Reading", "Music"};

    private final String[] subjects = {"English", "Chemistry", "Computer Science",
            "Commerce", "Economics", "Social Studies", "Arts", "History",
            "Maths", "Accounting", "Physics", "Biology", "Hindi", "Civics"};

    private final String[] pictures = {"test.jpg", "test_2.jpg", "test_2.jpg"};

    public RandomUtils() {
        this.fakerRu = new Faker(new Locale("ru"));
        this.fakerEn = new Faker(new Locale("en-US"));
    }

    public String getRandomPhoneNumber(int numbersOfDigits) {
        return fakerRu.phoneNumber().subscriberNumber(numbersOfDigits);
    }

    public String getRandomLastName() {
        return fakerRu.name().lastName();
    }

    public String getRandomFirstName() {
        return fakerRu.name().firstName();
    }

    public String getRandomEmail() {
        return fakerEn.internet().emailAddress();
    }

    public String getRandomAddress() {
        return String.format("%s, %s, %s",
                fakerRu.address().country(),
                fakerRu.address().city(),
                fakerRu.address().streetAddress()
        );
    }

    public LocalDate getRandomBirthDay(int minAge, int maxAge) {
        var birthDay = fakerEn.date().birthday(minAge, maxAge);

        return LocalDate.ofInstant(birthDay.toInstant(), ZoneId.systemDefault());
    }

    public String getRandomGender() {
        return fakerRu.options().option(genders);
    }

    public String getRandomHobby() {
        return fakerRu.options().option(hobbies);
    }

    public String getRandomSubjects() {
        return fakerRu.options().option(subjects);
    }

    public String getRandomPicture() {
        return fakerRu.options().option(pictures);
    }

    public String getRandomState() {
        return fakerRu.options().option(state);
    }

    public String getRandomCity(String state) {
        String[] cities = citiesByState.get(state);
        return fakerRu.options().option(cities);
    }
}
