# Matkalaskujärjestelmä
## Harjoitustyö, Ohjelmistotekniikka (HY kevät 2020)

### Sovelluksen tarkoitus
Sovelluksella pidetään kirjaa matkalaskuista esim. pienessä yrityksessä tai 
yhdistyksessä. Rekisteröitynyt käyttäjä voi tehdä itselleen matkalaskun.
Sovellusta käytetään graafisen käyttöliittymän kautta.

### Käyttäjät
Ensimmäisessä vaiheessa sovelluksessa on vain peruskäyttäjiä. Toisessa vaiheessa on tarkoitus olla
kaksi käyttäjäryhmää, matkalaskujen tekijät  eli normaalit käyttäjät ja admin-tason käyttäjä, 
joka voi hyväksyä tehtyjä matkalaskuja maksettavaksi.

### Perustoiminnallisuudet

#### Ennen kirjautumista
* Käyttäjä voi valita haluaako hän alustaa tietokannan vai ei.
* Käyttäjä voi rekisteröityä sovellukseen. 
* Rekisteröitynyt käyttäjä voi kirjautua sisään sovellukseen. 

#### Kirjautumisen jälkeen 
* Käyttäjä voi lisätä itselleen matkalaskun.
* Yhdellä käyttäjällä voi olla useampi matkalasku.
* Matkalaskussa on tieto matkustajasta, matkakohteesta ja sen sijainnista, matkan alku- ja loppupäivämäärästä, 
matkakuluista sekä päivärahasta.
* Sovellus laskee päivärahan matkan keston perusteella, ja sen mukaan, onko matka suuntautunut 
kotimaahan vai ulkomaille.
* Kaikki laskun tiedot tallentuvat omiin sarakkeisiinsa tietokantaan.


### Jatkokehitysmahdollisuuksia
* Käyttöliittymässä näkyy kirjautuneen käyttäjän tunnus
* Yhdelle laskulle voi syöttää useita kulueriä ja alasvetovalikosta valittava kulutyyppi määrittää 
kulun tallentumisen tietokantaan. Tässä vaiheessa tietokantaan olisi parasta luoda kuluja varten uusi taulu.
* Käyttäjä näkee listauksen omista matkalaskuistaan ja voi selata laskujaan tietyltä aikaväliltä.
* Sovellukseen lisätään admin-käyttäjä, jonka on mahdollista merkitä 
matkalaskuja hyväksytyiksi.


