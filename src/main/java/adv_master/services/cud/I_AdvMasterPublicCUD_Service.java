package adv_master.services.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import adv_master.model.dto.AdvMaster_DTO;

public interface I_AdvMasterPublicCUD_Service
{
    public CompletableFuture<AdvMaster_DTO> newAd(AdvMaster_DTO advMaster_DTO);
    public CompletableFuture<Void> updAd(AdvMaster_DTO lDetail);
    CompletableFuture<Void> delSelectAds(CopyOnWriteArrayList<Long> ids);
	CompletableFuture<Void> delAllAds();
	CompletableFuture<Void> delAdsBetweenDTTM(String fr, String to);
	CompletableFuture<Void> delSelectAdsByParties(CopyOnWriteArrayList<Long> ids);
}