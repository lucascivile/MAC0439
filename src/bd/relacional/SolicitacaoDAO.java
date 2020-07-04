package bd.relacional;

import java.util.ArrayList;

import modelo.relacional.Solicitacao;

public class SolicitacaoDAO {
    
    // private Connection connection;

    public SolicitacaoDAO() {
		// this.connection = ConnectionFactory.getInstance().getConnection();
    }

    /**
    * Cria a solicitação no banco.
    */
    public void insert(Solicitacao solicitacao) {
    }

    /**
    * Atualiza a solicitação no banco.
    */
    public void update(Solicitacao solicitacao) {
	}

    /**
    * Retorna a solicitação com o id fornecido.
    */
    public Solicitacao get(int idSolicitacao) {
        return null;
    }

    /**
    * Retorna um ArrayList de todas as solicitações realizadas pelo motorista
    * com o cpf fornecido.
    */
    public ArrayList<Solicitacao> listByCpfMotorista(String cpf) {
        return null;
    }

    /**
    * Retorna um ArrayList de todas as solicitações sem resposta do proprietário
    * cujo cpf é fornecido.
    */
    public ArrayList<Solicitacao> listUnansweredByCpfProprietario(String cpf) {
        return null;
    }
}
