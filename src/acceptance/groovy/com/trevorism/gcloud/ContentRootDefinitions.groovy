package com.trevorism.gcloud

/**
 * @author tbrooks
 */

this.metaClass.mixin(io.cucumber.groovy.Hooks)
this.metaClass.mixin(io.cucumber.groovy.EN)

Given(~/^the application is alive$/) { ->
    try{
        new URL("https://project.trevorism.com/ping").text
    }
    catch (Exception ignored){
        Thread.sleep(10000)
        new URL("https://project.trevorism.com/ping").text
    }
}

When(~/^I navigate to "([^"]*)"$/) { String url ->
    contextRootContent = new URL(url).text
}

Then(~/^then a link to the help page is displayed$/) { ->
    assert contextRootContent
    assert contextRootContent.contains("/help")
}