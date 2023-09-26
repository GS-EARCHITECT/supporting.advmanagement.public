package adv_master.model.repo.read;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import adv_master.model.master.AdvMaster;

@Repository("adsSearchRepo")
public class AdsSearch_Repo implements IAdsSearch_Repo 
{
	private static final Logger logger = LoggerFactory.getLogger(AdsSearch_Repo.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<AdvMaster> findAllAdsForConditions(List<String> sList)
	{
		logger.info("Slist :"+sList.size());
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		String qryString = null;
		String likeString = "";
		String subString = "";
		
		if (sList.size()==1) 
		{
			subString = sList.get(0).toUpperCase().trim();
			qryString = "select * from adv_master where upper(trim(adv)) like "+"'%"+subString+"%'";
		}
		
		if (sList.size() > 1) 
		{
			subString = sList.get(0).toUpperCase().trim();
			likeString = "'%"+subString+"%'";
			
			for (int i = 1; i < sList.size(); i++) 
			{
					subString = sList.get(i).toUpperCase().trim();
					likeString = likeString + " or upper(trim(adv)) like "+"'%"+subString+"%'" ;
					
			}
			mapSqlParameterSource.addValue("likeString", likeString);
			qryString = "select * from adv_master where upper(trim(adv)) like "+likeString;			
		}
	
		logger.info("query string  :"+qryString);
		logger.info("like string  :"+likeString);
		List<AdvMaster> advMasters = this.jdbcTemplate.query( qryString, new AdvMapper()); 
		logger.info("Masters List Size  :"+advMasters.size());
		
		return advMasters;
	
	}
	
	private static final class AdvMapper implements RowMapper<AdvMaster>
	{

	    public AdvMaster mapRow(ResultSet rs, int rowNum) throws SQLException {
	        AdvMaster ad = new AdvMaster();
	        ad.setAdvSeqNo(rs.getLong("adv_seq_no"));
	        ad.setAd(rs.getString("adv"));
	        return ad;
	    } 
	}
}
