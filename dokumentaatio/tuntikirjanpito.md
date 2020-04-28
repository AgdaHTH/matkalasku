## Työaikakirjanpito

| Päivä | Aika   | Mitä tein                                               |
| ------|:------:|:--------------------------------------------------------|
| 24.3. |  2.5   | Suunnittelin aihetta ja kirjoitin alustavaa määrittelyä |
| 29.3  |  1     | Loin projektin ja ensimmäiset luokat, kokeilin ajamista |
|       |	 | Netbeansissa ja komentoriviltä omalla koneella ja Linuxissa
| 30.3  |  2     | Koodasin alustavasti Bill-luokkaa |
| 31.3. |  7     | Koodasin Bill-luokkaa, tekstikäyttöliittymää ja ExpenseRegister-|
|       |        | luokkaa. Jaottelin koodin domain- ja ui-paketteihin. Tein Bill-|
|       |        | luokalle testiluokan ja sinne muutamia testejä. Yritin ajaa niitä|
|       |        | komentoriviltä, ei onnistunut, löysin Telegramista vinkin ja korjasin| 
|       |        | pom.xml:n, sitten onnistui, samoin testikattavuusraportti. Päivitin |
|       |        | vaatimusmäärittelyä ja README:tä. Tarkistin että toimii edelleen|
|       |        | komentoriviltä ajettaessa Linuxissa. |
|  6.4  |   2    | Perehdyin checkstyleen ja suunnittelin tiedontallennusta tietokantaan.  |
|  7.4. |   9    | Lisäsin projektiin chekstylen. Tein Dao-pakkauksen, kirjoitin Dao-rajapinnat ja Dao-luokat |
|       |        | Bill- ja User-luokille. Otin käyttöön sqlite-tietokannan, mihin meni paljon aikaa.| 
|       |        | Lisäsin toiminnallisuuden, jossa lasku (Bill) voidaan lisätä tietokantaan. 
|       |        | Ajoin checkstyle-tarkistuksen ja tein korjaukset, kaikki virheet olivat joko ylimääräisiä tai|
|       |        | puuttuvia välilyöntejä. Lisäsin yhden testin jota en viimeksi saanut toimimaan, sekä kaksi|
|       |        | uutta testiä Bill-luokalle. Pohdin ohjelman rakennetta ja tietokantayhteyden luomista.|
|       |        | Rakenne pitää muuttaa, teen sen varmaan samalla kun otan käyttöön JavaFX:n.|
|       |        | Jatkoin Bill-luokan kehittämistä, mutta en päässyt kovin pitkälle. |
|       |        | Tein alustavan pakkaus- ja luokkakaavion ja arkkitehtuuri-tiedoston. Päivitin |
|       |        | vaatimusmäärittelyn, README:n ja .gitignoren.|
| 20.4. |   8    | Suunnittelin graafista käyttöliittymää ja koodasin siitä ensimmäisen version. Lisäsin| 
|       |        | käyttäjän rekisteröitymis- ja kirjautumistoiminnallisuuden DatabaseUserDao-luokkaan,| 
|       |        | sovelluslogiikkaan ja käyttöliittymään.|
| 21.4. |   8    | Jatkoin käyttöliittymän ja sovelluslogiikan kehittämistä. Muutin tietokannan Bill-taulua|
|       |        | niin että sinne voi jatkossa lisätä tiedon matkakuluista ja viiteavainsarakkeena tiedon|
|       |        | laskun tehneestä käyttäjästä. Tein testejä sovelluslogiikkaluokalle (TravelExpensesApp).|
|       |        | Lisäsin DatabaseUserDao-luokkaan ja sovelluslogiikkaan mahdollisuuden poistaa käyttäjä|
|       |        | tietokannasta. Sain tämän avulla testit toimimaan ja testikattavuudeksi reilut 60%.| 
|       |        | Lopuksi siirsin vielä tietokannan alustamisen järkevämpään paikkaan eli käyttöliittymästä| 
|       |        | sovelluslogiikkaan, jolloin ohjelman suoritus jar-tiedostosta onnistuu.| 
|       |        | Tein sekvenssikaavion käyttäjän luomisesta ja viikon 5 releasen Githubiin. Päivitin |
|       |        | vaatimusmäärittelyn, README:n ja .gitignoren.|
| 27.4. |    1   | Tutustuin JavaDociin ja aloitin sen kirjoittamisen.|
| 28.4. |    9   | Kirjoitin JavaDocin useammalle luokalle. Lisäsin tietokannan Bill-tauluun sarakkeet|
|       |        | kuluja ja päivärahaa varten. Lisäsin nämä myös muuttujiksi Bill-luokkaan. Muutin Dao-|
|       |        | luokan metodit ottamaan huomioon nämä ja Bill-taulun viiteavaimen User-luokkaan. |
|       |        | Lisäsin käyttöliittymään kohdat, joissa valitaan syötetyn kulun tyyppi ja lasketaan|
|       |        | päiväraha. Korjasin bugin matkan loppupäivämäärän syötössä. Siirsin päivärahaan liittyvän|
|       |        | toiminnallisuuden omaan luokkaansa, mutta tämän toteuttaminen jäi kesken. Laadin alustavan|
|       |        | arkkitehtuurikuvauksen ja käyttöohjeen sekä päivitin vaatimusmäärittelyn ja readmen.|
|       |        | Testikattavuusraportti antoi aiemmin yli 60% mutta nyt lopuksi ajaessani sen tuli ihan muita|
|       |        | tuloksia, ja varoituksia että jokin ei täsmää. En ehtinyt selvittää mistä tämä johtuu.|
| Yht.  | 49.5   |

