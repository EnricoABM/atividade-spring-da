package umc.devapp.crud_produto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import umc.devapp.crud_produto.entity.Produto;
import umc.devapp.crud_produto.service.ProdutoService;

@RestController
@RequestMapping("produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // CONSULTAR TODOS
    @GetMapping("/listall")
    public ResponseEntity<List<Produto>> getAllProducts() {
        List<Produto> produtos = produtoService.getAllProductsService();
        return ResponseEntity.ok(produtos);
    }

    // CONSULTAR POR ID
    @GetMapping("/list/{id}")
    public ResponseEntity<Optional<Produto>> getProduct(@PathVariable Integer id) {
        Optional<Produto> produto = produtoService.getProductService(id);
        
        if (produto.isPresent()) {
            return new ResponseEntity<>(produto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(produto, HttpStatus.NOT_FOUND);
        }

    }

    // CADASTRAR PRODUTO
    @PostMapping("/add")
    public ResponseEntity<Void> addProduct(@RequestBody Produto produto) {
        produtoService.insertProductService(produto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // ATUALIZAR PRODUTO
    @PutMapping("/update")
    public ResponseEntity<Produto> updateProduct(@RequestBody Produto produto) {
        Optional<Produto> procuct = produtoService.getProductService(produto.getId());
        if (procuct.isPresent()) {
            Produto updatedProduct = produtoService.updateProductService(produto);
            return new ResponseEntity<>(updatedProduct, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    // DELETAR PRODUTO
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        Optional<Produto> product = produtoService.getProductService(id);

        if (product.isPresent()) {
            produtoService.deleteProductByIdService(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
    
}
