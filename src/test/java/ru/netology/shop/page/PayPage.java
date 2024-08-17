package ru.netology.shop.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.shop.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PayPage {

    private final SelenideElement payButton = $(byText("Купить"));
//    private final SelenideElement continueButton = $(byText("Продолжить"));
//    private final SelenideElement successMessage = $(byText("Успешно \nОперация одобрена Банком."));
//    private final SelenideElement errorMessage = $(byText("Ошибка \nОшибка! Банк отказал в проведении операции."));
    private final SelenideElement cardField = $$(".input__control").get(0);
    private final SelenideElement monthField = $$(".input__control").get(1);
    private final SelenideElement yearField = $$(".input__control").get(2);
    private final SelenideElement nameField = $$(".input__control").get(3);
    private final SelenideElement cvcField = $$(".input__control").get(4);



    public void makeValidCard(DataHelper.CardInfo cardInfo) {
        payButton.click();
        cardField.setValue(cardInfo.getCard());
        monthField.setValue(cardInfo.getMonth());
        yearField.setValue(cardInfo.getYear());
        nameField.setValue(cardInfo.getName());
        cvcField.setValue(cardInfo.getCvc());
    }


}
