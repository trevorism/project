package com.trevorism.gcloud.webapi.model

import groovy.transform.Canonical

@Canonical
class TrevorismProject {

    public static TrevorismProject UNKNOWN_PROJECT = new TrevorismProject("UNKNOWN",null)

    String name
    String dns
}
