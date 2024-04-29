package com.example.tvu_sistema.Models.Entity;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pleca")
@Setter
@Getter
public class Pleca implements Serializable{
    private static final long serialVersionUID = 2629195288020321924L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id_pleca;
    private String tema_pleca;
    private String titulo_transmicion_pleca;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date f_pleca;
    @Column(length = 10485760)
    private String url_video_pleca;
    @Column(length = 10485760)
    private String invitados_pleca;
    private String est_pleca;

    @ManyToOne
    @JoinColumn(name = "id_programa")
    private Programa programa;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "pleca_persona", joinColumns = @JoinColumn(name = "id_pleca"), inverseJoinColumns = @JoinColumn(name = "id_persona"))
    private Set<Persona> personas;
}
