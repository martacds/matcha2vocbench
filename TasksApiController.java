package io.swagger.api;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.codec.digest.DigestUtils;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.model.AlignmentPlan;
import io.swagger.model.AlignmentPlanMatcherDefinition;
import io.swagger.model.ScenarioDefinition;
import io.swagger.model.Settings;
import io.swagger.model.Task;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import liseda.matcha.alignment.Alignment;
import liseda.matcha.io.ResourceManager;
import liseda.matcha.io.alignment.owl.AlignmentIOOWL;
import liseda.matcha.io.ontology.OntologyReader;
import liseda.matcha.ontology.Ontology;
import liseda.matcha.ontology.lexicon.ParenthesisExtender;
import liseda.matcha.ontology.lexicon.StopWordExtender;
import liseda.matcha.pipeline.AutomaticMatcher;
import liseda.matcha.settings.StopList;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-21T17:56:07.345478100+01:00[Europe/London]")
@RestController
public class TasksApiController implements TasksApi {

	private static final Logger log = LoggerFactory.getLogger(TasksApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	private final Environment env;

	@org.springframework.beans.factory.annotation.Autowired
	public TasksApiController(ObjectMapper objectMapper, HttpServletRequest request, Environment env) {
		this.objectMapper = objectMapper;
		this.request = request;
		this.env = env;
	}

	// TODO: implement for matcha
	public ResponseEntity<Void> deleteTaskByID(@Parameter(in = ParameterIn.PATH, description = "Task ID", required=true, schema=@Schema()) @PathVariable("id") String id) {
		String accept = request.getHeader("Accept");

		// remove run from logs and files from folders
		File result = new File(env.getProperty("repository.path") +  "/results/" + id + ".owl");
		//		File log = new File(env.getProperty("repository.path") + "/logs/" + id + ".txt");

		if(result.delete()) {
			System.out.println("Task results were deleted successfully");
		}
		else {
			System.out.println("Failed to delete the task results");
		}
		//		if(log.delete()) {
		//			System.out.println("Task log were deleted successfully");
		//		}
		//		else {
		//			System.out.println("Failed to delete the task log");
		//		}

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	public ResponseEntity<Resource> downloadAlignment(@Parameter(in = ParameterIn.PATH, description = "Task ID", required=true, schema=@Schema()) @PathVariable("id") String id) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<Resource>(objectMapper.readValue("\"\"", Resource.class), HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<Resource>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<Resource>(HttpStatus.NOT_IMPLEMENTED);
	}

	public ResponseEntity<Task> getTaskByID(@Parameter(in = ParameterIn.PATH, description = "Task ID", required=true, schema=@Schema()) @PathVariable("id") String id) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<Task>(objectMapper.readValue("{\r\n  \"id\" : \"c27d77380cf4d0bcdd5529eef1f020871d5f95c2\",\r\n  \"leftDataset\" : \"http://example.org/void.ttl#EuroVoc\",\r\n  \"rightDataset\" : \"http://example.org/void.ttl#TESEO\",\r\n  \"submissionTime\" : \"202-02-10T18:00:00+01:00\",\r\n  \"startTime\" : \"202-02-10T18:00:30+01:00\",\r\n  \"status\" : \"running\",\r\n  \"progress\" : 60\r\n}", Task.class), HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<Task>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<Task>(HttpStatus.NOT_IMPLEMENTED);
	}

	public ResponseEntity<List<Task>> getTasks() {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<List<Task>>(objectMapper.readValue("[ {\r\n  \"id\" : \"c27d77380cf4d0bcdd5529eef1f020871d5f95c2\",\r\n  \"leftDataset\" : \"http://example.org/void.ttl#EuroVoc\",\r\n  \"rightDataset\" : \"http://example.org/void.ttl#TESEO\",\r\n  \"submissionTime\" : \"202-02-10T18:00:00+01:00\",\r\n  \"startTime\" : \"202-02-10T18:00:30+01:00\",\r\n  \"status\" : \"running\",\r\n  \"progress\" : 60\r\n}, {\r\n  \"id\" : \"c27d77380cf4d0bcdd5529eef1f020871d5f95c2\",\r\n  \"leftDataset\" : \"http://example.org/void.ttl#EuroVoc\",\r\n  \"rightDataset\" : \"http://example.org/void.ttl#TESEO\",\r\n  \"submissionTime\" : \"202-02-10T18:00:00+01:00\",\r\n  \"startTime\" : \"202-02-10T18:00:30+01:00\",\r\n  \"status\" : \"running\",\r\n  \"progress\" : 60\r\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<List<Task>>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<List<Task>>(HttpStatus.NOT_IMPLEMENTED);
	}

	// TODO: implement for matcha
	public ResponseEntity<Task> submitTask(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody AlignmentPlan body) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				@NotNull
				@Valid
				ScenarioDefinition sd = body.getScenarioDefinition(); // get datasets
				@Valid
				Settings s = body.getSettings(); // any json
				@Valid
				AlignmentPlanMatcherDefinition md = body.getMatcherDefinition();
				System.out.println("printing settings");
				System.out.println(s);
				System.out.println("printing matcher definition");
				System.out.println(md);

				// get data for matching task
				String source = sd.getLeftDataset().getAtId(); // need the source URI
				String target = sd.getRightDataset().getAtId(); // need the target URI
				// create id
				String id = DigestUtils.sha1Hex(source + target + Math.random());

				///// Matcha section
				// settings
				liseda.matcha.settings.Settings set = liseda.matcha.settings.Settings.getInstance();
				StopList.init(ResourceManager.getStopSet());

				Ontology src = OntologyReader.parseInputOntology(source);
				Ontology tgt = OntologyReader.parseInputOntology(target);

				set.defaultConfig(src, tgt);
				// extenders
				ParenthesisExtender pe = new ParenthesisExtender(); 
				pe.extendLexicons(src); 
				pe.extendLexicons(tgt); 
				StopWordExtender se = new StopWordExtender(); 
				se.extendLexicons(src); 
				se.extendLexicons(tgt);

				AutomaticMatcher am = new AutomaticMatcher();
				Alignment a = am.match(src, tgt);

				AlignmentIOOWL.save(a, env.getProperty("repository.path") +  "/results/" + id + ".owl");

				// convert matcha result to a vocbench task
				Task task = new Task();
				task.setId(id);
				task.setLeftDataset(sd.getLeftDataset().getAtId());
				task.setRightDataset(sd.getRightDataset().getAtId());
				task.setStatus("completed");

				// final Result res = new Result(taskReport.getLeftDataset().getAtId(),taskReport.getRightDataset().getAtId(), "file:///" + engine, "file:///" + basePath);
				//        		task.setId(result.getAlignmentId()); // Result is a genoma thing 
				//        		task.setLeftDataset(result.getOntology1());
				//        		task.setRightDataset(result.getOntology2());

				//              return new ResponseEntity<Task>(objectMapper.readValue("{\r\n  \"id\" : \"c27d77380cf4d0bcdd5529eef1f020871d5f95c2\",\r\n  \"leftDataset\" : \"http://example.org/void.ttl#EuroVoc\",\r\n  \"rightDataset\" : \"http://example.org/void.ttl#TESEO\",\r\n  \"submissionTime\" : \"202-02-10T18:00:00+01:00\",\r\n  \"startTime\" : \"202-02-10T18:00:30+01:00\",\r\n  \"status\" : \"running\",\r\n  \"progress\" : 60\r\n}", Task.class), HttpStatus.NOT_IMPLEMENTED);

				return new ResponseEntity<Task>(task, HttpStatus.CREATED);
				// this exception is never thrown
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<Task>(HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (OWLOntologyCreationException | OWLOntologyStorageException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return new ResponseEntity<Task>(HttpStatus.NOT_IMPLEMENTED);
	}

}
