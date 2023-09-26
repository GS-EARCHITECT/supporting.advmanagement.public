package advresponse_details.controller.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import advresponse_details.model.dto.AdvResponseDetail_DTO;
import advresponse_details.model.master.AdvResponseDetailPK;
import advresponse_details.services.cud.I_AdvResponseDetailsPublicCUD_Service;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/advResponseDetailsPublicCUDMgmt")
public class AdvResponseDetailsPublicCUD_Controller {

	// private static final Logger AdvResponseDetailsgger =
	// LoggerFactory.getLogger(Ad_Ad_Lontroller.class);

	@Autowired
	private I_AdvResponseDetailsPublicCUD_Service advResponseDetailsPublicCUDService;

	@PostMapping("/new")
	public ResponseEntity<AdvResponseDetail_DTO> newAdResponse(
			@RequestBody AdvResponseDetail_DTO advResponseDetailDTO) {
		CompletableFuture<AdvResponseDetail_DTO> completableFuture = advResponseDetailsPublicCUDService
				.newAdvResponseDetail(advResponseDetailDTO);
		AdvResponseDetail_DTO advResponseDetailDTO2 = completableFuture.join();
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(advResponseDetailDTO2, httpHeaders, HttpStatus.CREATED);
	}

	@PutMapping("/updAdResponseDetail")
	public void updateAdResponseDetail(@RequestBody AdvResponseDetail_DTO advResponseDetailDTO) {
		advResponseDetailsPublicCUDService.updAdvResponseDetail(advResponseDetailDTO);
	}

	@DeleteMapping("/delSelectAdvResponseDetails")
	public void deleteSelectAdvResponseDetails(@RequestBody CopyOnWriteArrayList<AdvResponseDetailPK> aList) {
		advResponseDetailsPublicCUDService.delSelectAdvResponseDetails(aList);
	}

	@DeleteMapping("/delSelectAdvResponseDetailsByResponses")
	public void delSelectAdvResponseDetailsByResponses(@RequestBody CopyOnWriteArrayList<Long> aList) {
		advResponseDetailsPublicCUDService.delSelectAdvResponseDetailsByResponses(aList);
	}

	@DeleteMapping("/delSelectAdvResponseDetailsByAdLines")
	public void delSelectAdvResponseDetailsByAdLines(@RequestBody CopyOnWriteArrayList<Long> aList) {
		advResponseDetailsPublicCUDService.delSelectAdvResponseDetailsByAdLines(aList);
	}

	@DeleteMapping("/delSelectAdvResponseDetailsByServiceRequests")
	public void delSelectAdvResponseDetailsByServiceRequests(@RequestBody CopyOnWriteArrayList<Long> aList) {
		advResponseDetailsPublicCUDService.delSelectAdvResponseDetailsByServiceRequests(aList);
	}

	@DeleteMapping("/delSelectAdvResponseDetailsBetweenDTTM/{fr}/{to}")
	public void delSelectAdvResponseDetailsBetweenDTTM(@PathVariable String fr, @PathVariable String to) {
		advResponseDetailsPublicCUDService.delSelectAdvResponseDetailsBetweenDTTM(fr, to);
	}

	@DeleteMapping("/delAllAdvResponseDetails")
	public void deleteAllAd() {
		advResponseDetailsPublicCUDService.delAllAdvResponseDetails();
	}
}