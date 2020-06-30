import java.util.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import action.*;

// banco: resposta pra solicitacao
// conceitual: preco vaga

public class EasyPark {

    private static enum Screen {
        EXIT_SYSTEM,
        INITIAL,
        SIGNUP,
        LOGIN,
        HOME_AGENTE,
        HOME_PROPRIETARIO,
        HOME_MOTORISTA,
        HOME_PROPRIETARIO_MOTORISTA
    };
    
    private static Scanner scanner;

    private static String userCpf;

    private static long requestLong(String message) {
        System.out.println(message);
        return Long.parseLong(scanner.nextLine());
    }

    private static int requestInt(String message) {
        System.out.println(message);
        return Integer.parseInt(scanner.nextLine());
    }

    private static String requestString(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    private static Screen homeProprietarioMotoristaScreen() {
        int selectedMode = 0;

        while (!Arrays.asList(1, 2).contains(selectedMode)) {
            System.out.println();
            System.out.println(
                "--------------------------------------------------------------------\n" +
                "| 1 - Proprietário                                                 |\n" +
                "| 2 - Motorista                                                    |\n" +
                "--------------------------------------------------------------------"
            );
            selectedMode = requestInt("Digite o código relativo ao perfil que você quer usar:");
        }

        return selectedMode == 1 ? Screen.HOME_PROPRIETARIO : Screen.HOME_MOTORISTA;
    }

    private static Screen homeMotoristaScreen() {
        int selectedAction = 0;

        ArrayList<String> futureAcordosDetails =
            ActionAcordo.listFutureByMotorista(userCpf);

        if (futureAcordosDetails != null) {
            System.out.println("Seus acordos futuros:");
            for (String acordo : futureAcordosDetails) {
                System.out.println(acordo);
            }
        } else {
            System.out.println("Não conseguimos obter seus acordos futuros");
        }

        while (!Arrays.asList(-1, 1, 2, 3, 4, 5).contains(selectedAction)) {
            System.out.println();
            System.out.println(
                "--------------------------------------------------------------------\n" +
                "| 1 - Inserir um veículo                                           |\n" +
                "| 2 - Listar minhas solicitações (com e sem resposta)              |\n" +
                "| 3 - Listar vagas para alugar                                     |\n" +
                "| 4 - Avaliar proprietários ou fazer denúncias                     |\n" +
                "| 5 - Ver minha nota média                                         |\n" +
                "| 6 - Logout                                                       |\n" +
                "|                                                                  |\n" +
                "| -1 - Encerrar o Easy Park                                        |\n" +
                "--------------------------------------------------------------------"
            );
            selectedAction = requestInt("Digite o código relativo à ação que deseja executar:");
        }

        if (selectedAction == -1) {
            return Screen.EXIT_SYSTEM;
        } else if (selectedAction == 1) {
            String modelo = requestString("Modelo: ");
            int ano = requestInt("Ano: ");
            String cor = requestString("Cor: ");
            String placa = requestString("Placa: ");

            if (ActionVeiculo.insert(userCpf, modelo, ano, cor, placa)) {
                System.out.println("Veículo adicionado com sucesso!");
            } else {
                System.out.println("Não conseguimos adicionar este veículo");
            }
        } else if (selectedAction == 2) {
            ArrayList<String> solicitacoesDetails = 
                ActionSolicitacao.listByMotorista(userCpf);

            if (solicitacoesDetails != null) {
                for (String solicitacao : solicitacoesDetails) {
                    System.out.println(solicitacao);
                }
            } else {
                System.out.println("Não conseguimos listar suas solicitações");
            }
        } else if (selectedAction == 3) {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String inicio, fim;

            while (true) {
                inicio = requestString("Início do uso (no formato dd/mm/aaa hh:mm):");
                try {
                    format.parse(inicio);
                    break;
                } catch (ParseException e) {
                    System.out.println("Período inválido!");
                }
            }

            while (true) {
                fim = requestString("Fim do uso (no formato dd/mm/aaa hh:mm):");
                try {
                    format.parse(fim);
                    break;
                } catch (ParseException e) {
                    System.out.println("Período inválido!");
                }
            }
    
            Long latitude = requestLong("Latitude desejada:");
            Long longitude = requestLong("Longitude desejada: ");

            ArrayList<String> vagasDetails = 
                ActionVaga.listByLocationAndTime(userCpf, inicio, fim, latitude, longitude);

            if (vagasDetails != null) {
                for (String vaga : vagasDetails) {
                    System.out.println(vaga);
                }

                int idVaga = requestInt("Digite o id da vaga desejada: ");

                if (ActionSolicitacao.insert(idVaga, userCpf, inicio, fim)) {
                    System.out.println("Solicitação realizada com sucesso!");
                } else {
                    System.out.println("Não conseguimos realizar a sua solicitação");
                }
            } else {
                System.out.println("Não conseguimos listar as vagas disponíveis");
            }
        } else if (selectedAction == 4) {
            ArrayList<String> pastAcordosDetails = ActionAcordo.listPastByMotorista(userCpf);

            if (pastAcordosDetails != null) {
                for (String acordo : pastAcordosDetails) {
                    System.out.println(acordo);
                }

                int idAcordo = requestInt("Digite o id do acordo cujo proprietário você deseja avaliar e/ou denunciar: ");

                int nota = 0;
                while (nota < 1 || nota > 5) {
                    nota = requestInt("Avalie com uma nota de 1 a 5: ");
                }

                String denuncia = requestString("Escreva sua denúncia (presione enter caso não haja): ");

                if (ActionAcordo.insertNotaToProprietario(idAcordo, nota)) {
                    System.out.println("Nota registrada com sucesso!");
                } else {
                    System.out.println("Não conseguimos registrar sua nota");
                }

                if (ActionAcordo.insertDenunciaToProprietario(idAcordo, denuncia)) {
                    System.out.println("Denúncia registrada com sucesso!");
                } else {
                    System.out.println("Não conseguimos registrar sua denúncia");
                }
            } else {
                System.out.println("Não conseguimos listar seus acordos");
            }
        } else if (selectedAction == 5) {
            Long notaMedia = ActionUsuario.getNotaMedia(userCpf);
            if (notaMedia != null) {
                System.out.println("Sua nota média é " + notaMedia);
            } else {
                System.out.println("Não conseguimos obter sua nota média");
            }
        } else if (selectedAction == 6) {
            userCpf = null;
            return Screen.INITIAL;
        }

        return Screen.HOME_MOTORISTA;
    }

    private static Screen homeProprietarioScreen() {
        int selectedAction = 0;

        ArrayList<String> futureAcordosDetails = ActionAcordo.listFutureByProprietario(userCpf);

        if (futureAcordosDetails != null) {
            System.out.println("Seus acordos futuros:");
            for (String acordo : futureAcordosDetails) {
                System.out.println(acordo);
            }
        } else {
            System.out.println("Não conseguimos obter seus acordos futuros");
        }

        ArrayList<String> solicitacoesDetails =
            ActionSolicitacao.listUnansweredByProprietario(userCpf);

        if (solicitacoesDetails != null) {
            if (!solicitacoesDetails.isEmpty()) {
                System.out.println("\nVocê tem uma ou mais solicitações de vaga para responder!");
            }
        } else {
            System.out.println("\nNão conseguimos obter as solicitações para suas vagas");
        }

        while (!Arrays.asList(-1, 1, 2, 3, 4, 5, 6).contains(selectedAction)) {
            System.out.println();
            System.out.println(
                "--------------------------------------------------------------------\n" +
                "| 1 - Alterar endereço                                             |\n" +
                "| 2 - Inserir uma vaga                                             |\n" +
                "| 3 - Responder a uma solicitação de uso de vaga                   |\n" +
                "| 4 - Avaliar motoristas ou fazer denúncias                        |\n" +
                "| 5 - Ver minha nota média                                         |\n" +
                "| 6 - Logout                                                       |\n" +
                "|                                                                  |\n" +
                "| -1 - Encerrar o Easy Park                                        |\n" +
                "--------------------------------------------------------------------"
            );
            selectedAction = requestInt("Digite o código relativo à ação que deseja executar:");
        }

        if (selectedAction == -1) {
            return Screen.EXIT_SYSTEM;
        } else if (selectedAction == 1) {
            String logradouro = requestString("Novo logradouro do endereço (ex.: Avenida Paulista): ");
            String numero = requestString("Novo número do endereço: ");
            String complemento = requestString("Novo complemento (pressione enter se não houver): ");
            String cep = requestString("Novo CEP (somente dígitos): ");

            if (ActionUsuario.updateEnderecoProprietario(userCpf, logradouro, numero, complemento, cep)) {
                System.out.println("Endereço atualizado com sucesso!");
            } else {
                System.out.println("Não conseguimos atualizar o seu endereço");
            }
        } else if (selectedAction == 2) {
            String bairro = requestString("Bairro da vaga: ");
            Long latitude = requestLong("Latitude: ");
            Long longitude = requestLong("Longitude: ");
            Long largura = requestLong("Largura: ");
            Long comprimento = requestLong("Comprimento: ");
            Long preco = requestLong("Preço por hora: ");

            if (ActionVaga.insert(userCpf, bairro, latitude, longitude, largura, comprimento, preco)) {
                System.out.println("Vaga adicionada com sucesso!");
                System.out.println("Lembre-se que ela só poderá ser disponibilizada após a aprovação de um agente municipal");
            } else {
                System.out.println("Não conseguimos adicionar esta vaga");
            }
        } else if (selectedAction == 3) {
            solicitacoesDetails = ActionSolicitacao.listUnansweredByProprietario(userCpf);

            if (solicitacoesDetails != null) {
                for (String solicitacao : solicitacoesDetails) {
                    System.out.println(solicitacao);
                }

                int idSolicitacao = Integer.parseInt("Digite o id da solicitação a que deseja responder: ");

                Boolean resposta = null;
                while (resposta == null) {
                    String r = requestString("\nSolicitação aceita ou reprovada? (a/r) ");
                    if (r == "a") {
                        resposta = true;
                    } else if (r == "r") {
                        resposta = false;
                    }
                }

                if (ActionSolicitacao.updateResposta(idSolicitacao, resposta)) {
                    System.out.println("Resposta registrada com sucesso!");
                } else {
                    System.out.println("Não conseguimos registrar sua resposta");
                }
            } else {
                System.out.println("Não conseguimos listar as solicitacoes");
            }
        } else if (selectedAction == 4) {
            ArrayList<String> pastAcordosDetails = ActionAcordo.listPastByProprietario(userCpf);

            if (pastAcordosDetails != null) {
                for (String acordo : pastAcordosDetails) {
                    System.out.println(acordo);
                }

                int idAcordo = requestInt("Digite o id do acordo cujo motorista você deseja avaliar e/ou denunciar: ");

                int nota = 0;
                while (nota < 1 || nota > 5) {
                    nota = requestInt("Avalie com uma nota de 1 a 5: ");
                }

                String denuncia = requestString("Escreva sua denúncia (presione enter caso não haja): ");

                if (ActionAcordo.insertNotaToMotorista(idAcordo, nota)) {
                    System.out.println("Nota registrada com sucesso!");
                } else {
                    System.out.println("Não conseguimos registrar sua nota");
                }

                if (ActionAcordo.insertDenunciaToMotorista(idAcordo, denuncia)) {
                    System.out.println("Denúncia registrada com sucesso!");
                } else {
                    System.out.println("Não conseguimos registrar sua denúncia");
                }
            } else {
                System.out.println("Não conseguimos listar seus acordos");
            }
        } else if (selectedAction == 5) {
            Long notaMedia = ActionUsuario.getNotaMedia(userCpf);
            if (notaMedia != null) {
                System.out.println("Sua nota média é " + notaMedia);
            } else {
                System.out.println("Não conseguimos obter sua nota média");
            }
        } else if (selectedAction == 6) {
            userCpf = null;
            return Screen.INITIAL;
        }

        return Screen.HOME_PROPRIETARIO;
    }

    private static Screen homeAgenteScreen() {
        int selectedAction = 0;

        while (!Arrays.asList(-1, 1, 2, 3).contains(selectedAction)) {
            System.out.println();
            System.out.println(
                "--------------------------------------------------------------------\n" +
                "| 1 - Alterar bairro de atuação                                    |\n" +
                "| 2 - Listar vagas no bairro de atuação                            |\n" +
                "| 3 - Avaliar uma vaga                                             |\n" +
                "| 4 - Logout                                                       |\n" +
                "|                                                                  |\n" +
                "| -1 - Encerrar o Easy Park                                        |\n" +
                "--------------------------------------------------------------------"
            );
            selectedAction = requestInt("Digite o código relativo à ação que deseja executar: ");
        }

        if (selectedAction == -1) {
            return Screen.EXIT_SYSTEM;
        } else if (selectedAction == 1) {
            String bairro = requestString("\nNovo bairro:");

            if (ActionUsuario.updateBairroAgente(userCpf, bairro)) {
                System.out.println("Bairro atualizado com sucesso!");
            } else {
                System.out.println("Não conseguimos atualizar o bairro");
            }
        } else if (selectedAction == 2) {
            ArrayList<String> vagasDetails = ActionVaga.listByAgenteBairro(userCpf);

            if (vagasDetails != null) {
                for (String vaga : vagasDetails) {
                    System.out.println(vaga);
                }
            } else {
                System.out.println("Não conseguimos listar as vagas");
            }
        } else if (selectedAction == 3) {
            int idVaga = requestInt("\nSelecione a vaga pelo id: ");

            Boolean avaliacao = null;
            while (avaliacao == null) {
                String res = requestString("\nVaga aprovada ou reprovada? (a/r) ");

                if (res == "a") {
                    avaliacao = true;
                } else if (res == "r") {
                    avaliacao = false;
                }
            }

            String comentario = requestString("\nComentário (pressione enter se não houver): ");

            if (ActionVaga.insertAvaliacao(idVaga, userCpf, avaliacao, comentario)) {
                System.out.println("Avaliação realizada com sucesso!");
            } else {
                System.out.println("Não conseguimos adicionar esta avaliação");
            }
        } else if (selectedAction == 4) {
            userCpf = null;
            return Screen.INITIAL;
        }

        return Screen.HOME_AGENTE;
    }

    private static Screen signUpScreen() {
        int tipoUsuario = -1;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        String nome = requestString("Nome: ");
        String cpf = requestString("CPF: ");
        String email = requestString("Email: ");
        String senha = requestString("Senha: ");
        String nascimento;

        while (true) {
            nascimento = requestString("\nData de nascimento (no formato dd/mm/aaaa):");
            try {
                format.parse(nascimento);
                break;
            } catch (ParseException e) {
                System.out.println("Data inválida!");
            }
        }

        while (!Arrays.asList(1, 2, 3, 4).contains(tipoUsuario)) {
            System.out.println();
            System.out.println(
                "--------------------------------------------------------------------\n" +
                "| 1 - Agente municipal                                             |\n" +
                "| 2 - Proprietário                                                 |\n" +
                "| 3 - Motorista                                                    |\n" +
                "| 4 - Proprietário e motorista                                     |\n" +
                "--------------------------------------------------------------------"
            );
            tipoUsuario = requestInt("Digite o código relativo ao tipo do usuário:");
        }

        if (tipoUsuario == 1) {
            String registroMunicipal = requestString("Registro municipal: ");
            String bairro = requestString("Bairro de atuação: ");

            if (ActionUsuario.insertAgente(nome, cpf, email, senha, nascimento, registroMunicipal, bairro)) {
                System.out.println("Agente cadastrado com sucesso");
            } else {
                System.out.println("Não conseguimos cadastrar este agente");
            }
        } else if (tipoUsuario == 2) {
            String logradouro = requestString("Logradouro do endereço (ex.: Avenida Paulista): ");
            String numero = requestString("Número do endereço: ");
            String complemento = requestString("Complemento (pressione enter se não houver): ");
            String cep = requestString("CEP (somente dígitos): ");

            if (ActionUsuario.insertProprietario(nome, cpf, email, senha, nascimento,
                    logradouro, numero, complemento, cep)) {
                System.out.println("Proprietário cadastrado com sucesso");
            } else {
                System.out.println("Não conseguimos cadastrar este proprietário");
            }
        } else if (tipoUsuario == 3) {
            String cnh = requestString("Número CNH (somente dígitos): ");

            if (ActionUsuario.insertMotorista(nome, cpf, email, senha, nascimento, cnh)) {
                System.out.println("Motorista cadastrado com sucesso");
            } else {
                System.out.println("Não conseguimos cadastrar este motorista");
            }
        } else if (tipoUsuario == 4) {
            String logradouro = requestString("Logradouro do endereço (ex.: Avenida Paulista): ");
            String numero = requestString("Número do endereço: ");
            String complemento = requestString("Complemento (pressione enter se não houver): ");
            String cep = requestString("CEP (somente dígitos): ");
            String cnh = requestString("Número CNH (somente dígitos): ");

            if (ActionUsuario.insertProprietarioMotorista(nome, cpf, email, senha, nascimento,
                    logradouro, numero, complemento, cep, cnh)) {
                System.out.println("Proprietário/motorista cadastrado com sucesso");
            } else {
                System.out.println("Não conseguimos cadastrar este proprietário/motorista");
            }
        }

        System.out.println("Houve um erro no cadastro do usuário.");
        return Screen.INITIAL;
    }

    private static Screen loginScreen() {
        String email = requestString("Email:");
        String senha = requestString("Senha:");

        ArrayList<String> userData = ActionUsuario.login(email, senha);

        if (userData != null) {
            userCpf = userData.get(0);

            switch (userData.get(1)) {
                case "AGENTE":
                    return Screen.HOME_AGENTE;
                case "PROPRIETARIO":
                    return Screen.HOME_PROPRIETARIO;
                case "MOTORISTA":
                    return Screen.HOME_MOTORISTA;
                case "PROPRIETARIO_MOTORISTA":
                    return Screen.HOME_PROPRIETARIO_MOTORISTA;
            }
        }

        System.out.println("Não encontramos um usuário com as credenciais fornecidas!");
        return Screen.INITIAL;
    }

    private static Screen initialScreen() {
        int selectedNavigation = 0;

        while (!Arrays.asList(-1, 1, 2).contains(selectedNavigation)) {
            System.out.println();
            System.out.println(
                "--------------------------------------------------------------------\n" +
                "| 1 - Cadastro de usuário                                          |\n" +
                "| 2 - Login                                                        |\n" +
                "|                                                                  |\n" +
                "| -1 - Encerrar o Easy Park                                        |\n" +
                "--------------------------------------------------------------------"
            );
            selectedNavigation = requestInt("Digite o código relativo à ação que deseja executar:");
        }

        if (selectedNavigation == -1) return Screen.EXIT_SYSTEM;
        else if (selectedNavigation == 1) return Screen.SIGNUP;
        else return Screen.LOGIN;
    }

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        Screen navigation = Screen.INITIAL;

        System.out.println("\nBem-vindo(a) ao Easy Park!");

        while (true) {
            switch (navigation) {
                case INITIAL:
                    navigation = initialScreen();
                case SIGNUP:
                    navigation = signUpScreen();
                case LOGIN:
                    navigation = loginScreen();
                case HOME_AGENTE:
                    navigation = homeAgenteScreen();
                case HOME_PROPRIETARIO:
                    navigation = homeProprietarioScreen();
                case HOME_MOTORISTA:
                    navigation = homeMotoristaScreen();
                case HOME_PROPRIETARIO_MOTORISTA:
                    navigation = homeProprietarioMotoristaScreen();
                case EXIT_SYSTEM:
                    scanner.close();
                    System.out.println("\nAté mais, e obrigado pelos peixes!");
                    System.exit(0);
            }
        }
    }
}
