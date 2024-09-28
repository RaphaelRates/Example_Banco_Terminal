import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Conta_banco> contas = new ArrayList<>();

        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Criar Conta");
            System.out.println("2. Depositar");
            System.out.println("3. Sacar");
            System.out.println("4. Ver Saldo");
            System.out.println("5. Transferir");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao;

            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida. Por favor, insira um número.");
                scanner.nextLine(); // Limpar o buffer
                continue; // Voltar para o início do loop
            }

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do titular: ");
                    String nome = scanner.nextLine();
                    if (Conta_banco.nomeValido(nome)) {
                        Conta_banco novaConta = new Conta_banco(nome);
                        contas.add(novaConta);
                        System.out.println("Conta criada com sucesso para " + nome + " com saldo inicial de R$ 50.0");
                    } else {
                        System.out.println("Nome inválido. O nome deve começar com uma letra e pode conter letras, números e espaços.");
                    }
                    break;
                case 2:
                    System.out.print("Digite o nome da conta para depositar: ");
                    String nomeDeposito = scanner.nextLine();
                    Conta_banco contaDeposito = encontrarConta(contas, nomeDeposito);
                    if (contaDeposito != null) {
                        double valorDeposito;
                        try {
                            System.out.print("Digite o valor a depositar: ");
                            valorDeposito = scanner.nextDouble();
                            contaDeposito.depositar(valorDeposito);
                        } catch (InputMismatchException e) {
                            System.out.println("Valor inválido. Por favor, insira um número.");
                            scanner.nextLine(); // Limpar o buffer
                        }
                    } else {
                        System.out.println("Conta não encontrada. Tente novamente.");
                    }
                    break;
                case 3:
                    System.out.print("Digite o nome da conta para sacar: ");
                    String nomeSaque = scanner.nextLine();
                    Conta_banco contaSaque = encontrarConta(contas, nomeSaque);
                    if (contaSaque != null) {
                        double valorSaque;
                        try {
                            System.out.print("Digite o valor a sacar: ");
                            valorSaque = scanner.nextDouble();
                            contaSaque.sacar(valorSaque);
                        } catch (InputMismatchException e) {
                            System.out.println("Valor inválido. Por favor, insira um número.");
                            scanner.nextLine(); // Limpar o buffer
                        }
                    } else {
                        System.out.println("Conta não encontrada. Tente novamente.");
                    }
                    break;
                case 4:
                    System.out.print("Digite o nome da conta para ver o saldo: ");
                    String nomeSaldo = scanner.nextLine();
                    Conta_banco contaSaldo = encontrarConta(contas, nomeSaldo);
                    if (contaSaldo != null) {
                        System.out.println("Saldo atual da conta " + contaSaldo.getNome() + ": R$ " + contaSaldo.getSaldo());
                    } else {
                        System.out.println("Conta não encontrada. Tente novamente.");
                    }
                    break;
                case 5:
                    System.out.print("Digite o nome da conta de origem: ");
                    String nomeOrigem = scanner.nextLine();
                    Conta_banco contaOrigem = encontrarConta(contas, nomeOrigem);
                    System.out.print("Digite o nome da conta de destino: ");
                    String nomeDestino = scanner.nextLine();
                    Conta_banco contaDestino = encontrarConta(contas, nomeDestino);

                    if (contaOrigem != null && contaDestino != null) {
                        double valorTransferencia;
                        try {
                            System.out.print("Digite o valor a transferir: ");
                            valorTransferencia = scanner.nextDouble();
                            contaOrigem.transferir(contaDestino, valorTransferencia);
                        } catch (InputMismatchException e) {
                            System.out.println("Valor inválido. Por favor, insira um número.");
                            scanner.nextLine(); // Limpar o buffer
                        }
                    } else {
                        System.out.println("Uma ou ambas as contas são inválidas. Tente novamente.");
                    }
                    break;
                case 6:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static Conta_banco encontrarConta(List<Conta_banco> contas, String nome) {
        for (Conta_banco conta : contas) {
            if (conta.getNome().equalsIgnoreCase(nome)) {
                return conta;
            }
        }
        return null;
    }
}
