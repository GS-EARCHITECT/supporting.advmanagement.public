package adv_details.services.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import adv_details.model.dto.AdvDetail_DTO;

public interface I_AdvDetailsPublicRead_Service
{
    public CompletableFuture<CopyOnWriteArrayList<AdvDetail_DTO>> getSelectAdDetails(CopyOnWriteArrayList<Long> ids);
    public CompletableFuture<CopyOnWriteArrayList<AdvDetail_DTO>> getAllAdDetails();
    public CompletableFuture<CopyOnWriteArrayList<AdvDetail_DTO>> getSelectAdDetailsByParties(CopyOnWriteArrayList<Long> ids);
    public CompletableFuture<CopyOnWriteArrayList<AdvDetail_DTO>> getSelectAdDetailsByServices(CopyOnWriteArrayList<Long> ids);
    public CompletableFuture<CopyOnWriteArrayList<AdvDetail_DTO>> getSelectAdDetailsByResources(CopyOnWriteArrayList<Long> ids);

}