package com.poo.classes.abstratos;

import com.poo.classes.Alimento;
import com.poo.classes.Animal;
import com.poo.classes.Funcionario;
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
                    mostraMsgInformacaoTela("Op??ao invalida! Por favor digite um numero de 1 a 4");
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
        sb.append("Escolha uma op??ao: ");

        return sb.toString();
    }

    @Override
    public void trataOpcaoMenuInicial() {
        if (opcaoInicialEnum == OpcaoInicialEnum.VISITANTE) {
            mostraMsgInformacaoTela("Iniciando fun??oes para se??ao de Visitantes!");

            StringBuilder sb = new StringBuilder();
            sb.append("|         1 - Cadastrar Visitante                  ").append('\n');
            sb.append("|         2 - Mostrar Visitantes Cadastrados       ").append('\n');
            sb.append("|         3 - Exibir Receita Total                 ").append('\n');
            sb.append("|         4 - Voltar                               ").append("\n");
            sb.append("|         5 - Sair                                 ").append("\n\n");
            sb.append("Escolha uma op??ao: ");

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
                    mostraMenuInicial();
                    trataOpcaoMenuInicial();
                    break;
                case 5:
                    verificaSeFinaliza(null);
                    break;
                default:
                    mostraMsgErroTela("OPCAO INVALIDA!");
            }
        }

        if (opcaoInicialEnum == OpcaoInicialEnum.ANIMAL) {
            mostraMsgInformacaoTela("Iniciando fun??oes para se??ao de Animais!");
            StringBuilder sb = new StringBuilder();
            sb.append("|         1 - Cadastrar Animal                  ").append('\n');
            sb.append("|         2 - Deletar Animal ").append('\n');
            sb.append("|         3 - Ver Dieta").append('\n');
            sb.append("|         4 - Mostrar Animais Cadastrados       ").append('\n');
            sb.append("|         5 - Exibir gastos                 ").append('\n');
            sb.append("|         6 - Voltar                                 ").append("\n");
            sb.append("|         7 - Sair                                 ").append("\n\n");
            sb.append("Escolha uma op??ao: ");

            String lerEntrada = JOptionPane.showInputDialog(null, sb.toString(),"M E N U - Animais", QUESTION_MESSAGE);
            int opcao = Integer.parseInt(lerEntrada);

            switch (opcao) {
                case 1:
                    cadastroNovoAnimal();
                    break;
                case 2:
                    deletarAnimal();
                    break;
                case 3:
                    mostrarDieta();
                    break;
                case 4:
                    mostrarAnimaisCadastrados();
                    break;
                case 5:
                    exibirGastos();
                    break;
                case 6:
                    mostraMenuInicial();
                    trataOpcaoMenuInicial();
                    break;
                case 7:
                    verificaSeFinaliza(null);
                    break;
                default:
                    mostraMsgErroTela("OPCAO INVALIDA!");
            }
        }

        if (opcaoInicialEnum == OpcaoInicialEnum.FUNCIONARIO) {
            mostraMsgInformacaoTela("Iniciando fun??oes para se??ao de Funcionarios!");
            StringBuilder sb = new StringBuilder();
            sb.append("|         1 - Cadastrar Funcionario           ").append('\n');
            sb.append("|         2 - Mostrar Funcionarios Cadastrados").append('\n');
            sb.append("|         3 - Exibir Folha de Pagamento       ").append('\n');
            sb.append("|         4 - Voltar                          ").append("\n");
            sb.append("|         5 - Sair                            ").append("\n\n");
            sb.append("Escolha uma op??ao: ");

            String lerEntrada = JOptionPane.showInputDialog(null, sb.toString(),"M E N U - Funcionarios", QUESTION_MESSAGE);
            int opcao = Integer.parseInt(lerEntrada);

            switch (opcao) {
                case 1:
                    cadastroNovoFuncionario();
                    break;
                case 2:
                    mostrarFuncionariosCadastrados();
                    break;
                case 3:
                    exibirFolhaPagamento();
                    break;
                case 4:
                    mostraMenuInicial();
                    trataOpcaoMenuInicial();
                    break;
                case 5:
                    verificaSeFinaliza(null);
                    break;
                default:
                    mostraMsgErroTela("OPCAO INVALIDA!");
            }
        }
    }


    // --------------------------------------- Metodos para OPCAO 1 - Visitantes ---------------------------------------

    private void cadastroNovoVisitante() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));

        JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
        label.add(new JLabel("Codigo Verifica??ao: ", SwingConstants.LEFT));
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
        } else {
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


    // ----------------------------------------- Metodos para OPCAO 2 - Animais ----------------------------------------

    private void cadastroNovoAnimal() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));

        JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
        label.add(new JLabel("Nome do Animal: ", SwingConstants.LEFT));
        label.add(new JLabel("Especie: ", SwingConstants.LEFT));
        label.add(new JLabel("Idade: ", SwingConstants.LEFT));
        label.add(new JLabel("Peso: ", SwingConstants.LEFT));
        panel.add(label, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
        JTextField nome = new JTextField();
        controls.add(nome);
        JTextField especie = new JTextField();
        controls.add(especie);
        JTextField idade = new JTextField();
        controls.add(idade);
        JTextField peso = new JTextField();
        controls.add(peso);
        panel.add(controls, BorderLayout.CENTER);
        JOptionPane.showMessageDialog(label, panel, "Cadastro Animal", JOptionPane.QUESTION_MESSAGE);

        Animal a = new Animal(nome.getText(), especie.getText(), Double.parseDouble(peso.getText()), Integer.parseInt(idade.getText()));
        a.cadastrarAnimal();
        cadastroDieta(a);
    }

    private void cadastroDieta(Animal a) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));

        JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
        label.add(new JLabel("Horas", SwingConstants.LEFT));
        label.add(new JLabel("Minutos: ", SwingConstants.LEFT));
        label.add(new JLabel("Alimento: ", SwingConstants.LEFT));
        label.add(new JLabel("Kg/Unidades: ", SwingConstants.LEFT));
        label.add(new JLabel("Pre??o por kg/unidades: ", SwingConstants.LEFT));
        panel.add(label, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
        JTextField horas = new JTextField();
        controls.add(horas);
        JTextField minutos = new JTextField();
        controls.add(minutos);
        JTextField comida = new JTextField();
        controls.add(comida);
        JTextField qtd = new JTextField();
        controls.add(qtd);
        JTextField preco = new JTextField();
        controls.add(preco);
        panel.add(controls, BorderLayout.CENTER);
        JOptionPane.showMessageDialog(label, panel, "Dieta do Animal", JOptionPane.QUESTION_MESSAGE);

        Alimento alimento = new Alimento(comida.getText(), Double.parseDouble(qtd.getText()), Double.parseDouble(preco.getText()));
        a.adicionarAlimentacao(Integer.parseInt(horas.getText()), Integer.parseInt(minutos.getText()), alimento);

        int opcao = JOptionPane.showConfirmDialog(null, "Voltar ao menu principal?","Zoologico POO \uD83E\uDD81", OK_CANCEL_OPTION, INFORMATION_MESSAGE);

        if (opcao == OK_OPTION) {
            mostraMenuInicial();
            trataOpcaoMenuInicial();
        } else {
            verificaSeFinaliza(null);
        }
    }

    private void mostrarAnimaisCadastrados() {
        String animais = leArquivo("nome,especie,peso,idade", "animais.csv");
        String cabecalho = "| #, Nome, Especie, Peso, Idade |\n";

        int opcao = JOptionPane.showConfirmDialog(null, cabecalho+animais,"Zoologico POO \uD83E\uDD81", OK_CANCEL_OPTION, INFORMATION_MESSAGE);
        if (opcao == OK_OPTION) {
            mostraMenuInicial();
            trataOpcaoMenuInicial();
        } else {
            verificaSeFinaliza(null);
        }
    }

    private void deletarAnimal() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));

        JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
        label.add(new JLabel("Nome: ", SwingConstants.LEFT));
        panel.add(label, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
        JTextField nome = new JTextField();
        controls.add(nome);
        panel.add(controls, BorderLayout.CENTER);
        JOptionPane.showMessageDialog(label, panel, "Deletar Animal", JOptionPane.QUESTION_MESSAGE);

        Animal a = new Animal();
        a.deletarAnimal(nome.getText());

        int opcao = JOptionPane.showConfirmDialog(null, "Voltar ao menu principal?","Zoologico POO \uD83E\uDD81", OK_CANCEL_OPTION, INFORMATION_MESSAGE);

        if (opcao == OK_OPTION) {
            mostraMenuInicial();
            trataOpcaoMenuInicial();
        } else {
            verificaSeFinaliza(null);
        }
    }

    private void mostrarDieta() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));

        JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
        label.add(new JLabel("Nome do Animal: ", SwingConstants.LEFT));
        panel.add(label, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
        JTextField nome = new JTextField();
        controls.add(nome);
        panel.add(controls, BorderLayout.CENTER);
        JOptionPane.showMessageDialog(label, panel, "Ver Dieta do Animal", JOptionPane.QUESTION_MESSAGE);

        Animal a = new Animal();
        String dieta = a.verDieta(nome.getText());

        String[] campos = dieta.split(",");
        StringBuilder sb = new StringBuilder();

        sb.append("Nome do Animal: ").append(campos[0] + '\n');
        sb.append("Hor??rio: ").append(campos[1] + '\n');
        sb.append("Alimento: ").append(campos[2] + '\n');

        int op = JOptionPane.showConfirmDialog(null,sb.toString(),"Zoologico POO \uD83E\uDD81", OK_CANCEL_OPTION, INFORMATION_MESSAGE);

        int opcao = JOptionPane.showConfirmDialog(null, "Voltar ao menu principal?","Zoologico POO \uD83E\uDD81", OK_CANCEL_OPTION, INFORMATION_MESSAGE);

        if (opcao == OK_OPTION) {
            mostraMenuInicial();
            trataOpcaoMenuInicial();
        } else {
            verificaSeFinaliza(null);
        }
    }

    private void exibirGastos() {
        Animal a = new Animal();
        double gastos = a.gastosTotais();
        DecimalFormat df = new DecimalFormat("#.00");
        String msg = "Os gastos gerados pelos ANIMAIS foi de: " + df.format(gastos);

        int opcao = JOptionPane.showConfirmDialog(null, msg,"Zoologico POO \uD83E\uDD81", OK_CANCEL_OPTION, INFORMATION_MESSAGE);
        if (opcao == OK_OPTION) {
            mostraMenuInicial();
            trataOpcaoMenuInicial();
        } else {
            verificaSeFinaliza(null);
        }
    }

    // -------------------------------------- Metodos para OPCAO 3 - Funcionarios --------------------------------------

    private void cadastroNovoFuncionario() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));

        JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
        label.add(new JLabel("Nome: ", SwingConstants.LEFT));
        label.add(new JLabel("CPF: ", SwingConstants.LEFT));
        label.add(new JLabel("Salario: ", SwingConstants.LEFT));
        panel.add(label, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
        JTextField nome = new JTextField();
        controls.add(nome);
        JTextField cpf = new JTextField();
        controls.add(cpf);
        JTextField salario = new JTextField();
        controls.add(salario);
        panel.add(controls, BorderLayout.CENTER);
        JOptionPane.showMessageDialog(label, panel, "Cadastro Funcionario", JOptionPane.QUESTION_MESSAGE);

        Funcionario f = new Funcionario(nome.getText(), cpf.getText(), Double.parseDouble(salario.getText()));
        f.cadastrarFuncionario();
        mostraMsgInformacaoTela("Sucesso! Funcionario Cadastrado");

        int opcao = JOptionPane.showConfirmDialog(null, "Voltar ao menu principal?","Zoologico POO \uD83E\uDD81", OK_CANCEL_OPTION, INFORMATION_MESSAGE);

        if (opcao == OK_OPTION) {
            mostraMenuInicial();
            trataOpcaoMenuInicial();
        }
        else {
            verificaSeFinaliza(null);
        }
    }

    private void mostrarFuncionariosCadastrados() {
        String funcionarios = leArquivo("nome,cpf,salario", "funcionarios.csv");
        String cabecalho = "| #, Nome, CPF, Salario |\n";

        int opcao = JOptionPane.showConfirmDialog(null, cabecalho+funcionarios,"Zoologico POO \uD83E\uDD81", OK_CANCEL_OPTION, INFORMATION_MESSAGE);
        if (opcao == OK_OPTION) {
            mostraMenuInicial();
            trataOpcaoMenuInicial();
        } else {
            verificaSeFinaliza(null);
        }
    }

    private void exibirFolhaPagamento() {
        Funcionario f = new Funcionario();
        double folha = f.folhaPagamentoTotal();
        DecimalFormat df = new DecimalFormat("#.00");
        String msg = "A folha de pagamento dos FUNCIONARIOS e de: " + df.format(folha);

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
        verificaSeFinaliza(null);
    }
}
