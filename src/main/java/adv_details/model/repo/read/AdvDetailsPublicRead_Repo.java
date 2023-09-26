package adv_details.model.repo.read;

import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import adv_details.model.master.AdvDetail;

@Repository("advDetailsPublicReadRepo")
public interface AdvDetailsPublicRead_Repo extends JpaRepository<AdvDetail, Long> 
{
	@Query(value = "SELECT * FROM ADV_DETAILS a WHERE a.ADV_SEQ_NO in :ids order by ADV_SEQ_NO, AD_LINE_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AdvDetail> getSelectAdDetails(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Query(value = "SELECT * FROM ADV_DETAILS a order by ADV_SEQ_NO, AD_LINE_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AdvDetail> getAllAdDetails();
	
	@Query(value = "SELECT * FROM ADV_DETAILS a WHERE a.party_SEQ_NO in :ids order by ADV_SEQ_NO, AD_LINE_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AdvDetail> getSelectAdDetailsByParties(@Param("ids") CopyOnWriteArrayList<Long> ids);

	@Query(value = "SELECT * FROM ADV_DETAILS a WHERE a.service_SEQ_NO in :ids order by ADV_SEQ_NO, AD_LINE_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AdvDetail> getSelectAdDetailsByServices(@Param("ids") CopyOnWriteArrayList<Long> ids);

	@Query(value = "SELECT * FROM ADV_DETAILS a WHERE a.resource_SEQ_NO in :ids order by ADV_SEQ_NO, AD_LINE_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AdvDetail> getSelectAdDetailsByResources(@Param("ids") CopyOnWriteArrayList<Long> ids);

}