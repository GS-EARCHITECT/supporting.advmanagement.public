package adv_details.services.read;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import adv_details.model.dto.AdvDetail_DTO;
import adv_details.model.master.AdvDetail;
import adv_details.model.repo.read.AdvDetailsPublicRead_Repo;

@Service("advDetailsPublicReadServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AdvDetailsPublicRead_Service implements I_AdvDetailsPublicRead_Service {
	// private static final Logger logger =
	// LoggerFactory.getLogger(AdvDetailService.class);

	@Autowired
	private AdvDetailsPublicRead_Repo advDetailsPublicReadRepo;

	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<CopyOnWriteArrayList<AdvDetail_DTO>> getAllAdDetails() {
		CompletableFuture<CopyOnWriteArrayList<AdvDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<AdvDetail> advDetails = advDetailsPublicReadRepo.getAllAdDetails();
			CopyOnWriteArrayList<AdvDetail_DTO> lDetailss = advDetails != null ? this.getAdvDetailDtos(advDetails)
					: null;
			return lDetailss;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<AdvDetail_DTO>> getSelectAdDetails(CopyOnWriteArrayList<Long> ids) {
		CompletableFuture<CopyOnWriteArrayList<AdvDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<AdvDetail> advDetails = advDetailsPublicReadRepo.getSelectAdDetails(ids);
			CopyOnWriteArrayList<AdvDetail_DTO> lDetailss = advDetails != null ? this.getAdvDetailDtos(advDetails)
					: null;
			return lDetailss;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<AdvDetail_DTO>> getSelectAdDetailsByParties(
			CopyOnWriteArrayList<Long> ids) {
		CompletableFuture<CopyOnWriteArrayList<AdvDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<AdvDetail> advDetails = advDetailsPublicReadRepo.getSelectAdDetailsByParties(ids);
			CopyOnWriteArrayList<AdvDetail_DTO> lDetailss = advDetails != null ? this.getAdvDetailDtos(advDetails)
					: null;
			return lDetailss;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<AdvDetail_DTO>> getSelectAdDetailsByResources(
			CopyOnWriteArrayList<Long> ids) {
		CompletableFuture<CopyOnWriteArrayList<AdvDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<AdvDetail> advDetails = advDetailsPublicReadRepo.getSelectAdDetailsByResources(ids);
			CopyOnWriteArrayList<AdvDetail_DTO> lDetailss = advDetails != null ? this.getAdvDetailDtos(advDetails)
					: null;
			return lDetailss;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<AdvDetail_DTO>> getSelectAdDetailsByServices(
			CopyOnWriteArrayList<Long> ids) {
		CompletableFuture<CopyOnWriteArrayList<AdvDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<AdvDetail> advDetails = advDetailsPublicReadRepo.getSelectAdDetailsByServices(ids);
			CopyOnWriteArrayList<AdvDetail_DTO> lDetailss = advDetails != null ? this.getAdvDetailDtos(advDetails)
					: null;
			return lDetailss;
		}, asyncExecutor);
		return future;
	}

	private synchronized CopyOnWriteArrayList<AdvDetail_DTO> getAdvDetailDtos(
			CopyOnWriteArrayList<AdvDetail> lDetails) {
		AdvDetail_DTO lDTO = null;
		CopyOnWriteArrayList<AdvDetail_DTO> lDetailDTOs = new CopyOnWriteArrayList<AdvDetail_DTO>();

		for (int i = 0; i < lDetails.size(); i++) {
			lDTO = this.getAdvDetail_DTO(lDetails.get(i));
			lDetailDTOs.add(lDTO);
		}
		return lDetailDTOs;
	}

	private synchronized AdvDetail_DTO getAdvDetail_DTO(AdvDetail lDetail) {
		AdvDetail_DTO lDTO = new AdvDetail_DTO();
		lDTO.setAdLineSeqNo(lDetail.getAdLineSeqNo());
		lDTO.setAdText(lDetail.getAdText());
		lDTO.setAdvSeqNo(lDetail.getAdvSeqNo());
		lDTO.setResourceSeqNo(lDetail.getResourceSeqNo());
		lDTO.setServiceSeqNo(lDetail.getServiceSeqNo());
		return lDTO;
	}

}