package ru.netology.delivery.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static ru.netology.delivery.data.DataGenerator.*;

public class DeliveryTest {
    @Test
    public void shouldTestingTheQuestionnaireForOrderingMeeting() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue(generateCity("ru")).sendKeys(Keys.chord(Keys.TAB));
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(firstMeetingDate);
        $("[data-test-id='name'] input").setValue(generateName("ru"));
        $("[data-test-id='phone'] input").setValue(generatePhone("ru"));
        $("[data-test-id='agreement']").click();
        $$("[type=button]").filter(Condition.visible).get(1).click();
        $("[data-test-id=success-notification]").shouldBe(visible, Duration.ofSeconds(5));
        $(".notification__content").shouldBe(text("Встреча успешно запланирована на " + firstMeetingDate))
                .shouldBe(visible);

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(secondMeetingDate);
        $$("[type=button]").filter(Condition.visible).get(1).click();
        $(withText("Необходимо подтверждение")).shouldBe(Condition.visible, Duration.ofSeconds(5));
        $(withText("У вас уже запланирована встреча на другую дату. Перепланировать?"))
                .shouldBe(Condition.visible, Duration.ofSeconds(5));
        ;
        $("[data-test-id= replan-notification] button").click();
        $("[data-test-id=success-notification]").shouldBe(visible, Duration.ofSeconds(5));
        $(".notification__content").shouldBe(text("Встреча успешно запланирована на " + secondMeetingDate))
                .shouldBe(visible);
    }
}
