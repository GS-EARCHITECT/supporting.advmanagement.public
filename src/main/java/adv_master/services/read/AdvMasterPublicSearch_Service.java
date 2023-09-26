package adv_master.services.read;

import java.util.List;
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
import adv_master.model.repo.read.IAdsSearch_Repo;

@Service("advMasterPublicSearchServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AdvMasterPublicSearch_Service implements I_AdvMasterPublicSearch_Service
{
	private static final Logger logger = LoggerFactory.getLogger(AdvMasterPublicSearch_Service.class);

	@Autowired
	private IAdsSearch_Repo adsSearchRepo;

	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<CopyOnWriteArrayList<AdvMaster_DTO>> searchAllAds(CopyOnWriteArrayList<String> sList) 
	{
		CompletableFuture<CopyOnWriteArrayList<AdvMaster_DTO>> future = CompletableFuture.supplyAsync(() -> {
			List<AdvMaster> lMasters = adsSearchRepo.findAllAdsForConditions(sList);
			logger.info("got list of size :"+lMasters.size());		
			CopyOnWriteArrayList<AdvMaster> cList = new CopyOnWriteArrayList<AdvMaster>();
			CopyOnWriteArrayList<AdvMaster_DTO> ldAdvMaster_DTOs = null;
			if(lMasters!=null && lMasters.size()>0)
			{
			cList.addAll(lMasters);			
			ldAdvMaster_DTOs = cList != null ? this.getAdvMasterDtos(cList)	: null;
			}
			return ldAdvMaster_DTOs;
		}, asyncExecutor);
		return future;
	}
	
	private synchronized CopyOnWriteArrayList<AdvMaster_DTO> getAdvMasterDtos(CopyOnWriteArrayList<AdvMaster> lMasters) 
	{
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
		lDTO.setAd(lMaster.getAd());
		lDTO.setAdvSeqNo(lMaster.getAdvSeqNo());		
		return lDTO;
	}

}