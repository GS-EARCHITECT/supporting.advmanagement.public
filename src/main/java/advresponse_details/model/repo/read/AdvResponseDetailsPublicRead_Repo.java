package advresponse_details.model.repo.read;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import advresponse_details.model.master.AdvResponseDetail;
import advresponse_details.model.master.AdvResponseDetailPK;

@Repository("advResponseDetailsPublicReadRepo")
public interface AdvResponseDetailsPublicRead_Repo extends JpaRepository<AdvResponseDetail, AdvResponseDetailPK> 
{
	@Query(value = "SELECT * FROM ADV_RESPONSE_DETAILS a order by ADV_SEQ_NO, AD_LINE_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AdvResponseDetail> getAllAdvResponseDetails();
	
	@Query(value = "SELECT * FROM ADV_RESPONSE_DETAILS a WHERE a.FR_PARTY_SEQ_NO in :ids order by FR_PARTY_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AdvResponseDetail> getSelectAdvResponseDetailsByResponses(@Param("ids") CopyOnWriteArrayList<Long> ids);

	@Query(value = "SELECT * FROM ADV_RESPONSE_DETAILS a WHERE a.ADV_LINE_SEQ_NO in :ids order by AD_LINE_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AdvResponseDetail> getSelectAdvResponseDetailsByAdLines(@Param("ids") CopyOnWriteArrayList<Long> ids);

	@Query(value = "SELECT * FROM ADV_RESPONSE_DETAILS a WHERE a.service_work_SEQ_NO in :ids order by service_work_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AdvResponseDetail> getSelectAdvResponseDetailsByServiceRequests(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Query(value = "SELECT * FROM ADV_RESPONSE_DETAILS a WHERE (a.ON_DTTM BETWEEN :fr and :to) order by ADV_SEQ_NO, AD_LINE_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AdvResponseDetail> getSelectAdvResponseDetailsBetweenDTTM(@Param(value = "fr") Timestamp fr, @Param(value = "to") Timestamp to);
}