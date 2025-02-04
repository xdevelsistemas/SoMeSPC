package org.openxava.mestrado.model.MedicaoDeSoftware.PlanejamentoDaMedicao.EntidadeMensuravel;

import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import org.openxava.annotations.*;
import org.openxava.mestrado.model.MedicaoDeSoftware.PlanejamentoDaMedicao.EntidadeMensuravel.*;

@Entity
@Views({
	@View(members="nome; descricao; elementoMensuravel"),
	@View(name="Simple", members="nome"),
	@View(name="SimpleNoFrame", members="nome"),
	})
@Tabs({
	@Tab(properties="nome", defaultOrder="${nome} asc")
})
public class TipoDeEntidadeMensuravel {
 
	@Id @GeneratedValue(generator="system-uuid") @Hidden
	@GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;   
    
	@Column(length=500, unique=true) @Required
	private String nome;

	@Stereotype("TEXT_AREA")
	@Column(columnDefinition="TEXT")
	private String descricao;

	@OneToMany(mappedBy="tipoDeEntidadeMensuravel")
	private Collection<EntidadeMensuravel> entidadeMensuravel;
	
    @ManyToMany
    @JoinTable(
	      name="elementoMensuravel_tipoDeEntidadeMensuravel"
	      , joinColumns={
	    		  @JoinColumn(name="tipoDeEntidadeMensuravel_id")
	       }
	      , inverseJoinColumns={
	    		  @JoinColumn(name="elementoMensuravel_id")
	       }
	      )
	private Collection<ElementoMensuravel> elementoMensuravel;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Collection<EntidadeMensuravel> getEntidadeMensuravel() {
		return entidadeMensuravel;
	}

	public void setEntidadeMensuravel(
			Collection<EntidadeMensuravel> entidadeMensuravel) {
		this.entidadeMensuravel = entidadeMensuravel;
	}

	public Collection<ElementoMensuravel> getElementoMensuravel() {
		return elementoMensuravel;
	}

	public void setElementoMensuravel(
			Collection<ElementoMensuravel> elementoMensuravel) {
		this.elementoMensuravel = elementoMensuravel;
	}

    
    
}
 
