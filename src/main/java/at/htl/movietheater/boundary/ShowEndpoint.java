package at.htl.movietheater.boundary;

import javax.json.JsonValue;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

public class ShowEndpoint {

    public Response create(JsonValue json, @Context UriInfo uri) {
        return null;
    }

    @Path("/{id}")
    public Response get(long id) {
        return null;
    }
}
