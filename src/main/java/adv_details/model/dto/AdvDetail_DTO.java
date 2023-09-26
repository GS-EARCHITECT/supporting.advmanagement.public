package adv_details.model.dto;

import java.io.Serializable;

public class AdvDetail_DTO implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8667367823133326717L;
	private Long adLineSeqNo;
	private String adText;
	private Long advSeqNo;
	private Long resourceSeqNo;
	private Long serviceSeqNo;

	public Long getAdLineSeqNo() {
		return adLineSeqNo;
	}

	public void setAdLineSeqNo(Long adLineSeqNo) {
		this.adLineSeqNo = adLineSeqNo;
	}

	public String getAdText() {
		return adText;
	}

	public void setAdText(String adText) {
		this.adText = adText;
	}

	public Long getAdvSeqNo() {
		return advSeqNo;
	}

	public void setAdvSeqNo(Long advSeqNo) {
		this.advSeqNo = advSeqNo;
	}

	public Long getResourceSeqNo() {
		return resourceSeqNo;
	}

	public void setResourceSeqNo(Long resourceSeqNo) {
		this.resourceSeqNo = resourceSeqNo;
	}

	public Long getServiceSeqNo() {
		return serviceSeqNo;
	}

	public void setServiceSeqNo(Long serviceSeqNo) {
		this.serviceSeqNo = serviceSeqNo;
	}

	public AdvDetail_DTO(Long adLineSeqNo, String adText, Long advSeqNo, Long resourceSeqNo, Long serviceSeqNo) {
		super();
		this.adLineSeqNo = adLineSeqNo;
		this.adText = adText;
		this.advSeqNo = advSeqNo;
		this.resourceSeqNo = resourceSeqNo;
		this.serviceSeqNo = serviceSeqNo;
	}

	public AdvDetail_DTO() {
		super();
	}

}