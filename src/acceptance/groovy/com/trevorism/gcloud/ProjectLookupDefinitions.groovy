package com.trevorism.gcloud

import com.trevorism.https.AppClientSecureHttpClient
import com.trevorism.https.SecureHttpClient
import io.cucumber.groovy.EN
import io.cucumber.groovy.Hooks

this.metaClass.mixin(Hooks)
this.metaClass.mixin(EN)

SecureHttpClient client = new AppClientSecureHttpClient()
String jsonList
String projectJson

When(/the list of google cloud projects is requested/) {  ->
    jsonList = client.get("https://project.trevorism.com/project")
}


Then(/the list of projects is returned/) {  ->
    println jsonList
    assert jsonList.contains("trevorism-project")
    assert jsonList.contains("trevorism-auth")
    assert jsonList.contains("trevorism-data")
    assert jsonList.contains("trevorism-action")
}


When(/the service name {string} is requested/) { String string ->
    projectJson = client.get("https://project.trevorism.com/project/service/${string}")
}


Then(/the correct project is returned/) {  ->
    println projectJson
    assert projectJson.contains("trevorism-auth")
}