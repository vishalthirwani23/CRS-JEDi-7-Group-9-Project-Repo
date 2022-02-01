package com.flipkart.restController;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.*;

@Path("rest")
public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {
        // Register all Controller classes Here
        register(AdminRESTAPIController.class);
        register(UserRESTAPIController.class);
        register(StudentRESTAPIController.class);
        register(ProfessorRESTAPIController.class);
    }

}