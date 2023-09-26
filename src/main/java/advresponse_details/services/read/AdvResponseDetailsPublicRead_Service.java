package advresponse_details.services.read;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import advresponse_details.model.dto.AdvResponseDetail_DTO;
import advresponse_details.model.master.AdvResponseDetail;
import advresponse_details.model.repo.read.AdvResponseDetailsPublicRead_Repo;

@Service("advResponseDetailsPublicReadServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AdvResponseDetailsPublicRead_Service implements I_AdvResponseDetailsPublicRead_Service {
	// private static final Logger logger =
	// LoggerFactory.getLogger(AdvResponseDetailService.class);

	@Autowired
	private AdvResponseDetailsPublicRead_Repo advResponseDetailsPublicReadRepo;

	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<CopyOnWriteArrayList<AdvResponseDetail_DTO>> getAllAdvResponseDetails() {
		CompletableFuture<CopyOnWriteArrayList<AdvResponseDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<AdvResponseDetail> advResponseDetails = advResponseDetailsPublicReadRepo
					.getAllAdvResponseDetails();
			CopyOnWriteArrayList<AdvResponseDetail_DTO> lDetailss = advResponseDetails != null
					? this.getAdvResponseDetailDtos(advResponseDetails)
					: null;
			return lDetailss;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<AdvResponseDetail_DTO>> getSelectAdvResponseDetailsByServiceRequests(
			CopyOnWriteArrayList<Long> ids) {
		CompletableFuture<CopyOnWriteArrayList<AdvResponseDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<AdvResponseDetail> advResponseDetails = advResponseDetailsPublicReadRepo
					.getSelectAdvResponseDetailsByServiceRequests(ids);
			CopyOnWriteArrayList<AdvResponseDetail_DTO> lDetailss = advResponseDetails != null
					? this.getAdvResponseDetailDtos(advResponseDetails)
					: null;
			return lDetailss;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<AdvResponseDetail_DTO>> getSelectAdvResponseDetailsBetweenDTTM(
			String fr, String to) {
		CompletableFuture<CopyOnWriteArrayList<AdvResponseDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			LocalDateTime dateOn = LocalDateTime.parse(fr, formatter);
			LocalDateTime dateTo = LocalDateTime.parse(to, formatter);
			Timestamp ts_Fr = Timestamp.valueOf(dateOn);
			Timestamp ts_To = Timestamp.valueOf(dateTo);
			CopyOnWriteArrayList<AdvResponseDetail> advResponseDetails = advResponseDetailsPublicReadRepo
					.getSelectAdvResponseDetailsBetweenDTTM(ts_Fr, ts_To);
			CopyOnWriteArrayList<AdvResponseDetail_DTO> lDetailss = advResponseDetails != null
					? this.getAdvResponseDetailDtos(advResponseDetails)
					: null;
			return lDetailss;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<AdvResponseDetail_DTO>> getSelectAdvResponseDetailsByResponses(
			CopyOnWriteArrayList<Long> ids) {
		CompletableFuture<CopyOnWriteArrayList<AdvResponseDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<AdvResponseDetail> advResponseDetails = advResponseDetailsPublicReadRepo
					.getSelectAdvResponseDetailsByResponses(ids);
			CopyOnWriteArrayList<AdvResponseDetail_DTO> lDetailss = advResponseDetails != null
					? this.getAdvResponseDetailDtos(advResponseDetails)
					: null;
			return lDetailss;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<AdvResponseDetail_DTO>> getSelectAdvResponseDetailsByAdLines(
			CopyOnWriteArrayList<Long> ids) {
		CompletableFuture<CopyOnWriteArrayList<AdvResponseDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<AdvResponseDetail> advResponseDetails = advResponseDetailsPublicReadRepo
					.getSelectAdvResponseDetailsByAdLines(ids);
			CopyOnWriteArrayList<AdvResponseDetail_DTO> lDetailss = advResponseDetails != null
					? this.getAdvResponseDetailDtos(advResponseDetails)
					: null;
			return lDetailss;
		}, asyncExecutor);
		return future;
	}

	private synchronized CopyOnWriteArrayList<AdvResponseDetail_DTO> getAdvResponseDetailDtos(
			CopyOnWriteArrayList<AdvResponseDetail> lDetails) {
		AdvResponseDetail_DTO lDTO = null;
		CopyOnWriteArrayList<AdvResponseDetail_DTO> lDetailDTOs = new CopyOnWriteArrayList<AdvResponseDetail_DTO>();

		for (int i = 0; i < lDetails.size(); i++) {
			lDTO = this.getAdvResponseDetail_DTO(lDetails.get(i));
			lDetailDTOs.add(lDTO);
		}
		return lDetailDTOs;
	}

	private synchronized AdvResponseDetail_DTO getAdvResponseDetail_DTO(AdvResponseDetail lDetail) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		AdvResponseDetail_DTO lDTO = new AdvResponseDetail_DTO();
		lDTO.setOnDttm(formatter.format(lDetail.getOnDttm().toLocalDateTime()));
		lDTO.setAdLineSeqNo(lDetail.getId().getAdLineSeqNo());
		lDTO.setAdvSeqNo(lDetail.getId().getAdvSeqNo());
		lDTO.setFrPartySeqNo(lDetail.getId().getFrPartySeqNo());
		lDTO.setAdText(lDetail.getAdText());
		lDTO.setRemark(lDetail.getRemark());
		lDTO.setServiceWorkSeqNo(lDetail.getServiceWorkSeqNo());
		return lDTO;
	}

}