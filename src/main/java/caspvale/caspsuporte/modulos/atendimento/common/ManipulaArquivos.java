/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.modulos.atendimento.common;

import caspvale.caspsuporte.modulos.atendimento.exception.NegocioException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Hilton
 */
@Service
public class ManipulaArquivos {

    @Value("${spring.servlet.multipart.max-request-size}")
    private String max;

    private long TAMANHO_MAXIMO = 5;

    public static long parseTamanhoMaximoString(String tamanhoMaximoString) {
        // Verifica se a string não está vazia
        if (tamanhoMaximoString != null && !tamanhoMaximoString.isEmpty()) {
            // Usa expressão regular para extrair o valor numérico da string
            Matcher matcher = Pattern.compile("(\\d+)\\s*MB").matcher(tamanhoMaximoString);
            if (matcher.find()) {
                try {
                    // Obtém o grupo de captura correspondente ao valor numérico
                    String tamanhoMaximoValorString = matcher.group(1);
                    return Long.parseLong(tamanhoMaximoValorString);
                } catch (NumberFormatException e) {
                    // Se houver algum erro na conversão, retorna um valor padrão ou lança uma exceção, conforme sua necessidade
                }
            }
        }
        // Retorna um valor padrão ou lança uma exceção, caso a string não esteja no formato esperado
        return 0; // Ou outro valor padrão
    }

    private boolean existeFile(List<MultipartFile> file) {
        if (file == null || file.isEmpty()) {
            return false;
        }
        return !file.get(0).isEmpty();
    }

    public boolean tamanhoPermitido(List<MultipartFile> file) {
        TAMANHO_MAXIMO = parseTamanhoMaximoString(max);
        System.out.println("TAMANHO_MAXIMO "+TAMANHO_MAXIMO);
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
