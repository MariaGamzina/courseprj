package ru.netology.shop.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.shop.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PayPage {


    private final SelenideElement cardField = $$(".input__control").get(0);
    private final SelenideElement monthField = $$(".input__control").get(1);
    private final SelenideElement yearField = $$(".input__control").get(2);
    private final SelenideElement nameField = $$(".input__control").get(3);
    private final SelenideElement cvcField = $$(".input__control").get(4);
    private final SelenideElement successNotification = $(withText("Операция одобрена"));
    private final SelenideElement failNotification = $(withText("Банк отказал"));
    private final SelenideElement errorNotification = $(".input__sub");


    public void makeValidCard(DataHelper.CardInfo cardInfo) {

        cardField.setValue(cardInfo.getCard());
        monthField.setValue(cardInfo.getMonth());
        yearField.setValue(cardInfo.getYear());
        nameField.setValue(cardInfo.getName());
        cvcField.setValue(cardInfo.getCvc());
        $$(".button").last().click();
    }

    public void successNotification() {
        successNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }


    public void failNotification() {
        failNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void errorNotification(String expectedText) {
        errorNotification.shouldHave(text(expectedText)).shouldBe(visible);
    }

    public void errorAllNotification() {
        $$(".input__sub").get(0).shouldHave(Condition.text("Неверный формат"));
        $$(".input__sub").get(1).shouldHave(Condition.text("Неверный формат"));
        $$(".input__sub").get(2).shouldHave(Condition.text("Неверный формат"));
        $$(".input__sub").get(3).shouldHave(Condition.text("Поле обязательно для заполнения"));
        $$(".input__sub").get(4).shouldHave(Condition.text("Неверный формат"));
    }
}
