package br.com.dankicommerce.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import br.com.dankicommerce.model.Categoria;
import br.com.dankicommerce.model.Produto;
import br.com.olimposistema.aipa.dao.DAO;

@RequestScoped
public class ProdutoDAO extends DAO<Produto> {

	@Deprecated public ProdutoDAO() {super(null,null);}
	
	@Inject
	public ProdutoDAO(EntityManager em) {
		super(em, Produto.class);
	}
	
	public List<Produto> buscaTodosOsProdutosOrdenadosPorNome(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Produto> q = cb.createQuery(Produto.class);
		Root<Produto> r = q.from(Produto.class);
		
		q.select(r).orderBy(cb.asc(r.get("nome")));
		
		TypedQuery<Produto> query = em.createQuery(q);
		List<Produto> results = query.getResultList();
		return results;
	}
	
	public List<Produto> buscaPorCategoriaEspecifica(){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Produto> q = cb.createQuery(Produto.class);
		Root<Produto> r = q.from(Produto.class);
		Join<Produto, Categoria> joinCategoria = r.join("categoria", JoinType.INNER);
		
		q.select(r)
		.where(cb.equal(joinCategoria.get("id"), 1))
		.orderBy(cb.asc(r.get("nome")));
		
		TypedQuery<Produto> query = em.createQuery(q);
		List<Produto> results = query.getResultList();
		return results;
		
	}
	
	public Long totalProdutos() {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Long> q = cb.createQuery(Long.class);
		Root<Produto> r = q.from(Produto.class);
		Join<Produto, Categoria> joinCategoria = r.join("categoria", JoinType.INNER);
		
		q.multiselect(cb.count(r));
		
		TypedQuery<Long> query = em.createQuery(q);
		Long results = query.getSingleResult();
		return results;
		
		
	}

}
