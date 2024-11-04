package umc.devapp.crud_produto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umc.devapp.crud_produto.entity.Produto;
import umc.devapp.crud_produto.repository.ProdutoRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> getAllProductsService() {
        List<Produto> produtos = produtoRepository.findAll();
        Comparator<Produto> comparator = (p1, p2) -> {
            return Integer.compare(p1.getId(), p2.getId());
        };
        Collections.sort(produtos, comparator);
        return produtos;
    }

    public Optional<Produto> getProductService(Integer id) {
        return produtoRepository.findById(id);
    }

    public Produto insertProductService(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void deleteProductByIdService(Integer id) {
        produtoRepository.deleteById(id);
    }

    public Produto updateProductService(Produto produto) {
        Produto updatedProduct = produtoRepository.findById(produto.getId()).get(); // Pra que serve essa linha?
        updatedProduct = produto;
        return produtoRepository.save(updatedProduct);
    }
}
