DROP TABLE IF EXISTS masina;

CREATE TABLE masina (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        numar_inmatriculare VARCHAR(20) NOT NULL UNIQUE,
                        marca VARCHAR(50),
                        an_fabricatie INT,
                        culoare VARCHAR(30),
                        numar_kilometri INT
);

INSERT INTO masina (numar_inmatriculare, marca, an_fabricatie, culoare, numar_kilometri)
VALUES
    ('AB-01-XYZ', 'Dacia', 2017, 'Alb', 75000),
    ('BC-02-WYZ', 'Ford', 2020, 'Negru', 40000),
    ('CJ-03-XYZ', 'Toyota', 2018, 'Rosu', 60000),
    ('TM-04-WXY', 'Volkswagen', 2016, 'Albastru', 85000),
    ('BH-05-YZX', 'BMW', 2021, 'Gri', 30000),
    ('BH-27-PTY', 'Mercedes-Benz', 2013, 'Negru', 127000);
