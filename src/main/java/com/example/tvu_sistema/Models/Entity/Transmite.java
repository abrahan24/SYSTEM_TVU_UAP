package com.example.tvu_sistema.Models.Entity;
import java.io.Serializable;
import java.sql.Time;
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
@Table(name = "transmite")
@Setter
@Getter
public class Transmite implements Serializable {
    private static final long serialVersionUID = 2629195288020321924L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id_transmite;    
    private String est_transmite;

    @ManyToOne
    @JoinColumn(name = "id_programa")
    private Programa programa;

    @ManyToOne
    @JoinColumn(name = "id_dias_transmision")
    private Dias_transmision dias_transmision;
    
}
