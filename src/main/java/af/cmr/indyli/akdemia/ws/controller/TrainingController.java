package af.cmr.indyli.akdemia.ws.controller;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import af.cmr.indyli.akdemia.business.dto.full.TopicFullDTO;
import af.cmr.indyli.akdemia.business.entity.Training;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.ITrainingService;
import af.cmr.indyli.akdemia.business.utils.ConstsValues;
import af.cmr.indyli.akdemia.ws.utils.AkdemiaUrlBase;
import jakarta.annotation.Resource;

/**
 * RESTful controller to manage operations related to topics.
 */
@RestController
@CrossOrigin(origins = AkdemiaUrlBase.url, maxAge = AkdemiaUrlBase.maxAge)
@RequestMapping("/formations")
public class TrainingController {
	
@Resource(name = ConstsValues.ServiceKeys.TRAINING_SERVICE_KEY)
private ITrainingService trainingService;
	
	/**
	 * Retrieve the list of all topics.
	 *
	 * @return ResponseEntity containing the list of topics.
	 */
	@GetMapping
	public ResponseEntity<List<Training>> getAll() {
		return ResponseEntity.ok(trainingService.findAll());
	}
	
	
	/**
	 * Retrieve information about a training by its identifier.
	 *
	 * @param id The identifier of the training.
	 * @return ResponseEntity containing information about the training.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Training> getOne(@PathVariable Long id) throws AkdemiaBusinessException {
		return ResponseEntity.ok(trainingService.getTrainingById(id));
	}
	
	/**
	 * Create a new training.
	 *
	 * @param dto Information about the training to create.
	 * @return ResponseEntity containing information about the newly created training.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
	@PostMapping
	public ResponseEntity<Training> create(@RequestBody Training training) throws AkdemiaBusinessException {
		return ResponseEntity.ok(trainingService.saveTraining(training));
	}
	
	/**
	 * Update information about a training.
	 *
	 * @param id  The identifier of the training to update.
	 * @param dto The new information about the training.
	 * @return ResponseEntity containing the updated information of the training.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 * @throws AccessDeniedException    If access is denied.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Training> update(@PathVariable int id, @RequestBody Training training)
			throws AkdemiaBusinessException, AccessDeniedException {
		return ResponseEntity.ok(trainingService.saveTraining(training));
	}
	
	/**
	 * Delete a training by its identifier.
	 *
	 * @param id The identifier of the training to delete.
	 * @return ResponseEntity with an empty body indicating the training has been
	 *         successfully deleted.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 * @throws AccessDeniedException    If access is denied.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) throws AkdemiaBusinessException, AccessDeniedException {
		trainingService.deleteTraining(id);
		return ResponseEntity.ok().build();
	}

}
