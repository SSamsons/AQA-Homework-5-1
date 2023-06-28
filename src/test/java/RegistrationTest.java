import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class RegistrationTest { ;
    public String setDate(int addDays) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldRegisterByAccountNumberDOMModification() {
        String currentDate = setDate(5);
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Санкт-Петербург");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE));
        $("[data-test-id='date'] input").setValue(currentDate);
        $("[data-test-id='name'] input").setValue("Самсонов Станислав");
        $("[data-test-id='phone'] input").setValue("+79363737373");
        $("[data-test-id='agreement']").click();
        $$("[type=button]").filter(visible).get(1).click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15));
    }
}
