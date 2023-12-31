package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
        private DataGenerator() {
        }

        public static String generateDate(int addDays) {
            String date = LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            return date;
        }

        public static String generateCity(String locale) {
            Faker faker = new Faker(new Locale("ru"));
            String city = faker.address().city();
            return city;
        }

        public static String generateName(String locale) {
            Faker faker = new Faker(new Locale("ru"));
            String name = faker.name().fullName();
            return name;
        }

        public static String generatePhone(String locale) {
            Faker faker = new Faker(new Locale(locale));
            String phoneNumber = faker.phoneNumber().phoneNumber();
            return phoneNumber;
        }

        public static class Registration {
            private Registration() {
            }

            public static UserInfo generateUser(String locale) {
                return new UserInfo(generateCity(locale), generateName(locale), generatePhone(locale));
            }
        }

        @Value
        public static class UserInfo {
            String city;
            String name;
            String phoneNumber;
        }
    }
