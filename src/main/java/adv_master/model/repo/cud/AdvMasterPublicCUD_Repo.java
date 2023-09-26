package adv_master.model.repo.cud;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import adv_master.model.master.AdvMaster;

@Repository("advMasterPublicCUDRepo")
public interface AdvMasterPublicCUD_Repo extends JpaRepository<AdvMaster, Long> 
{
	@Query(value = "delete FROM ADV_MASTER a WHERE a.ADV_SEQ_NO in :ids ", nativeQuery = true)
	void delSelectAds(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Query(value = "delete FROM ADV_MASTER a ", nativeQuery = true)
	void delAllAds();
	
	@Query(value = "delete FROM ADV_MASTER where ((:fr BETWEEN FR_DTTM and TO_DTTM) and (:to BETWEEN FR_DTTM and TO_DTTM)) ORDER BY ADV_SEQ_NO",nativeQuery = true) 
	void delAdsBetweenDTTM(@Param(value = "fr") Timestamp fr, @Param(value = "to") Timestamp to);
	
	@Query(value = "delete FROM ADV_MASTER a WHERE a.party_SEQ_NO in :ids ", nativeQuery = true)
	void delSelectAdsByParties(@Param("ids") CopyOnWriteArrayList<Long> ids);

}