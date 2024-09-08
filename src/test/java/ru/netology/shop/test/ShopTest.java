package ru.netology.shop.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.shop.data.DataHelper;
import ru.netology.shop.data.SqlHelper;
import ru.netology.shop.page.MainPage;
import ru.netology.shop.page.PayPage;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class ShopTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDown() {
        SelenideLogger.removeListener("allure");
    }


    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }

    @Test
    void successPayFirstCard() {

        var mainPage = new MainPage();
        var payPage = mainPage.openPayPage();
        var cardInfo = DataHelper.getFirstCardInfo();
        payPage.makeValidCard(cardInfo);
        payPage.successNotification();
        Assertions.assertEquals("APPROVED", SqlHelper.getStatus());


    }

    @Test
    void successPaySecondCard() {

        var mainPage = new MainPage();
        var payPage = mainPage.openPayPage();
        var cardInfo = DataHelper.getSecondCardInfo();
        payPage.makeValidCard(cardInfo);
        payPage.successNotification();
        Assertions.assertEquals("APPROVED", SqlHelper.getStatus());

    }

    @Test
    void payWrongCard() {

        var mainPage = new MainPage();
        var payPage = mainPage.openPayPage();
        var cardInfo = DataHelper.getWrongCardInfo();
        payPage.makeValidCard(cardInfo);
        payPage.failNotification();
        Assertions.assertEquals("DECLINED", SqlHelper.getStatus());
    }

    @Test
    void payWrongFormatCard() {

        var mainPage = new MainPage();
        var payPage = mainPage.openPayPage();
        var cardInfo = DataHelper.getWrongFormatCardInfo();
        payPage.makeValidCard(cardInfo);
        payPage.errorNotification("Неверный формат");


    }

    @Test
    void payWrongFormatMonth() {

        var mainPage = new MainPage();
        var payPage = mainPage.openPayPage();
        var cardInfo = DataHelper.getWrongFormatMonth();
        payPage.makeValidCard(cardInfo);
        payPage.errorNotification("Неверный формат");

    }

    @Test
    void payMonthZero() {

        var mainPage = new MainPage();
        var payPage = mainPage.openPayPage();
        var cardInfo = DataHelper.getMonthZero();
        payPage.makeValidCard(cardInfo);
        payPage.errorNotification("Неверный формат");
    }

    @Test
    void payMonth12() {

        var mainPage = new MainPage();
        var payPage = mainPage.openPayPage();
        var cardInfo = DataHelper.getMonth12();
        payPage.makeValidCard(cardInfo);
        payPage.successNotification();
        Assertions.assertEquals("APPROVED", SqlHelper.getStatus());
    }

    @Test
    void payMonth13() {

        var mainPage = new MainPage();
        var payPage = mainPage.openPayPage();
        var cardInfo = DataHelper.getMonth13();
        payPage.makeValidCard(cardInfo);
        payPage.errorNotification("Неверно указан срок действия карты");

    }

    @Test
    void payOldCard() {

        var mainPage = new MainPage();
        var payPage = mainPage.openPayPage();
        var cardInfo = DataHelper.getExpiredCardInfo();
        payPage.makeValidCard(cardInfo);
        payPage.errorNotification("Истёк срок действия карты");

    }

    @Test
    void payOldCardBackMonth() {

        var mainPage = new MainPage();
        var payPage = mainPage.openPayPage();
        var cardInfo = DataHelper.getOldCardInfoBackMonth();
        payPage.makeValidCard(cardInfo);
        payPage.errorNotification("Неверно указан срок действия карты");

    }

    @Test
    void setNameRu() {

        var mainPage = new MainPage();
        var payPage = mainPage.openPayPage();
        var cardInfo = DataHelper.nameRus();
        payPage.makeValidCard(cardInfo);
        payPage.errorNotification("Неверный формат");

    }

    @Test
    void setNameNumb() {

        var mainPage = new MainPage();
        var payPage = mainPage.openPayPage();
        var cardInfo = DataHelper.nameNumb();
        payPage.makeValidCard(cardInfo);
        payPage.errorNotification("Неверный формат");

    }

    @Test
    void setNameSymb() {

        var mainPage = new MainPage();
        var payPage = mainPage.openPayPage();
        var cardInfo = DataHelper.nameSymb();
        payPage.makeValidCard(cardInfo);
        payPage.errorNotification("Неверный формат");

    }

    @Test
    void setNameSpace() {

        var mainPage = new MainPage();
        var payPage = mainPage.openPayPage();
        var cardInfo = DataHelper.nameSpace();
        payPage.makeValidCard(cardInfo);
        payPage.errorNotification("Поле обязательно для заполнения");

    }

    @Test
    void setSpaceField() {

        var mainPage = new MainPage();
        var payPage = mainPage.openPayPage();
        var cardInfo = DataHelper.spaceField();
        payPage.makeValidCard(cardInfo);
        payPage.errorAllNotification();

    }
}
