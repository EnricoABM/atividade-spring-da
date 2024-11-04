function clearTextFields() {
    document.getElementById("descricao").value = "";
    document.getElementById("preco").value = "";
    document.getElementById("fornecedor").value = "";
    document.getElementById("qtdestoque").value = "";
}

async function addProduct() {
    const formE1 = document.querySelector("#formadd");
    const formData = new FormData(formE1);
    const product = Object.fromEntries(formData);

    const url = "http://localhost:8080/produto/add";
    const option = {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(product)
    }

    const result = await fetch(url, option);
    if (result.status === 201) {
        clearTextFields();
        console.log();
        await alert('Cadastrado com Sucesso');
    } else {
        alert('Erro ao cadastrar')
    }
}
