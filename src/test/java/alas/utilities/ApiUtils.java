package alas.utilities;

import alas.pojos.User;
import com.github.javafaker.Faker;

public class ApiUtils {

    public static User createUser () {

        Faker faker = new Faker();

        User user = new User();

        user.setId(faker.number().numberBetween(10000, 100000));

        user.setName(faker.name().fullName());

        user.setEmail(faker.internet().emailAddress());

        user.setGender(faker.demographic().sex());

        user.setStatus("active");

        return user;

    }


}
