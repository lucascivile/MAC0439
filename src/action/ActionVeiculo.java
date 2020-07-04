package action;

import bd.relacional.VeiculoDAO;
import modelo.relacional.Veiculo;

public class ActionVeiculo {
    
    /**
    * Cria um veículo para o motorista no banco.
    * Retorna verdadeiro se teve sucesso, falso caso contrário.
    */
    public static boolean insert(String cpf, String modelo, int ano, String cor, String placa) {
        Veiculo veiculo = new Veiculo();

        veiculo.setCpfMotorista(cpf);
        veiculo.setModelo(modelo);
        veiculo.setAno(ano);
        veiculo.setCor(cor);
        veiculo.setPlaca(placa);

        VeiculoDAO veiculoDAO = new VeiculoDAO();

        try {
            veiculoDAO.insert(veiculo);
        } catch (RuntimeException e) {
            return false;
        }

        return true;
    }
}
