package adv_master.model.repo.read;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import adv_master.model.master.AdvMaster;

@Repository("advMasterPublicReadRepo")
public interface AdvMasterPublicRead_Repo extends JpaRepository<AdvMaster, Long> 
{
	@Query(value = "SELECT * FROM ADV_MASTER a WHERE a.ADV_SEQ_NO in :ids order by ADV_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AdvMaster> getSelectAds(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Query(value = "SELECT * FROM ADV_MASTER a order by ADV_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AdvMaster> getAllAds();
	
	@Query(value = "SELECT * FROM ADV_MASTER where ((:fr BETWEEN FR_DTTM and TO_DTTM) and (:to BETWEEN FR_DTTM and TO_DTTM)) ORDER BY ADV_SEQ_NO",nativeQuery = true) 
	CopyOnWriteArrayList<AdvMaster> getAdsBetweenDTTM(@Param(value = "fr") Timestamp fr, @Param(value = "to") Timestamp to);
	
	@Query(value = "SELECT * FROM ADV_MASTER a WHERE a.party_SEQ_NO in :ids order by ADV_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AdvMaster> getSelectAdsByParties(@Param("ids") CopyOnWriteArrayList<Long> ids);

}