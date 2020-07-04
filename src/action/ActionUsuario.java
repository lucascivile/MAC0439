package action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import bd.relacional.AcordoDAO;
import bd.relacional.AgenteDAO;
import bd.relacional.MotoristaDAO;
import bd.relacional.ProprietarioDAO;
import bd.relacional.SolicitacaoDAO;
import bd.relacional.UsuarioDAO;
import bd.relacional.VagaDAO;
import modelo.relacional.Vaga;
import modelo.relacional.Acordo;
import modelo.relacional.Agente;
import modelo.relacional.Motorista;
import modelo.relacional.Proprietario;
import modelo.relacional.Solicitacao;
import modelo.relacional.Usuario;

public class ActionUsuario {

    /**
    * Cria um usuário e o proprietário correspondente no banco.
    * Retorna verdadeiro se teve sucesso, falso caso contrário.
    */
    public static boolean insertProprietario(String nome, String cpf, String email,
            String senha, Date nascimento, String logradouro, String numero,
            String complemento, String cep) {
        Usuario usuario = new Usuario();
        UsuarioDAO usuarioDao = new UsuarioDAO();

        usuario.setCpf(cpf);
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setNascimento(nascimento);

        bd.documentos.UsuarioDAO usuarioDAODoc = new bd.documentos.UsuarioDAO();
        modelo.documentos.Usuario usuarioDoc = new modelo.documentos.Usuario();
        usuarioDoc.setCpf(cpf);

        try {
            usuarioDao.insert(usuario);
            usuarioDAODoc.insert(usuarioDoc);
        } catch (RuntimeException e) {
            return false;
        }

        Proprietario proprietario = new Proprietario();
        ProprietarioDAO proprietarioDao = new ProprietarioDAO();

        proprietario.setCpfUsuario(cpf);
        proprietario.setLogradouro(logradouro);
        proprietario.setNumero(numero);
        proprietario.setComplemento(complemento);
        proprietario.setCep(cep);

        try {
            proprietarioDao.insert(proprietario);
        } catch (RuntimeException e) {
            usuarioDao.remove(cpf);
            return false;
        }

        return true;
    }

    /**
    * Cria um usuário e o motorista correspondente no banco.
    * Retorna verdadeiro se teve sucesso, falso caso contrário.
    */
    public static boolean insertMotorista(String nome, String cpf, String email,
            String senha, Date nascimento, String cnh) {
        Usuario usuario = new Usuario();
        UsuarioDAO usuarioDao = new UsuarioDAO();

        usuario.setCpf(cpf);
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setNascimento(nascimento);

        bd.documentos.UsuarioDAO usuarioDAODoc = new bd.documentos.UsuarioDAO();
        modelo.documentos.Usuario usuarioDoc = new modelo.documentos.Usuario();
        usuarioDoc.setCpf(cpf);

        try {
            usuarioDao.insert(usuario);
            usuarioDAODoc.insert(usuarioDoc);
        } catch (RuntimeException e) {
            return false;
        }


        Motorista motorista = new Motorista();
        MotoristaDAO motoristaDao = new MotoristaDAO();

        motorista.setCpfUsuario(cpf);
        motorista.setCnh(cnh);

        try {
            motoristaDao.insert(motorista);
        } catch (RuntimeException e) {
            usuarioDao.remove(cpf);
            return false;
        }

        return true;
    }

    /**
    * Cria um usuário e o proprietário e motorista correspondentes no banco.
    * Retorna verdadeiro se teve sucesso, falso caso contrário.
    */
    public static boolean insertProprietarioMotorista(String nome, String cpf, String email,
            String senha, Date nascimento, String logradouro, String numero,
            String complemento, String cep, String cnh) {
        Usuario usuario = new Usuario();
        UsuarioDAO usuarioDao = new UsuarioDAO();

        usuario.setCpf(cpf);
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setNascimento(nascimento);

        bd.documentos.UsuarioDAO usuarioDAODoc = new bd.documentos.UsuarioDAO();
        modelo.documentos.Usuario usuarioDoc = new modelo.documentos.Usuario();
        usuarioDoc.setCpf(cpf);

        try {
            usuarioDao.insert(usuario);
            usuarioDAODoc.insert(usuarioDoc);
        } catch (RuntimeException e) {
            return false;
        }


        Proprietario proprietario = new Proprietario();
        ProprietarioDAO proprietarioDao = new ProprietarioDAO();

        proprietario.setCpfUsuario(cpf);
        proprietario.setLogradouro(logradouro);
        proprietario.setNumero(numero);
        proprietario.setComplemento(complemento);
        proprietario.setCep(cep);

        try {
            proprietarioDao.insert(proprietario);
        } catch (RuntimeException e) {
            usuarioDao.remove(cpf);
            return false;
        }

        Motorista motorista = new Motorista();
        MotoristaDAO motoristaDao = new MotoristaDAO();

        motorista.setCpfUsuario(cpf);
        motorista.setCnh(cnh);

        try {
            motoristaDao.insert(motorista);
        } catch (RuntimeException e) {
            motoristaDao.remove(cpf);
            usuarioDao.remove(cpf);
            return false;
        }

        return true;
    }

    /**
    * Cria um usuário e o agente correspondente no banco.
    * Retorna verdadeiro se teve sucesso, falso caso contrário.
    */
    public static boolean insertAgente(String nome, String cpf, String email,
            String senha, Date nascimento, String registroMunicipal, String bairro) {
        Usuario usuario = new Usuario();
        UsuarioDAO usuarioDao = new UsuarioDAO();

        usuario.setCpf(cpf);
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setNascimento(nascimento);

        bd.grafos.AgenteDAO agenteDAOGrafos = new bd.grafos.AgenteDAO ();
        modelo.grafos.Agente agenteGrafos = new modelo.grafos.Agente();
        agenteGrafos.setCpf(cpf);
        agenteGrafos.setBairro(bairro);

        try {
            usuarioDao.insert(usuario);
            agenteDAOGrafos.insert(agenteGrafos);
        } catch (RuntimeException e) {
            return false;
        }


        Agente agente = new Agente();
        AgenteDAO agenteDao = new AgenteDAO();

        agente.setCpfUsuario(cpf);
        agente.setRegistroMunicipal(registroMunicipal);

        try {
            agenteDao.insert(agente);
        } catch (RuntimeException e) {
            usuarioDao.remove(cpf);
            return false;
        }

        return true;  
    }

    /**
    * Se as credenciais fornecidas existem no banco, retorna um array com o cpf e o
    * tipo do usuário ("AGENTE", "PROPRIETARIO, etc.") 
    * Retorna nulo caso contrário.
    */
    public static ArrayList<String> login(String email, String senha) {
        Usuario usuario;
        UsuarioDAO usuarioDao = new UsuarioDAO();
        AgenteDAO agenteDao = new AgenteDAO();
        ProprietarioDAO proprietarioDao = new ProprietarioDAO();
        MotoristaDAO motoristaDao = new MotoristaDAO();
        String cpf, tipo;

        try {
            usuario = usuarioDao.get(email, senha);
            if (usuario != null) {
                cpf = usuario.getCpf();

                if (agenteDao.get(cpf) != null) {
                    tipo = "AGENTE";
                } else if (proprietarioDao.get(cpf) != null) {
                    if (motoristaDao.get(cpf) != null) {
                        tipo = "PROPRIETARIO_MOTORISTA";
                    } else {
                        tipo = "MOTORISTA";
                    }
                } else {
                    tipo = "MOTORISTA";
                }
            } else {
                return null;
            }
        } catch (RuntimeException e) {
            return null;
        }

        return new ArrayList<String>(Arrays.asList(cpf, tipo));
    }
    
    /**
    * Retorna a nota média do usuário ou nulo, se houver erro.
    */
    public static Double getNotaMedia(String cpf, String tipoUsuario) {
        AcordoDAO acordoDao = new AcordoDAO();
        ArrayList<Acordo> acordos = new ArrayList<>();
        SolicitacaoDAO solicitacaoDao = new SolicitacaoDAO();
        bd.documentos.AcordoDAO acordoDAODoc = new bd.documentos.AcordoDAO();

        double somaNota = 0;
        int qtdeNotas = 0;

        if (tipoUsuario == "MOTORISTA") {
            try {
                acordos = acordoDao.list();
                
                for (Acordo a : acordos) {
                    Solicitacao s = solicitacaoDao.get(a.getIdSolicitacao());

                    Date now = new Date();
                    if (s.getCpfMotorista().equals(cpf) && s.getFim().compareTo(now) < 0) {
                        Integer nota = acordoDAODoc.get(a.getIdAcordo()).getNotaPM();
                        if (nota != null) {
                            somaNota += nota;
                            qtdeNotas += 1;
                        }
                    }
                }
            } catch (RuntimeException e) {
                return null;
            }
        } else if (tipoUsuario == "PROPRIETARIO") {
            try {
                acordos = acordoDao.list();
                VagaDAO vagaDao = new VagaDAO();
                
                for (Acordo a : acordos) {
                    Solicitacao s = solicitacaoDao.get(a.getIdSolicitacao());
                    Vaga v = vagaDao.get(s.getIdVaga());

                    Date now = new Date();
                    if (v.getCpfProprietario().equals(cpf) && s.getFim().compareTo(now) < 0) {
                        Integer nota = acordoDAODoc.get(a.getIdAcordo()).getNotaPM();
                        if (nota != null) {
                            somaNota += nota;
                            qtdeNotas += 1;
                        }
                    }
                }
            } catch (RuntimeException e) {
                return null;
            }
        }

        return qtdeNotas > 0 ? somaNota/qtdeNotas : -1;
    }

    /**
    * Atualiza o endereço do proprietário no banco.
    * Retorna verdadeiro se teve sucesso, falso caso contrário.
    */
    public static boolean updateEnderecoProprietario(String cpf, String logradouro,
            String numero, String complemento, String cep) {
        ProprietarioDAO proprietarioDAO = new ProprietarioDAO();
        Proprietario proprietario;

        try {
            proprietario = proprietarioDAO.get(cpf);
            proprietario.setLogradouro(logradouro);
            proprietario.setNumero(numero);
            proprietario.setComplemento(complemento);
            proprietario.setCep(cep);

            proprietarioDAO.update(proprietario);
        } catch (RuntimeException e) {
            return false;
        }

        return true;
    }

    /**
    * Atualiza o bairro do agente no banco.
    * Retorna verdadeiro se teve sucesso, falso caso contrário.
    */
    public static boolean updateBairroAgente(String cpf, String bairro) {
        bd.grafos.AgenteDAO agenteDAOGrafos = new bd.grafos.AgenteDAO();
        modelo.grafos.Agente agenteGrafos;

        try {
            agenteGrafos = agenteDAOGrafos.get(cpf);
            agenteGrafos.setBairro(bairro);
            agenteDAOGrafos.update(agenteGrafos);
        } catch (RuntimeException e) {
            return false;
        }

        return true;
    }
}
