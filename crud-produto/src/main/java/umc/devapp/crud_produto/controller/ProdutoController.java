package umc.devapp.crud_produto.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.devapp.crud_produto.entity.Produto;
import umc.devapp.crud_produto.service.ProdutoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // CONSULTAR TODOS
    @GetMapping
    public ResponseEntity<List<Produto>> getAllProducts() {
        List<Produto> produtos = produtoService.getAllProductsService();
        return ResponseEntity.ok(produtos);
    }

    // CONSULTAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Produto>> getProduct(@PathVariable Integer id) {
        ResponseEntity<Optional<Produto>> response;

        Optional<Produto> produto = produtoService.getProductService(id);
        if (produto.isPresent()) {
            response = ResponseEntity.ok(produto);
        } else {
            response = new ResponseEntity<>(produto, HttpStatus.NOT_FOUND);
        }

        return response;
    }

    // CADASTRAR PRODUTO
    @PostMapping("/add")
    public ResponseEntity<Produto> addProduct(@RequestBody Produto produto) {
        Produto newProduct = produtoService.insertProductService(produto);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    // ATUALIZAR PRODUTO
    @PutMapping("/update")
    public ResponseEntity<Produto> updateProduct(@RequestBody Produto produto) {
        ResponseEntity<Produto> response;

        Optional<Produto> searchedProduct = produtoService.getProductService(produto.getId());
        if (searchedProduct.isPresent()) {
            Produto updatedProduct = produtoService.updateProductService(produto);
            response = new ResponseEntity<>(updatedProduct, HttpStatus.CREATED);
        } else {
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return response;
    }

    // DELETAR PRODUTO
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        ResponseEntity<Void> response;

        Optional<Produto> product = produtoService.getProductService(id);
        if (product.isPresent()) {
            produtoService.deleteProductByIdService(id);
            response = ResponseEntity.noContent().build();
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return response;
    }

}
