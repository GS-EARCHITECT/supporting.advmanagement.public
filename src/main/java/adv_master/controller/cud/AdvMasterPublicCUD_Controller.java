package adv_master.controller.cud;

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
import adv_master.model.dto.AdvMaster_DTO;
import adv_master.services.cud.I_AdvMasterPublicCUD_Service;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/advMasterPublicCUDMgmt")
public class AdvMasterPublicCUD_Controller {

	// private static final Logger Adsgger =
	// LoggerFactory.getLogger(Ad_Ad_Lontroller.class);

	@Autowired
	private I_AdvMasterPublicCUD_Service advMasterPublicCUDService;

	@PostMapping("/new")
	public ResponseEntity<AdvMaster_DTO> newAd(@RequestBody AdvMaster_DTO advMasterDTO) {
		CompletableFuture<AdvMaster_DTO> completableFuture = advMasterPublicCUDService.newAd(advMasterDTO);
		AdvMaster_DTO advMasterDTO2 = completableFuture.join();
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(advMasterDTO2, httpHeaders, HttpStatus.CREATED);
	}

	@PutMapping("/updAd")
	public void updateAd(@RequestBody AdvMaster_DTO advMasterDTO) {
		advMasterPublicCUDService.updAd(advMasterDTO);
	}

	@DeleteMapping("/delSelectAds")
	public void deleteSelectAds(@RequestBody CopyOnWriteArrayList<Long> aList) {
		advMasterPublicCUDService.delSelectAds(aList);
	}

	@DeleteMapping("/delSelectAdsByParties")
	public void delSelectAdsByParties(@RequestBody CopyOnWriteArrayList<Long> aList) {
		advMasterPublicCUDService.delSelectAdsByParties(aList);
	}

	@DeleteMapping("/delAdsBetweenDTTM/{fr}/{to}")
	public void delAdsBetweenDTTM(String fr, String to) {
		advMasterPublicCUDService.delAdsBetweenDTTM(fr, to);
	}

	@DeleteMapping("/delAllAds")
	public void deleteAllAd() {
		advMasterPublicCUDService.delAllAds();
	}
}