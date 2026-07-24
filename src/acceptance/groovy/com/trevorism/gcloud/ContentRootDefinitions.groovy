package com.trevorism.gcloud

/**
 * @author tbrooks
 */

this.metaClass.mixin(io.cucumber.groovy.Hooks)
this.metaClass.mixin(io.cucumber.groovy.EN)

String baseUrl = System.getenv("ACCEPTANCE_BASE_URL") ?: "https://project.trevorism.com"

Given(~/^the application is alive$/) { ->
    try{
        new URL("${baseUrl}/ping").text
    }
    catch (Exception ignored){
        Thread.sleep(10000)
        new URL("${baseUrl}/ping").text
    }
}

When(~/^I navigate to "([^"]*)"$/) { String url ->
    contextRootContent = new URL(baseUrl).text
}

Then(~/^then a link to the help page is displayed$/) { ->
    assert contextRootContent
    assert contextRootContent.contains("/help")
}