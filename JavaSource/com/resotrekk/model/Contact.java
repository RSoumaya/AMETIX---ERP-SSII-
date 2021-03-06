package com.resotrekk.model;

// Generated 18 juin 2015 09:54:20 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Contact generated by hbm2java
 */
@Entity
@Table(name = "contact", catalog = "resotrekk")
public class Contact implements java.io.Serializable {

	private Integer idcontact;
	private String nom;
	private String email;
	private String tel;
	private String sujet;
	private String message;

	public Contact() {
	}

	public Contact(String email, String sujet, String message) {
		this.email = email;
		this.sujet = sujet;
		this.message = message;
	}

	public Contact(String nom, String email, String tel, String sujet,
			String message) {
		this.nom = nom;
		this.email = email;
		this.tel = tel;
		this.sujet = sujet;
		this.message = message;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idcontact", unique = true, nullable = false)
	public Integer getIdcontact() {
		return this.idcontact;
	}

	public void setIdcontact(Integer idcontact) {
		this.idcontact = idcontact;
	}

	@Column(name = "nom", length = 70)
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Column(name = "email", nullable = false, length = 70)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "tel", length = 45)
	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "sujet", nullable = false, length = 100)
	public String getSujet() {
		return this.sujet;
	}

	public void setSujet(String sujet) {
		this.sujet = sujet;
	}

	@Column(name = "message", nullable = false, length = 65535)
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
