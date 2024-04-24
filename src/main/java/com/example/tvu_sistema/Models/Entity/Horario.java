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
@Table(name = "horario")
@Setter
@Getter
public class Horario implements Serializable {
    private static final long serialVersionUID = 2629195288020321924L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id_horario;    
    private String desc_horario;
    private String ano_horario;
    private String est_horario;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "horario", fetch = FetchType.LAZY)
	private List<Programa> programas;
    
}
