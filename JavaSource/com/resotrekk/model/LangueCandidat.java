package com.resotrekk.model;

// Generated 18 juin 2015 09:54:20 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * LangueCandidat generated by hbm2java
 */
@Entity
@Table(name = "langue_candidat", catalog = "resotrekk")
public class LangueCandidat implements java.io.Serializable {

	private Integer idlangue;
	private Candidat candidat;
	private String langueCandidat;
	private String statusLangue;

	public LangueCandidat() {
	}

	public LangueCandidat(Candidat candidat, String langueCandidat,
			String statusLangue) {
		this.candidat = candidat;
		this.langueCandidat = langueCandidat;
		this.statusLangue = statusLangue;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idlangue", unique = true, nullable = false)
	public Integer getIdlangue() {
		return this.idlangue;
	}

	public void setIdlangue(Integer idlangue) {
		this.idlangue = idlangue;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "candidat", nullable = false)
	public Candidat getCandidat() {
		return this.candidat;
	}

	public void setCandidat(Candidat candidat) {
		this.candidat = candidat;
	}

	@Column(name = "langue_candidat", nullable = false, length = 50)
	public String getLangueCandidat() {
		return this.langueCandidat;
	}

	public void setLangueCandidat(String langueCandidat) {
		this.langueCandidat = langueCandidat;
	}

	@Column(name = "status_langue", nullable = false, length = 45)
	public String getStatusLangue() {
		return this.statusLangue;
	}

	public void setStatusLangue(String statusLangue) {
		this.statusLangue = statusLangue;
	}

}