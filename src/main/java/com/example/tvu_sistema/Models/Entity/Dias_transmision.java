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
@Table(name = "dias_transmision")
@Setter
@Getter
public class Dias_transmision implements Serializable {
    private static final long serialVersionUID = 2629195288020321924L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id_dias_transmision;
    private String desc_dias_transmision;    
    private String est_dias_transmision;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dias_transmision", fetch = FetchType.LAZY)
	private List<Transmite> transmites;
    
}
