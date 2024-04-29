package com.example.tvu_sistema.Models.Entity;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Set;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "programa")
@Setter
@Getter
public class Programa implements Serializable {
    private static final long serialVersionUID = 2629195288020321924L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id_programa;
    private String desc_programa;
    private Time hr_fin_programa;
    private Time hr_empiezo_pograma;
    private String ano_programa;
    private String est_programa;

    @ManyToOne
    @JoinColumn(name = "id_horario")
    private Horario horario;

    @ManyToOne
    @JoinColumn(name = "id_persona")
    private Persona persona;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "programa_dias_transmision", joinColumns = @JoinColumn(name = "id_programa"), inverseJoinColumns = @JoinColumn(name = "id_dias_transmision"))
    private Set<Dias_transmision> dias_transmision;



}
