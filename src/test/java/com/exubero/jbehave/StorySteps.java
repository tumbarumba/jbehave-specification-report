package com.exubero.jbehave;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Pending;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

public class StorySteps {
    @Given("a system state")
    public void givenASystemState() {
    }

    @When("I do something")
    public void whenIDoSomething() {
    }

    @Then("system is in a different state")
    public void thenSystemIsInADifferentState() {
    }

    @Then("we're not ready to check this yet")
    @Pending
    public void thenWereNotReadyToCheckThisYet() {
    }

    @Then("something went wrong")
    public void thenSomethingWentWrong() {
        Assert.fail("Something went wrong");
    }
}
