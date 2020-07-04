package action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import bd.relacional.AcordoDAO;
import bd.relacional.SolicitacaoDAO;
import bd.relacional.VagaDAO;
import modelo.relacional.Acordo;
import modelo.relacional.Solicitacao;
import modelo.relacional.Vaga;

public class ActionAcordo {

    public static Boolean insert(int idSolicitacao) {
        AcordoDAO acordoDAO = new AcordoDAO();
        Acordo acordo = new Acordo();

        acordo.setIdSolicitacao(idSolicitacao);

        bd.documentos.AcordoDAO acordoDAODoc = new bd.documentos.AcordoDAO();
        modelo.documentos.Acordo acordoDoc = new modelo.documentos.Acordo();

        try {
            int idAcordo = acordoDAO.insert(acordo);
            acordoDoc.setIdAcordo(idAcordo);
            acordoDAODoc.insert(acordoDoc);
        } catch (RuntimeException e) {
            return false;
        }

        return true;
    }

    /**
    * Se teve sucesso, retorna um array de strings relativas aos acordos futuros do motorista.
    * Retorna nulo caso contrário.
    */
    public static ArrayList<String> listFutureByMotorista(String cpf) {
        AcordoDAO acordoDAO = new AcordoDAO();
        ArrayList<Acordo> acordos = new ArrayList<>();
        ArrayList<String> acordosAsString = new ArrayList<>();

        try {
            acordos = acordoDAO.list();

            SolicitacaoDAO solicitacaoDao = new SolicitacaoDAO();
            
            for (Acordo a : acordos) {
                Solicitacao s = solicitacaoDao.get(a.getIdSolicitacao());

                Date now = new Date();
                if (s.getCpfMotorista().equals(cpf) && s.getInicio().compareTo(now) > 0) {
                    String acordoAsString =
                        "idAcordo: " + a.getIdAcordo() + "\n" +
                        "idSolicitacao: " + a.getIdAcordo() + "\n" +
                        "idVaga: " + s.getIdVaga() + "\n" +
                        "início: " + s.getInicio() + "\n" +
                        "fim: "    + s.getFim();
                
                acordosAsString.add(acordoAsString);
                }
            }
        } catch (RuntimeException e) {
            return null;
        }

        return acordosAsString;
    }

    /**
    * Se teve sucesso, retorna um array de strings relativas aos acordos passados do motorista.
    * Retorna nulo caso contrário.
    */
    public static ArrayList<String>  listPastByMotorista(String cpf) {
        AcordoDAO acordoDAO = new AcordoDAO();
        ArrayList<Acordo> acordos = new ArrayList<>();
        ArrayList<String> acordosAsString = new ArrayList<>();

        try {
            acordos = acordoDAO.list();

            SolicitacaoDAO solicitacaoDao = new SolicitacaoDAO();
            
            for (Acordo a : acordos) {
                Solicitacao s = solicitacaoDao.get(a.getIdSolicitacao());

                Date now = new Date();
                if (s.getCpfMotorista().equals(cpf) && s.getFim().compareTo(now) < 0) {
                    String acordoAsString =
                        "idAcordo: " + a.getIdAcordo() + "\n" +
                        "idSolicitacao: " + a.getIdAcordo() + "\n" +
                        "idVaga: " + s.getIdVaga() + "\n" +
                        "início: " + s.getInicio() + "\n" +
                        "fim: "    + s.getFim();
                
                acordosAsString.add(acordoAsString);
                }
            }
        } catch (RuntimeException e) {
            return null;
        }

        return acordosAsString;
    }

    /**
    * Se teve sucesso, retorna  um array de strings relativas aos acordos futuros do proprietário.
    * Retorna nulo caso contrário.
    */
    public static ArrayList<String>  listFutureByProprietario(String cpf) {
        AcordoDAO acordoDao = new AcordoDAO();
        ArrayList<Acordo> acordos = new ArrayList<>();
        ArrayList<String> acordosAsString = new ArrayList<>();

        try {
            acordos = acordoDao.list();

            SolicitacaoDAO solicitacaoDao = new SolicitacaoDAO();
            VagaDAO vagaDao = new VagaDAO();
            
            for (Acordo a : acordos) {
                Solicitacao s = solicitacaoDao.get(a.getIdSolicitacao());
                Vaga v = vagaDao.get(s.getIdVaga());


                Date now = new Date();
                if (v.getCpfProprietario().equals(cpf) && s.getInicio().compareTo(now) > 0) {
                    String acordoAsString =
                        "idAcordo: " + a.getIdAcordo() + "\n" +
                        "idSolicitacao: " + a.getIdAcordo() + "\n" +
                        "idVaga: " + s.getIdVaga() + "\n" +
                        "início: " + s.getInicio() + "\n" +
                        "fim: "    + s.getFim();
                
                acordosAsString.add(acordoAsString);
                }
            }
        } catch (RuntimeException e) {
            return null;
        }

        return acordosAsString;
    }

    /**
    * Se teve sucesso, retorna um array de strings relativas aos acordos passados do proprietário.
    * Retorna nulo caso contrário.
    */
    public static ArrayList<String>  listPastByProprietario(String cpf) {
        AcordoDAO acordoDAO = new AcordoDAO();
        ArrayList<Acordo> acordos = new ArrayList<>();
        ArrayList<String> acordosAsString = new ArrayList<>();

        try {
            acordos = acordoDAO.list();

            SolicitacaoDAO solicitacaoDao = new SolicitacaoDAO();
            VagaDAO vagaDao = new VagaDAO();
            
            for (Acordo a : acordos) {
                Solicitacao s = solicitacaoDao.get(a.getIdSolicitacao());
                Vaga v = vagaDao.get(s.getIdVaga());

                Date now = new Date();
                if (v.getCpfProprietario().equals(cpf) && s.getFim().compareTo(now) < 0) {
                    String acordoAsString =
                        "idAcordo: " + a.getIdAcordo() + "\n" +
                        "idSolicitacao: " + a.getIdAcordo() + "\n" +
                        "idVaga: " + s.getIdVaga() + "\n" +
                        "início: " + s.getInicio() + "\n" +
                        "fim: "    + s.getFim();
                
                acordosAsString.add(acordoAsString);
                }
            }
        } catch (RuntimeException e) {
            return null;
        }

        return acordosAsString;
    }

    /**
    * Insere a nota do motorista para o proprietário no acordo fornecido.
    * Retorna verdadeiro se teve sucesso, falso caso contrário.
    */
    public static boolean insertNotaToProprietario(int idAcordo, int nota) {
        bd.documentos.AcordoDAO acordoDAODoc = new bd.documentos.AcordoDAO();

        try {
            modelo.documentos.Acordo acordoDoc = acordoDAODoc.get(idAcordo);
            acordoDoc.setNotaMP(nota);
            acordoDAODoc.update(acordoDoc);
        } catch (RuntimeException e) {
            return false;
        }
        
        return true;
    }

    /**
    * Insere a denúncia do motorista para o proprietário no acordo fornecido.
    * Retorna verdadeiro se teve sucesso, falso caso contrário.
    */
    public static boolean insertDenunciaToProprietario(int idAcordo, String denuncia) {
        bd.documentos.AcordoDAO acordoDAODoc = new bd.documentos.AcordoDAO();

        try {
            modelo.documentos.Acordo acordoDoc = acordoDAODoc.get(idAcordo);
            ArrayList<modelo.documentos.Denuncia> denuncias = acordoDoc.getDenuncias();

            modelo.documentos.Denuncia novaDenuncia = new modelo.documentos.Denuncia();
            novaDenuncia.setComentario(denuncia);
            novaDenuncia.setPM(false);

            denuncias.add(novaDenuncia);
            acordoDoc.setDenuncias(denuncias);
            acordoDAODoc.update(acordoDoc);
        } catch (RuntimeException e) {
            return false;
        }

        return true;
    }

    /**
    * Insere a nota do proprietário para o motorista no acordo fornecido.
    * Retorna verdadeiro se teve sucesso, falso caso contrário.
    */
    public static boolean insertNotaToMotorista(int idAcordo, int nota) {
        bd.documentos.AcordoDAO acordoDAODoc = new bd.documentos.AcordoDAO();

        try {
            modelo.documentos.Acordo acordoDoc = acordoDAODoc.get(idAcordo);
            acordoDoc.setNotaPM(nota);
            acordoDAODoc.update(acordoDoc);
        } catch (RuntimeException e) {
            return false;
        }
        
        return true;
    }

    /**
    * Insere a denúncia do proprietário para o motorista no acordo fornecido.
    * Retorna verdadeiro se teve sucesso, falso caso contrário.
    */
    public static boolean insertDenunciaToMotorista(int idAcordo, String denuncia) {
        bd.documentos.AcordoDAO acordoDAODoc = new bd.documentos.AcordoDAO();

        try {
            modelo.documentos.Acordo acordoDoc = acordoDAODoc.get(idAcordo);
            ArrayList<modelo.documentos.Denuncia> denuncias = acordoDoc.getDenuncias();

            modelo.documentos.Denuncia novaDenuncia = new modelo.documentos.Denuncia();
            novaDenuncia.setComentario(denuncia);
            novaDenuncia.setPM(true);

            denuncias.add(novaDenuncia);
            acordoDoc.setDenuncias(denuncias);
            acordoDAODoc.update(acordoDoc);
        } catch (RuntimeException e) {
            return false;
        }

        return true;
    }
}
