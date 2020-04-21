# Matkalaskutus (Ohjelmistotekniikka, harjoitustyö, HY kevät 2020)

Sovelluksen avulla on mahdollista pitää kirjaa matkalaskuista. Sovellusta voi käyttää useampi rekisteröitynyt 
käyttäjä, joilla voi kaikilla olla useita matkalaskuja.

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/AgdaHTH/matkalasku/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/AgdaHTH/matkalasku/blob/master/dokumentaatio/arkkitehtuuri.md)

[Työaikakirjanpito](https://github.com/AgdaHTH/matkalasku/blob/master/dokumentaatio/tuntikirjanpito.md)


## Tietokannan alustaminen

Ohjelma alustaa tietokannan kutsumalla luokan travelexpenses.domain.TravelExpensesApp 
konstruktorissa metodia createDatabase(), joka alustaa tietokannan luomalla luokan:

    CreateDatabase database = new CreateDatabase();

Metodia createDatabase ei tarvitse kutsua sen jälkeen kun tietokanta on luotu.

## Komentorivitoiminnot

### Projektin ajaminen

Projektin ajaminen tapahtuu komennolla

    mvn compile exec:java -Dexec.mainClass=travelexpenses.main.Main

### Testaus
Testit suoritetaan komennolla

    mvn test

Testikattavuusraportti luodaan komennolla 

    mvn test jacoco:report

Jarin paketoiminen tapahtuu komennolla

    mvn package

Checkstyle-raportti syntyy komennolla

    mvn jxr:jxr checkstyle:checkstyle


