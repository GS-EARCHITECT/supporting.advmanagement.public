package advresponse_details.controller.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import adv_details.model.dto.AdvDetail_DTO;
import adv_details.services.read.I_AdvDetailsPublicRead_Service;
import org.springframework.http.MediaType;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/advResponseDetailsPublicReadMgmt")
public class AdvResponseDetailsPublicRead_Controller {

	// private static final Logger Advgger =
	// LoggerFactory.getLogger(AdvSchd_AdvSchd_Lontroller.class);

	@Autowired
	private I_AdvDetailsPublicRead_Service advResponseDetailsPublicReadServ;

	@GetMapping(value = "/getAllAdvDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AdvDetail_DTO>> getAllAdvDetails() {
		CompletableFuture<CopyOnWriteArrayList<AdvDetail_DTO>> completableFuture = advResponseDetailsPublicReadServ.getAllAdDetails();
		CopyOnWriteArrayList<AdvDetail_DTO> advDetail_DTOs = completableFuture.join();
		return new ResponseEntity<>(advDetail_DTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectAdvDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AdvDetail_DTO>> getSelectAdvDetails(
			@RequestBody CopyOnWriteArrayList<Long> ids) {
		CompletableFuture<CopyOnWriteArrayList<AdvDetail_DTO>> completableFuture = advResponseDetailsPublicReadServ.getSelectAdDetails(ids);
		CopyOnWriteArrayList<AdvDetail_DTO> advDetail_DTOs = completableFuture.join();
		return new ResponseEntity<>(advDetail_DTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getSelectAdvsDetailsForParties", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AdvDetail_DTO>> getSelectAdvDetailsForParties(
			@RequestBody CopyOnWriteArrayList<Long> ids) {
		CompletableFuture<CopyOnWriteArrayList<AdvDetail_DTO>> completableFuture = advResponseDetailsPublicReadServ.getSelectAdDetailsByParties(ids);
		CopyOnWriteArrayList<AdvDetail_DTO> advDetail_DTOs = completableFuture.join();
		return new ResponseEntity<>(advDetail_DTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectAdvsDetailsForServices", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AdvDetail_DTO>> getSelectAdvDetailsForServices(
			@RequestBody CopyOnWriteArrayList<Long> ids) {
		CompletableFuture<CopyOnWriteArrayList<AdvDetail_DTO>> completableFuture = advResponseDetailsPublicReadServ.getSelectAdDetailsByServices(ids);
		CopyOnWriteArrayList<AdvDetail_DTO> advDetail_DTOs = completableFuture.join();
		return new ResponseEntity<>(advDetail_DTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectAdvsDetailsForResources", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<AdvDetail_DTO>> getSelectAdvDetailsForResources(
			@RequestBody CopyOnWriteArrayList<Long> ids) {
		CompletableFuture<CopyOnWriteArrayList<AdvDetail_DTO>> completableFuture = advResponseDetailsPublicReadServ.getSelectAdDetailsByResources(ids);
		CopyOnWriteArrayList<AdvDetail_DTO> advDetail_DTOs = completableFuture.join();
		return new ResponseEntity<>(advDetail_DTOs, HttpStatus.OK);
	}
	
}