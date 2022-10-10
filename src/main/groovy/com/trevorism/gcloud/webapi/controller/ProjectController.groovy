package com.trevorism.gcloud.webapi.controller

import com.trevorism.gcloud.webapi.model.TrevorismProject
import com.trevorism.http.BlankHttpClient
import com.trevorism.http.HttpClient
import com.trevorism.secure.Roles
import com.trevorism.secure.Secure
import io.swagger.annotations.ApiOperation

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/project")
class ProjectController {

    HttpClient client = new BlankHttpClient()

    @ApiOperation(value = "Get all projects")
    @GET
    @Secure(value = Roles.USER, allowInternal = true)
    @Produces(MediaType.APPLICATION_JSON)
    List<TrevorismProject> getAllProjects() {
        [
                new TrevorismProject("trevorism-gcloud", "datastore"),
                new TrevorismProject("trevorism-eventhub", "event"),
                new TrevorismProject("trevorism-pushbutton", "click"),
                new TrevorismProject("trevorism-lifesafety", "lifesafety"),
                new TrevorismProject("trevorism-predict", "predict"),
                new TrevorismProject("trevorism-auth", "auth"),
                new TrevorismProject("trevorism-testing", "testing"),
                new TrevorismProject("trevorism-action", "action"),
                new TrevorismProject("trevorism-project", "project"),
                new TrevorismProject("trevorism-data", "data"),
                new TrevorismProject("trevorism-draw", "draw"),
                new TrevorismProject("trevorism-trade", "trade"),
                new TrevorismProject("trevorism-memo", "memo"),
                new TrevorismProject("trevorism", "www")
        ]
    }

    @ApiOperation(value = "Get project for a service")
    @GET
    @Secure(value = Roles.USER, allowInternal = true)
    @Path("service/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    TrevorismProject getProjectForService(@PathParam("name") name) {
        String text = client.get("https://raw.githubusercontent.com/trevorism/$name/master/.github/workflows/deploy.yml")

        if (text == "404: Not Found")
            return TrevorismProject.UNKNOWN_PROJECT

        int indexOf = text.indexOf("gcp_project:")
        int firstQuote = text.indexOf("'", indexOf) + 1
        int lastQuote = text.indexOf("'", firstQuote)

        String projectName = text.substring(firstQuote, lastQuote)

        getAllProjects().find {
            it.name == projectName
        }
    }

}
