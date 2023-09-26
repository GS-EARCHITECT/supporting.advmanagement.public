package adv_master.services.read;

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
import adv_master.model.dto.AdvMaster_DTO;
import adv_master.model.master.AdvMaster;
import adv_master.model.repo.read.AdvMasterPublicRead_Repo;

@Service("advMasterPublicReadServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AdvMasterPublicRead_Service implements I_AdvMasterPublicRead_Service {
	// private static final Logger logger =
	// LoggerFactory.getLogger(AdvMasterService.class);

	@Autowired
	private AdvMasterPublicRead_Repo advMasterPublicReadRepo;

	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<CopyOnWriteArrayList<AdvMaster_DTO>> getAllAds() {
		CompletableFuture<CopyOnWriteArrayList<AdvMaster_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<AdvMaster> advMasters = advMasterPublicReadRepo.getAllAds();
			CopyOnWriteArrayList<AdvMaster_DTO> lMasterss = advMasters != null ? this.getAdvMasterDtos(advMasters)
					: null;
			return lMasterss;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<AdvMaster_DTO>> getSelectAds(CopyOnWriteArrayList<Long> ids) {
		CompletableFuture<CopyOnWriteArrayList<AdvMaster_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<AdvMaster> advMasters = advMasterPublicReadRepo.getSelectAds(ids);
			CopyOnWriteArrayList<AdvMaster_DTO> lMasterss = advMasters != null ? this.getAdvMasterDtos(advMasters)
					: null;
			return lMasterss;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<AdvMaster_DTO>> getSelectAdsByParties(
			CopyOnWriteArrayList<Long> ids) {
		CompletableFuture<CopyOnWriteArrayList<AdvMaster_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<AdvMaster> advMasters = advMasterPublicReadRepo.getSelectAdsByParties(ids);
			CopyOnWriteArrayList<AdvMaster_DTO> lMasterss = advMasters != null ? this.getAdvMasterDtos(advMasters)
					: null;
			return lMasterss;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<AdvMaster_DTO>> getAdsBetweenDTTM(String fr, String to) {
		CompletableFuture<CopyOnWriteArrayList<AdvMaster_DTO>> future = CompletableFuture.supplyAsync(() -> {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			LocalDateTime dateOn = LocalDateTime.parse(fr, formatter);
			LocalDateTime dateTo = LocalDateTime.parse(to, formatter);
			Timestamp ts_Fr = Timestamp.valueOf(dateOn);
			Timestamp ts_To = Timestamp.valueOf(dateTo);
			CopyOnWriteArrayList<AdvMaster> advMasters = advMasterPublicReadRepo.getAdsBetweenDTTM(ts_Fr, ts_To);
			CopyOnWriteArrayList<AdvMaster_DTO> lMasterss = advMasters != null ? this.getAdvMasterDtos(advMasters)
					: null;
			return lMasterss;
		}, asyncExecutor);
		return future;
	}

	private synchronized CopyOnWriteArrayList<AdvMaster_DTO> getAdvMasterDtos(
			CopyOnWriteArrayList<AdvMaster> lMasters) {
		AdvMaster_DTO lDTO = null;
		CopyOnWriteArrayList<AdvMaster_DTO> lMasterDTOs = new CopyOnWriteArrayList<AdvMaster_DTO>();

		for (int i = 0; i < lMasters.size(); i++) {
			lDTO = this.getAdvMaster_DTO(lMasters.get(i));
			lMasterDTOs.add(lDTO);
		}
		return lMasterDTOs;
	}

	private synchronized AdvMaster_DTO getAdvMaster_DTO(AdvMaster lMaster) {
		AdvMaster_DTO lDTO = new AdvMaster_DTO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		lDTO.setFrDttm(formatter.format(lMaster.getFrDttm().toLocalDateTime()));
		lDTO.setToDttm(formatter.format(lMaster.getToDttm().toLocalDateTime()));
		lDTO.setAd(lMaster.getAd());
		lDTO.setAdvSeqNo(lMaster.getAdvSeqNo());
		lDTO.setPartySeqNo(lMaster.getPartySeqNo());
		return lDTO;
	}

}