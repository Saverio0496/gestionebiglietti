package it.prova.gestionebiglietti.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "biglietto")
public class Biglietto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "provenienza")
	private String provenienza;
	@Column(name = "destinazione")
	private String destinazione;
	@Column(name = "data")
	private Date data;
	@Column(name = "prezzo")
	private Integer prezzo;

	@CreationTimestamp
	private LocalDateTime createDateTime;
	@UpdateTimestamp
	private LocalDateTime updateDateTime;

	public Biglietto() {
	}

	public Biglietto(Long id, String provenienza, String destinazione, Date data, Integer prezzo) {
		this.id = id;
		this.provenienza = provenienza;
		this.destinazione = destinazione;
		this.data = data;
		this.prezzo = prezzo;
	}

	public Biglietto(String provenienza, String destinazione, Date data, Integer prezzo) {
		this.provenienza = provenienza;
		this.destinazione = destinazione;
		this.data = data;
		this.prezzo = prezzo;
	}

	public Biglietto(String provenienza, String destinazione) {
		this.provenienza = provenienza;
		this.destinazione = destinazione;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProvenienza() {
		return provenienza;
	}

	public void setProvenienza(String provenienza) {
		this.provenienza = provenienza;
	}

	public String getDestinazione() {
		return destinazione;
	}

	public void setDestinazione(String destinazione) {
		this.destinazione = destinazione;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Integer prezzo) {
		this.prezzo = prezzo;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

}
