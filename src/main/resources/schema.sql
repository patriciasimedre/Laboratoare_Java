-- Crearea tabelului `eveniment` daca nu exista
CREATE TABLE IF NOT EXISTS eveniment (
                                         id INT AUTO_INCREMENT PRIMARY KEY,
                                         denumire VARCHAR(255) NOT NULL,
    locatie VARCHAR(255) NOT NULL,
    data_timp DATETIME NOT NULL,
    pret_bilet DECIMAL(10, 2) NOT NULL
    );