package com.trevorism.gcloud

import com.trevorism.https.AppClientSecureHttpClient
import com.trevorism.https.SecureHttpClient
import io.cucumber.groovy.EN
import io.cucumber.groovy.Hooks

this.metaClass.mixin(Hooks)
this.metaClass.mixin(EN)

String baseUrl = System.getenv("ACCEPTANCE_BASE_URL") ?: "https://project.trevorism.com"

SecureHttpClient client = new AppClientSecureHttpClient()
String jsonList
String projectJson

When(/the list of google cloud projects is requested/) {  ->
    jsonList = client.get("${baseUrl}/project")
}


Then(/the list of projects is returned/) {  ->
    assert jsonList.contains("trevorism-project")
    assert jsonList.contains("trevorism-auth")
    assert jsonList.contains("trevorism-data")
    assert jsonList.contains("trevorism-action")
}


When(/the service name {string} is requested/) { String string ->
    projectJson = client.get("${baseUrl}/project/service/${string}")
}


Then(/the correct project is returned/) {  ->
    assert projectJson.contains("trevorism-auth")
}