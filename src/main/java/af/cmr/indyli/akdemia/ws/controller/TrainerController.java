package af.cmr.indyli.akdemia.ws.controller;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import af.cmr.indyli.akdemia.business.entity.Trainer;
import af.cmr.indyli.akdemia.business.entity.Training;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.ITrainerService;
import af.cmr.indyli.akdemia.business.utils.ConstsValues;
import af.cmr.indyli.akdemia.ws.utils.AkdemiaUrlBase;
import jakarta.annotation.Resource;

/**
 * 
 * @author Magamba
 * Controllers de la classe Trainer
 *
 */
@RestController
@CrossOrigin(origins = AkdemiaUrlBase.url, maxAge = AkdemiaUrlBase.maxAge)
@RequestMapping("/formateurs")
public class TrainerController {
	
	@Resource(name = ConstsValues.ServiceKeys.TRAINER_SERVICE_KEY)
	private ITrainerService trainerService;
	
	
	//--- Api pour afficher tous les formateurs
	@GetMapping
	public ResponseEntity<List<Trainer>> getAll() {
		return ResponseEntity.ok(trainerService.findAllTrainers());
	}
	
	//--- Api pour retourner formateur en fonction de son Id
	@GetMapping("/{id}")
    public ResponseEntity<Trainer> getById(@PathVariable Integer id) throws AkdemiaBusinessException {
        Trainer trainer = trainerService.getTrainerById(id);
        if (trainer != null) {
            return ResponseEntity.ok(trainer);
        } else {
            throw new AkdemiaBusinessException("Formateur non trouvé");
        }
    }
	
	 @PostMapping
	    public ResponseEntity<Trainer> createTrainer(@RequestBody Trainer trainer) {
	        Trainer createdTrainer = trainerService.save(trainer);
	        return new ResponseEntity<>(createdTrainer, HttpStatus.CREATED);
	    }
	 
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) throws AkdemiaBusinessException, AccessDeniedException {
		trainerService.deleteTrainerById(id);
		return ResponseEntity.ok().build();
	}
	
	
}
