package com.example.masini_JPA;

import com.example.masini_JPA.entitate.Masina;
import com.example.masini_JPA.service.MasinaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class MasiniJPAApplication implements CommandLineRunner {

	private final MasinaService masinaService;

	public MasiniJPAApplication(MasinaService masinaService) {
		this.masinaService = masinaService;
	}

	public static void main(String[] args) {
		SpringApplication.run(MasiniJPAApplication.class, args);
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
			scanner.nextLine();

			switch (optiune) {
				case 1 -> masinaService.toateMasinile().forEach(System.out::println);
				case 2 -> {
					System.out.print("Număr înmatriculare: ");
					String numar = scanner.nextLine();
					System.out.print("Marcă: ");
					String marca = scanner.nextLine();
					System.out.print("An fabricație: ");
					int an = scanner.nextInt();
					scanner.nextLine();
					System.out.print("Culoare: ");
					String culoare = scanner.nextLine();
					System.out.print("Număr kilometri: ");
					int km = scanner.nextInt();

					Masina masina = new Masina();
					masina.setNumarInmatriculare(numar);
					masina.setMarca(marca);
					masina.setAnFabricatie(an);
					masina.setCuloare(culoare);
					masina.setNumarKilometri(km);

					masinaService.adaugaMasina(masina);
				}
				case 3 -> {
					System.out.print("Introduceți numărul de înmatriculare: ");
					String numar = scanner.nextLine();
					Masina masina = masinaService.cautaMasinaDupaNumar(numar);
					System.out.println(masina != null ? masina : "Mașina nu există.");
				}
				case 4 -> {
					System.out.print("Introduceți marca: ");
					String marca = scanner.nextLine();
					System.out.println("Număr mașini: " + masinaService.numaraMasiniDupaMarca(marca));
				}
				case 5 -> System.out.println("Număr mașini sub 100.000 km: " + masinaService.numaraMasiniSub100000Km());
				case 6 -> masinaService.masiniMaiNoiDe5Ani().forEach(System.out::println);
				case 7 -> {
					System.out.print("Introduceți numărul de înmatriculare: ");
					String numar = scanner.nextLine();
					masinaService.stergeMasinaDupaNumar(numar);
				}
				case 8 -> running = false;
				default -> System.out.println("Opțiune invalidă.");
			}
		}
	}
}
