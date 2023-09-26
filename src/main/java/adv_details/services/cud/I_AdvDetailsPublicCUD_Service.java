package adv_details.services.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import adv_details.model.dto.AdvDetail_DTO;

public interface I_AdvDetailsPublicCUD_Service
{
    public CompletableFuture<AdvDetail_DTO> newAdDetail(AdvDetail_DTO advDetails_DTO);
    public CompletableFuture<Void> updAdDetail(AdvDetail_DTO lDetail);
	CompletableFuture<Void> delSelectAdDetails(CopyOnWriteArrayList<Long> ids);
	CompletableFuture<Void> delAllAdDetails();
	CompletableFuture<Void> delSelectAdDetailsByParties(CopyOnWriteArrayList<Long> ids);
	CompletableFuture<Void> delSelectAdDetailsByServices(CopyOnWriteArrayList<Long> ids);
	CompletableFuture<Void> delSelectAdDetailsByResources(CopyOnWriteArrayList<Long> ids);
}