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
@Table(name = "reportaje")
@Setter
@Getter
public class Reportaje implements Serializable {
    private static final long serialVersionUID = 2629195288020321924L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id_reportaje;
    private String tema_reportaje;    
    @Column(length = 10485760)
    private String desc_reportaje;
    @Column(length = 10485760)
    private String lugar_reportaje;
    @Column(length = 10485760)
    private String url_video_reportaje;
    private Date f_reportaje;    
    @Column(length = 10485760)
    private String invitados_reportaje;
    private String est_reportaje;

    @ManyToOne
    @JoinColumn(name = "id_programa")
    private Programa programa;

    @ManyToOne
    @JoinColumn(name = "id_categoria_reportaje")
    private Categoria_reportaje categoria_reportaje;

    @ManyToOne
    @JoinColumn(name = "id_persona")
    private Persona persona;

}
