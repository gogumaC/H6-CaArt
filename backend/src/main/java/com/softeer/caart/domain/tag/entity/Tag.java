package com.softeer.caart.domain.tag.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.softeer.caart.domain.Image;

import lombok.Getter;

@Entity
@Getter
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tag_id")
	private Long id;

	@Column(nullable = false)
	private String name;

	@Embedded
	private Image image;

	@Column(nullable = false)
	private String icon;

	@Column(nullable = false)
	private String iconSelected;

	@Column(nullable = false)
	private Integer priority;
}