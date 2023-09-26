package advresponse_details.model.master;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the ADV_RESPONSE_DETAILS database table.
 * 
 */
@Entity
@Table(name = "ADV_RESPONSE_DETAILS")
public class AdvResponseDetail implements Serializable 
{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AdvResponseDetailPK id;

	@Column(name = "ADV_TEXT")
	private String adText;

	@Column(name = "ON_DTTM")
	private Timestamp onDttm;

	@Column(name = "REMARK")
	private String remark;

	@Column(name = "SERVICE_WORK_SEQ_NO")
	private Long serviceWorkSeqNo;

	public AdvResponseDetail() {
	}

	public AdvResponseDetailPK getId() {
		return this.id;
	}

	public void setId(AdvResponseDetailPK id) {
		this.id = id;
	}

	public String getAdText() {
		return this.adText;
	}

	public void setAdText(String adText) {
		this.adText = adText;
	}

	public Timestamp getOnDttm() {
		return this.onDttm;
	}

	public void setOnDttm(Timestamp onDttm) {
		this.onDttm = onDttm;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getServiceWorkSeqNo() {
		return this.serviceWorkSeqNo;
	}

	public void setServiceWorkSeqNo(Long serviceWorkSeqNo) {
		this.serviceWorkSeqNo = serviceWorkSeqNo;
	}

	public AdvResponseDetail(AdvResponseDetailPK id, String adText, Timestamp onDttm, String remark,
			java.lang.Long serviceWorkSeqNo) {
		super();
		this.id = id;
		this.adText = adText;
		this.onDttm = onDttm;
		this.remark = remark;
		this.serviceWorkSeqNo = serviceWorkSeqNo;
	}

}