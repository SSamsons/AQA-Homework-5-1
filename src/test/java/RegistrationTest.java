import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTest {


    @Test
    public void shouldTestingTheQuestionnaireForOrderingMeeting() {
        var validationTest = GenerateClient.Registration.generateUser("ru");
        var daysAddFirst = 5;
        var firstDate = GenerateClient.setDate(daysAddFirst);
        var daysAddSecond = 6;
        var secondDate = GenerateClient.setDate(daysAddSecond);
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue(validationTest.getCity()).sendKeys(Keys.chord(Keys.TAB));
        $("[data-test-id='date'] input").sendKeys( Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(firstDate);
        $("[data-test-id='name'] input").setValue(validationTest.getName());
        $("[data-test-id='phone'] input").setValue(validationTest.getPhoneNumber());
        $("[data-test-id='agreement']").click();
        $$("[type=button]").filter(Condition.visible).get(1).click();
        $("[data-test-id=success-notification]").shouldBe(visible, Duration.ofSeconds(5));
        $(".notification__content").shouldBe(text("Встреча успешно запланирована на " + firstDate))
                .shouldBe(visible);

        $("[data-test-id='date'] input").doubleClick().sendKeys( Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(secondDate);
        $$("[type=button]").filter(Condition.visible).get(1).click();
        $(withText("Необходимо подтверждение")).shouldBe(Condition.visible, Duration.ofSeconds(5));
        $(withText("У вас уже запланирована встреча на другую дату. Перепланировать?"))
                .shouldBe(Condition.visible, Duration.ofSeconds(5));;
        $("[data-test-id= replan-notification] button").click();
        $("[data-test-id=success-notification]").shouldBe(visible, Duration.ofSeconds(5));
        $(".notification__content").shouldBe(text("Встреча успешно запланирована на " + secondDate))
                .shouldBe(visible);
    }
}