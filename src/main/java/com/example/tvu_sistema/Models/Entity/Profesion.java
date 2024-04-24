package com.example.tvu_sistema.Models.Entity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "profesion")
@Setter
@Getter
public class Profesion implements Serializable {

    private static final long serialVersionUID = 2629195288020321924L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_profesion;
    private String desc_profesion;
    private String estado_profesion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profesion", fetch = FetchType.LAZY)
	private List<Tiene> tienes;

    
}