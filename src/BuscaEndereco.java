import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BuscaEndereco {
    public static void main(String[] args) {
        int option = 1;
        List<Endereco> listaDeEnderecos = new ArrayList<>();
        Scanner read = new Scanner(System.in);
        while (option != 4) {
            System.out.println("""
                    
                    ###### BUSCA CEP ######
                    Digite o número de uma das seguintes opções:
                    
                    1 - Consultar endereço pelo CEP
                    2 - Visualizar lista de CEPs pesquisados
                    3 - Gerar arquivo JSON com a lista de CEPs pesquisados
                    4 - Sair da aplicação
                    
                    """);
            option = read.nextInt();
            read.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Digite um CEP a ser pesquisado, com 8 dígitos, sem caracteres especiais: ");
                    String query = read.next();
                    read.nextLine();
                    if (query.matches("^[0-9]{8}$")){
                        CepApi cepApi = new CepApi();
                        try {
                            Endereco endereco = cepApi.consultaCep(query);
                            if (endereco != null) {
                                listaDeEnderecos.add(endereco);
                                System.out.println(endereco);
                            }
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("O CEP deve conter apenas 8 números!");
                    }
                    System.out.println("Pressione Enter para continuar");
                    read.nextLine();
                    break;
                case 2:
                    System.out.println("########## CEPS PESQUISADOS #############");
                    System.out.println("Total de CEPs pesquisados: " + listaDeEnderecos.size());
                    if (listaDeEnderecos.isEmpty()) {
                        System.out.println("""
                        Nenhum CEP pesquisado
                        """);
                    } else {
                        for (Endereco item: listaDeEnderecos) {
                            System.out.println(item);
                        }
                    }
                    System.out.println("Pressione Enter para continuar");
                    read.nextLine();
                    break;
                case 3:
                    try {
                        FileExport file = new FileExport();
                        file.exportToJson(listaDeEnderecos);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Pressione Enter para continuar");
                    read.nextLine();
                    break;
                case 4:
                    System.out.println("Encerrando a aplicação...");
                    break;
                default:
                    System.out.println("Opção inválida! Selecione uma opção de 1 a 4");
                    break;
            }
        }
    }
}