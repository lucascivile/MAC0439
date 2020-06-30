package action;

import java.util.ArrayList;

import modelo.relacional.Solicitacao;

public class ActionSolicitacao {

    /**
    * Cria uma solicitação do motorista para a vaga.
    * Retorna verdadeiro se teve sucesso, falso caso contrário.
    */
    public static boolean insert(int idVaga, String cpfMotorista, String inicio, String fim) {
        Solicitacao solicitacao = new Solicitacao();

        solicitacao.setCpfMotorista(cpfMotorista);
        solicitacao.setIdVaga(idVaga);
        solicitacao.setInicio(inicio);
        solicitacao.setFim(fim);

        // SolicitacaoDAO dao = new SolicitacaoDAO();

        // try {
        //     dao.insert(solicitacao);
        // } catch (RuntimeException e) { -- runtime ou sqlerror? --
        //     return false;
        // }

        return true;
    }
    
    /**
    * Se teve sucesso, retorna um array de strings relativas às solicitações feitas pelo motorista,
    * incluindo as respostas a elas.
    * Retorna nulo caso contrário.
    */
    public static ArrayList<String> listByMotorista(String cpf) {
        // SolicitacaoDAO dao = new SolicitacaoDAO();
        ArrayList<Solicitacao> solicitacoes = new ArrayList<>();
        ArrayList<String> solicitacoesAsString = new ArrayList<>();
            
        // try {
        //     solicitacoes = dao.listByMotorista(cpf);
            
        //     for (Solicitacao s : solicitacoes) {
        //         String solicitacaoAsString =
        //                 "idSolicitacao: " + s.getIdSolicitacao() + "\n" +
        //                 "idVaga: " + s.getIdVaga() + "\n" +
        //                 "início: " + s.getInicio() + "\n" +
        //                 "fim: "    + s.getFim();
                
        //         Boolean resposta = s.getResposta();
        //         if (resposta != null) {
        //             solicitacaoAsString += "\nresposta: " + (resposta ? "aceita" : "recusada");
        //         }
                
        //         solicitacoesAsString.add(solicitacaoAsString);
        //     }
        // } catch (RuntimeException e) { runtime?
        //     return null;
        // }

        return solicitacoesAsString;
    }

    /**
    * Se teve sucesso, retorna um array de strings relativas às solicitações recebidas pelo proprietário,
    * mas não respondidas.
    * Retorna nulo caso contrário.
    */
    public static ArrayList<String> listUnansweredByProprietario(String cpf) {
        // SolicitacaoDAO dao = new SolicitacaoDAO();
        ArrayList<Solicitacao> solicitacoes = new ArrayList<>();
        ArrayList<String> solicitacoesAsString = new ArrayList<>();

        // try {
        //     solicitacoes = dao.listUnansweredByProprietario(cpf);
            
        //     for (Solicitacao s : solicitacoes) {
        //         String solicitacaoAsString =
        //                 "idSolicitacao: " + s.getIdSolicitacao() + "\n" +
        //                 "cpfMotorista: " + s.getCpfMotorista() + "\n" +
        //                 "idVaga: " + s.getIdVaga() + "\n" +
        //                 "início: " + s.getInicio() + "\n" +
        //                 "fim: "    + s.getFim();
                
        //         solicitacoesAsString.add(solicitacaoAsString);
        //     }
        // } catch (RuntimeException e) { runtime?
        //     return null;
        // }

        return solicitacoesAsString;
    }

    /**
    * Atualiza a resposta a uma solicitação. Caso seja positiva, deve-se criar um acordo no banco.
    * Retorna verdadeiro se teve sucesso, falso caso contrário.
    */
    public static boolean updateResposta(int idSolicitacao, boolean resposta) {
        // SolicitacaoDAO dao = new SolicitacaoDAO();
        Solicitacao solicitacao = new Solicitacao(); // retirar a atribuição

        // try {
        //     solicitacao = dao.get(idSolicitacao);
        // } catch (RuntimeException e) { // runtime?
        //     return false;
        // }

        solicitacao.setResposta(resposta);

        // try {
        //     dao.update(solicitacao);
        // } catch (RuntimeException e) {
        //     return false;
        // }

        return true;
    }
}
