package adv_master.model.repo.read;

import java.util.List;
import adv_master.model.master.AdvMaster;

public interface IAdsSearch_Repo 
{
	public List<AdvMaster> findAllAdsForConditions(List<String> sList);

}
