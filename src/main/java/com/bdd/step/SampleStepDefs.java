package com.bdd.step;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

public class SampleStepDefs {

    @When("user provide data")
    public void addTable(DataTable table){
        Map<String,String> map = table.asMap(String.class,String.class);
        map.forEach((x,y)->{
            System.out.println(x+" : "+y);
        });
    }

    @When("i give {string} as a name")
    public void iGiveAsAName(String arg0) {

    }

    @And("i give {double} as a double")
    public void iGiveAsAFloat(double d) {

    }

    @And("i give {int} as a number")
    public void iGiveAsANumber(int arg0) {

    }

    @And("i given two numbers as {int} and {int}")
    public void iGivenTwoNumbersAsAnd(int arg0, int arg1) {

    }

    @And("i give datatable as")
    public void iGiveDatatableAs(DataTable table) {
        List<Map<String,String>> data = table.asMaps();
        data.forEach((x)->{
            System.out.println(x);
        });
    }
}
