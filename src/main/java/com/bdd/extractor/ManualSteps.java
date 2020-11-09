package com.bdd.extractor;

import com.bdd.annotations.Action;

public class ManualSteps {

    @Action("user opens the url")
    public void urlMethod(){
        System.out.println("url is opened");
    }

    @Action("user clicks on login under homepage")
    public void loginHomePage(){
        System.out.println("user clicked on login in the homepage");
    }

    @Action("user enters username")
    public void enterUsername(){
        System.out.println("entered username details");
    }

    @Action("user enters password")
    public void enterPassword(){
        System.out.println("entered password details");
    }

    @Action("user click on login")
    public void clickOnSignIn(){
        System.out.println("clicked on sign in");
    }

    @Action("user able to login")
    public void success(){
        System.out.println("user able to login");
    }


}
