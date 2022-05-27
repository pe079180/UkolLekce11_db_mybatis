# Domácí úkol (11. lekce)

"Naše firma" se rozhodla vytvořit e-shop a na Vás bude se postarat o část pracující se zbožím.

Budete si tedy muset vytvořit databázi (doporučuji [MySQL](https://www.mysql.com/)) a v ní tabulku odpovídající třídě Item - pak se můžete pustit už do tvorby třídy, která bude implementovat zadané rozhraní GoodsMethods, které předpřipravil jeden z našich seniorů, aby Vám usnadnil práci. Popis toho, co mají dělat jednotlivé metody je v jejich komentářích a je na Vás, jestli zvolíte postup, který jsme si ukazovali na hodině (čistě za pomoci JDBC), nebo jestli použijete jeden z frameworků, o kterých jsme si říkali (MyBatis, či Hibernate).


### realizace - tabulka v mysql
CREATE TABLE `item` (

`id` int NOT NULL AUTO_INCREMENT,

`part_no` varchar(45) NOT NULL,

`serial_no` varchar(45) NOT NULL,

`name` varchar(45) NOT NULL,

`description` varchar(45) DEFAULT NULL,

`number_in_stock` int NOT NULL,

`price` decimal(15,2) NOT NULL,

PRIMARY KEY (`id`)

)

### výstup
viz git soubor output_log.txt