package br.org.generation.lojagames.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.generation.lojagames.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	public List <Produto> findAllByNomeContainingIgnoreCase (String nome);
	
	public List <Produto> findByPrecoGreaterThanOrderByPreco (BigDecimal preco);
	
	/*
	 * Método - Buscar todos os produtos cujo o preco seja menor
	 * do que um valor digitado ordenado pelo preço em ordem decrescente
	 * 
	 * MsSQL: select * from tb_produtos where preco < preco order by preco desc;
	 * 
	 */

	public List <Produto> findByPrecoLessThanOrderByPrecoDesc(BigDecimal preco);
}
