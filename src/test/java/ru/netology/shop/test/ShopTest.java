package ru.netology.shop.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import ru.netology.shop.data.DataHelper;
import ru.netology.shop.page.PayPage;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class ShopTest {
    @Test
    void successPayFirstCard() {
        open("http://localhost:8080");

        var payPage = new PayPage();
        var cardInfo = DataHelper.getFirstCardInfo();
        payPage.makeValidCard(cardInfo);

        $$(".button").last().click();

        $(withText("Операция одобрена")).shouldBe(Condition.visible, Duration.ofSeconds(15));

    }

    @Test
    void successPaySecondCard() {
        open("http://localhost:8080");

        var payPage = new PayPage();
        var cardInfo = DataHelper.getSecondCardInfo();
        payPage.makeValidCard(cardInfo);

        $$(".button").last().click();

        $(withText("Операция одобрена")).shouldBe(Condition.visible, Duration.ofSeconds(15));

    }

    @Test
    void PayWrongCard() {
        open("http://localhost:8080");

        var payPage = new PayPage();
        var cardInfo = DataHelper.getWrongCardInfo();
        payPage.makeValidCard(cardInfo);

        $$(".button").last().click();

        $(withText("Банк отказал")).shouldBe(Condition.visible, Duration.ofSeconds(15));

    }

    @Test
    void PayWrongFormatCard() {
        open("http://localhost:8080");

        var payPage = new PayPage();
        var cardInfo = DataHelper.getWrongFormatCardInfo();
        payPage.makeValidCard(cardInfo);

        $$(".button").last().click();

        $(".input__sub").shouldHave(Condition.text("Неверный формат"));

    }

    @Test
    void PayWrongFormatMonth() {
        open("http://localhost:8080");

        var payPage = new PayPage();
        var cardInfo = DataHelper.getWrongFormatMonth();
        payPage.makeValidCard(cardInfo);

        $$(".button").last().click();

        $(".input__sub").shouldHave(Condition.text("Неверный формат"));

    }

    @Test
    void PayMonthZero() {
        open("http://localhost:8080");

        var payPage = new PayPage();
        var cardInfo = DataHelper.getMonthZero();
        payPage.makeValidCard(cardInfo);

        $$(".button").last().click();

        $(".input__sub").shouldHave(Condition.text("Неверный формат"));

    }

    @Test
    void PayMonth12() {
        open("http://localhost:8080");

        var payPage = new PayPage();
        var cardInfo = DataHelper.getMonth12();
        payPage.makeValidCard(cardInfo);

        $$(".button").last().click();

        $(withText("Операция одобрена")).shouldBe(Condition.visible, Duration.ofSeconds(15));

    }

    @Test
    void PayMonth13() {
        open("http://localhost:8080");

        var payPage = new PayPage();
        var cardInfo = DataHelper.getMonth13();
        payPage.makeValidCard(cardInfo);

        $$(".button").last().click();

        $(".input__sub").shouldHave(Condition.text("Неверно указан срок действия карты"));

    }

    @Test
    void PayOldCard() {
        open("http://localhost:8080");

        var payPage = new PayPage();
        var cardInfo = DataHelper.getOldCardInfo();
        payPage.makeValidCard(cardInfo);

        $$(".button").last().click();

        $(".input__sub").shouldHave(Condition.text("Истёк срок действия карты"));

    }

    @Test
    void PayOldCardBackMonth() {
        open("http://localhost:8080");

        var payPage = new PayPage();
        var cardInfo = DataHelper.getOldCardInfoBackMonth();
        payPage.makeValidCard(cardInfo);

        $$(".button").last().click();

        $(".input__sub").shouldHave(Condition.text("Неверно указан срок действия карты"));

    }

    @Test
    void SetNameRu() {
        open("http://localhost:8080");

        var payPage = new PayPage();
        var cardInfo = DataHelper.nameRus();
        payPage.makeValidCard(cardInfo);

        $$(".button").last().click();

        $(".input__sub").shouldHave(Condition.text("Неверный формат"));

    }

    @Test
    void SetNameNumb() {
        open("http://localhost:8080");

        var payPage = new PayPage();
        var cardInfo = DataHelper.nameNumb();
        payPage.makeValidCard(cardInfo);

        $$(".button").last().click();

        $(".input__sub").shouldHave(Condition.text("Неверный формат"));

    }

    @Test
    void SetNameSymb() {
        open("http://localhost:8080");

        var payPage = new PayPage();
        var cardInfo = DataHelper.nameSymb();
        payPage.makeValidCard(cardInfo);

        $$(".button").last().click();

        $(".input__sub").shouldHave(Condition.text("Неверный формат"));

    }

    @Test
    void SetNameSpace() {
        open("http://localhost:8080");

        var payPage = new PayPage();
        var cardInfo = DataHelper.nameSpace();
        payPage.makeValidCard(cardInfo);

        $$(".button").last().click();

        $(".input__sub").shouldHave(Condition.text("Поле обязательно для заполнения"));

    }

    @Test
    void SetSpaceField() {
        open("http://localhost:8080");

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
