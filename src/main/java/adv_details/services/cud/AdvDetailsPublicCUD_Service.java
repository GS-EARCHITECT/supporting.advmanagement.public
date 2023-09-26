package adv_details.services.cud;

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
import adv_details.model.repo.cud.AdvDetailsPublicCUD_Repo;

@Service("advDetailsPublicCUDServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AdvDetailsPublicCUD_Service implements I_AdvDetailsPublicCUD_Service {
	// private static final Logger logger =
	// LoggerFactory.getLogger(AdvDetailService.class);

	@Autowired
	private AdvDetailsPublicCUD_Repo advDetailsPublicCUDRepo;

	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<AdvDetail_DTO> newAdDetail(AdvDetail_DTO lDetail) {
		CompletableFuture<AdvDetail_DTO> future = CompletableFuture.supplyAsync(() -> {
			AdvDetail_DTO advDetail_DTO = null;

			if (!advDetailsPublicCUDRepo.existsById(lDetail.getAdvSeqNo())) {
				advDetail_DTO = getAdvDetail_DTO(advDetailsPublicCUDRepo.save(setAdvDetail(lDetail)));
			}
			return advDetail_DTO;
		}, asyncExecutor);
		return future;

	}

	public CompletableFuture<Void> updAdDetail(AdvDetail_DTO lDetail) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			if (lDetail != null) {
				if (advDetailsPublicCUDRepo.existsById(lDetail.getAdvSeqNo())) {
					advDetailsPublicCUDRepo.save(this.setAdvDetail(lDetail));
				}
			}
			return;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delAllAdDetails() {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			advDetailsPublicCUDRepo.deleteAll();
			return;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delSelectAdDetails(CopyOnWriteArrayList<Long> seqNos) {

		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			if (seqNos != null) {
				advDetailsPublicCUDRepo.deleteAllById(seqNos);
			}
			return;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delSelectAdDetailsByParties(CopyOnWriteArrayList<Long> ids) {

		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			if (ids != null) {
				advDetailsPublicCUDRepo.delSelectAdDetailsByParties(ids);
			}
			return;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delSelectAdDetailsByServices(CopyOnWriteArrayList<Long> ids) {

		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			if (ids != null) {
				advDetailsPublicCUDRepo.delSelectAdDetailsByServices(ids);
			}
			return;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delSelectAdDetailsByResources(CopyOnWriteArrayList<Long> ids) {

		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			if (ids != null) {
				advDetailsPublicCUDRepo.delSelectAdDetailsByResources(ids);
			}
			return;
		}, asyncExecutor);
		return future;
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

	private synchronized AdvDetail setAdvDetail(AdvDetail_DTO lDTO) {
		AdvDetail lDetail = new AdvDetail();
		lDetail.setAdText(lDTO.getAdText());
		lDetail.setAdvSeqNo(lDTO.getAdvSeqNo());
		lDetail.setResourceSeqNo(lDTO.getResourceSeqNo());
		lDetail.setServiceSeqNo(lDTO.getServiceSeqNo());
		return lDetail;
	}

}