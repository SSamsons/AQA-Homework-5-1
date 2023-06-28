import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class GenerateClient {
    private GenerateClient() {
    }

        public static String setDate ( int addDays){
            String date = LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            return date;
        }

        public static String getCity (String locale){
            Faker faker = new Faker(new Locale("ru"));
            String city = faker.address().city();
            return city;
        }

        public static String getName (String locale){
            Faker faker = new Faker(new Locale("ru"));
            String name = faker.name().fullName();
            return name;
        }

        public static String getPhoneNumber (String locale){
            Faker faker = new Faker(new Locale(locale));
            String phoneNumber = faker.phoneNumber().phoneNumber();
            return phoneNumber;
        }

        public static class Registration {
            private Registration() {
            }

            public static UserInfo generateUser(String locale) {
                return new UserInfo(getCity(locale), getName(locale), getPhoneNumber(locale));
            }
        }

        @Value
        public static class UserInfo {
        String city;
        String name;
        String phoneNumber;
    }
}
