package fif.rs.service;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RequestJ {
	@XmlElement
	public String request_type;
	@XmlElement
	public String request_id;
	@XmlElement
	public String filter_id;
	@XmlElement
	public String resource_id;
	@XmlElement
	public HashMap<String, Hashtable<String, Double>> resource;
	
	public RequestJ(String requestType, String requestId, String filterId, 
			String resourceId, HashMap<String, Hashtable<String, Double>> r) {
		this.request_id = requestId;
		this.request_type = requestType;
		this.filter_id = filterId;
		this.resource_id = resourceId;
		this.resource = r;
	}
	
	public String getRequestType() {
		return request_type;
	}
	
	public void setRequestType(String request_type) {
		this.request_type = request_type;
	}

	public String getRequestId() {
		return request_id;
	}

	public void setRequestId(String request_id) {
		this.request_id = request_id;
	}

	public String getFilterId() {
		return filter_id;
	}

	public void setFilterId(String filter_id) {
		this.filter_id = filter_id;
	}

	public String getResourceId() {
		return resource_id;
	}

	public void setResourceId(String resource_id) {
		this.resource_id = resource_id;
	}
	
	public HashMap<String, Hashtable<String, Double>> getResource(){
		return resource;
	}
	
	public void setResource(HashMap<String, Hashtable<String, Double>> r) {
		this.resource = r;
	}
	
	// empty constructor needed for deserialization by JAXB
	public RequestJ() {}
	
	@Override
	public String toString(){
		String str = "";
		str += "RequestJ:\n{\n";
		str += " \"request_type\":\"" + request_type + "\",\n";
		str += " \"request_id\":\"" + request_id + "\",\n";
		str += " \"filter_id\":\"" + filter_id + "\",\n";
		str += " \"resource_id\":\"" + resource_id + "\",\n";
		str += " \"resource\": [\n";
		Set keys = resource.keySet();
		for (Iterator i = keys.iterator(); i.hasNext();) {
			String key = (String) i.next();//TODO to string innestati T_T
			Hashtable<String, Double> value = resource.get(key);
			Set values = value.keySet();
			for(Iterator j = values.iterator(); j.hasNext();){
				String k = (String) j.next();
				double v = value.get(k);
			}
			str += "  {\"" + key + "\":" + value + "}\n";
		}
		str += " ]\n}\n";
		return str;
	}

}
