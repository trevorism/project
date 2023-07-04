package com.trevorism.gcloud.webapi.controller

import com.trevorism.gcloud.webapi.model.TrevorismProject
import com.trevorism.http.BlankHttpClient
import com.trevorism.http.HttpClient
import com.trevorism.http.util.InvalidRequestException
import com.trevorism.secure.Roles
import com.trevorism.secure.Secure
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.Logger
import org.slf4j.LoggerFactory


@Controller("/project")
class ProjectController {

    private static final Logger log = LoggerFactory.getLogger(ProjectController)
    HttpClient client = new BlankHttpClient()

    @Tag(name = "Project Operations")
    @Operation(summary = "Get all projects **Secure")
    @Get(produces = MediaType.APPLICATION_JSON)
    @Secure(value = Roles.USER, allowInternal = true)
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

    @Tag(name = "Project Operations")
    @Operation(summary = "Get project for a service **Secure")
    @Get(value = "/service/{name}", produces = MediaType.APPLICATION_JSON)
    @Secure(value = Roles.USER, allowInternal = true)
    TrevorismProject getProjectForService(String name) {
        try{
            String text = client.get("https://raw.githubusercontent.com/trevorism/$name/master/.github/workflows/deploy.yml")

            int indexOf = text.indexOf("gcp_project:")
            int firstQuote = text.indexOf("'", indexOf) + 1
            int lastQuote = text.indexOf("'", firstQuote)

            String projectName = text.substring(firstQuote, lastQuote)

            getAllProjects().find {
                it.name == projectName
            }
        }catch(InvalidRequestException e){
            log.warn("Could not find project for service $name", e)
            return TrevorismProject.UNKNOWN_PROJECT
        }

    }

}
