package exjdbc;

import java.sql.*;
import java.time.Year;
import java.util.InputMismatchException;
import java.util.Scanner;

//   Excepții
class ExceptieVarsta extends Exception {
    public ExceptieVarsta(String mesaj) {
        super(mesaj);
    }
}

class ExceptieAnExcursie extends Exception {
    public ExceptieAnExcursie(String mesaj) {
        super(mesaj);
    }
}

//   MainApp
public class MainApp {

    // Ajustează parametrii de conectare după nevoile tale (port, user, parola).
    private static final String URL = "jdbc:mysql://localhost:3306/lab8";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Limite acceptate pentru vârstă
    private static final int MIN_VARSTA = 0;
    private static final int MAX_VARSTA = 100;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== MENIU ===");
            System.out.println("1. Adăugare persoană");
            System.out.println("2. Adăugare excursie");
            System.out.println("3. Afișare toate persoanele + excursiile lor");
            System.out.println("4. Afișare excursii pentru o persoană (după nume)");
            System.out.println("5. Afișare persoane care au vizitat o destinație dată");
            System.out.println("6. Afișare persoane care au făcut excursii într-un an dat");
            System.out.println("7. Ștergere excursie (după id_excursie)");
            System.out.println("8. Ștergere persoană (împreună cu excursiile ei)");
            System.out.println("9. Ieșire");

            System.out.print("Alege opțiunea: ");
            int optiune;
            try {
                optiune = sc.nextInt();
                sc.nextLine(); // curățăm bufferul
            } catch (InputMismatchException e) {
                System.err.println("Te rog introdu un număr valid!");
                sc.nextLine();
                continue;
            }

            switch (optiune) {
                case 1 -> adaugaPersoana(sc);
                case 2 -> adaugaExcursie(sc);
                case 3 -> afisarePersoaneCuExcursii();
                case 4 -> afisareExcursiiPersoana(sc);
                case 5 -> afisarePersoaneDupaDestinatie(sc);
                case 6 -> afisarePersoaneDupaAn(sc);
                case 7 -> stergereExcursie(sc);
                case 8 -> stergerePersoana(sc);
                case 9 -> {
                    System.out.println("La revedere!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Opțiune inexistentă!");
            }
        }
    }

    //   1. ADĂUGARE PERSOANĂ

    private static void adaugaPersoana(Scanner sc) {
        System.out.print("Nume persoană: ");
        String nume = sc.nextLine();
        int varsta;
        while (true) {
            try {
                System.out.print("Vârsta: ");
                varsta = citesteVarstaValida(sc);
                break;
            } catch (ExceptieVarsta e) {
                System.err.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.err.println("Trebuie să introduci un număr întreg!");
                sc.nextLine(); // golim bufferul
            }
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO persoane(nume, varsta) VALUES(?,?)"
             )) {
            ps.setString(1, nume);
            ps.setInt(2, varsta);
            ps.executeUpdate();
            System.out.println("Persoana a fost adăugată cu succes!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //   2. ADĂUGARE EXCURSIE

    private static void adaugaExcursie(Scanner sc) {
        System.out.print("ID persoană căreia îi aparține excursia: ");
        int idPersoana;
        try {
            idPersoana = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.err.println("ID persoanei trebuie să fie întreg!");
            sc.nextLine();
            return;
        }

        // Verificăm dacă persoana există
        if (!existaPersoana(idPersoana)) {
            System.out.println("Persoana cu id=" + idPersoana + " nu există în tabela persoane!");
            return;
        }

        System.out.print("Destinația excursiei: ");
        String destinatie = sc.nextLine();

        // Aflăm vârsta persoanei (pentru validarea anului excursiei >= anul nașterii)
        int varstaPersoana = getVarstaPersoana(idPersoana);
        if (varstaPersoana == -1) {
            System.out.println("Eroare la determinarea vârstei persoanei!");
            return;
        }
        int anulNasterii = Year.now().getValue() - varstaPersoana;

        int anExcursie;
        while (true) {
            try {
                System.out.print("Anul excursiei: ");
                anExcursie = citesteAnExcursieValid(sc, anulNasterii);
                break;
            } catch (ExceptieAnExcursie e) {
                System.err.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.err.println("Anul excursiei trebuie să fie întreg!");
                sc.nextLine();
            }
        }

        // Inserție în DB
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO excursii(id_persoana, destinatia, anul) VALUES (?,?,?)"
             )) {
            ps.setInt(1, idPersoana);
            ps.setString(2, destinatie);
            ps.setInt(3, anExcursie);
            ps.executeUpdate();
            System.out.println("Excursie adăugată cu succes!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //   3. AFIȘAREA TUTUROR PERSOANELOR + EXCURSIILE LOR

    private static void afisarePersoaneCuExcursii() {
        String sql = """
            SELECT p.id, p.nume, p.varsta, e.id_excursie, e.destinatia, e.anul
            FROM persoane p
            LEFT JOIN excursii e ON p.id = e.id_persoana
            ORDER BY p.id, e.id_excursie
            """;
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            int lastPersId = -1;
            boolean firstRow = true;

            while (rs.next()) {
                int idPers = rs.getInt("id");
                String nume = rs.getString("nume");
                int varsta = rs.getInt("varsta");
                int idExc = rs.getInt("id_excursie");
                String dest = rs.getString("destinatia");
                int an = rs.getInt("anul");

                if (idPers != lastPersId) {
                    if (!firstRow) {
                        System.out.println(); // linie liberă între persoane
                    }
                    System.out.println("Persoană: (id=" + idPers + "), " + nume + ", " + varsta + " ani");
                    System.out.println("Excursii:");
                    lastPersId = idPers;
                    firstRow = false;
                }

                if (idExc == 0) {
                    // Persoana nu are excursii
                    System.out.println("   - Nicio excursie");
                } else {
                    System.out.println("   - id_excursie=" + idExc + " -> " + dest + ", anul: " + an);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //   4. AFIȘAREA EXCURSIILOR UNEI PERSOANE (după nume)

    private static void afisareExcursiiPersoana(Scanner sc) {
        System.out.print("Numele persoanei: ");
        String numeCautat = sc.nextLine();

        String sql = """
            SELECT p.id, p.nume, e.id_excursie, e.destinatia, e.anul
            FROM persoane p
            JOIN excursii e ON p.id = e.id_persoana
            WHERE p.nume = ?
            ORDER BY e.id_excursie
            """;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, numeCautat);
            try (ResultSet rs = ps.executeQuery()) {
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    int idPers = rs.getInt("id");
                    int idExc = rs.getInt("id_excursie");
                    String dest = rs.getString("destinatia");
                    int an = rs.getInt("anul");
                    System.out.println("Persoană (id=" + idPers + ", " + numeCautat + ") -> "
                            + "Excursie (id=" + idExc + "): " + dest + ", anul " + an);
                }
                if (!found) {
                    System.out.println("Nicio excursie găsită pentru persoana '" + numeCautat
                            + "' sau persoana nu există.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //   5. AFIȘAREA PERSOANELOR CARE AU VIZITAT O DESTINAȚIE DATĂ

    private static void afisarePersoaneDupaDestinatie(Scanner sc) {
        System.out.print("Destinația dorită: ");
        String destinatie = sc.nextLine();

        String sql = """
            SELECT DISTINCT p.id, p.nume, p.varsta
            FROM persoane p
            JOIN excursii e ON p.id = e.id_persoana
            WHERE e.destinatia = ?
            ORDER BY p.id
            """;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, destinatie);
            try (ResultSet rs = ps.executeQuery()) {
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.println("Persoană (id=" + rs.getInt("id") + ", "
                            + rs.getString("nume") + ", " + rs.getInt("varsta") + " ani)");
                }
                if (!found) {
                    System.out.println("Nicio persoană nu a vizitat destinația '" + destinatie + "'.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //   6. AFIȘAREA PERSOANELOR CARE AU FĂCUT EXCURSII ÎNTR-UN AN DAT

    private static void afisarePersoaneDupaAn(Scanner sc) {
        System.out.print("Anul căutat: ");
        int an;
        try {
            an = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.err.println("Anul trebuie să fie un număr întreg!");
            sc.nextLine();
            return;
        }

        String sql = """
            SELECT DISTINCT p.id, p.nume, p.varsta
            FROM persoane p
            JOIN excursii e ON p.id = e.id_persoana
            WHERE e.anul = ?
            ORDER BY p.id
            """;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, an);
            try (ResultSet rs = ps.executeQuery()) {
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.println("Persoană (id=" + rs.getInt("id") + ", "
                            + rs.getString("nume") + ", " + rs.getInt("varsta") + " ani)");
                }
                if (!found) {
                    System.out.println("Nicio persoană nu a făcut excursii în anul " + an + ".");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //   7. ȘTERGEREA UNEI EXCURSII (după id_excursie)

    private static void stergereExcursie(Scanner sc) {
        System.out.print("ID-ul excursiei de șters: ");
        int idExc;
        try {
            idExc = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.err.println("ID-ul excursiei trebuie să fie întreg!");
            sc.nextLine();
            return;
        }

        String sql = "DELETE FROM excursii WHERE id_excursie = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idExc);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Excursia cu id_excursie=" + idExc + " a fost ștearsă.");
            } else {
                System.out.println("Nu există excursie cu id_excursie=" + idExc + ".");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //   8. ȘTERGEREA UNEI PERSOANE (și a excursiilor ei)

    private static void stergerePersoana(Scanner sc) {
        System.out.print("ID-ul persoanei de șters: ");
        int idPers;
        try {
            idPers = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.err.println("ID-ul persoanei trebuie să fie întreg!");
            sc.nextLine();
            return;
        }

        // Ștergem mai întâi excursiile persoanei, apoi persoana
        String sqlExc = "DELETE FROM excursii WHERE id_persoana = ?";
        String sqlPers = "DELETE FROM persoane WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            conn.setAutoCommit(false);
            try (PreparedStatement psExc = conn.prepareStatement(sqlExc);
                 PreparedStatement psPers = conn.prepareStatement(sqlPers)) {

                // Ștergere excursii
                psExc.setInt(1, idPers);
                psExc.executeUpdate();

                // Ștergere persoană
                psPers.setInt(1, idPers);
                int rowsPers = psPers.executeUpdate();

                if (rowsPers > 0) {
                    System.out.println("Persoana cu id=" + idPers + " și excursiile ei au fost șterse.");
                } else {
                    System.out.println("Nu există persoană cu id=" + idPers + ".");
                }

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            } finally {
                conn.setAutoCommit(true);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //   METODE UTILE (validări, existență persoană, etc.)

    private static boolean existaPersoana(int idPersoana) {
        String sql = "SELECT * FROM persoane WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPersoana);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // true dacă există rând
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static int getVarstaPersoana(int idPersoana) {
        String sql = "SELECT varsta FROM persoane WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPersoana);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("varsta");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private static int citesteVarstaValida(Scanner sc) throws ExceptieVarsta, InputMismatchException {
        int varsta = sc.nextInt(); // poate arunca InputMismatchException
        if (varsta < MIN_VARSTA) {
            throw new ExceptieVarsta("Vârsta nu poate fi negativă!");
        }
        if (varsta > MAX_VARSTA) {
            throw new ExceptieVarsta("Vârsta nu poate depăși " + MAX_VARSTA + " ani!");
        }
        return varsta;
    }

    private static int citesteAnExcursieValid(Scanner sc, int anulNasterii)
            throws ExceptieAnExcursie, InputMismatchException {
        int an = sc.nextInt(); // poate arunca InputMismatchException
        int anCurent = Year.now().getValue();
        if (an < anulNasterii) {
            throw new ExceptieAnExcursie("Anul excursiei (" + an
                    + ") nu poate fi mai mic decât anul nașterii (" + anulNasterii + ")!");
        }
        if (an > anCurent) {
            throw new ExceptieAnExcursie("Anul excursiei (" + an
                    + ") nu poate fi mai mare decât anul curent (" + anCurent + ")!");
        }
        return an;
    }
}

// mysql_container