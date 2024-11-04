function clearLoading() {
    document.getElementById('loading').style.display = "none";
}

function showProducts(products) {
    let tab = `
    <thead>
        <th>ID</th>
        <th>Descrição</th>
        <th>Preço</th>
        <th>Fornecedor</th>
        <th>Qtd. Estoque</th>
        <th>Editar</th>
        <th>Remover</th>
    </thead>`;

    for (let product of products) {
        tab += `
        <tr>
            <td>${product.id}</td>
            <td>${product.descricao}</td>
            <td>${product.preco}</td>
            <td>${product.fornecedor}</td>
            <td>${product.qtdestoque}</td>
            <td>
                <img src="imgs/edit-icon.svg" onclick="openEditForm(${product.id})" width="20" heigth="15">
            </td>
            <td>
                <img src="imgs/trash-icon.svg" onclick="removeProduct(${product.id})" width="20" heigth="15">
            </td>
        </tr>`;
    }
    document.getElementById("products").innerHTML = tab;
}

async function listAllProducts() {
    const url = "http://localhost:8080/produto/listall"
    const dados = await fetch(url, { method: "GET" });
    if (dados.status === 200) {
        const products = await dados.json();
        if (products) {
            clearLoading();
        }
        showProducts(products);
    }
}

async function openEditForm(id) {
    const product = await searchProduct(id);
    const params = new URLSearchParams({
        id: product.id,
        descricao: product.descricao,
        preco: product.preco,
        fornecedor: product.fornecedor,
        qtdestoque: product.qtdestoque
    }).toString();


    window.location.href = `editform.html?${params}`;
}

async function searchProduct(id) {
    const product = await fetch(`http://localhost:8080/produto/list/${id}`);
    if (product.status === 200) {
        return product.json();
    } else {
        return null;
    }
}

async function removeProduct(id) {
    const url = `http://localhost:8080/produto/delete/${id}`
    const option = {
        method: 'DELETE'
    };
    if (confirm('Deseja remover o produto?')) {
        const response = await fetch(url, option);
        if (response.status === 204) {
            alert('Produto removido com sucesso')
        } else {
            alert('Falha ao remover o produto')
        }
    }
    listAllProducts();
}

listAllProducts();