package br.com.dankicommerce.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.olimposistema.aipa.model.Model;

@Entity
public class Produto extends Model {
	
	@NotEmpty(message = "{produto.nome.notempty}") @Size(min = 3, max = 150, message = "{produto.nome.size}")
	private String nome;
	
	@NotEmpty(message = "{produto.descricao.notempty}") @Type(type="text")
	private String descricao;
	
	@NotNull(message = "{produto.valor.notnull}") @Min(value=0,message = "{produto.valor.min}")
	private Double valor;
	
	@ManyToOne @NotNull(message = "{produto.categoria.notnull}")
	private Categoria categoria;

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

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
}
