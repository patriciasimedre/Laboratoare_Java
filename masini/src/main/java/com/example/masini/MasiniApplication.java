package com.example.masini;

import com.example.masini.entitate.Masina;
import com.example.masini.service.MasinaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class MasiniApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(MasiniApplication.class);
	private final MasinaService masinaService;

	public MasiniApplication(MasinaService masinaService) {
		this.masinaService = masinaService;
	}

	public static void main(String[] args) {
		SpringApplication.run(MasiniApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Scanner scanner = new Scanner(System.in);
		boolean running = true;

		while (running) {
			System.out.println("\n=== MENIU ===");
			System.out.println("1. Afișează toate mașinile");
			System.out.println("2. Adaugă o mașină");
			System.out.println("3. Caută o mașină după număr de înmatriculare");
			System.out.println("4. Număr mașini cu o anumită marcă");
			System.out.println("5. Număr mașini sub 100.000 km");
			System.out.println("6. Mașini mai noi de 5 ani");
			System.out.println("7. Șterge o mașină");
			System.out.println("8. Ieșire");
			System.out.print("Alege o opțiune: ");

			int optiune = scanner.nextInt();
			scanner.nextLine(); // Consumă newline-ul rămas

			switch (optiune) {
				case 1 -> {
					logger.info("=== Toate Mașinile ===");
					List<Masina> masini = masinaService.toateMasinile();
					masini.forEach(masina -> logger.info(masina.toString()));
				}
				case 2 -> {
					System.out.println("Introduceți datele mașinii:");
					System.out.print("Număr de înmatriculare: ");
					String numarInmatriculare = scanner.nextLine();
					System.out.print("Marcă: ");
					String marca = scanner.nextLine();
					System.out.print("An fabricație: ");
					int anFabricatie = scanner.nextInt();
					scanner.nextLine(); // Consumă newline-ul
					System.out.print("Culoare: ");
					String culoare = scanner.nextLine();
					System.out.print("Număr kilometri: ");
					int numarKilometri = scanner.nextInt();

					Masina masinaNoua = new Masina();
					masinaNoua.setNumarInmatriculare(numarInmatriculare);
					masinaNoua.setMarca(marca);
					masinaNoua.setAnFabricatie(anFabricatie);
					masinaNoua.setCuloare(culoare);
					masinaNoua.setNumarKilometri(numarKilometri);

					masinaService.adaugaMasina(masinaNoua);
					logger.info("Mașina a fost adăugată cu succes!");
				}
				case 3 -> {
					System.out.print("Introduceți numărul de înmatriculare: ");
					String numarInmatriculare = scanner.nextLine();
					Masina masina = masinaService.cautaMasinaDupaNumar(numarInmatriculare);
					if (masina != null) {
						logger.info(masina.toString());
					} else {
						logger.warn("Mașina nu a fost găsită!");
					}
				}
				case 4 -> {
					System.out.print("Introduceți marca: ");
					String marca = scanner.nextLine();
					int numarMasini = masinaService.numaraMasiniDupaMarca(marca);
					logger.info("Numărul de mașini cu marca {} este {}", marca, numarMasini);
				}
				case 5 -> {
					int numarMasini = masinaService.numaraMasiniSub100000Km();
					logger.info("Numărul de mașini cu sub 100.000 km este {}", numarMasini);
				}
				case 6 -> {
					logger.info("=== Mașini Mai Noi De 5 Ani ===");
					List<Masina> masiniNoi = masinaService.masiniMaiNoiDe5Ani();
					masiniNoi.forEach(masina -> logger.info(masina.toString()));
				}
				case 7 -> {
					System.out.print("Introduceți numărul de înmatriculare al mașinii de șters: ");
					String numarInmatriculare = scanner.nextLine();
					masinaService.stergeMasinaDupaNumar(numarInmatriculare);
					logger.info("Mașina a fost ștearsă (dacă exista în baza de date).");
				}
				case 8 -> {
					logger.info("Ieșire din aplicație...");
					running = false;
				}
				default -> System.out.println("Opțiune invalidă! Alegeți o opțiune validă.");
			}
		}
	}
}
