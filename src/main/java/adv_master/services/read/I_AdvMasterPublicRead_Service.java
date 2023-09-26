package adv_master.services.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import adv_master.model.dto.AdvMaster_DTO;

public interface I_AdvMasterPublicRead_Service
{
    public CompletableFuture<CopyOnWriteArrayList<AdvMaster_DTO>> getSelectAds(CopyOnWriteArrayList<Long> ids);
    public CompletableFuture<CopyOnWriteArrayList<AdvMaster_DTO>> getAllAds();
    public CompletableFuture<CopyOnWriteArrayList<AdvMaster_DTO>> getAdsBetweenDTTM(String fr, String to);
    public CompletableFuture<CopyOnWriteArrayList<AdvMaster_DTO>> getSelectAdsByParties( CopyOnWriteArrayList<Long> ids);
}