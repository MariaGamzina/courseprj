package ru.netology.shop.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.shop.data.DataHelper;
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

        var payPage = new PayPage();
        var cardInfo = DataHelper.getFirstCardInfo();
        payPage.makeValidCard(cardInfo);

        $$(".button").last().click();

        $(withText("Операция одобрена")).shouldBe(Condition.visible, Duration.ofSeconds(15));

    }

    @Test
    void successPaySecondCard() {

        var payPage = new PayPage();
        var cardInfo = DataHelper.getSecondCardInfo();
        payPage.makeValidCard(cardInfo);

        $$(".button").last().click();

        $(withText("Операция одобрена")).shouldBe(Condition.visible, Duration.ofSeconds(15));

    }

    @Test
    void PayWrongCard() {

        var payPage = new PayPage();
        var cardInfo = DataHelper.getWrongCardInfo();
        payPage.makeValidCard(cardInfo);

        $$(".button").last().click();

        $(withText("Банк отказал")).shouldBe(Condition.visible, Duration.ofSeconds(15));

    }

    @Test
    void PayWrongFormatCard() {

        var payPage = new PayPage();
        var cardInfo = DataHelper.getWrongFormatCardInfo();
        payPage.makeValidCard(cardInfo);

        $$(".button").last().click();

        $(".input__sub").shouldHave(Condition.text("Неверный формат"));

    }

    @Test
    void PayWrongFormatMonth() {

        var payPage = new PayPage();
        var cardInfo = DataHelper.getWrongFormatMonth();
        payPage.makeValidCard(cardInfo);

        $$(".button").last().click();

        $(".input__sub").shouldHave(Condition.text("Неверный формат"));

    }

    @Test
    void PayMonthZero() {

        var payPage = new PayPage();
        var cardInfo = DataHelper.getMonthZero();
        payPage.makeValidCard(cardInfo);

        $$(".button").last().click();

        $(".input__sub").shouldHave(Condition.text("Неверный формат"));

    }

    @Test
    void PayMonth12() {

        var payPage = new PayPage();
        var cardInfo = DataHelper.getMonth12();
        payPage.makeValidCard(cardInfo);

        $$(".button").last().click();

        $(withText("Операция одобрена")).shouldBe(Condition.visible, Duration.ofSeconds(15));

    }

    @Test
    void PayMonth13() {

        var payPage = new PayPage();
        var cardInfo = DataHelper.getMonth13();
        payPage.makeValidCard(cardInfo);

        $$(".button").last().click();

        $(".input__sub").shouldHave(Condition.text("Неверно указан срок действия карты"));

    }

    @Test
    void PayOldCard() {

        var payPage = new PayPage();
        var cardInfo = DataHelper.getOldCardInfo();
        payPage.makeValidCard(cardInfo);

        $$(".button").last().click();

        $(".input__sub").shouldHave(Condition.text("Истёк срок действия карты"));

    }

    @Test
    void PayOldCardBackMonth() {

        var payPage = new PayPage();
        var cardInfo = DataHelper.getOldCardInfoBackMonth();
        payPage.makeValidCard(cardInfo);

        $$(".button").last().click();

        $(".input__sub").shouldHave(Condition.text("Неверно указан срок действия карты"));

    }

    @Test
    void SetNameRu() {

        var payPage = new PayPage();
        var cardInfo = DataHelper.nameRus();
        payPage.makeValidCard(cardInfo);

        $$(".button").last().click();

        $(".input__sub").shouldHave(Condition.text("Неверный формат"));

    }

    @Test
    void SetNameNumb() {

        var payPage = new PayPage();
        var cardInfo = DataHelper.nameNumb();
        payPage.makeValidCard(cardInfo);

        $$(".button").last().click();

        $(".input__sub").shouldHave(Condition.text("Неверный формат"));

    }

    @Test
    void SetNameSymb() {

        var payPage = new PayPage();
        var cardInfo = DataHelper.nameSymb();
        payPage.makeValidCard(cardInfo);

        $$(".button").last().click();

        $(".input__sub").shouldHave(Condition.text("Неверный формат"));

    }

    @Test
    void SetNameSpace() {

        var payPage = new PayPage();
        var cardInfo = DataHelper.nameSpace();
        payPage.makeValidCard(cardInfo);

        $$(".button").last().click();

        $(".input__sub").shouldHave(Condition.text("Поле обязательно для заполнения"));

    }

    @Test
    void SetSpaceField() {

        var payPage = new PayPage();
        var cardInfo = DataHelper.spaceField();
        payPage.makeValidCard(cardInfo);

        $$(".button").last().click();

        $$(".input__sub").get(0).shouldHave(Condition.text("Неверный формат"));
        $$(".input__sub").get(1).shouldHave(Condition.text("Неверный формат"));
        $$(".input__sub").get(2).shouldHave(Condition.text("Неверный формат"));
        $$(".input__sub").get(3).shouldHave(Condition.text("Поле обязательно для заполнения"));
        $$(".input__sub").get(4).shouldHave(Condition.text("Неверный формат"));


    }
}
