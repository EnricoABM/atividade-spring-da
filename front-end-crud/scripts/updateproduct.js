function getProduct() {
    const params = new URLSearchParams(window.location.search);
    const product = {
        id: params.get('id'),
        descricao: params.get('descricao'),
        preco: params.get('preco'),
        fornecedor: params.get('fornecedor'),
        qtdestoque: params.get('qtdestoque')
    };
    return product;
}

function fillFields() {
    const product = getProduct();
    
    document.getElementById('txtid').setAttribute('value', product.id);
    document.getElementById('txtdescricao').setAttribute('value', product.descricao);
    document.getElementById('txtpreco').setAttribute('value', product.preco);
    document.getElementById('txtfornecedor').setAttribute('value', product.fornecedor);
    document.getElementById('txtqtdestoque').setAttribute('value', product.qtdestoque);
}

async function updateProduct() {
    const form = document.querySelector('#edit-form');
    const data = new FormData(form);
    const product = Object.fromEntries(data);

    const url = 'http://localhost:8080/produto/update';
    const option = {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(product)
    };
    
    const response = await fetch(url, option);
    if (response.status === 201) {
        alert('Produto Atualizado com Sucesso');
        window.location.href = 'listallproducts.html';
    } else {
        alert('Erro ao Atualizar o Produto');
    }
}

fillFields();