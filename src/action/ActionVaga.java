package action;

import java.util.ArrayList;
import java.util.Date;

import bd.relacional.VagaDAO;
import modelo.relacional.Vaga;

public class ActionVaga {
    
    /**
    * Cria uma vaga no banco.
    * Retorna verdadeiro se teve sucesso, falso caso contrário.
    */
    public static boolean insert(String cpf, String bairro, Double latitude, Double longitude,
            Double largura, Double comprimento, Double preco) {
        VagaDAO vagaDAO = new VagaDAO();        
        Vaga vaga = new Vaga();

        vaga.setCpfProprietario(cpf);
        vaga.setLatitude(latitude);
        vaga.setLongitude(longitude);
        vaga.setLargura(largura);
        vaga.setComprimento(comprimento);
        vaga.setPreco(preco);

        bd.documentos.VagaDAO vagaDAODoc = new bd.documentos.VagaDAO();        
        modelo.documentos.Vaga vagaDoc = new modelo.documentos.Vaga();

        bd.grafos.VagaDAO vagaDAOGrafos = new bd.grafos.VagaDAO();
        modelo.grafos.Vaga vagaGrafos = new modelo.grafos.Vaga();
        vagaGrafos.setBairro(bairro);

        try {
            int idVaga = vagaDAO.insert(vaga);

            vagaDoc.setIdVaga(idVaga);
            vagaDAODoc.insert(vagaDoc);

            vagaGrafos.setIdVaga(idVaga);
            vagaDAOGrafos.insert(vagaGrafos);
        } catch (RuntimeException e) {
            return false;
        }

        return true;
    }

    /**
    * Se teve sucesso, retorna um array de strings relativas às vagas próximas das coordenadas dadas
    * e disponíveis no momento fornecido caso haja vagas disponíveis. Senão, retorna os estacionamentos
    * próximos.
    * Retorna nulo se der erro.
    */
    public static ArrayList<String> listByLocationAndTime(String userCpf,
            Date inicio, Date fim, Double latitude, Double longitude) {
        VagaDAO vagaDAO = new VagaDAO();
        ArrayList<Vaga> vagas = new ArrayList<>();
        ArrayList<String> vagasAsString = new ArrayList<>();
            
        try {
            vagas = vagaDAO.listFreeByLocationAndTime(userCpf, latitude, longitude, inicio, fim);
            
            for (Vaga v : vagas) {
                String vagaAsString =
                        "idVaga: " + v.getIdVaga() + "\n" +
                        "Latitude: " + v.getLatitude() + "\n" +
                        "Longitude: "    + v.getLongitude() + "\n" +
                        "Largura: " + v.getLargura() + "\n" +
                        "Comprimento: "    + v.getComprimento();
                
                vagasAsString.add(vagaAsString);
            }
        } catch (RuntimeException e) {
            return null;
        }

        if (vagasAsString.isEmpty()) {
            bd.grafos.EstacionamentoDAO estacionamentoDAOGrafos = new bd.grafos.EstacionamentoDAO();

            ArrayList<modelo.grafos.Estacionamento> estacionamentos =
                    estacionamentoDAOGrafos.listByCoordinates(latitude, longitude);
            ArrayList<String> estacionamentosAsString = new ArrayList<>();

            estacionamentosAsString.add("Não encontramos nenhuma vaga, mas há opções de estacionamento disponíveis!");

            for (modelo.grafos.Estacionamento e : estacionamentos) {
                String estacionamentoAsString =
                        "Nome: " + e.getNome() + "\n" +
                        "Latitude: " + e.getLatitude() + "\n" +
                        "Longitude: "    + e.getLongitude();
                
                estacionamentosAsString.add(estacionamentoAsString);
            }

            return estacionamentosAsString;
        }

        return vagasAsString;
    }

    /**
    * Se teve sucesso, um array de strings relativas às vagas presentes no bairro do agente
    * fornecido.
    * Retorna nulo caso contrário.
    */
    public static ArrayList<String> listByAgenteBairro(String cpf) {
        bd.grafos.VagaDAO vagaDAOGrafos = new bd.grafos.VagaDAO();
        ArrayList<modelo.grafos.Vaga> vagas = new ArrayList<>();
        ArrayList<String> vagasAsString = new ArrayList<>();

        try {
            vagas = vagaDAOGrafos.listByAgenteBairro(cpf);
            
            for (modelo.grafos.Vaga v : vagas) {
                String vagaAsString =
                        "idVaga: " + v.getIdVaga() + "\n" +
                        "Latitude: " + v.getLatitude() + "\n" +
                        "Longitude: "    + v.getLongitude();
                
                vagasAsString.add(vagaAsString);
            }
        } catch (RuntimeException e) {
            return null;
        }

        return vagasAsString;
    }

    /**
    * Cria uma avaliação de um agente para a vaga fornecida.
    * Retorna verdadeiro se teve sucesso, falso caso contrário.
    */
    public static boolean insertAvaliacao(int idVaga, String cpfAgente, boolean avaliacao, String comentario) {
        bd.documentos.VagaDAO vagaDAOdoc = new bd.documentos.VagaDAO();
        modelo.documentos.Vaga vagaDoc;

        try {
            vagaDoc = vagaDAOdoc.get(idVaga);
            ArrayList<modelo.documentos.Avaliacao> avaliacoes = vagaDoc.getAvaliacoes();

            modelo.documentos.Avaliacao novaAvaliacao = new modelo.documentos.Avaliacao();
            novaAvaliacao.setCpfAgente(cpfAgente);
            novaAvaliacao.setComentario(comentario);
            novaAvaliacao.setResultado(avaliacao);

            avaliacoes.add(novaAvaliacao);
            vagaDoc.setAvaliacoes(avaliacoes);

            vagaDAOdoc.update(vagaDoc);
        } catch (RuntimeException e) {
            return false;
        }

        return true;
    }
}
