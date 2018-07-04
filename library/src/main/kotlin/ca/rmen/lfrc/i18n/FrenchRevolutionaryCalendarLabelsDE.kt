/*
 * French Revolutionary Calendar Library
 * 
 * Copyright (c) 2012-2017 Carmen Alvarez
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301
 */
package ca.rmen.lfrc.i18n

/**
 * Provides translations of weekday names, month names, and day of year names, in Spanish.
 *
 *
 * All translations come from Wikipedia.
 * Wikipedia article: http://es.wikipedia.org/wiki/Calendario_republicano_franc%C3%A9s

 * @author calvarez
 */
internal class FrenchRevolutionaryCalendarLabelsDE : FrenchRevolutionaryCalendarLabels(FrenchRevolutionaryCalendarLabelsFR.WEEKDAYS, FrenchRevolutionaryCalendarLabelsDE.MONTHS, FrenchRevolutionaryCalendarLabelsDE.DAY_OF_YEAR, FrenchRevolutionaryCalendarLabelsDE.DAILY_OBJECT_TYPES) {
    companion object {

        @JvmField
        val MONTHS = arrayOf("Weinmonat", "Nebelmonat", "Reifmonat", "Schneemonat", "Regenmonat", "Windmonat", "Keimmonat", "Blütenmonat", "Wiesenmonat", "Erntemonat", "Hitzemonat", "Fruchtmonat", "Sansculottiden")

        @JvmField
        val DAY_OF_YEAR = arrayOf(


                // Vendemiaire
                arrayOf("Traube", "Safran", "Kastanien", "Wiesensafran", "Pferd", "Springkraut", "Mohrrüben", "Amarant", "Pastinake", "Bottich", "Kartoffeln", "Strohblume", "Melonenkürbis", "Reseda", "Esel", "Wunderblume", "Kürbis", "Buchweizen", "Sonnenblume", "Kelter", "Hanf", "Pfirsiche", "Wasserrüben", "Amaryllis", "Rind", "Aubergine", "Nelkenpfeffer", "Tomate", "Gerste", "Fass"),
                // Brumaire
                arrayOf("Apfel", "Sellerie", "Birne", "Runkelrübe", "Gans", "Sonnenblume", "Feige", "Schwarzwurzel", "Elsbeere", "Pflug", "Wiesen-Bocksbart", "Wassernuss", "Erdartischocke", "Endivie", "Truthahn", "Zuckerwurz", "Kresse", "Bleiwurz", "Granatapfel", "Egge", "asarum baccharis", "Welsche Mispel", "Krapp", "Orange", "Fasan", "Pistazie", "Platterbse", "Quitte", "Vogelbeere", "Walze"),
                // Frimaire
                arrayOf("Rapunzel", "Steckrübe", "Zichorie", "Mispel", "Schwein", "Feldsalat", "Blumenkohl", "Honig", "Wacholder", "Hacke", "Wachs", "Meerrettich", "Zeder", "Tanne", "Reh", "Stechginster", "Zypresse", "Efeu", "Sadebaum", "Axt", "Zuckerahorn", "Heidekraut", "Schilfrohr", "Sauerampfer", "Grille", "Pinienzapfen", "Korkeiche", "Trüffel", "Olive", "Schaufel"),
                // Nivôse
                arrayOf("Torf", "Kohle", "Asphalt", "Schwefel", "Hund", "Lava", "Humus", "Dung", "Salpeter", "Dreschflegel", "Granit", "Tonerde", "Schiefer", "Sandstein", "Kaninchen", "Feuerstein", "Mergel", "Kalkstein", "Marmor", "Pferdewagen", "Gips", "Salz", "Eisen", "Kupfer", "Katze", "Zinn", "Blei", "Zink", "Quecksilber", "Kornsieb"),
                //Pluviôse
                arrayOf("Lorbeer-Seidelbast", "Moos", "Mäusedorn", "Schneeglöckchen", "Stier", "Lorbeerbaum", "Zunderschwamm", "Seidelbast", "Pappel", "Beil", "Nieswurz", "Brokkoli", "Lorbeer", "Lambertshasel", "Kuh", "Buchsbaum", "Flechte", "Eibe", "Lungenkraut", "Gartenmesser", "Hellerkraut", "Daphne", "Quecke", "Wegtritt", "Hase", "Färberwaid", "Haselstaude", "Alpenveilchen", "Schöllkraut", "Schlitten"),
                // Ventôse
                arrayOf("Huflattich", "Kornelkirsche", "Levkoje", "Liguster", "Ziegenbock", "Haselwurz", "Kreuzdorn", "Veilchen", "Weide", "Spaten", "Narzisse", "Ulme", "Erdrauch", "Wegrauke", "Ziege", "Spinat", "Gemswurz", "Vogelkraut", "Kerbel", "Messschnur", "Alraunen", "Petersilie", "Löffelkraut", "Gänseblümchen", "Thunfisch", "Löwenzahn", "Wald-Anemone", "Frauenhaar", "Esche", "Grabstock"),
                // Germinal
                arrayOf("Schlüsselblume", "Platane", "Spargel", "Tulpe", "Huhn", "Mangold", "Birke", "Osterglocke", "Erle", "Brutstätte", "Immergrün", "Hainbuche", "Morchel", "Buche", "Biene", "Kopfsalat", "Lärche", "Hundspetersilie", "Radieschen", "Bienenstock", "Judasbaum", "Römersalat", "Rosskastanie", "Senfrauke", "Taube", "Anemone/Flieder", "Flieder/Anemone", "Stiefmütterchen", "Heidelbeere", "Pfropfmesser"),
                // Floréal
                arrayOf("Rose", "Eiche", "Farn", "Hagedorn", "Nachtigall", "Akelei", "Maiglöckchen", "Champignon", "Hyazinthe", "Harke", "Rhabarber", "Süßklee", "Goldlack", "Heckenkirsche", "Seidenraupe", "Schwarzwurz", "Bibernelle", "Felsensteinkraut", "Gartenmelde", "Jäthacke", "Meerlavendel", "Schachblume", "Borretsch", "Baldrian", "Karpfen", "Pfaffenhütchen", "Schnittlauch", "Ochsenzunge", "Ackersenf", "Hirtenstab"),
                // Plairial
                arrayOf("Luzerne", "Taglilie", "Klee", "Engelwurz", "Ente", "Melisse", "Glatthafer", "Türkenbund", "Quendel", "Sense", "Erdbeere", "Betonie", "Erbse", "Akazie", "Wachtel", "Nelke", "Holunder", "Mohn", "Linde", "Heugabel", "Kornblume", "Kamille", "Geißblatt", "Labkraut", "Schleie", "Jasmin", "Eisenkraut", "Thymian", "Pfingstrose", "Wagen"),
                // Messidor
                arrayOf("Roggen", "Hafer", "Zwiebel", "Ehrenpreis", "Maultier", "Rosmarin", "Gurke", "Schalotten", "Wermut", "Sichel", "Koriander", "Artischocke", "Gewürznelke", "Lavendel", "Gemse", "Tabak", "Johannisbeere", "Platterbse", "Kirsche", "Pferch", "Minze", "Kümmel", "Bohnen", "Schminkwurz", "Perlhuhn", "Salbei", "Knoblauch", "Wicke", "Weizen", "Schalmei"),
                // Thermidor
                arrayOf("Dinkel", "Königskerze", "Melone", "Weidelgras", "Widder", "Schachtelhalm", "Beifuß", "Färberdistel", "Brombeere", "Gießkanne", "Mannstreu", "Queller", "Aprikose", "Basilikum", "Schaf", "Eibisch", "Flachs", "Mandel", "Enzian", "Schleuse", "Silberdistel", "Kapernstrauch", "Linse", "Alant", "Otter", "Myrte", "Raps", "Lupine", "Baumwolle", "Mühle"),
                // Fructidor
                arrayOf("Pflaume", "Hirse", "Stäubling", "Futtergerste", "Lachs", "Tuberose", "Wintergerste", "Hundswürger", "Lakritze", "Leiter", "Wassermelone", "Fenchel", "Berberitze", "Walnuss", "Forelle", "Zitrone", "Karde", "Kreuzdorn", "Studentenblume", "Tragkorb", "Heckenrose", "Haselnuss", "Hopfen", "Sorghum", "Krebs", "Pomeranze", "Goldraute", "Mais", "Eßkastanie", "Korb"),

                // Sansculotides
                arrayOf("Tugenden", "Geistes", "Arbeit", "Meinung", "Belohnungen", "Revolution"))

        @JvmField
        val DAILY_OBJECT_TYPES = arrayOf("Die Pflanze", "Das Tier", "Das Werkzeug", "Das Mineral", "Das Konzept")
    }

}
