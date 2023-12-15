package com.whitemaks.github.polyday.db.developer_service.domain;

import jakarta.persistence.*;

import java.util.Date;

@jakarta.persistence.Entity
@Table(name = "entities", schema = "root")
public class Entity {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String type;
	@Column(name = "user_id")
	private Long userId;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
	@Column(name = "parent_entity_id")
	private Long parentEntityId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getParentEntityId() {
		return parentEntityId;
	}

	public void setParentEntityId(Long parentEntityId) {
		this.parentEntityId = parentEntityId;
	}
}
