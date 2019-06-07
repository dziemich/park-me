package com.github.adminfaces.showcase.pages.components;

import com.github.adminfaces.showcase.pages.BasePage;
import org.jboss.arquillian.graphene.findby.FindByJQuery;
import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.jboss.arquillian.graphene.Graphene.waitGui;

/**
 * Created by rafael-pestano on 16/01/17.
 */
@Location("pages/components/chips.xhtml")
public class ChipsPage extends BasePage {

    @FindByJQuery("input[id$=chips_input]")
    protected WebElement defaultChipsInput;

    @FindByJQuery("input[id$=danger_input]")
    protected WebElement dangerChipsInput;

    @FindByJQuery("input[id$=warn_input]")
    protected WebElement warnChipsInput;

    @FindByJQuery("input[id$=success_input]")
    protected WebElement successChipsInput;

    @FindByJQuery("input[id$=info_input]")
    protected WebElement infoChipsInput;

    @FindByJQuery("input[id$=fatal_input]")
    protected WebElement fatalChipsInput;

    @FindByJQuery("input[id$=color_input]")
    protected WebElement noColorChipsInput;

    @FindByJQuery("input[id$=custom_input]")
    protected WebElement customChipsInput;


    public void addDefaultChips() {
        addChip(defaultChipsInput, "tag1");
        addChip(defaultChipsInput,"tag2");
        chipAssertion( 2);
    }

    public void addDangerChips() {
        addChip(dangerChipsInput, "tag1");
        addChip(dangerChipsInput, "tag2");
        chipAssertion( 2);
    }

    public void addWarnChips() {
        addChip(warnChipsInput, "tag1");
        addChip(warnChipsInput, "tag2");
        chipAssertion( 2);
    }

    public void addSuccessChips() {
        addChip(successChipsInput, "tag1");
        addChip(successChipsInput, "tag2");
        chipAssertion( 2);
    }

    public void addInfoChips() {
        addChip(infoChipsInput, "tag1");
        addChip(infoChipsInput, "tag2");
        chipAssertion( 2);
    }

    public void addFatalChips() {
        addChip(fatalChipsInput, "tag1");
        addChip(fatalChipsInput, "tag2");
        chipAssertion(2);
    }

    public void addNoColorChips() {
        addChip(noColorChipsInput, "tag1");
        addChip(noColorChipsInput, "tag2");
        chipAssertion( 2);
    }

    public void addCustomChips() {
        addChip(customChipsInput, "tag1");
        addChip(customChipsInput, "tag2");
        chipAssertion( 2);
    }

    private void addChip(WebElement element, String value) {
        element.sendKeys(value);
        element.sendKeys(Keys.RETURN);
    }

    private void chipAssertion(int numChips) {
        List<WebElement> elements = browser.findElements(By.cssSelector(".ui-chips-token-label"));
        assertThat(elements).size().isEqualTo(numChips);
        for (int i = 0; i < numChips; i++) {
            WebElement element = browser.findElement(By.cssSelector(".ui-chips-token-icon"));
            element.click();
            waitGui().until().element(By.xpath("//span[@class='ui-chips-token-label'][text()='tag" +
                    (i+1) +"']")).is().not().present();
        }
        elements = browser.findElements(By.cssSelector(".ui-chips-token-label"));
        assertThat(elements).size().isEqualTo(0);
    }
}
