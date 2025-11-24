package com.alura.FIPE;

import model.Anos;
import model.Modelo;
import model.Veiculo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import service.Menu;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class FipeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FipeApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        System.out.println("FipeApplication executando...");

        Menu menu = new Menu();
        Map<String, List<Veiculo>> mapVeiculos = menu.listaTipoVeiculo();
        Map<String, List<Modelo>> mapModelos = menu.listaModelos(mapVeiculos);
        menu.listaAnos(mapModelos);
    }
}
