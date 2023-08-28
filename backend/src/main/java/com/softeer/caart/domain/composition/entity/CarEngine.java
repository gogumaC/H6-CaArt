package com.softeer.caart.domain.composition.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.softeer.caart.domain.Image;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "car_engine")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CarEngine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_engine_id")
	private Long id;

	@Column(nullable = false, length = 30)
	private String name;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private String summary;

	@Column(nullable = false)
	private Integer price;

	@Column(length = 30, nullable = false)
	private String maxPower;

	@Column(length = 30, nullable = false)
	private String maxTorque;

	@Embedded
	private Image image;

	@Builder
	public CarEngine(String name, String description, Integer price, String maxPower, String maxTorque,
		String imageUrl, String summary) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.maxPower = maxPower;
		this.maxTorque = maxTorque;
		this.image = Image.from(imageUrl);
		this.summary = summary;
	}
}
