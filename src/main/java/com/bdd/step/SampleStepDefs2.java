package com.bdd.step;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Map;

public class SampleStepDefs2 {

    private int firstNumber;
    private int secondNumber;
    public String option;

    @When("user provides data as")
    public void userProvidesDataAs(DataTable table) {
        Map<String,Integer> data = table.asMap(String.class,Integer.class);
        firstNumber = data.get("first number");
        secondNumber = data.get("second number");
    }

    @And("user provide second number as {int}")
    public void userProvideSecondNumberAs(int arg0) {
        secondNumber = arg0;
    }

    @When("user provide first number as {int}")
    public void userProvideFirstNumberAs(int arg0) {
        firstNumber = arg0;
    }

    @Then("the result will be {int}")
    public void theResultWillBe(int arg0) throws Exception {
        if ("addition".equals(option.toLowerCase())) {
            Assert.assertEquals(firstNumber + secondNumber, arg0);
        } else {
            System.out.println("not compatible option");
            throw new Exception("not compatible option : " + option);
        }
    }

    @Given("user is on {string}")
    public void userIsOnAddition(String option) {
        System.out.println("the user selected "+option);
        this.option = option;
    }



}
