package advresponse_details.services.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import advresponse_details.model.dto.AdvResponseDetail_DTO;

public interface I_AdvResponseDetailsPublicRead_Service
{
    public CompletableFuture<CopyOnWriteArrayList<AdvResponseDetail_DTO>> getAllAdvResponseDetails();
    public CompletableFuture<CopyOnWriteArrayList<AdvResponseDetail_DTO>> getSelectAdvResponseDetailsByResponses(CopyOnWriteArrayList<Long> ids);
    public CompletableFuture<CopyOnWriteArrayList<AdvResponseDetail_DTO>> getSelectAdvResponseDetailsByAdLines(CopyOnWriteArrayList<Long> ids);
    public CompletableFuture<CopyOnWriteArrayList<AdvResponseDetail_DTO>> getSelectAdvResponseDetailsByServiceRequests(CopyOnWriteArrayList<Long> ids);
    public CompletableFuture<CopyOnWriteArrayList<AdvResponseDetail_DTO>> getSelectAdvResponseDetailsBetweenDTTM(String fr, String to);
}