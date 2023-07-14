/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.atendimento.view.common;

/**
 *
 * @author Hilton
 */
public class StringToCSS {

    public static String mapDescricaoAreaToCSSClass(String descricaoArea) {
        switch (descricaoArea) {
            case "CONTABILIDADE":
                return "cor-contabil";
            case "ADMINISTRAÇÃO E GESTÃO":
                return "cor-compras";
            case "ARRECADAÇÃO E FISCALIZAÇÃO":
                return "cor-tributos";            
            case "FERRAMENTAS":
                return "cor-betha";
            case "PESSOAL E RECURSOS HUMANOS":
                return "cor-rh";
            case "SAÚDE":
                return "cor-saude";
            case "SUPORTE CASPVALE":
                return "cor-suporte";
            default:
                return "cor-betha";
        }
    }

}
