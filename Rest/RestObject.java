package netgloo.com.java.Rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RestObject {

	@JsonProperty("@odata.context")
	public String context;
	
	@JsonProperty("value")
	public String values;
}