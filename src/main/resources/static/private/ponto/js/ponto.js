function moduloPonto() {
    requestDef('/mv/ponto', 'GET', null)
            .then(response => {
                $('#root').html(response);
            })
            .catch(error => {
                requestError(error, 'alertaGeral');
            });
}