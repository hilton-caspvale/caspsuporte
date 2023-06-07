function getChamado() {
    let listaEntidades = document.getElementById("entidades")||null;
    let entidades = [];
    for (i = 0; i < listaEntidades.length; i = i + 1) {
        let entidade = {ientidade: listaEntidades.options[i].value,
            nomeEntidade: listaEntidades.options[i].text};
        entidades.push(entidade);
    }
    let listaAreas = document.getElementById("areas")||null;
    let areas = [];
    for (i = 0; i < listaAreas.length; i = i + 1) {
        let area = {iarea: listaAreas.options[i].value,
            descricaoArea: listaAreas.options[i].text};
        areas.push(area);
    }
    let listaSistemas = document.getElementById("sistemas")||null;
    let sistemas = [];
    for (i = 0; i < listaSistemas.length; i = i + 1) {
        let sistema = {isistema: listaSistemas.options[i].value,
            descricaoSistema: listaSistemas.options[i].text};
        sistemas.push(sistema);
    }
    let listaProblemas = document.getElementById("problemas")||null;
    let problemas = [];
    for (i = 0; i < listaProblemas.length; i = i + 1) {
        let problema = {iproblema: listaProblemas.options[i].value,
            descricaoProblema: listaProblemas.options[i].text};
        problemas.push(problema);
    }
    let listaNiveis = document.getElementById("nivel")||null;
    let nivel = listaNiveis.options[listaNiveis.selectedIndex].value||null;
    let listaOrigens = document.getElementById("origem")||null;
    let origem = listaOrigens.options[listaOrigens.selectedIndex].value||null;
    let listaPrioridades = document.getElementById("prioridade")||null;
    let prioridade = listaPrioridades.options[listaPrioridades.selectedIndex].value||null;
    let iChamado = document.getElementById('iChamado')||null;
    let iSituacao = document.getElementById('iSituacao')||null;

    let iusuarioAbertura = {iusuario: document.getElementById('iusuarioAbertura').value||null};
    let chamado = {
        iusuarioAbertura: document.getElementById('iusuarioAbertura').value||null,
        contatoSolicitante: document.getElementById('contato').value||null,
        emailSolicitante: document.getElementById('email').value||null,
        caspEntidadesList: entidades,
        caspAreasList: areas,
        caspSistemasList: sistemas,
        caspProblemasList: problemas,
        inivel: nivel,
        iorigemChamado: origem,
        iprioridade: prioridade,
        resumoChamado: document.getElementById('resumoChamado').value||null,
        descricaoChamado: document.getElementById('descricaoChamado').value||null,
        descricaoProblema: document.getElementById('descricaoProblema').value||null,
        descricaoSolucao: document.getElementById('descricaoSolucao').value||null
    };
    let dto = JSON.stringify(chamado);
    return dto;
}
