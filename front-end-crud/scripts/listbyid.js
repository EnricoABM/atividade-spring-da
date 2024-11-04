function mostrarProduto(produto) {
    document.querySelector('#txtdescricao').value = produto.descricao;
    document.querySelector('#txtpreco').value = produto.preco;
    document.querySelector('#txtfornecedor').value = produto.fornecedor;
    document.querySelector('#txtqtdestoque').value = produto.qtdestoque;
}

function limparCampos() {
    document.querySelector('#txtdescricao').value = "";
    document.querySelector('#txtpreco').value = "";
    document.querySelector('#txtfornecedor').value = "";
    document.querySelector('#txtqtdestoque').value = "";
}

async function consultarProduto() {
    const idproduct = document.querySelector('#idproduct').value;
    if (idproduct.lenght < 1) {
        alert('Id Inválido');
        return ;
    }

    const url = `http://localhost:8080/produto/list/${idproduct}`;
    const dados = await fetch(url);
    if (dados.status === 200) {
        const product = await dados.json();
        mostrarProduto(product);
    }
    else {
        limparCampos();
        alert(`ID: ${idproduct} não encontrado`);
    }
}

