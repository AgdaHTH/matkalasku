# Matkalaskutus (Ohjelmistotekniikka, harjoitustyö, HY kevät 2020)

Sovelluksen avulla on mahdollista pitää kirjaa matkalaskuista. Sovellusta voi käyttää useampi rekisteröitynyt 
käyttäjä, joilla voi kaikilla olla useita matkalaskuja.

## Dokumentaatio

[Käyttöohje](https://github.com/AgdaHTH/matkalasku/blob/master/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/AgdaHTH/matkalasku/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/AgdaHTH/matkalasku/blob/master/dokumentaatio/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/AgdaHTH/matkalasku/blob/master/dokumentaatio/testaus.md)

[Työaikakirjanpito](https://github.com/AgdaHTH/matkalasku/blob/master/dokumentaatio/tuntikirjanpito.md)

## Tietokannan alustaminen

Ohjelma kysyy alussa käyttäjältä, haluaako tämä luoda tietokannan.

## Releaset

[Viikko 5](https://github.com/AgdaHTH/matkalasku/releases/tag/viikko5)

[Viikko 6](https://github.com/AgdaHTH/matkalasku/releases/tag/viikko6)

## Komentorivitoiminnot

### Projektin ajaminen

Projektin ajaminen tapahtuu komennolla

    mvn compile exec:java -Dexec.mainClass=travelexpenses.main.Main

### Testaus
Testit suoritetaan komennolla

    mvn test

Testikattavuusraportti luodaan komennolla 

    mvn test jacoco:report

Luotua raporttia voi tarkastella avaamalla selaimella tiedoston *target/site/jacoco/index.html*

### Jar

Jarin paketoiminen tapahtuu komennolla

    mvn package

joka tuottaa hakemistoon *target* suoritettavan jar-tiedoston *Matkalaskutus-1.0-SNAPSHOT.jar*

### JavaDoc

JavaDoc luodaan komennolla

    mvn javadoc:javadoc

minkä jälkeen JavaDocin näkee avaamalla selaimella tiedoston *target/site/apidocs/index.html*

### Checkstyle

Checkstyle-raportti syntyy komennolla

    mvn jxr:jxr checkstyle:checkstyle

ja sitä voi tarkastella avaamalla selaimessa tiedoston *target/site/checkstyle.html*
