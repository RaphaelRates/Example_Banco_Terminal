

public class Conta_banco {

        private final String nome;
        private double saldo;

        public Conta_banco(String nome) {
            this.nome = nome;
            this.saldo = 50;
        }

        public String getNome() {
            return nome;
        }

        public double getSaldo() {
            return saldo;
        }

        public void depositar(double valor) {
            if (valor > 0) {
                saldo += valor;
                System.out.println("Depósito de R$ " + valor + " realizado com sucesso. Saldo atual: R$ " + saldo);
            } else {
                System.out.println("Valor de depósito deve ser maior que zero.");
            }
        }

        public void sacar(double valor) {
            if (valor > 0 && valor <= saldo) {
                saldo -= valor;
                System.out.println("Saque de R$ " + valor + " realizado com sucesso. Saldo atual: R$ " + saldo);
            } else {
                System.out.println("Saldo insuficiente ou valor inválido.");
            }
        }

        public void transferir(Conta_banco destinatario, double valor) {
            if (valor > 0 && valor <= saldo) {
                this.saldo -= valor;
                destinatario.saldo += valor;
                System.out.println("Transferência de R$ " + valor + " para " + destinatario.getNome() + " realizada com sucesso.");
                System.out.println("Saldo atual de " + this.nome + ": R$ " + this.saldo);
            } else {
                System.out.println("Saldo insuficiente ou valor de transferência inválido.");
            }

        }

        public static boolean nomeValido(String nome) {
            return nome.matches("^[A-Za-z][A-Za-z0-9 ]*$");
        }
    }


