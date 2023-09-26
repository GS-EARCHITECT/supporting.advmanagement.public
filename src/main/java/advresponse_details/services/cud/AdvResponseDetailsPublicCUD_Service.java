package advresponse_details.services.cud;

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
import advresponse_details.model.dto.AdvResponseDetail_DTO;
import advresponse_details.model.master.AdvResponseDetail;
import advresponse_details.model.master.AdvResponseDetailPK;
import advresponse_details.model.repo.cud.AdvResponseDetailsPublicCUD_Repo;

@Service("advResponseDetailsPublicCUDServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AdvResponseDetailsPublicCUD_Service implements I_AdvResponseDetailsPublicCUD_Service {
	// private static final Logger logger =
	// LoggerFactory.getLogger(AdvResponseDetailService.class);

	@Autowired
	private AdvResponseDetailsPublicCUD_Repo advResponseDetailsPublicCUDRepo;

	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<AdvResponseDetail_DTO> newAdvResponseDetail(AdvResponseDetail_DTO lDetail) {
		CompletableFuture<AdvResponseDetail_DTO> future = CompletableFuture.supplyAsync(() -> {
			AdvResponseDetail_DTO advDetail_DTO = null;
			AdvResponseDetailPK advResponseDetailPK = new AdvResponseDetailPK();			
			advResponseDetailPK.setAdLineSeqNo(lDetail.getAdLineSeqNo());
			advResponseDetailPK.setAdvSeqNo(lDetail.getAdvSeqNo());
			advResponseDetailPK.setFrPartySeqNo(lDetail.getFrPartySeqNo());			

			if (!advResponseDetailsPublicCUDRepo.existsById(advResponseDetailPK))
			{
			advDetail_DTO = getAdvResponseDetail_DTO(advResponseDetailsPublicCUDRepo.save(setAdvResponseDetail(lDetail)));
			}
			return advDetail_DTO;
		}, asyncExecutor);
		return future;

	}

	public CompletableFuture<Void> updAdvResponseDetail(AdvResponseDetail_DTO lDetail) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			if (lDetail != null) 
			{
				AdvResponseDetailPK advResponseDetailPK = new AdvResponseDetailPK();			
				advResponseDetailPK.setAdLineSeqNo(lDetail.getAdLineSeqNo());
				advResponseDetailPK.setAdvSeqNo(lDetail.getAdvSeqNo());
				advResponseDetailPK.setFrPartySeqNo(lDetail.getFrPartySeqNo());
				
				if (advResponseDetailsPublicCUDRepo.existsById(advResponseDetailPK))
				{
					advResponseDetailsPublicCUDRepo.save(this.setAdvResponseDetail(lDetail));
				}
			}
			return;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delAllAdvResponseDetails() 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			advResponseDetailsPublicCUDRepo.deleteAll();
			return;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delSelectAdvResponseDetails(CopyOnWriteArrayList<AdvResponseDetailPK> advResponseDetailPKs)
	{

		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			if (advResponseDetailPKs != null) {
				advResponseDetailsPublicCUDRepo.deleteAllById(advResponseDetailPKs);
			}
			return;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delSelectAdvResponseDetailsByResponses(CopyOnWriteArrayList<Long> ids) 
	{

		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			if (ids != null) 
			{
				advResponseDetailsPublicCUDRepo.delSelectAdvResponseDetailsByResponses(ids);;
			}
			return;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delSelectAdvResponseDetailsByAdLines(CopyOnWriteArrayList<Long> ids) 
	{

		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			if (ids != null) {
				advResponseDetailsPublicCUDRepo.delSelectAdvResponseDetailsByAdLines(ids);
			}
			return;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delSelectAdvResponseDetailsByServiceRequests(CopyOnWriteArrayList<Long> ids) 
	{

		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			if (ids != null) {
				advResponseDetailsPublicCUDRepo.delSelectAdvResponseDetailsByServiceRequests(ids);
			}
			return;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delSelectAdvResponseDetailsBetweenDTTM(String fr, String to) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			LocalDateTime dateOn = LocalDateTime.parse(fr, formatter);
			LocalDateTime dateTo = LocalDateTime.parse(to, formatter);
			Timestamp ts_Fr = Timestamp.valueOf(dateOn);
			Timestamp ts_To = Timestamp.valueOf(dateTo);
			advResponseDetailsPublicCUDRepo.delSelectAdvResponseDetailsBetweenDTTM(ts_Fr, ts_To);			
			return;
		}, asyncExecutor);
		return future;
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

	private synchronized AdvResponseDetail setAdvResponseDetail(AdvResponseDetail_DTO lDTO) {
		AdvResponseDetail lDetail = new AdvResponseDetail();
		AdvResponseDetailPK advResponseDetailPK = new AdvResponseDetailPK(); 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime onDate = LocalDateTime.parse(lDTO.getOnDttm(), formatter);
		advResponseDetailPK.setAdLineSeqNo(lDTO.getAdLineSeqNo());
		advResponseDetailPK.setAdvSeqNo(lDTO.getAdvSeqNo());
		advResponseDetailPK.setFrPartySeqNo(lDTO.getFrPartySeqNo());
		lDetail.setAdText(lDTO.getAdText());
		lDetail.setRemark(lDTO.getRemark());
		lDetail.setServiceWorkSeqNo(lDTO.getServiceWorkSeqNo());
		return lDetail;
	}

}