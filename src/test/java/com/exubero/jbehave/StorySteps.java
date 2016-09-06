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

    @Given("system is $state")
    public void givenSystemIsState(String state){
    }

    @When("I perform $action")
    public void whenIPerformAction(String action) {
    }

    @When("I do something")
    public void whenIDoSmething() {
    }

    @When("I do something pending")
    @Pending
    public void whenIDoSomethingPending() {
    }

    @Then("system is in a different state")
    public void thenSystemIsInADifferentState() {
    }

    @Then("system should be $expectec")
    public void thenSystemShouldBe(String expected) {
        if(expected.contains("falling")) {
            Assert.fail(expected);
        }
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
