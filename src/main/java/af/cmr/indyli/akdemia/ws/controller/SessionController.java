package af.cmr.indyli.akdemia.ws.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import af.cmr.indyli.akdemia.business.entity.Session;
import af.cmr.indyli.akdemia.business.service.ISessionService;
import af.cmr.indyli.akdemia.business.utils.ConstsValues;
import af.cmr.indyli.akdemia.ws.utils.AkdemiaUrlBase;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.annotation.Resource;

/**
 * 
 * @author Magamba
 *RESTful controller to manage operations related to Sessions.
 *
 */
@RestController
@CrossOrigin(origins = AkdemiaUrlBase.url, maxAge = AkdemiaUrlBase.maxAge)
@RequestMapping("/sessions")
public class SessionController {

	//--- ajout des ressouces du service Session
		@Resource(name = ConstsValues.ServiceKeys.SESSION_SERVICE_KEY)
		private ISessionService sessionService;
		
		/**
		 * @return ResponseEntity containing the list of Sessions (API 
		 */
		 @GetMapping
		    public ResponseEntity<List<Session>> getAllSessions() {
		        List<Session> sessions = sessionService.findAll();
		        return new ResponseEntity(sessions, HttpStatus.OK);
		    }
		
		 /**
		  * @return Session by given Id
		  */
		 @GetMapping("/{id}")
		    public ResponseEntity<Session> getSessionById(@PathVariable Long id) {
		        Session session = sessionService.findById(id);
		        if (session != null) {
		            return new ResponseEntity<>(session, HttpStatus.OK);
		        } else {
		            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		        }
		    }
		 
		 /**
		  * 
		  * @param session
		  * @return create a session and send it to data base to be insert
		  */
		 @PostMapping
		    public ResponseEntity<Session> createSession(@RequestBody Session session) {
		        Session createdSession = sessionService.save(session);
		        return new ResponseEntity<>(createdSession, HttpStatus.CREATED);
		    }

		 
		 /**
		  * 
		  * @param id
		  * @return delete a session by given id
		  */
		    @DeleteMapping("/{id}")
		    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
		        sessionService.delete(id);
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		 
}
