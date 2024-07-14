package com.trevorism.gcloud.webapi.controller

import com.trevorism.http.HttpClient
import org.junit.jupiter.api.Test

class ProjectControllerTest {
    @Test
    void testGetAllProjects() {
        ProjectController pc = new ProjectController()
        assert 11 == pc.getAllProjects().size()
    }

    @Test
    void testGetProjectForService() {
        ProjectController pc = new ProjectController()
        pc.client = [get: {x -> "gcp_project: 'trevorism-testing'"}] as HttpClient
        def project = pc.getProjectForService("endpoint-tester")
        assert project.name == "trevorism-testing"
        assert project.dns == "testing"
    }
}
