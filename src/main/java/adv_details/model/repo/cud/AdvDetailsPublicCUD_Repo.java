package adv_details.model.repo.cud;

import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import adv_details.model.master.AdvDetail;

@Repository("advDetailsPublicCUDRepo")
public interface AdvDetailsPublicCUD_Repo extends JpaRepository<AdvDetail, Long> 
{
	@Query(value = "delete FROM ADV_Details a WHERE a.ADV_SEQ_NO in :ids ", nativeQuery = true)
	void delSelectAdDetails(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Query(value = "delete FROM ADV_Details a ", nativeQuery = true)
	void delAllAdDetails();
	
	@Query(value = "delete FROM ADV_Details a WHERE a.party_SEQ_NO in :ids ", nativeQuery = true)
	void delSelectAdsByParties(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Query(value = "delete FROM ADV_DETAILS a WHERE a.party_SEQ_NO in :ids ", nativeQuery = true)
	void delSelectAdDetailsByParties(@Param("ids") CopyOnWriteArrayList<Long> ids);

	@Query(value = "delete FROM ADV_DETAILS a WHERE a.service_SEQ_NO in :ids ", nativeQuery = true)
	void delSelectAdDetailsByServices(@Param("ids") CopyOnWriteArrayList<Long> ids);

	@Query(value = "delete FROM ADV_DETAILS a WHERE a.resource_SEQ_NO in :ids ", nativeQuery = true)
	void delSelectAdDetailsByResources(@Param("ids") CopyOnWriteArrayList<Long> ids);

}