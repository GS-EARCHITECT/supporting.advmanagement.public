package advresponse_details.model.repo.cud;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import advresponse_details.model.master.AdvResponseDetail;
import advresponse_details.model.master.AdvResponseDetailPK;

@Repository("advResponseDetailsPublicCUDRepo")
public interface AdvResponseDetailsPublicCUD_Repo extends JpaRepository<AdvResponseDetail, AdvResponseDetailPK> 
{
	@Query(value = "truncate table ADV_RESPONSE_DETAILS", nativeQuery = true)
	void delAllAdvResponseDetails();
	
	@Query(value = "delete FROM ADV_RESPONSE_DETAILS a WHERE a.FR_PARTY_SEQ_NO in :ids", nativeQuery = true)
	void delSelectAdvResponseDetailsByResponses(@Param("ids") CopyOnWriteArrayList<Long> ids);

	@Query(value = "delete FROM ADV_RESPONSE_DETAILS a WHERE a.ADV_LINE_SEQ_NO in :ids", nativeQuery = true)
	void delSelectAdvResponseDetailsByAdLines(@Param("ids") CopyOnWriteArrayList<Long> ids);

	@Query(value = "delete FROM ADV_RESPONSE_DETAILS a WHERE a.service_work_SEQ_NO in :ids", nativeQuery = true)
	void delSelectAdvResponseDetailsByServiceRequests(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Query(value = "delete FROM ADV_RESPONSE_DETAILS a WHERE (a.ON_DTTM BETWEEN :fr and :to) ", nativeQuery = true)
	void delSelectAdvResponseDetailsBetweenDTTM(@Param(value = "fr") Timestamp fr, @Param(value = "to") Timestamp to);

	
}