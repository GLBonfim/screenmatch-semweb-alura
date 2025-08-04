package br.com.alura.screenmatchh;


import br.com.alura.screenmatchh.model.DadosEpisodio;
import br.com.alura.screenmatchh.model.DadosSerie;
import br.com.alura.screenmatchh.model.DadosTemporada;
import br.com.alura.screenmatchh.service.ConsumoAPI;
import br.com.alura.screenmatchh.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchhApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchhApplication.class, args);
	}

	@Override
	public void run(String... args) {
		var consumoApi = new ConsumoAPI();
		var json = consumoApi.obterDados("https://www.omdbapi.com/?t=The+100=&apikey=e3bdf48d");
		System.out.println(json);

		ConverteDados conversor = new ConverteDados();
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
		System.out.println(dados);
		json = consumoApi.obterDados("https://www.omdbapi.com/?t=The+100=&season=1&episode=2&&apikey=e3bdf48d");

		DadosEpisodio dadosEpisodio = conversor.obterDados(json, DadosEpisodio.class);
		System.out.println(dadosEpisodio);

		List<DadosTemporada> temporadas = new ArrayList<>();

		for (int i = 1; i<= dados.totalTemporadas(); i++) {
			json = consumoApi.obterDados("https://www.omdbapi.com/?t=The+100=&season="+ i + "&&apikey=e3bdf48d");
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}
		temporadas.forEach(System.out::println);


	}
}
