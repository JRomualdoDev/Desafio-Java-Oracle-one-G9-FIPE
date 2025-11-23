package service;

import model.Anos;
import model.Modelo;
import model.Veiculo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Menu {

    Scanner sc = new Scanner(System.in);
    private final String URL_API = "https://parallelum.com.br/fipe/api/v1/";
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados converte = new ConverteDados();

    public Map<String, List<Veiculo>> listaTipoVeiculo() {

        System.out.println("Digite 1 - carros, 2 - motos ou 3 - caminhoes) : ");
        String tipo = sc.next();

        tipo = switch (tipo) {
            case "1" -> "carros";
            case "2" -> "motos";
            case "3" -> "caminhos";
            default -> "sair";
        };

        if (tipo.equals("sair")) {
            System.out.println("Valor não esperado");
            System.exit(0);
        }

        String url = URL_API + tipo + "/marcas";
        String dados = consumo.obterDados(url);
        List<Veiculo> veiculos = List.of(converte.obterDados(dados, Veiculo[].class));
        System.out.println("\n***********************************");
        System.out.println("       LISTA DE VEÍCULOS       ");
        System.out.println("***********************************");
        System.out.printf("%-7s | %-20s%n", "CÓDIGO", "MARCA");
        System.out.println("-----------+-----------------------");

        veiculos.forEach(marca -> {
            System.out.printf("%-7s | %s%n", marca.codigo(), marca.nome());
        });

        Map<String, List<Veiculo>> map = new HashMap<>();
        map.put(url, veiculos);

        return map;
    }

    public Map<String, List<Veiculo>> listaModelos(Map<String, List<Veiculo>> veiculos) {

        String url = veiculos.keySet().iterator().next();

        System.out.println("Digite um código ou nome do veiculo : ");
        String modelo = sc.next();

        //TODO: Agora está somente pelo id , precisa colocar por nome também

        url = url + "/" + modelo + "/modelos";
        String dados = consumo.obterDados(url);
        Modelo modelos = converte.obterDados(dados, Modelo.class);
        modelos.anos().forEach(System.out::println);

        System.out.println("\n***********************************");
        System.out.println("       LISTA DE MODELOS       ");
        System.out.println("***********************************");
        System.out.printf("%-7s | %-20s%n", "CÓDIGO", "MODELO");
        System.out.println("-----------+-----------------------");

        modelos.modelos().forEach(model -> {
            System.out.printf("%-7s | %s%n", model.codigo(), model.nome());
        });

        Map<String, List<Veiculo>> map = new HashMap<>();
        map.put(url, modelos.modelos());

        return map;
    }

    public Map<String, List<Anos>> listaAnos(Map<String, List<Veiculo>> modelos) {

        String url = modelos.keySet().iterator().next();

        System.out.println("Digite um código ou nome do modelo : ");
        String ano = sc.next();

        //TODO: Agora está somente pelo id , precisa colocar por nome também

        url = url + "/" + ano + "/anos";
        String dados = consumo.obterDados(url);
        List<Anos> anos = List.of(converte.obterDados(dados, Anos[].class));

        System.out.println("\n***********************************");
        System.out.println("       LISTA DE ANOS      ");
        System.out.println("***********************************");
        System.out.printf("%-7s | %-20s%n", "CÓDIGO", "NOME");
        System.out.println("-----------+-----------------------");

        anos.forEach(model -> {
            System.out.printf("%-7s | %s%n", model.codigo(), model.nome());
        });

        Map<String, List<Anos>> map = new HashMap<>();
        map.put(url, anos);

        return map;
    }

    public void listaDadosCompleto(Map<String, List<Anos>> anos) {

        String url = anos.keySet().iterator().next();

        System.out.println("Digite um código ou nome do ano : ");
        String cod = sc.next();

        //TODO: Agora está somente pelo id , precisa colocar por nome também

        url = url + "/" + cod;
        String dados = consumo.obterDados(url);
        List<Anos> dadoCompleto = List.of(converte.obterDados(dados, Anos[].class));

        System.out.println("\n***********************************");
        System.out.println("       LISTA DE ANOS      ");
        System.out.println("***********************************");
        System.out.printf("%-7s | %-20s%n", "CÓDIGO", "NOME");
        System.out.println("-----------+-----------------------");

        anos.forEach(model -> {
            System.out.printf("%-7s | %s%n", model.codigo(), model.nome());
        });

        Map<String, List<Anos>> map = new HashMap<>();
        map.put(url, anos);

        return map;

    }


}
