package aggregationserver;

import shares.PagingPayload;
import shares.ResponseBody;
import shares.LamportClock;
import shares.TimestampAndWeather;
import shares.Weather;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/weather")
public class API {
    private final CreateOrUpdateWeatherService createOrUpdateWeatherService;
    private final GetWeatherService getWeatherService;
    private final ListWeatherService listWeatherService;
    private final LamportClock lamportClock;

    public API() {
        this.createOrUpdateWeatherService = new CreateOrUpdateWeatherService();
        this.getWeatherService = new GetWeatherService();
        this.listWeatherService = new ListWeatherService();
        this.lamportClock = LamportClockSingleton.getInstance();
    }

    @PUT
    @Path("/update")
    @Produces("application/json")
    public Response createOrUpdate(TimestampAndWeather timestampAndWeather) throws Exception {
        long timestamp = createOrUpdateWeatherService.createOrUpdate(timestampAndWeather);
        return Response.ok().status(200).type(MediaType.APPLICATION_JSON)
                .entity(new ResponseBody(PagingPayload.empty(), ResponseBody.Status.SUCCESS, ResponseBody.Code.SUCCESS, timestamp)).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/get")
    @Produces("application/json")
    public Response get(@QueryParam("id") String id,
                        @QueryParam("request-time") long requestTime) throws Exception {
        long newTimestamp = lamportClock.tick(requestTime);
        PagingPayload<Weather> pagingPayload = new PagingPayload<>();
        pagingPayload.setData(getWeatherService.getById(id));
        return Response.ok().status(200).type(MediaType.APPLICATION_JSON)
                .entity(new ResponseBody(pagingPayload, ResponseBody.Status.SUCCESS, ResponseBody.Code.SUCCESS, newTimestamp)).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/list")
    @Produces("application/json")
    public Response get(@QueryParam("request-time") long requestTime) throws Exception {
        long newTimestamp = lamportClock.tick(requestTime);
        PagingPayload<List<Weather>> pagingPayload = new PagingPayload<>();
        pagingPayload.setData(listWeatherService.list());
        return Response.ok().status(200).type(MediaType.APPLICATION_JSON)
                .entity(new ResponseBody(pagingPayload, ResponseBody.Status.SUCCESS, ResponseBody.Code.SUCCESS, newTimestamp)).type(MediaType.APPLICATION_JSON).build();
    }
}
