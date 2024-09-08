package ru.netology.shop.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private final SelenideElement payButton = $(byText("Купить"));
    private final SelenideElement creditButton = $(byText("Купить в кредит"));

    public PayPage openPayPage() {
        payButton.click();
        return new PayPage();
    }

    public CreditPage openCreditPage() {
        creditButton.click();
        return new CreditPage();
    }
}
