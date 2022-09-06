package com.poo.classes.abstratos;

import com.poo.classes.Visitantes;
import com.poo.enums.OpcaoInicialEnum;
import com.poo.interfaces.FuncionamentoConfig;
import lombok.NoArgsConstructor;
import javax.swing.*;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import static javax.swing.JOptionPane.*;

@NoArgsConstructor
public abstract class AbstratoFuncionamento extends AbstratoZoologico implements FuncionamentoConfig {

    private OpcaoInicialEnum opcaoInicialEnum;

    @Override
    public void iniciaPrograma() {
        int opcaoInicial = JOptionPane.showConfirmDialog(null, "Iniciar Gerenciador do Zoologico?",
                "Zoologico POO \uD83E\uDD81", OK_CANCEL_OPTION, QUESTION_MESSAGE);

        if (opcaoInicial == CANCEL_OPTION) {
            JOptionPane.showMessageDialog(null, "O programa sera finalizado! \uD83D\uDE03");
            System.exit(0);
        }
    }

    @Override
    public void realizaLogin() {
        boolean b = checaCredenciais();
        while (!b) {
            mostraMsgOkCancelTela("USUARIO OU SENHA INVALIDOS. Tente novamente!");
            b = checaCredenciais();
        }
        mostraMsgInformacaoTela("Sucesso, login feito!");
    }

    private boolean checaCredenciais() {
        boolean checaSucessoLogin = false;
        JPanel panel = new JPanel(new BorderLayout(5, 5));

        JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
        label.add(new JLabel("Login", SwingConstants.RIGHT));
        label.add(new JLabel("Password", SwingConstants.RIGHT));
        panel.add(label, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
        JTextField username = new JTextField();
        controls.add(username);
        JPasswordField password = new JPasswordField();
        controls.add(password);
        panel.add(controls, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(label, panel, "Login  \uD83D\uDD10", JOptionPane.QUESTION_MESSAGE);

        HashMap<String, String> logins = loginsValidos();
        for (Map.Entry<String, String> pair : logins.entrySet()) {
            if (pair.getKey().equals(username.getText()) && pair.getValue().equals(new String(password.getPassword()))) {
                checaSucessoLogin = true;
                break;
            }
        }

        return checaSucessoLogin;
    }

    @Override
    public void mostraMenuInicial() {
        int opcao;
        do {
            String lerEntrada = JOptionPane.showInputDialog(null, montaMenu(),"M E N U", QUESTION_MESSAGE);
            verificaSeFinaliza(lerEntrada);
            opcao = Integer.parseInt(lerEntrada);

            switch (opcao) {
                case 1:
                    opcaoInicialEnum = OpcaoInicialEnum.VISITANTE;
                    break;
                case 2:
                    opcaoInicialEnum = OpcaoInicialEnum.ANIMAL;
                    break;
                case 3:
                    opcaoInicialEnum = OpcaoInicialEnum.FUNCIONARIO;
                    break;
                case 4:
                    verificaSeFinaliza(null);
                    break;
                default:
                    mostraMsgInformacaoTela("Opçao invalida! Por favor digite um numero de 1 a 4");
                    opcao = 5;
            }
        } while (opcao == 5);
    }

    private String montaMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("|         1 - Visitantes           ").append('\n');
        sb.append("|         2 - Animais              ").append('\n');
        sb.append("|         3 - Funcionarios         ").append('\n');
        sb.append("|         4 - Sair                 ").append("\n\n");
        sb.append("Escolha uma opçao: ");

        return sb.toString();
    }

    @Override
    public void trataOpcaoMenuInicial() {
        if (opcaoInicialEnum == OpcaoInicialEnum.VISITANTE) {
            mostraMsgInformacaoTela("Iniciando funçoes para seçao de Visitantes!");

            StringBuilder sb = new StringBuilder();
            sb.append("|         1 - Cadastrar Visitante                  ").append('\n');
            sb.append("|         2 - Mostrar Visitantes Cadastrados       ").append('\n');
            sb.append("|         3 - Exibir receita total                 ").append('\n');
            sb.append("|         4 - Sair                                 ").append("\n\n");
            sb.append("Escolha uma opçao: ");

            String lerEntrada = JOptionPane.showInputDialog(null, sb.toString(),"M E N U - Visitantes", QUESTION_MESSAGE);
            int opcao = Integer.parseInt(lerEntrada);

            switch (opcao) {
                case 1:
                    cadastroNovoVisitante();
                    break;
                case 2:
                    mostrarVisitantesCadastrados();
                    break;
                case 3:
                    exibirReceitaVisitantes();
                    break;
                case 4:
                    verificaSeFinaliza(null);
                    break;
                default:
                    mostraMsgErroTela("OPCAO INVALIDA!");
            }
        }

        if (opcaoInicialEnum == OpcaoInicialEnum.ANIMAL) {
            mostraMsgInformacaoTela("Iniciando funçoes para seçao de Animais!");
//            cadastroNovoAnimal() se cadastrou pode escolher add alimentaçao ou nao
//            mostrarAnimaisCadastrados()
//            exibirGastos()
        }

        if (opcaoInicialEnum == OpcaoInicialEnum.FUNCIONARIO) {
            mostraMsgInformacaoTela("Iniciando funçoes para seçao de Funcionarios!");
//            cadastroNovoFuncionario()
//            mostrarFuncionariosCadastrados()
//            exibirFolhaPagamento()
        }
    }

    private void cadastroNovoVisitante() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));

        JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
        label.add(new JLabel("Codigo Verificaçao: ", SwingConstants.LEFT));
        label.add(new JLabel("CPF: ", SwingConstants.LEFT));
        label.add(new JLabel("Idade: ", SwingConstants.LEFT));
        panel.add(label, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
        JTextField codigo = new JTextField();
        controls.add(codigo);
        JTextField cpf = new JTextField();
        controls.add(cpf);
        JTextField idade = new JTextField();
        controls.add(idade);
        panel.add(controls, BorderLayout.CENTER);
        JOptionPane.showMessageDialog(label, panel, "Cadastro Visitante", JOptionPane.QUESTION_MESSAGE);

        Visitantes v = new Visitantes(Long.parseLong(codigo.getText()), cpf.getText(), Integer.parseInt(idade.getText()));
        v.realizaCadastroVisitante();
        
        int opcao = JOptionPane.showConfirmDialog(null, "Voltar ao menu principal?","Zoologico POO \uD83E\uDD81", OK_CANCEL_OPTION, INFORMATION_MESSAGE);
        
        if (opcao == OK_OPTION) {
            mostraMenuInicial();
            trataOpcaoMenuInicial();
        } 
        else {
            verificaSeFinaliza(null);
        }
    }

    private void mostrarVisitantesCadastrados() {
        String visitantes = leArquivo("dia,mes,codigo,cpf,idade,valor", "visitantes.csv");
        String cabecalho = "| #, Dia, Mes, Codigo, CPF, Idade, Valor |\n";
        int opcao = JOptionPane.showConfirmDialog(null, cabecalho+visitantes,"Zoologico POO \uD83E\uDD81", OK_CANCEL_OPTION, INFORMATION_MESSAGE);
        if (opcao == OK_OPTION) {
            mostraMenuInicial();
            trataOpcaoMenuInicial();
        } else {
            verificaSeFinaliza(null);
        }
    }

    private void exibirReceitaVisitantes() {
        Visitantes v = new Visitantes();
        double receita = v.getReceitaVisitantes();
        DecimalFormat df = new DecimalFormat("#.00");
        String msg = "A receita gerada pelos VISITANTES foi de: " + df.format(receita);
        int opcao = JOptionPane.showConfirmDialog(null, msg,"Zoologico POO \uD83E\uDD81", OK_CANCEL_OPTION, INFORMATION_MESSAGE);
        if (opcao == OK_OPTION) {
            mostraMenuInicial();
            trataOpcaoMenuInicial();
        } else {
            verificaSeFinaliza(null);
        }
    }

    @Override
    public void finalizaPrograma() {

    }
}
