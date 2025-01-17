## 1
# Implementarea clasei Parabola

Se cere să se definească o clasă `Parabola` ai cărei membri vor fi:

### Membri ai clasei
1. 3 variabile de tip `int` care reprezintă coeficienții a, b și c.
2. Un constructor cu 3 parametri de tip `int`.
3. O metodă care calculează și returnează vârful parabolei.
4. Se va redefini metoda `toString()` din clasa `Object`, astfel încât să returneze un `String` de forma \( f(x) = ax^2 + bx + c \). Caracteristicile a, b și c ale parabolei se vor înlocui cu valorile efective. Metoda va fi utilizată pentru afișarea parabolei sub forma unei funcții.
5. O metodă care primește ca și parametru de intrare o parabolă și returnează coordonatele mijlocului segmentului de dreaptă care unește vârful parabolei curente cu vârful parabolei transmise ca și parametru de intrare.
6. O metodă statică ce primește ca parametri de intrare două parabolă și calculează coordonatele mijlocului segmentului de dreaptă care unește vârfurile celor două parabole.
7. O metodă care primește ca și parametru de intrare o parabolă și returnează lungimea segmentului de dreaptă care unește vârful parabolei curente cu vârful parabolei transmise ca și parametru de intrare. Se va utiliza metoda statică `Math.hypot`.
8. O metodă statică care primește ca și parametri de intrare două parabolă și calculează lungimea segmentului de dreaptă ce unește vârfurile celor două parabole. Se va utiliza metoda statică `Math.hypot`.

### Calculul coordonatelor mijlocului segmentului de dreaptă
Coordonatele mijlocului segmentului de dreaptă care unește vârfurile a două parabole se calculează în felul următor:
\[
\begin{aligned}
    x &= \frac{x_1 + x_2}{2}, \\
    y &= \frac{y_1 + y_2}{2},
\end{aligned}
\]
unde \((x_1, y_1)\) sunt coordonatele vârfului primei parabole, iar \((x_2, y_2)\) descriu vârful celei de-a doua parabole.

### Calculul lungimii segmentului de dreaptă
Lungimea segmentului care unește vârfurile a două parabole se calculează în felul următor:
\[
\sqrt{(x_2 - x_1)^2 + (y_2 - y_1)^2}
\]

## Detalii despre fișierul de intrare
Fișierul de intrare `in.txt` conține mai multe linii, pe fiecare linie aflându-se coeficienții unei parabole. Pentru fiecare linie:
1. Se citește informația din fișierul de intrare și pentru fiecare linie să se creeze câte un obiect de tip `Parabola` care să se adauge unei colecții de tip `List`.
2. Colecția va fi apoi traversată și pentru fiecare element se va afișa parabola sub forma unei funcții și apoi vârful parabolei.
3. Pentru două parabolă din colecție se va afișa mijlocul segmentului care le unește vârfurile și lungimea segmentului care le unește vârfurile apelând metodele dezvoltate în clasa `Parabola`.

## Exemple de calcul

### Exemplu de fișier de intrare
Fișierul `in.txt`:
```
1 2 1
2 -4 2
```

### Rezultate așteptate
1. Se vor crea două obiecte de tip `Parabola`.
2. Se vor afișa funcțiile:
   - `f(x) = x^2 + 2x + 1`
   - `f(x) = 2x^2 - 4x + 2`
3. Se vor afișa vârfurile fiecărei parabole.
4. Se vor calcula și afișa:
   - Coordonatele mijlocului segmentului care unește vârfurile.
   - Lungimea segmentului care unește vârfurile celor două parabole.

## 2

# Gestionarea produselor dintr-un magazin

Se cere să se scrie un program care citește din fișierul `produse.csv` informații referitoare la produsele dintr-un magazin. Pe fiecare linie se află:

- denumirea produsului
- prețul (număr real)
- cantitatea (număr întreg)
- data expirării (tip `LocalDate`)

### Structura fișierului de intrare
Fiecare linie din fișier are cele patru elemente separate prin caracterul `,`.
Exemplu:
```
zahar, 1.5, 50, 2024-02-25
```

### Cerințe de implementare

Pentru fiecare rând citit se va crea un obiect de tip `Produs`, care va fi adăugat unei colecții de obiecte de tip `List`.

Se va defini o clasă `Produs` care va conține:
- Variabile membre private corespunzătoare celor trei elemente care descriu un produs.
- Cel puțin un constructor.
- **Gettere** și **settere** în funcție de necesități.
- Redefinirea metodei `toString()` din clasa `Object`, metodă care va fi utilizată pentru afișarea produselor.

### Funcționalitățile programului
Se va realiza un program care va dispune de un meniu și va implementa următoarele cerințe:

#### a) Afișarea tuturor produselor din colecția `List`
Toate produsele vor fi afișate folosind metoda `toString()`.

#### b) Afișarea produselor expirate
Produsele expirate sunt cele a căror dată de expirare este anterioară datei curente. Programul va folosi `LocalDate.now()` pentru comparație.

#### c) Vânzarea unui produs
- Vânzarea unui produs se poate realiza doar dacă există suficientă cantitate pe stoc.
- Dacă produsul ajunge la cantitatea zero, se elimină din listă.
- În clasa `Produs` se va declara o variabilă statică `incasari` care se va actualiza la fiecare vânzare a unui produs, luând în considerare prețul produsului și cantitatea vândută.

#### d) Afișarea produselor cu prețul minim
Se vor afișa toate produsele care au cel mai mic preț (pot fi mai multe produse cu același preț).

#### e) Salvarea produselor într-un fișier de ieșire
Se vor salva produsele care au o cantitate mai mică decât o valoare citită de la tastatură într-un fișier de ieșire. Structura fișierului de ieșire va fi similară cu cea a fișierului de intrare.

### Exemplu de utilizare
1. **Fișierul de intrare** `produse.csv`:
```
zahar, 1.5, 50, 2024-02-25
faina, 2.0, 30, 2023-12-01
lapte, 3.5, 10, 2023-01-15
```
2. **Funcționalități posibile**:
   - Afișarea tuturor produselor.
   - Afișarea produselor expirate (dacă data curentă este după 2023-12-01, produsele expirate sunt `faina` și `lapte`).
   - Vânzarea a 5 unități de `zahar` și actualizarea încasărilor.
   - Afișarea produselor cu cel mai mic preț (în exemplul de mai sus: `zahar`).
   - Salvarea produselor cu cantitate mai mică de 20 într-un fișier `produse_iesire.csv`.
