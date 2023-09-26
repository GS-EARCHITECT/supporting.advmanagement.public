package advresponse_details.model.master;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ADV_RESPONSE_DETAILS database table.
 * 
 */
@Embeddable
public class AdvResponseDetailPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "ADV_SEQ_NO")
	private Long advSeqNo;

	@Column(name = "FR_PARTY_SEQ_NO")
	private Long frPartySeqNo;

	@Column(name = "ADV_LINE_SEQ_NO")
	private Long adLineSeqNo;

	public AdvResponseDetailPK() {
	}

	public Long getAdvSeqNo() {
		return this.advSeqNo;
	}

	public void setAdvSeqNo(Long advSeqNo) {
		this.advSeqNo = advSeqNo;
	}

	public Long getFrPartySeqNo() {
		return this.frPartySeqNo;
	}

	public void setFrPartySeqNo(Long frPartySeqNo) {
		this.frPartySeqNo = frPartySeqNo;
	}

	public Long getAdLineSeqNo() {
		return this.adLineSeqNo;
	}

	public void setAdLineSeqNo(Long adLineSeqNo) {
		this.adLineSeqNo = adLineSeqNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AdvResponseDetailPK)) {
			return false;
		}
		AdvResponseDetailPK castOther = (AdvResponseDetailPK) other;
		return (this.advSeqNo == castOther.advSeqNo) && (this.frPartySeqNo == castOther.frPartySeqNo)
				&& (this.adLineSeqNo == castOther.adLineSeqNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.advSeqNo ^ (this.advSeqNo >>> 32)));
		hash = hash * prime + ((int) (this.frPartySeqNo ^ (this.frPartySeqNo >>> 32)));
		hash = hash * prime + ((int) (this.adLineSeqNo ^ (this.adLineSeqNo >>> 32)));

		return hash;
	}

	public AdvResponseDetailPK(Long advSeqNo, Long frPartySeqNo, Long adLineSeqNo) {
		super();
		this.advSeqNo = advSeqNo;
		this.frPartySeqNo = frPartySeqNo;
		this.adLineSeqNo = adLineSeqNo;
	}

}