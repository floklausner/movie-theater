package at.htl.movietheater.boundary;

import at.htl.movietheater.control.MovieRepository;
import at.htl.movietheater.control.ShowRepository;
import at.htl.movietheater.control.TheaterRepository;
import at.htl.movietheater.entity.Show;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("/show")
public class ShowEndpoint {

    @Inject
    ShowRepository showRepository;

    @Inject
    MovieRepository movieRepository;

    @Inject
    TheaterRepository theaterRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(JsonValue json, @Context UriInfo uri) {
        Show createdShow = null;

        if (json.getValueType().equals(JsonValue.ValueType.OBJECT)) {
            try {
                createdShow = showRepository.save(createShow(json.asJsonObject()));
            } catch (Exception e) {
                return Response
                        .status(Response.Status.BAD_REQUEST)
                        .build();
            }
        } else if (json.getValueType().equals(JsonValue.ValueType.ARRAY)) {
            try {
                for (JsonValue value : json.asJsonArray()) {
                    createdShow = showRepository.save(createShow(value.asJsonObject()));
                }
            } catch (Exception e) {
                return Response
                        .status(Response.Status.BAD_REQUEST)
                        .build();
            }
        }

        if (createdShow != null) {
            return Response
                    .created(URI.create(uri.getAbsolutePath() + "/" + createdShow.getId()))
                    .build();
        } else {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") long id) {
        Show show = showRepository.findById(id);

        if (show != null) {
            JsonObjectBuilder builder = Json.createObjectBuilder();

            builder
                    .add("id", show.getId())
                    .add("movie", show.getMovie().getTitle())
                    .add("theater", show.getTheater().getName());

            return Response
                    .ok(builder.build())
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

    private Show createShow(JsonObject json) throws Exception {
        try {
            return new Show(
                    movieRepository.findByTitle(json.getString("movie")),
                    theaterRepository.findByName(json.getString("theater")),
                    null,
                    null
            );
        } catch (Exception e) {
            throw new Exception();
        }
    }
}
