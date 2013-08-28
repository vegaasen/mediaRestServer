package com.vegaasen.fun.mediaserver.rest.controller;

import com.vegaasen.fun.mediaserver.rest.abs.AbstractRestController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * @author <a href="vegard.aasen@gmail.com">vegardaasen</a>
 */
@Path("/")
public final class DefaultRestController extends AbstractRestController {

    @GET
    public Response def() {
        return Response.ok().entity("default").build();
    }
}
