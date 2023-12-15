package com.whitemaks.github.polyday.db.developer_service.domain;

import jakarta.persistence.*;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity
@Table(name = "model_sources", schema = "root")
public class ModelSource {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	@Column(name = "entity_id")
	private Long entityId;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
	@Column(name = "source_base_64")
	private String sourceBase64;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Object getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public String getSourceBase64() {
		return sourceBase64;
	}

	public void setSourceBase64(String sourceBase64) {
		this.sourceBase64 = sourceBase64;
	}
}
