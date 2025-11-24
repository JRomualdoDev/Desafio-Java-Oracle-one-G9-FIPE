package service;

import model.*;

import java.util.*;

public class Menu {

    Scanner sc = new Scanner(System.in);
    private final String URL_API = "https://fipe.parallelum.com.br/api/v2/";
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados converte = new ConverteDados();

    public Map<String, List<Veiculo>> listaTipoVeiculo() {

        System.out.println("Digite 1 - carros, 2 - motos ou 3 - caminhoes) : ");
        String tipo = sc.next();

        tipo = switch (tipo) {
            case "1" -> "cars";
            case "2" -> "motorcycles";
            case "3" -> "trucks";
            default -> "sair";
        };

        if (tipo.equals("sair")) {
            System.out.println("Valor não esperado");
            System.exit(0);
        }

        String url = URL_API + tipo + "/brands";
        String dados = consumo.obterDados(url);
        List<Veiculo> veiculos = List.of(converte.obterDados(dados, Veiculo[].class));
        System.out.println("\n***********************************");
        System.out.println("       LISTA DE VEÍCULOS       ");
        System.out.println("***********************************");
        System.out.printf("%-7s | %-20s%n", "CÓDIGO", "MARCA");
        System.out.println("-----------+-----------------------");

        veiculos.stream()
                .sorted(Comparator.comparing(Veiculo::code))
                .forEach(marca -> {
                    System.out.printf("%-7s | %s%n", marca.code(), marca.name());
                });

        Map<String, List<Veiculo>> map = new HashMap<>();
        map.put(url, veiculos);

        return map;
    }

    public Map<String, List<Modelo>> listaModelos(Map<String, List<Veiculo>> veiculos) {

        String url = veiculos.keySet().iterator().next();

        System.out.println("Digite um código ou nome do veiculo : ");
        String modelo = "";

        modelo = auxiliar(modelo, veiculos.values());

        url = url + "/" + modelo + "/models";
        String dados = consumo.obterDados(url);

        List<Modelo> modelos = List.of(converte.obterDados(dados, Modelo[].class));

        System.out.println("\n***********************************");
        System.out.println("       LISTA DE MODELOS       ");
        System.out.println("***********************************");
        System.out.printf("%-7s | %-20s%n", "CÓDIGO", "MODELO");
        System.out.println("-----------+-----------------------");

        modelos.stream()
                .sorted(Comparator.comparing(Modelo::code))
                .forEach(model -> {
            System.out.printf("%-7s | %s%n", model.code(), model.name());
        });

        Map<String, List<Modelo>> map = new HashMap<>();
        map.put(url, modelos);

        return map;
    }

    public void listaDadosCompletos(Map<String, List<Modelo>> modelos) {

        String url = modelos.keySet().iterator().next();

        System.out.println("Digite um código ou nome do modelo : ");
        String ano = "";

        ano = auxiliar(ano, modelos.values());

        url = url + "/" + ano + "/years";
        String dados = consumo.obterDados(url);
        List<Anos> anos = List.of(converte.obterDados(dados, Anos[].class));

        String finalUrl = url;

        String formato = "%-12.12s | %-15.15s | %-15.15s | %-30.30s | %-9.9s | %-12.12s | %-10.10s | %-18.18s | %-5.5s%n";

        System.out.println("");
        System.out.printf(formato,
                "Tipo", "Valor", "Marca", "Modelo", "Ano", "Combust.", "FIPE", "Mês Ref.", "Sigla");

        anos.forEach(
                    model -> {
                        String dadosCompleto = consumo.obterDados(finalUrl + "/" + model.code());
                        DadosVeiculo dadosConvertido = converte.obterDados(dadosCompleto, DadosVeiculo.class);

                        System.out.println("-".repeat(145));

                        System.out.printf(formato,
                                dadosConvertido.vehicleType(),
                                dadosConvertido.price(),
                                dadosConvertido.brand(),
                                dadosConvertido.model(),
                                dadosConvertido.modelYear(),
                                dadosConvertido.fuel(),
                                dadosConvertido.codeFipe(),
                                dadosConvertido.referenceMonth(),
                                dadosConvertido.fuelAcronym()
                        );
                    }
                );

        sc.close();
    }

    private <T extends DadosBasicos> String auxiliar(String cod, Collection<List<T>> listaClasses) {

            String wordFilter = sc.next();

            Optional<T> veiculoFiltrado = listaClasses.stream()
                    .flatMap(List::stream)
                    .filter( v -> v.code().contains(wordFilter))
                    .findFirst()
                    .or( () ->
                            listaClasses.stream()
                                    .flatMap(List::stream)
                                    .filter(v -> v.name().toLowerCase().contains(wordFilter.toLowerCase()))
                                    .findFirst()
                    );

            if (veiculoFiltrado.isPresent()) {
                cod = String.valueOf(veiculoFiltrado.get().code());
                System.out.println("");
                System.out.println("------------------");
                System.out.println("Primeiro Modelo encontrado com as letras: " + wordFilter);
                System.out.println(veiculoFiltrado.get());
                System.out.println("------------------");
            } else {
                System.out.println("O código ou nome do modelo não existe.");
                System.exit(0);
                sc.close();
            }

            return cod;
    }
}
