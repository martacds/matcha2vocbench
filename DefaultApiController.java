package io.swagger.api;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.model.ServiceMetadata;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-21T17:56:07.345478100+01:00[Europe/London]")
@RestController
public class DefaultApiController implements DefaultApi {

    private static final Logger log = LoggerFactory.getLogger(DefaultApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public DefaultApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<ServiceMetadata> getServiceMetadata() {
    	
    	ServiceMetadata serviceMetadata = new ServiceMetadata();
    	serviceMetadata.setService("MATCHA");
    	serviceMetadata.setVersion("1.0");
    	serviceMetadata.setStatus("active");
//    	serviceMetadata.setDocumentation(URI.create("https://github.com/liseda-lab/Matcha"));
    	serviceMetadata.setSpecs(Arrays.asList("http://art.uniroma2.it/maple/alignment-services-3.1.yaml"));
    	
    	return new ResponseEntity<>(serviceMetadata, HttpStatus.OK);
    	
//        String accept = request.getHeader("Accept");
//        if (accept != null && accept.contains("application/json")) {
//            try {
//                return new ResponseEntity<ServiceMetadata>(objectMapper.readValue("{\r\n  \"service\" : \"Matcha REST API\",\r\n  \"version\" : 1.0,\r\n  \"status\" : \"active\",\r\n  \"documentation\" : \"na\",\r\n  \"specs\" : [ \"https://github.com/liseda-lab/Matcha\" ]\r\n}", ServiceMetadata.class), HttpStatus.OK);
//            } catch (IOException e) {
//                log.error("Couldn't serialize response for content type application/json", e);
//                return new ResponseEntity<ServiceMetadata>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        }

//        return new ResponseEntity<ServiceMetadata>(HttpStatus.OK);
    }

}
