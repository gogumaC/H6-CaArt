package com.softeer.caart.domain.trim;

import static javax.persistence.FetchType.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.softeer.caart.domain.color.Color;

@Entity
@Table(name = "rel_trim_color")
public class AvailableColor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rel_trim_color_id")
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "trim_id", nullable = false)
	private Trim trim;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "color_id", nullable = false)
	private Color color;
}
