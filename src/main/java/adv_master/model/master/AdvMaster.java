package adv_master.model.master;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the ADV_MASTER database table.
 * 
 */
@Entity
@Table(name = "ADV_MASTER")
public class AdvMaster implements Serializable 
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADV_SEQUENCE")
	@SequenceGenerator(name = "ADV_SEQUENCE", sequenceName = "ADV_SEQUENCE", allocationSize = 1)
	@Column(name = "ADV_SEQ_NO")
	private Long advSeqNo;

	@Column(name = "ADV")
	private String ad;

	@Column(name = "FR_DTTM")
	private Timestamp frDttm;

	@Column(name = "PARTY_SEQ_NO")
	private Long partySeqNo;

	@Column(name = "TO_DTTM")
	private Timestamp toDttm;

	public AdvMaster() {
	}

	public Long getAdvSeqNo() {
		return this.advSeqNo;
	}

	public void setAdvSeqNo(Long advSeqNo) {
		this.advSeqNo = advSeqNo;
	}

	public String getAd() {
		return this.ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public Timestamp getFrDttm() {
		return this.frDttm;
	}

	public void setFrDttm(Timestamp frDttm) {
		this.frDttm = frDttm;
	}

	public Long getPartySeqNo() {
		return this.partySeqNo;
	}

	public void setPartySeqNo(Long partySeqNo) {
		this.partySeqNo = partySeqNo;
	}

	public Timestamp getToDttm() {
		return this.toDttm;
	}

	public void setToDttm(Timestamp toDttm) {
		this.toDttm = toDttm;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((advSeqNo == null) ? 0 : advSeqNo.hashCode());
		result = prime * result + ((partySeqNo == null) ? 0 : partySeqNo.hashCode());
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
		AdvMaster other = (AdvMaster) obj;
		if (advSeqNo == null) {
			if (other.advSeqNo != null)
				return false;
		} else if (!advSeqNo.equals(other.advSeqNo))
			return false;
		if (partySeqNo == null) {
			if (other.partySeqNo != null)
				return false;
		} else if (!partySeqNo.equals(other.partySeqNo))
			return false;
		return true;
	}

	public AdvMaster(java.lang.Long advSeqNo, String ad, Timestamp frDttm, java.lang.Long partySeqNo,
			Timestamp toDttm) {
		super();
		this.advSeqNo = advSeqNo;
		this.ad = ad;
		this.frDttm = frDttm;
		this.partySeqNo = partySeqNo;
		this.toDttm = toDttm;
	}

}