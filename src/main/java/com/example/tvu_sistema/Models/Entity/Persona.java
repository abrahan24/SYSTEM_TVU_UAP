package com.example.tvu_sistema.Models.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "persona")
@Setter
@Getter
public class Persona implements Serializable {

    private static final long serialVersionUID = 2629195288020321924L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id_persona;
    private String nombre;
    private String ap_paterno;
    private String ap_materno;
    private Integer edad;
    private String ci;
    private String num_celular;
    private String estado_persona;

    @ManyToOne
    @JoinColumn(name = "id_genero")
    private Genero genero;



    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona", fetch = FetchType.LAZY)
    private List<Usuario> usuarios;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona", fetch = FetchType.LAZY)
    private List<Programa> programas;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona", fetch = FetchType.LAZY)
    private List<Reportaje> reportajes;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "persona_profesion", joinColumns = @JoinColumn(name = "id_persona"), inverseJoinColumns = @JoinColumn(name = "id_profesion"))
    private Set<Profesion> profesion;

    @JsonIgnore
    @ManyToMany(mappedBy = "personas")
    private Set<Pleca> plecas;
}
