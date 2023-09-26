package adv_master.controller.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import adv_master.model.dto.AdvMaster_DTO;
import adv_master.services.read.I_AdvMasterPublicRead_Service;
import adv_master.services.read.I_AdvMasterPublicSearch_Service;
import org.springframework.http.MediaType;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/advPublicReadMgmt")
public class AdvMasterPublicRead_Controller {

	// private static final Logger Advgger =
	// LoggerFactory.getLogger(AdvSchd_AdvSchd_Lontroller.class);

	@Autowired
	private I_AdvMasterPublicRead_Service advMasterPublicReadServ;

	@Autowired
	private I_AdvMasterPublicSearch_Service advMasterPublicSearchService;

	@GetMapping(value = "/search")
	public ResponseEntity<CopyOnWriteArrayList<AdvMaster_DTO>> search(@RequestBody CopyOnWriteArrayList<String> lStrings) 
	{
		HttpHeaders httpHeaders = new HttpHeaders();
		CompletableFuture<CopyOnWriteArrayList<AdvMaster_DTO>> completableFuture = advMasterPublicSearchService.searchAllAds(lStrings);
		CopyOnWriteArrayList<AdvMaster_DTO> cList = completableFuture.join();
		return new ResponseEntity<>(cList, httpHeaders, HttpStatus.CREATED);
	}

	@GetMapping(value = "/getAllAdv", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AdvMaster_DTO>> getAllAdvMasters() {
		CompletableFuture<CopyOnWriteArrayList<AdvMaster_DTO>> completableFuture = advMasterPublicReadServ.getAllAds();
		CopyOnWriteArrayList<AdvMaster_DTO> advMaster_DTOs = completableFuture.join();
		return new ResponseEntity<>(advMaster_DTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectAdvs", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AdvMaster_DTO>> getSelectAdvMasters(
			@RequestBody CopyOnWriteArrayList<Long> ids) {
		CompletableFuture<CopyOnWriteArrayList<AdvMaster_DTO>> completableFuture = advMasterPublicReadServ
				.getSelectAds(ids);
		CopyOnWriteArrayList<AdvMaster_DTO> advMaster_DTOs = completableFuture.join();
		return new ResponseEntity<>(advMaster_DTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectAdvsBetweenTimes/{fr}/{to}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AdvMaster_DTO>> getSelectAdvsBetweenTimes(@PathVariable String fr,
			@PathVariable String to) {
		CompletableFuture<CopyOnWriteArrayList<AdvMaster_DTO>> completableFuture = advMasterPublicReadServ
				.getAdsBetweenDTTM(fr, to);
		CopyOnWriteArrayList<AdvMaster_DTO> advMaster_DTOs = completableFuture.join();
		return new ResponseEntity<>(advMaster_DTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectAdvsForParties", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AdvMaster_DTO>> getSelectAdvForParties(
			@RequestBody CopyOnWriteArrayList<Long> ids) {
		CompletableFuture<CopyOnWriteArrayList<AdvMaster_DTO>> completableFuture = advMasterPublicReadServ
				.getSelectAdsByParties(ids);
		CopyOnWriteArrayList<AdvMaster_DTO> advMaster_DTOs = completableFuture.join();
		return new ResponseEntity<>(advMaster_DTOs, HttpStatus.OK);
	}

}