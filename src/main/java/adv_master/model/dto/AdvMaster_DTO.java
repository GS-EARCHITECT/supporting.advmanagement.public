package adv_master.model.dto;

import java.io.Serializable;

public class AdvMaster_DTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4558717942995237432L;
	private Long advSeqNo;
	private String ad;
	private String frDttm;
	private Long partySeqNo;
	private String toDttm;

	public Long getAdvSeqNo() {
		return advSeqNo;
	}

	public void setAdvSeqNo(Long advSeqNo) {
		this.advSeqNo = advSeqNo;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getFrDttm() {
		return frDttm;
	}

	public void setFrDttm(String frDttm) {
		this.frDttm = frDttm;
	}

	public Long getPartySeqNo() {
		return partySeqNo;
	}

	public void setPartySeqNo(Long partySeqNo) {
		this.partySeqNo = partySeqNo;
	}

	public String getToDttm() {
		return toDttm;
	}

	public void setToDttm(String toDttm) {
		this.toDttm = toDttm;
	}

	public AdvMaster_DTO(Long advSeqNo, String ad, String frDttm, Long partySeqNo, String toDttm) {
		super();
		this.advSeqNo = advSeqNo;
		this.ad = ad;
		this.frDttm = frDttm;
		this.partySeqNo = partySeqNo;
		this.toDttm = toDttm;
	}

	public AdvMaster_DTO() {
		super();
	}

}