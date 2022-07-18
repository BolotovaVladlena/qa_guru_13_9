package github;
import com.codeborne.selenide.*;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeAll;

public class SelenideRepositoryTest {
    @BeforeAll
    static void beforeAllBase() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "1080x720";}

    @Test
    void shouldFindSelenideAsFirstRepository() throws InterruptedException {
        //открыть страницу Selenide в Github
        open("https://github.com/selenide");
        //в строку поиска вбить Selenide
        $("[data-test-selector=nav-search-input]").setValue("selenide").pressEnter();
        Thread.sleep(1000);
        //Перейти в раздел Wiki проекта
        $("nav.menu a.menu-item[href*=wikis]").click();
        Thread.sleep(1000);
        //Найти и открыть страницу SoftAssertions
        $("a[title=SoftAssertions]").click();
        //Проверить что это верная страница, содержащая текст  JUnit5

    }

    @Test
    @DisplayName("Раздел JUnit5 присутствует в SoftAssertions на GitHub")
    public void wikiSearch() {
        Selenide.open("/selenide/selenide");
        $("#wiki-tab").click();
        $(".wiki-pages-box").click();
        $(".wiki-more-pages-link button").click();
        $$("#wiki-pages-box .flex-1").filterBy(text("SoftAssertions")).first()
                .shouldBe(visible).click();
        $(".markdown-body").shouldHave(text("JUnit5"));

    }

}
