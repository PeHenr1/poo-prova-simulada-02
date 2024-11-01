package org.example;

import java.util.Scanner;

public class Main {
    final Scanner scanner = new Scanner(System.in);
    final FakeDeclaracaoRepository repo = new FakeDeclaracaoRepository();

    void main() {
        while(true){
            int option = readOptions();
            switch (option){
                case 1 -> registrarDeclaracoes();
                case 2 -> alterarDeclaracoes();
                case 3 -> removerDeclaracoes();
                case 4 -> adicionarGasto();
                case 5 -> editarGasto();
                case 6 -> removerGasto();
                case 7 -> exibirDeclaracoes();
                case 0 -> { return; }
            }
        }
    }

    private void registrarDeclaracoes() {
        System.out.println("Ganho Tributável: ");
        double ganhoTributavel = Double.parseDouble(scanner.nextLine());
        System.out.println("Valor Pago: ");
        double valorPago = Double.parseDouble(scanner.nextLine());

        var service = new RegistraDeclaracoesService(repo);
        service.register(ganhoTributavel,valorPago);
    }

    private void alterarDeclaracoes() {
        if ( repo.findAll().isEmpty() ){
            System.out.println("Declaração não encontrada.");
        }

        System.out.println("Ganho Tributável: ");
        double ganhoTributavel = Double.parseDouble(scanner.nextLine());
        System.out.println("Valor Pago: ");
        double valorPago = Double.parseDouble(scanner.nextLine());

        repo.update(new DeclaracaoCompleta(1, ganhoTributavel, valorPago));
        repo.update(new DeclaracaoSimplificada(2, ganhoTributavel, valorPago));
    }

    private void removerDeclaracoes() {
        repo.findAll().stream()
                .map(Declaracao::getId)
                .forEach(repo::delete);

    }

    private void adicionarGasto() {
        var maybe = repo.findById(1L);
        if(maybe.isEmpty()) {
            printError("Declaração",1);
            return;
        }
        System.out.println("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.println("CNPJ: ");
        String cnpj = scanner.nextLine();
        System.out.println("Valor: ");
        double valor = Double.parseDouble(scanner.nextLine());
        System.out.println("[1]: Saúde | [2] Educação: ");
        int tipo = Integer.parseInt(scanner.nextLine());
        if(tipo != 1 && tipo != 2){
            System.out.println("Opção inválida");
            return;
        }
        System.out.println(tipo == 1 ? "Conselho: " : "Instituição: ");
        String flexible = scanner.nextLine();

        Gasto gasto = tipo == 1
                ?  new GastoSaude(Gasto.novoId(),descricao,cnpj,valor,flexible)
                :  new GastoEducacao(Gasto.novoId(),descricao,cnpj,valor,flexible);

        DeclaracaoCompleta completa = (DeclaracaoCompleta) maybe.get();
        completa.adicionarGasto(gasto);
        repo.update(completa);
    }

    private void editarGasto() {
    }

    private void removerGasto() {
        System.out.println("ID do gasto:");
        Long id = Long.parseLong(scanner.nextLine());
        repo.findById(1L).ifPresentOrElse(
                declaracao -> ((DeclaracaoCompleta) declaracao).removerGasto(id)
                , () -> printError("Declaração",1)
        );

    }

    private void exibirDeclaracoes() {
        repo.findById(1L).ifPresentOrElse(
                System.out::println,
                () -> printError("Declaração",1)
        );
        repo.findById(2L).ifPresentOrElse(
                System.out::println,
                () -> printError("Declaração",2)
        );
    }

    private void printError(String entity, long id){
        System.out.println("Entity of type " + entity + "with id " + id + "not found.");
    }

    private int readOptions(){
        while(true){
            System.out.println("1. Informar dados declaração");
            System.out.println("2. Alterar dados declaração");
            System.out.println("3. Remover declarações");
            System.out.println("4. Adicionar gasto");
            System.out.println("5. Editar gasto");
            System.out.println("6. Remover gasto");
            System.out.println("7. Exibir declarações");
            System.out.println("0. Sair");
            System.out.println(">> ");

            int option = Integer.parseInt(scanner.nextLine());
            if (option < 0 || option > 7){
                System.out.println("Valor Inválido");
                continue;
            }
            return option;
        }
    }

}