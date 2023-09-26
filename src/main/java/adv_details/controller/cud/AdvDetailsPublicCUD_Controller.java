package adv_details.controller.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import adv_details.model.dto.AdvDetail_DTO;
import adv_details.services.cud.I_AdvDetailsPublicCUD_Service;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/advDetailsPublicCUDMgmt")
public class AdvDetailsPublicCUD_Controller {

	// private static final Logger AdDetailsgger =
	// LoggerFactory.getLogger(Ad_Ad_Lontroller.class);

	@Autowired
	private I_AdvDetailsPublicCUD_Service advDetailsPublicCUDService;

	@PostMapping("/new")
	public ResponseEntity<AdvDetail_DTO> newAd(@RequestBody AdvDetail_DTO advDetailDTO) {
		CompletableFuture<AdvDetail_DTO> completableFuture = advDetailsPublicCUDService.newAdDetail(advDetailDTO);
		AdvDetail_DTO advDetailDTO2 = completableFuture.join();
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(advDetailDTO2, httpHeaders, HttpStatus.CREATED);
	}

	@PutMapping("/updAdDetail")
	public void updateAdDetail(@RequestBody AdvDetail_DTO advDetailDTO) {
		advDetailsPublicCUDService.updAdDetail(advDetailDTO);
	}

	@DeleteMapping("/delSelectAdDetails")
	public void deleteSelectAdDetails(@RequestBody CopyOnWriteArrayList<Long> aList) {
		advDetailsPublicCUDService.delSelectAdDetails(aList);
	}

	@DeleteMapping("/delSelectAdDetailsByParties")
	public void delSelectAdDetailsByParties(@RequestBody CopyOnWriteArrayList<Long> aList) {
		advDetailsPublicCUDService.delSelectAdDetailsByParties(aList);
	}

	@DeleteMapping("/delSelectAdDetailsByServices")
	public void delSelectAdDetailsByServices(@RequestBody CopyOnWriteArrayList<Long> aList) {
		advDetailsPublicCUDService.delSelectAdDetailsByServices(aList);
	}

	@DeleteMapping("/delSelectAdDetailsByResources")
	public void delSelectAdDetailsByResources(@RequestBody CopyOnWriteArrayList<Long> aList) {
		advDetailsPublicCUDService.delSelectAdDetailsByResources(aList);
	}

	@DeleteMapping("/delAllAdDetails")
	public void deleteAllAd() {
		advDetailsPublicCUDService.delAllAdDetails();
	}
}