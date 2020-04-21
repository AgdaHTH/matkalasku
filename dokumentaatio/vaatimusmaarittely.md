# Matkalaskujärjestelmä
## Harjoitustyö, Ohjelmistotekniikka (HY kevät 2020)

### Sovelluksen tarkoitus
Sovelluksella pidetään kirjaa matkalaskuista esim. pienessä yrityksessä tai 
yhdistyksessä. Rekisteröitynyt käyttäjä voi tehdä itselleen matkalaskun.
Sovellusta käytetään graafisen käyttöliittymän kautta.

### Käyttäjät
Sovelluksessa on tarkoitus olla kaksi käyttäjäryhmää, matkalaskujen tekijät 
eli normaalit käyttäjät ja admin-tason käyttäjä, joka voi hyväksyä tehtyjä 
matkalaskuja maksettavaksi. Ensimmäisessä vaiheessa sovelluksessa on kuitenkin
vain peruskäyttäjiä.

### Perustoiminnallisuudet
#### Ennen kirjautumista
* Käyttäjä voi rekisteröityä sovellukseen. -TEHTY
* Rekisteröitynyt käyttäjä voi kirjautua sisään sovellukseen. -TEHTY

#### Kirjautumisen jälkeen
* Käyttäjä näkee listauksen omista matkalaskuistaan. -TEKEMÄTTÄ
* Käyttäjä voi lisätä itselleen matkalaskun. -TEHTY (PUUTTUU yhteys laskun ja tekijän väliltä)
* Yhdellä käyttäjällä voi olla useampi matkalasku. -TEHTY (PUUTTUU yhteys laskun ja tekijän väliltä)
* Matkalaskussa on tieto matkustajasta, matkakohteesta, matkan alku- ja loppupäivämäärästä, matkakuluista 
sekä päivärahasta. -TEHTY (PUUTTUU tieto matkustajasta, eivätkä kulut ja pvrahat vielä tallennu tietokantaan)
* Käyttäjä voi syöttää laskulle useita kulueriä. -TEKEMÄTTÄ (eikä ehkä onnistu ilman tietokannan laajentamista)
* Sovellus laskee päivärahan matkan keston perusteella, ja sen mukaan,
onko matka suuntautunut kotimaahan vai ulkomaille. -TEHTY (PUUTTUU tämän näkyminen käyttöliittymässä)

### Jatkokehitysmahdollisuuksia
* Käyttäjä näkee kaikkien laskujensa yhteissumman 
* Käyttäjä voi selata laskujaan tietyltä aikaväliltä
* Ensimmäisessä vaiheessa matkakulut esitetään yhtenä summana mutta tätä on 
mahdollista myöhemmin laajentaa niin, että matkakuluihin tulee tarkempi 
erittely matkan eri vaiheista.
* Sovellukseen lisätään admin-käyttäjä, jonka on mahdollista merkitä 
matkalaskuja hyväksytyiksi.

