package advresponse_details.model.dto;

import java.io.Serializable;

public class AdvResponseDetail_DTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3302569025695029123L;
	private String adText;
	private String onDttm;
	private String remark;
	private Long serviceWorkSeqNo;
	private Long advSeqNo;
	private Long frPartySeqNo;
	private Long adLineSeqNo;

	public String getAdText() {
		return adText;
	}

	public void setAdText(String adText) {
		this.adText = adText;
	}

	public String getOnDttm() {
		return onDttm;
	}

	public void setOnDttm(String onDttm) {
		this.onDttm = onDttm;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getServiceWorkSeqNo() {
		return serviceWorkSeqNo;
	}

	public void setServiceWorkSeqNo(Long serviceWorkSeqNo) {
		this.serviceWorkSeqNo = serviceWorkSeqNo;
	}

	public Long getAdvSeqNo() {
		return advSeqNo;
	}

	public void setAdvSeqNo(Long advSeqNo) {
		this.advSeqNo = advSeqNo;
	}

	public Long getFrPartySeqNo() {
		return frPartySeqNo;
	}

	public void setFrPartySeqNo(Long frPartySeqNo) {
		this.frPartySeqNo = frPartySeqNo;
	}

	public Long getAdLineSeqNo() {
		return adLineSeqNo;
	}

	public void setAdLineSeqNo(Long adLineSeqNo) {
		this.adLineSeqNo = adLineSeqNo;
	}

	public AdvResponseDetail_DTO(String adText, String onDttm, String remark, Long serviceWorkSeqNo, Long advSeqNo,
			Long frPartySeqNo, Long adLineSeqNo) {
		super();
		this.adText = adText;
		this.onDttm = onDttm;
		this.remark = remark;
		this.serviceWorkSeqNo = serviceWorkSeqNo;
		this.advSeqNo = advSeqNo;
		this.frPartySeqNo = frPartySeqNo;
		this.adLineSeqNo = adLineSeqNo;
	}

	public AdvResponseDetail_DTO() {
		super();
	}

}