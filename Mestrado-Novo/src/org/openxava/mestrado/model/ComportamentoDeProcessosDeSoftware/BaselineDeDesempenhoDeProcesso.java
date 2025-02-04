package org.openxava.mestrado.model.ComportamentoDeProcessosDeSoftware;

import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import org.openxava.annotations.*;
import org.openxava.mestrado.model.MedicaoDeSoftware.AnaliseDeMedicao.*;
import org.openxava.mestrado.model.MedicaoDeSoftware.Medicao.*;
import org.openxava.mestrado.model.MedicaoDeSoftware.PlanejamentoDaMedicao.DefinicaoOperacional.*;
import org.openxava.mestrado.model.MedicaoDeSoftware.PlanejamentoDaMedicao.EntidadeMensuravel.*;
import org.openxava.mestrado.model.OrganizacaoDeSoftware.*;
import org.openxava.mestrado.model.ProcessoDeSoftware.*;


/**
 * BDP-01
 */
@Entity
@Views({
	@View(members=
			"nome; "
			+ "data; "
			//+ "processoPadrao;"
			//+ "descricao;"
			//+ "medida;"
			//+ "Limites ["
			//+ "limiteDeControle; "
			//+ "];"
			//+ "Detalhes do Registro {"
			+ "registradoPor; "
			//+ "modeloDeDesempenhoDeProcesso;"
			+ "contextoDeBaselineDeDesempenhoDeProcesso;"			
			//+ "atualizaBaselineDeDesempenhoDeProcesso; " 
			//+ "}"
	),
	@View(name="Simple", members="data; limiteDeControle; Processo Padr�o [processoPadrao.nome]; Medida [medida.nome];"),
	})
@Tabs({
	@Tab(properties="medida.nome, processoPadrao.nome, data, limiteDeControle.limiteInferior, limiteDeControle.limiteSuperior", defaultOrder="${processoPadrao.nome} asc, ${data} desc")
})
public class BaselineDeDesempenhoDeProcesso {
   
	@Id @GeneratedValue(generator="system-uuid") @Hidden
	@GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;    
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
    
	@Stereotype("NO_CHANGE_DATE")
	private Date data;
	
    @Required 
	@Column(length=500, unique=true) 
    private String nome;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Stereotype("TEXT_AREA")
	@Column(columnDefinition="TEXT")
	private String descricao;
	 
	@ManyToOne
	@NoFrame
	//@Required
	private ContextoDeBaselineDeDesempenhoDeProcesso contextoDeBaselineDeDesempenhoDeProcesso;
	 
	@OneToOne
	//@Required
	private AnaliseDeMedicao analiseDeMedicao;
	
    public AnaliseDeMedicao getAnaliseDeMedicao() {
		return analiseDeMedicao;
	}

	public void setAnaliseDeMedicao(AnaliseDeMedicao analiseDeMedicao) {
		this.analiseDeMedicao = analiseDeMedicao;
	}

	//private Collection<ModeloDeDesempenhoDeProcesso> modeloDeDesempenhoDeProcesso;
	 
	@ManyToOne 
	//@Required
	@ReferenceView("Simple")
	private Medida medida;
	 
	@ManyToMany 
    @JoinTable(
	      name="baselineDeDesempenhoDeProcesso_Medicao"
	      , joinColumns={
	    		  @JoinColumn(name="baselineDeDesempenhoDeProcesso_id")
	       }
	      , inverseJoinColumns={
	    		  @JoinColumn(name="medicao_id")
	       }
	      )
	private Collection<Medicao> medicao;
	 
	@ManyToOne 
	@NoCreate
	@NoModify
	//@Required
	@ReferenceView("Simple")
	private RecursoHumano registradoPor;
	 
	@ManyToOne 
	//@Required
	@ReferenceView(value="Simple",notForViews="Simple")
	private ProcessoPadrao  processoPadrao ;
	 
	//private CapacidadeDeProcesso capacidadeDeProcesso;
	 
	//private Collection<LimiteDeControle> limiteDeControle;
	//@OneToOne
	//@PrimaryKeyJoinColumn
	@Embedded
	@NoFrame
	private LimiteDeControle limiteDeControle;
	 
	@OneToOne
	//@PrimaryKeyJoinColumn
	@ReferenceView("Simple")
	@NoCreate
	@NoModify
	private BaselineDeDesempenhoDeProcesso atualizaBaselineDeDesempenhoDeProcesso;

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public ContextoDeBaselineDeDesempenhoDeProcesso getContextoDeBaselineDeDesempenhoDeProcesso() {
		return contextoDeBaselineDeDesempenhoDeProcesso;
	}

	public void setContextoDeBaselineDeDesempenhoDeProcesso(
			ContextoDeBaselineDeDesempenhoDeProcesso contextoDeBaselineDeDesempenhoDeProcesso) {
		this.contextoDeBaselineDeDesempenhoDeProcesso = contextoDeBaselineDeDesempenhoDeProcesso;
	}

	public Medida getMedida() {
		return medida;
	}

	public void setMedida(Medida medida) {
		this.medida = medida;
	}

	public Collection<Medicao> getMedicao() {
		return medicao;
	}

	public void setMedicao(Collection<Medicao> medicao) {
		this.medicao = medicao;
	}
	
	public ProcessoPadrao getProcessoPadrao() {
		return processoPadrao;
	}

	public void setProcessoPadrao(ProcessoPadrao processoPadrao) {
		this.processoPadrao = processoPadrao;
	}

	public BaselineDeDesempenhoDeProcesso getAtualizaBaselineDeDesempenhoDeProcesso() {
		return atualizaBaselineDeDesempenhoDeProcesso;
	}

	public LimiteDeControle getLimiteDeControle() {
		return limiteDeControle;
	}

	public void setLimiteDeControle(LimiteDeControle limiteDeControle) {
		this.limiteDeControle = limiteDeControle;
	}

	public void setAtualizaBaselineDeDesempenhoDeProcesso(
			BaselineDeDesempenhoDeProcesso atualizaBaselineDeDesempenhoDeProcesso) {
		this.atualizaBaselineDeDesempenhoDeProcesso = atualizaBaselineDeDesempenhoDeProcesso;
	}

	public RecursoHumano getRegistradoPor() {
		return registradoPor;
	}

	public void setRegistradoPor(RecursoHumano registradoPor) {
		this.registradoPor = registradoPor;
	}
	
	@PreCreate
	public void ajustaLimite(){
		if(limiteDeControle == null)
		{
			limiteDeControle = new LimiteDeControle();
			limiteDeControle.setLimiteCentral(0);
			limiteDeControle.setLimiteInferior(0);
			limiteDeControle.setLimiteSuperior(0);
		}
	}
	 
}
 
