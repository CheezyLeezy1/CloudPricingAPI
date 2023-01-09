package com.mycompany.packagepricingsystem;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/test")
public class Test {

    @GET
    @Path("/ping1")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response sayHelloWorld() {
        String output = "<message>Running Successfully</message>";
        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("/ping2")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response saySomethingJson() {
        String output = "{\"message\": \"Running Successfully\"}";
        return Response.status(200).entity(output).type(MediaType.APPLICATION_JSON).build();
    }
}
