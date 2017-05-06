package fif.rs.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

/**
 * RESTful Root Resource Class for the Fuzzy Information Filtering System FIF.
 * 
 * @author Mariagiovanna Di Venosa
 * @version 1.0, release 06 April 2017
 */
@Path("/service")
public class FifService {
	
	/**
	 * Checks the service availability after a GET request from the client.
	 * 
	 * @return a string explaining service state
	 */
	@GET
	@Path("/verify")
	@Produces(MediaType.TEXT_PLAIN)
	public Response checkService(){
		String result = "Service currently available...";
		return Response.status(200).entity(result).build();
	}
	
	/**
	 * Handles a POST request from the client for filtering a resource with a
	 * designed filter in input.
	 * 
	 * Preconditions: The Request rj has to be formally correct in every part.
	 * 
	 * @param rj the request indicating resource and filter
	 * @return a JSON response indicating the matching value
	 * @throws Exception
	 */
	@POST
	@Path("/filter")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response filteringRequest(RequestJ rj) throws Exception{
		
		double matching = -1;
		
		matching = FifCoreIntegrationUtil.doFilter(rj);
		
		String reqId = rj.getRequestId();
		String filterId = rj.getFilterId();
		int status;

		if (matching != -1){
			status = 200;
		} else {
			status = 400;
		}
		
		//setting server response
		JSONObject jo = new JSONObject();
		
		jo.put("request_id", reqId);
		jo.put("filter_id", filterId);
		jo.put("state", status);
		jo.put("matching", matching);
		
		String result = "Risposta: " + jo;
		
		return Response.status(status).entity(result).build();
	}
}
