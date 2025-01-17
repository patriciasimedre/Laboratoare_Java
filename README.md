## 1
  Fișierul `judete_in.txt`, conține lista neordonată a județelor din țară. Să se încarce datele din fișier într-un tablou de String-uri și să se ordoneze acest tablou cu ajutorul metodei sort() din clasa Arrays. Să se returneze pe ce poziție se află în vectorul ordonat un județ introdus de la tastatură. Se va utiliza metoda de căutare binară din clasa Arrays.

## 2

  Fișierul `cantec_in.txt` conține versurile unui cântec la alegere. Să se scrie un program care creează fișierul `cantec_out.txt`, care conține versurile cântecului original dar în plus în dreptul fiecărui rând sunt afișate numărul de cuvinte de pe rând şi numărul de vocale de pe fiecare rând. În dreptul rândurilor care se încheie cu o grupare de litere aleasă se va pune o steluță (*). Rândurile pentru care un număr generat aleator este mai mic decât 0.1 vor fi scrise cu majuscule (se vor genera aleator numere între 0 şi 1). Se va defini o clasă Vers, care are o variabilă membră privată un șir de caractere reprezentând versul și se va dezvolta câte un operator pentru fiecare cerință de mai sus (o metodă care returnează numărul de cuvinte, o metodă care returnează numărul de vocale, etc). Se va crea un vector de obiecte de tip Vers care va conține informația preluată din fișierul de intrare.

## 3

  Să se insereze într-o anumită poziție a unui șir de caractere, un alt șir. Datele vor fi preluate de la tastatură sau din fișier. Să se șteargă o porțiune a unui șir de caractere care începe dintr-o anumită poziție și are un anumit număr de caractere. Se recomandă utilizarea clasei <i> StringBuilder </i>.

## 4

  Să se realizeze un program care citește numele si <i>CNP-ul</i> pe care îl au n persoane. Valoarea lui n se citește de la tastatură. Programul va afișa informațiile introduse și în plus pentru fiecare persoana va afișa <b>vârsta</b>. Cât timp un <i>CNP</i> este introdus greșit programul va cere reintroducerea acestuia. Pentru simplitate se consideră că <i>CNP-ul</i> este valid dacă îndeplinește următoarele condiții:
- Are 13 caractere
- Toate caracterele sunt cifre
- Prima cifră are una din valorile 1, 2, 5, 6
- Cifra de control a CNP-ului are o valoare validă.
- 
Detalii legate de semnificația cifrelor din codul numeric personal şi de modul de calcul al cifrei de control se găsesc pe link-ul:
``` link
https://www.scientia.ro/stiinta-la-minut/128-cultura-economie/459-cnp-codul-numeric-personal.html
```
  Se va crea clasa <i>Persoana</i> cu variabile membre private <b>nume</b> (String) şi <b>cnp</b> (String). Clasa va avea constructor cu parametri, gettere si settere în funcție de necesități şi metoda getVarsta() care va calcula şi va returna vârsta persoanei extrăgând data nașterii din CNP şi citind din sistem data curentă. Se va utiliza clasa LocalDate. Se va crea un vector în care se vor adăuga obiectele de tip Persoana. Fiecare element din vectorul va fi afișat pe un rând în formatul nume, CNP, varsta.

