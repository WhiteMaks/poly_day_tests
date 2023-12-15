package com.whitemaks.github.polyday.db.developer_service.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "shader_sources", schema = "root")
public class ShaderSource {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	@Column(name = "entity_id")
	private Long entityId;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
	@Column(name = "vertex_shader")
	private String vertexShader;
	@Column(name = "fragment_shader")
	private String fragmentShader;
	@Column(name = "geometry_shader")
	private String geometryShader;

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

	public String getVertexShader() {
		return vertexShader;
	}

	public void setVertexShader(String vertexShader) {
		this.vertexShader = vertexShader;
	}

	public String getFragmentShader() {
		return fragmentShader;
	}

	public void setFragmentShader(String fragmentShader) {
		this.fragmentShader = fragmentShader;
	}

	public String getGeometryShader() {
		return geometryShader;
	}

	public void setGeometryShader(String geometryShader) {
		this.geometryShader = geometryShader;
	}
}
