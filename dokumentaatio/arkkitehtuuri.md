## Arkkitehtuurikuvaus

### Rakenne

Ohjelman rakenne on kolmitasoinen. Luokat on jaoteltu kolmeen pakkaukseen: 

* travelexpenses.ui sisältää JavaFX-kirjaston avulla toteutetun graafisen käyttöliittymän (TravelExpensesApp)
* travelexpenses.domain sisältää sovelluslogiikan sekä käyttäjää (User), laskua (Bill)
ja päivärahaa (Allowance) kuvaavat luokat
* travelexpenses.dao sisältää tietokannan käsittelyyn tarvittavat rajapinnat (UserDao ja BillDao) 
ja luokat (DatabaseUserDao ja DatabaseBillDao).

Pakkausrakenteen kuvaus:

![package chart](package_chart.png)

### Käyttöliittymä

Käyttöliittymä on toteutettu luokassa travelexpenses.ui.GraphicalUI ja se sisältää viisi näkymää: 

* kysymys tietokannan alustamisesta
* kirjautuminen
* uuden käyttäjän luominen
* uuden matkalaskun luominen
* lopetusnäkymä  

Kukin näkymä on toteutettu omana Scene-olionaan. Käyttöliittymä on pyritty eristämään sovelluslogiikasta.

### Sovelluslogiikka

Sovelluslogiikka on toteutettu luokassa travelexpenses.domain.TravelExpensesApp. 
Graafisessa käyttöliittymässä luodaan tästä luokasta oli, jolle injektoidaan riippuvuudet 
UserDao- ja BillDao -rajapinnat toteuttaviin luokkiin DatabaseUserDao ja DatabaseBillDao. 
Näiden luokkien kautta käsitellään tietokantaan tallennettavaa ja siellä olevaa tietoa.

Sovelluslogiikassa näitä tietokannankäsittelyluokkia (ja tietokannan tauluja, ks. alla) vastaavat
luokat User ja Bill. Luokkien välillä ei ole riippuvuutta ohjelman tasolla, vaan niitä samoin kuin
kolmatta, päivärahaa kuvaavaa luokka Allowance käytetään sovelluslogiikan kautta.

Sovelluslogiikan tärkeimmät metodit ovat:

* boolean login (username)
* boolean createUser (User user)
* boolean addBill (Bill bill)

Sovelluslogiikkaluokassa suoritetaan myös päivämääräsyötteiden muodon tarkastus ja konvertointi 
LocalDate-muotoisiksi:

* LocalDate convertDate (String date)
* boolean checkDate (String date)
* boolean checkBeginDateIsNotAfterEndDate(String beginDate, String endDate)

Päivämääräsyötteiden tarkistus tapahtuu Count Allowance -nappia painamalla, jolloin
myös hyväksytyt päivämäärät tallentuvat luokan oliomuuttujiksi, jotta niitä voidaan käyttää
myös varsinaisessa laskun luomisessa.

### Tiedon pysyväistallennus

Sovellus käyttää travelexpenses.db-nimistä sqlite-tietokantaa. Tietokannan
rakenne on kuvattu seuraavassa kaaviossa.

![database](database.png)

Käynnistyessään ohjelma kysyy käyttäjältä, alustetaanko tai luodaanko
tietokanta. Jos käyttäjä valitsee "Yes", kutsutaan sovelluslogiikan metodia
createDatabase()-metodia, joka poistaa mahdollisesti olemassaolevat taulut
ja luo taulut uudestaan.

### Päätoiminnallisuudet

Allaolevissa kaavioissa on kuvattu tärkeimpien metodien toiminta.

NB Lisää selittävää tekstiä näiden alle!

Uuden käyttäjän luominen:

![create user sequence](creating_user.png)

HUOMAA LISÄTTY TARKISTUS ETTÄ käyttäjänimeä ei jo ole vaikuttaako?

Sisäänkirjautuminen:

LISÄÄ
 
Uuden matkalaskun luominen:

![create bill sequence](creating_bill.png)

Päivämäärän konvertointi ja tarkistus: //ehkä päivärahan laskeminen mieluummin

LISÄÄ

### Heikkoudet

Sovelluslogiikan eriyttäminen käyttöliittymästä ei täysin toteudu ohjelman nykyisessä versiossa. 
Tämä johtuu tavasta, jolla päiväraha lasketaan. Päivämäärätiedot luetaan käyttäjältä yhdessä kohdassa, mutta niitä tarvitaan
kahdessa kohdassa. Päivämäärät ja niiden sekä matkakohteen sijainnin perusteella laskettu päiväraha
ovat nyt oliomuuttujina käyttöliittymäluokassa, mikä ei ole optimaalista. Päivärahan laskeminen olisi
paras toteuttaa sovelluslogiikan kautta.

Lisäksi käyttöliittymän rakenne on nyt yhdessä pitkässä start-metodissa. Se olisi pitänyt jakaa
useampaan luokkaan, jotka olisivat huolehtineet eri näkymistä.


 

