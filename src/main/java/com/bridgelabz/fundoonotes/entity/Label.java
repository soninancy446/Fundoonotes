package com.bridgelabz.fundoonotes.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Component
@Data
@Entity
@Table
public class Label {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long labelid;
    @Column
	private String labelName;
    @Column
	private Long userid;
	@Column
	private Long id;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Label_Note", joinColumns = { @JoinColumn(name = "labelid") }, inverseJoinColumns = {
			@JoinColumn(name = "id") })

	// @JsonBackReference
@JsonIgnore
	private List<Note> list;

}
