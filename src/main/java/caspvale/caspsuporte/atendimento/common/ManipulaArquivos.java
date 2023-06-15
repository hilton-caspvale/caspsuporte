/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.atendimento.common;

import caspvale.caspsuporte.domain.exception.NegocioException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Hilton
 */
@Service
@lombok.AllArgsConstructor
public class ManipulaArquivos {

    private final long TAMANHO_MAXIMO = 20;

    private boolean existeFile(List<MultipartFile> file) {
        if (file == null || file.isEmpty()) {
            return false;
        }
        return !file.get(0).isEmpty();
    }

    public boolean tamanhoPermitido(List<MultipartFile> file) {
        if (existeFile(file)) {
            long total = 0;
            long mb = 1024L * 1024L;
            for (MultipartFile arquivo : file) {
                total += arquivo.getSize();
            }
            total = total / mb;
            if (total <= TAMANHO_MAXIMO) {
                return true;
            } else {
                throw new NegocioException("Tamanho excedido. O máximo permitido é " + TAMANHO_MAXIMO);
            }
        } else {
            throw new NegocioException("Anexo não informado");
        }
    }

}
