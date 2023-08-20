package com.softeer.caart.domain.model.entity;

import static javax.persistence.FetchType.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.softeer.caart.domain.option.entity.BaseOptionInfo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rel_model_base_option_info")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AvailableOption {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rel_model_base_option_info_id")
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "base_option_info_id", nullable = false)
	private BaseOptionInfo baseOptionInfo;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "model_id", nullable = false)
	private Model model;
}