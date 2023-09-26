package advresponse_details.services.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import advresponse_details.model.dto.AdvResponseDetail_DTO;
import advresponse_details.model.master.AdvResponseDetailPK;

public interface I_AdvResponseDetailsPublicCUD_Service
{
    public CompletableFuture<AdvResponseDetail_DTO> newAdvResponseDetail(AdvResponseDetail_DTO advResponseDetail_DTO);
    public CompletableFuture<Void> updAdvResponseDetail(AdvResponseDetail_DTO advResponseDetail_DTO);
    public CompletableFuture<Void> delAllAdvResponseDetails();
    public CompletableFuture<Void> delSelectAdvResponseDetails(CopyOnWriteArrayList<AdvResponseDetailPK> ids);
    public CompletableFuture<Void> delSelectAdvResponseDetailsByResponses(CopyOnWriteArrayList<Long> ids);
    public CompletableFuture<Void> delSelectAdvResponseDetailsByAdLines(CopyOnWriteArrayList<Long> ids);
    public CompletableFuture<Void> delSelectAdvResponseDetailsByServiceRequests(CopyOnWriteArrayList<Long> ids);
    public CompletableFuture<Void> delSelectAdvResponseDetailsBetweenDTTM(String fr, String to);
}