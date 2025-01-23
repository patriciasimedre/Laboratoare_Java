# Laborator 11

Acest proiect este o aplicație **Spring Boot** care expune un **REST API** pentru gestionarea evenimentelor culturale dintr-un oraș. Aplicația oferă funcționalități CRUD pentru tabela evenimente dintr-o bază de date **MySQL**.

---

## Funcționalități

- Afișarea informațiilor despre toate evenimentele din baza de date.
- Afișarea informațiilor despre evenimentele care au loc într-o anumită locație.
- Afișarea informațiilor despre evenimentele care au loc într-o dată specificată.
- Adăugarea unui nou eveniment.
- Actualizarea unui eveniment existent.
- Ștergerea unui eveniment identificat prin ID.

---

## Structura Proiectului

- **Entity**: Clasa `Eveniment` care mapează tabela bazei de date.
- **Repository**: Interfața `EvenimentRepository` pentru operații CRUD.
- **Service**: Clasa `EvenimentService` care implementează logica de business.
- **Controller**: Clasa `EvenimenteController` care expune REST API-ul.
- **Resources**:
  - `application.properties`: Configurația aplicației.
  - `data.sql`: Script SQL pentru inițializarea bazei de date cu date de test.

---

## Tehnologii Utilizate

- **Spring Boot**
- **Spring Data JPA**
- **Spring Web**
- **Spring Boot DevTools**
- **MySQL** pentru stocarea datelor.
- **Docker** pentru rularea aplicației și bazei de date într-un mediu containerizat.
- **Lombok** pentru reducerea boilerplate-ului în cod.

---

## Cerințe de Sistem

- **Java 17+**
- **Maven**
- **Docker și Docker Compose**
- **MySQL 8+**

---

## Instalare și Configurare

### Clonarea Repozitoriului

```bash
git clone https://github.com/username/Laborator11_Java.git
cd Laborator11_Java
```

### Construirea Aplicației

Asigură-te că Maven este instalat și rulează următoarea comandă pentru a construi aplicația:

```bash
mvn clean install
```

###

Actualizează fișierul `application.properties` cu detaliile tale de conectare MySQL dacă este necesar:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/cultural_events
spring.datasource.username=root
spring.datasource.password=my-secret-pw
spring.jpa.hibernate.ddl-auto=update
spring.sql.init.mode=always
```

### Rularea Aplicației cu Docker

Rulează următoarea comandă pentru a porni aplicația și baza de date într-un mediu containerizat:

```bash
docker-compose up --build
```

---

## Accesarea API-ului
Accesează API-ul la adresa: ```http http://localhost:8080/api/evenimente```.

---

## Testare

### Testarea API-ului cu Postman sau cURL

Obține toate evenimentele
```http
GET http://localhost:8080/api/evenimente
```

Obține un eveniment după ID
```http
GET http://localhost:8080/api/evenimente/{id}
```

Creează un nou eveniment
```http
POST http://localhost:8080/api/evenimente
```

Corpul cererii (JSON):

```json
{
  "denumire": "Concert Jazz",
  "locatie": "Opera Română",
  "dataTimp": "2025-01-15T20:00:00",
  "pretBilet": 120.0
}
```

Actualizează un eveniment

```http
PUT http://localhost:8080/api/evenimente/{id}
```
Corpul cererii (JSON):

```json
{
  "denumire": "Concert Jazz Actualizat",
  "locatie": "Opera Națională",
  "dataTimp": "2025-02-01T19:00:00",
  "pretBilet": 150.0
}
```
Șterge un eveniment
```http
DELETE http://localhost:8080/api/evenimente/{id}
```

---

## Conectare la Docker

### Asigură-te că serviciile sunt pornite:
```bash
docker-compose up --build
```

### Verifică dacă containerele rulează corect:
```bash
docker ps
```
Vei vedea containerele `mysql-container` și `springboot-container` în listă.

---

## Conectare la MySQL din Terminal

### Conectare directă din terminal la containerul MySQL
```bash
docker exec -it mysql-container mysql -u root -p
```
- Introdu parola setată în docker-compose.yml (ex.: parola123).

### Verificarea bazei de date

După conectare, poți folosi următoarele comenzi SQL pentru a verifica baza de date:

1. Afișează toate bazele de date:

```sql
SHOW DATABASES;
```

2. Selectează baza de date `cultural_events`:

```sql
USE cultural_events;
```

3. Afișează toate tabelele din baza de date:

```sql
SHOW TABLES;
```

4. Afișează toate datele din tabela `eveniment`:

```sql
SELECT * FROM eveniment;
```

---

## Temă laborator Java - UPT (sesiune 2025)
