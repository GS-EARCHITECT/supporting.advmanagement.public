package adv_details.model.master;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the ADV_DETAILS database table.
 * 
 */
@Entity
@Table(name = "ADV_DETAILS")
public class AdvDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADVLINE_SEQUENCE")
	@SequenceGenerator(name = "ADVLINE_SEQUENCE", sequenceName = "ADVLINE_SEQUENCE", allocationSize = 1)
	@Column(name = "ADV_LINE_SEQ_NO")
	private Long adLineSeqNo;

	@Column(name = "ADV_TEXT")
	private String adText;

	@Column(name = "ADV_SEQ_NO")
	private Long advSeqNo;

	@Column(name = "RESOURCE_SEQ_NO")
	private Long resourceSeqNo;

	@Column(name = "SERVICE_SEQ_NO")
	private Long serviceSeqNo;

	public AdvDetail() {
	}

	public Long getAdLineSeqNo() {
		return this.adLineSeqNo;
	}

	public void setAdLineSeqNo(Long adLineSeqNo) {
		this.adLineSeqNo = adLineSeqNo;
	}

	public String getAdText() {
		return this.adText;
	}

	public void setAdText(String adText) {
		this.adText = adText;
	}

	public Long getAdvSeqNo() {
		return this.advSeqNo;
	}

	public void setAdvSeqNo(Long advSeqNo) {
		this.advSeqNo = advSeqNo;
	}

	public Long getResourceSeqNo() {
		return this.resourceSeqNo;
	}

	public void setResourceSeqNo(Long resourceSeqNo) {
		this.resourceSeqNo = resourceSeqNo;
	}

	public Long getServiceSeqNo() {
		return this.serviceSeqNo;
	}

	public void setServiceSeqNo(Long serviceSeqNo) {
		this.serviceSeqNo = serviceSeqNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adLineSeqNo == null) ? 0 : adLineSeqNo.hashCode());
		result = prime * result + ((advSeqNo == null) ? 0 : advSeqNo.hashCode());
		result = prime * result + ((resourceSeqNo == null) ? 0 : resourceSeqNo.hashCode());
		result = prime * result + ((serviceSeqNo == null) ? 0 : serviceSeqNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdvDetail other = (AdvDetail) obj;
		if (adLineSeqNo == null) {
			if (other.adLineSeqNo != null)
				return false;
		} else if (!adLineSeqNo.equals(other.adLineSeqNo))
			return false;
		if (advSeqNo == null) {
			if (other.advSeqNo != null)
				return false;
		} else if (!advSeqNo.equals(other.advSeqNo))
			return false;
		if (resourceSeqNo == null) {
			if (other.resourceSeqNo != null)
				return false;
		} else if (!resourceSeqNo.equals(other.resourceSeqNo))
			return false;
		if (serviceSeqNo == null) {
			if (other.serviceSeqNo != null)
				return false;
		} else if (!serviceSeqNo.equals(other.serviceSeqNo))
			return false;
		return true;
	}

	public AdvDetail(Long adLineSeqNo, String adText, Long advSeqNo, Long resourceSeqNo, Long serviceSeqNo) {
		super();
		this.adLineSeqNo = adLineSeqNo;
		this.adText = adText;
		this.advSeqNo = advSeqNo;
		this.resourceSeqNo = resourceSeqNo;
		this.serviceSeqNo = serviceSeqNo;
	}

}