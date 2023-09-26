package adv_master.services.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import adv_master.model.dto.AdvMaster_DTO;

public interface I_AdvMasterPublicSearch_Service
{
public CompletableFuture<CopyOnWriteArrayList<AdvMaster_DTO>> searchAllAds(CopyOnWriteArrayList<String> sList);
}