package ru.netology.shop.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    private static final Faker FAKER = new Faker(new Locale("en"));

    private DataHelper() {
    }


    public static CardInfo getFirstCardInfo() {
        return new CardInfo("1111 2222 3333 4444", generateRandomMonth(), generateRandomYear(), generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getSecondCardInfo() {
        return new CardInfo("5555 6666 7777 8888", generateRandomMonth(), generateRandomYear(), generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getWrongCardInfo() {
        return new CardInfo(FAKER.numerify("#### #### #### ####"), generateRandomMonth(), generateRandomYear(), generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getWrongFormatCardInfo() {
        return new CardInfo(FAKER.numerify("#### #### ####"), generateRandomMonth(), generateRandomYear(), generateRandomName(), generateRandomCVC());
    }

    private static String generateRandomMonth() {
        return LocalDate.now().plusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
    }

    private static String generateOneMonthBack() {
        return LocalDate.now().plusMonths(-1).format(DateTimeFormatter.ofPattern("MM"));
    }

    private static String generateRandomYear() {
        return LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    private static String generateNowYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }

    private static String generateRandomCVC() {
        return FAKER.numerify("###");
    }

    private static String generateRandomName() {
        return FAKER.name().fullName();
    }

    @Value
    public static class CardInfo {
        private String card;
        private String month;
        private String year;
        private String name;
        private String cvc;
    }

    public static CardInfo getWrongFormatMonth() {
        return new CardInfo(FAKER.numerify("1111 2222 3333 4444"), "1", generateRandomYear(), generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getMonthZero() {
        return new CardInfo(FAKER.numerify("1111 2222 3333 4444"), "0", generateRandomYear(), generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getMonth12() {
        return new CardInfo(FAKER.numerify("1111 2222 3333 4444"), "12", generateRandomYear(), generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getMonth13() {
        return new CardInfo(FAKER.numerify("1111 2222 3333 4444"), "13", generateRandomYear(), generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getExpiredCardInfo() {
        return new CardInfo("1111 2222 3333 4444", "12", "23", generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getOldCardInfoBackMonth() {
        return new CardInfo("1111 2222 3333 4444", generateOneMonthBack(), generateNowYear(), generateRandomName(), generateRandomCVC());
    }

    public static CardInfo nameRus() {
        return new CardInfo("1111 2222 3333 4444", generateRandomMonth(), generateRandomYear(), "Иван Иванов", generateRandomCVC());
    }

    public static CardInfo nameNumb() {
        return new CardInfo("1111 2222 3333 4444", generateRandomMonth(), generateRandomYear(), "123456", generateRandomCVC());
    }

    public static CardInfo nameSymb() {
        return new CardInfo("1111 2222 3333 4444", generateRandomMonth(), generateRandomYear(), "!\"№;%%:", generateRandomCVC());
    }

    public static CardInfo nameSpace() {
        return new CardInfo("1111 2222 3333 4444", generateRandomMonth(), generateRandomYear(), "      ", generateRandomCVC());
    }

    public static CardInfo spaceField() {
        return new CardInfo("", "", "", "", "");
    }
}
