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
    	serviceMetadata.setSpecs(Arrays.asList("http://art.uniroma2.it/maple/alignment-services-3.1.yaml"));
    	
    	return new ResponseEntity<>(serviceMetadata, HttpStatus.OK);
    }

}
