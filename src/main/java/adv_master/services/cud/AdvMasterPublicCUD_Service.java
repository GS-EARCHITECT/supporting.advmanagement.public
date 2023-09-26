package adv_master.services.cud;

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
import adv_master.model.repo.cud.AdvMasterPublicCUD_Repo;

@Service("advPublicCUDServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AdvMasterPublicCUD_Service implements I_AdvMasterPublicCUD_Service 
{
	// private static final Logger logger =
	// LoggerFactory.getLogger(AdvMasterService.class);

	@Autowired
	private AdvMasterPublicCUD_Repo advPublicCUDRepo;

	@Autowired
	private Executor asyncExecutor;
	
	public CompletableFuture<AdvMaster_DTO> newAd(AdvMaster_DTO lDetail) 
	{
		CompletableFuture<AdvMaster_DTO> future = CompletableFuture.supplyAsync(() -> 
		{
		AdvMaster_DTO advMaster_DTO = null;
		
		if (!advPublicCUDRepo.existsById(lDetail.getAdvSeqNo())) 
		{
		advMaster_DTO = getAdvMaster_DTO(advPublicCUDRepo.save(setAdvMaster(lDetail)));
		}
		return advMaster_DTO;
   		},asyncExecutor);
		return future;

	}

	public CompletableFuture<Void> updAd(AdvMaster_DTO lDetail) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		if (lDetail != null) 
		{
			if (advPublicCUDRepo.existsById(lDetail.getAdvSeqNo())) 
			{
			advPublicCUDRepo.save(this.setAdvMaster(lDetail));
			}
		}
		return;
   		},asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delAllAds() 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{	
		advPublicCUDRepo.deleteAll();
		return;
   		},asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delSelectAds(CopyOnWriteArrayList<Long> seqNos) {

		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		if (seqNos != null) {
			advPublicCUDRepo.deleteAllById(seqNos);
		}
		return;
   		},asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delSelectAdsByParties(CopyOnWriteArrayList<Long> ids) 
	{

		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		if (ids != null) {
			advPublicCUDRepo.delSelectAdsByParties(ids);
		}
		return;
   		},asyncExecutor);
		return future;
	}

	
	public CompletableFuture<Void> delAdsBetweenDTTM( String fr,  String to) 
	{

		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime dateOn = LocalDateTime.parse(fr, formatter);
		LocalDateTime dateTo = LocalDateTime.parse(to, formatter);
		Timestamp ts_Fr = Timestamp.valueOf(dateOn);
		Timestamp ts_To = Timestamp.valueOf(dateTo);
		advPublicCUDRepo.delAdsBetweenDTTM(ts_Fr, ts_To);			
		return;
   		},asyncExecutor);
		return future;
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

	private synchronized AdvMaster setAdvMaster(AdvMaster_DTO lDTO) {
		AdvMaster lMaster = new AdvMaster();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime dateFr = LocalDateTime.parse(lDTO.getFrDttm(), formatter);
		LocalDateTime dateTo = LocalDateTime.parse(lDTO.getToDttm(), formatter);
		Timestamp ts_Fr = Timestamp.valueOf(dateFr);
		Timestamp ts_To = Timestamp.valueOf(dateTo);
		lMaster.setFrDttm(ts_Fr);
		lMaster.setToDttm(ts_To);
		lMaster.setPartySeqNo(lDTO.getPartySeqNo());
		return lMaster;
	}

}