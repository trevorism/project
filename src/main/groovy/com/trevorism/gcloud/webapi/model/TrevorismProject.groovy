package com.trevorism.gcloud.webapi.model

class TrevorismProject {

    public static TrevorismProject UNKNOWN_PROJECT = new TrevorismProject("UNKNOWN",null)

    String name
    String dns

    TrevorismProject(String name, String dns){
        this.name = name
        this.dns = dns
    }

}
