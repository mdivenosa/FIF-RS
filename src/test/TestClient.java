package test;

import java.util.HashMap;
import java.util.Hashtable;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.junit.Assert;

import fif.rs.service.RequestJ;

public class TestClient {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ClientConfig clientConfig = new ClientConfig();
		javax.ws.rs.client.Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target("http://localhost:8080/FIF-RESTService/fif");
		WebTarget resourceWebTarget = webTarget.path("service");
		WebTarget filterWebTarget = resourceWebTarget.path("filter");
		WebTarget verifyWebTarget = resourceWebTarget.path("verify");
		
		Response response = verifyWebTarget.request(MediaType.TEXT_PLAIN).get();
		Assert.assertEquals(200, response.getStatus());
		Assert.assertEquals("Service currently available...",
							response.readEntity(String.class));
		
		//setting resource
		HashMap<String, Hashtable<String, Double>> resource = new HashMap<String, Hashtable<String, Double>>();
		String att = "genere";
		Hashtable<String, Double> ht = new Hashtable<String, Double>();
		ht.put("horror", 0.7);
		ht.put("thriller", 0.5);
		ht.put("romanzo", 1.0);
		ht.put("dark romance", 0.2);
		resource.put(att, ht);
		
		att = "editore";
		ht = new Hashtable<String, Double>();
		ht.put("TEA", 1.0);
		resource.put(att, ht);
		
		att = "anno";
		ht = new Hashtable<String, Double>();
		ht.put("1980", 0.2);
		ht.put("1981", 0.2);
		ht.put("1982", 0.3);
		ht.put("1983", 0.4);
		ht.put("1984", 0.6);
		ht.put("1985", 0.6);
		ht.put("1986", 0.7);
		ht.put("1987", 0.8);
		ht.put("1988", 0.8);
		ht.put("1989", 0.9);					
		ht.put("1990", 1.0);			
		ht.put("1991", 0.9);
		ht.put("1992", 0.8);
		ht.put("1993", 0.8);
		ht.put("1994", 0.7);
		ht.put("1995", 0.6);
		ht.put("1996", 0.6);
		ht.put("1997", 0.5);
		ht.put("1998", 0.3);
		ht.put("1999", 0.2);					
		ht.put("2000", 0.2);
		resource.put(att, ht);
		
		att = "versione";
		ht = new Hashtable<String, Double>();
		ht.put("brossura", 1.0);
		resource.put(att, ht);
				
		//Richiesta RequestJ
		RequestJ rj = new RequestJ();
		
		rj.setFilterId("3");//filtro non presente
		//rj.setFilterId("filtro1");//filtro presente
		//rj.setFilterId(null);//id filtro null
		
		rj.setRequestId("request1");
		//rj.setRequestId("");//id richiesta stringa vuota
		//rj.setRequestId(null);//id richiesta null
		
		rj.setRequestType("filtering");
		//rj.setRequestType("");//tipo richiesta stringa vuota
		//rj.setRequestType(null);//tipo richiesta null
		
		rj.setResourceId("L_ora_delle_streghe");//id risorsa corretto
		//rj.setResourceId("");//id risorsa stringa vuota
		//rj.setResourceId("ciao ciao");//id risorsa formalmente non corretto
		//rj.setResourceId(null);//id risorsa null
		
		//rj.setResource(resource); //risorsa corretta
		//rj.setResource(new HashMap<String, Hashtable<String, Double>>());//risorsa vuota
		//rj.setResource(null);//risorsa null

		System.out.println(rj);
		
		response = filterWebTarget.request().post(Entity.entity(rj, MediaType.APPLICATION_JSON));
		System.out.println("Status: " + response.getStatus());
		System.out.println(response.readEntity(String.class));
	}

}
