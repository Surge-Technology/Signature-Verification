package com.gen.signatureverification.util;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	@CreatedBy
	@Column(name = "created_by", updatable = false)
	protected String createdBy;

	@CreatedDate	
	@Column(name = "created_date", updatable = false)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected LocalDateTime createdOn;

	@LastModifiedBy
	@Column(name = "last_modified_by")
	protected String updatedBy;

	@LastModifiedDate
	@Column(name = "last_modified_date")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected LocalDateTime updatedOn;

}
