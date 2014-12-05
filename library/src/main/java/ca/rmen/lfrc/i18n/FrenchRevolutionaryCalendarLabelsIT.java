/*
 * French Revolutionary Calendar Library
 * 
 * Copyright (c) 2012-2014 Carmen Alvarez
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
package ca.rmen.lfrc.i18n;

/**
 * 
 * Provides trnaslations of weekday names, month names, and day of year names.
 * 
 * All translations come from Wikipedia. Wikipedia article:
 * http://it.wikipedia.org/wiki/Calendario_rivoluzionario_francese
 * 
 * @author calvarez
 */
class FrenchRevolutionaryCalendarLabelsIT extends FrenchRevolutionaryCalendarLabels {

    public static final String[] WEEKDAYS = new String[] { "Primidì", "Duodì", "Tridì", "Quartidì", "Quintidì", "Sestidì", "Settidì", "Ottidì", "Nonidì",
            "Decadì"

    };
    public static final String[] MONTHS = new String[] { "Vendemmiaio", "Brumaio", "Frimaio", "Nevoso", "Piovoso", "Ventoso", "Germinale", "Fiorile",
            "Pratile", "Messidoro", "Termidoro", "Fruttidoro", "Sanculottidi"

    };

    public static final String[][] DAY_OF_YEAR = new String[][] {
            // Vendemiaire
            new String[] { "Uva", "Zafferano", "Castagna", "Colchico", "Cavallo", "Balsamina", "Carota", "Amaranto", "Pastinaca", "Tino", "Patata",
                    "Perpetuino", "Zucca", "Reseda", "Asino", "Bella di notte", "Zucca", "Grano saraceno", "Girasole", "Torchio", "Canapa", "Pesca", "Rapa",
                    "Amarillide", "Bue", "Melanzana", "Peperoncino", "Pomodoro", "Orzo", "Barile" },
            // Brumaire
            new String[] { "Mela", "Sedano", "Pera", "Barbabietola", "Oca", "Eliotropio", "Fico", "Scorzonera", "Ciavardello", "Aratro", "Barba di becco",
                    "Castagna d'acqua", "Topinambur", "Indivia", "Tacchino", "Sisaro", "Crescione", "Piombaggine", "Melograno", "Erpice", "Baccaro",
                    "Azzeruolo", "Robbia", "Arancia", "Fagiano", "Pistacchio", "Cicerchia", "Cotogno", "Sorbo", "Rullo" },
            // Frimaire
            new String[] { "Raponzolo", "Rapa", "Cicoria", "Nespolo", "Maiale", "Soncino", "Cavolfiore", "Miele", "Ginepro", "Zappa", "Cera", "Rafano",
                    "Cedro", "Abete", "Capriolo", "Ginestrone", "Cipresso", "Edera", "Sabina", "Ascia", "Acero da zucchero", "Erica", "Canna", "Acetosa",
                    "Grillo", "Pino", "Sughero", "Tartufo", "Oliva", "Pala" },
            // Nivôse
            new String[] { "Torba", "Carbone bituminoso", "Bitume", "Zolfo", "Cane", "Lava", "Terra vegetale", "Letame", "Salnitro", "Correggiato", "Granito",
                    "Argilla", "Ardesia", "Arenaria", "Coniglio", "Selce", "Marna", "Calcare", "Marmo", "Setaccio", "Gesso", "Sale", "Ferro", "Rame", "Gatto",
                    "Stagno", "Piombo", "Zinco", "Mercurio", "Colino", },
            // Pluviôse
            new String[] { "Dafne laurella", "Muschio", "Pungitopo", "Bucaneve", "Toro", "Viburno", "Fungo dell'esca", "Camalea", "Pioppo", "Scure",
                    "Elleboro", "Broccolo", "Alloro", "Nocciolo", "Vacca", "Bosso", "Lichene", "Tasso", "Polmonaria", "Coltello da potatura", "Thlaspi",
                    "Dafne odorosa", "Gramigna", "Centinodio", "Lepre", "Guado", "Nocciolo", "Ciclamino", "Celidonia", "Slitta", },
            // Ventôse
            new String[] { "Tossillagine", "Corniolo", "Violacciocca", "Ligustro", "Caprone", "Baccaro comune", "Alaterno", "Violetta", "Salicone", "Vanga",
                    "Narciso", "Olmo", "Fumaria", "Erisimo", "Capra", "Spinacio", "Doronico", "Primula", "Cerfoglio", "Corda", "Mandragola", "Prezzemolo",
                    "Coclearia", "Margherita", "Tonno", "Dente di leone", "Anemone", "Capelvenere", "Frassino", "Piantatoio" },
            // Germinal
            new String[] { "Primula", "Platano", "Asparago", "Tulipano", "Gallina", "Bietola", "Betulla", "Narciso", "Ontano", "Covata", "Pervinca", "Carpino",
                    "Spugnola", "Faggio", "Ape", "Lattuga", "Larice", "Cicuta", "Ravanello", "Arnia", "Albero di Giuda", "Lattuga", "Ippocastano", "Rucola",
                    "Piccione", "Anemone/Lillà", "Lillà/Anemone", "Viola del pensiero", "Mirtillo", "Coltello da innesto" },
            // Floréal
            new String[] { "Rosa", "Quercia", "Felce", "Biancospino", "Usignolo", "Aquilegia", "Mughetto", "Fungo", "Giacinto", "Rastrello", "Rabarbaro",
                    "Lupinella", "Violacciocca gialla", "Lonicera", "Baco da seta", "Consolida maggiore", "Pimpinella", "Cesto di oro", "Atriplice",
                    "Sarchiello", "Statice", "Fritillaria", "Borragine", "Valeriana", "Carpa", "Fusaggine", "Erba cipollina", "Buglossa", "Senape", "Vincastro" },
            // Plairial
            new String[] { "Erba medica", "Emerocallide", "Trifoglio", "Angelica", "Anatra", "Melissa", "Avena altissima", "Giglio martagone", "Timo serpillo",
                    "Falce", "Fragola", "Betonica", "Pisello", "Acacia", "Quaglia", "Garofano", "Sambuco", "Papavero", "Tiglio", "Forcone", "Fiordaliso",
                    "Camomilla", "Caprifoglio", "Caglio", "Tinca", "Gelsomino", "Verbena", "Timo", "Peonia", "Carro" },
            // Messidor
            new String[] { "Segale", "Avena", "Cipolla", "Veronica", "Mulo", "Rosmarino", "Cetriolo", "Scalogno", "Assenzio", "Falcetto", "Coriandolo",
                    "Carciofo", "Violacciocca", "Lavanda", "Camoscio", "Tabacco", "Ribes", "Cicerchia", "Ciliegia", "Ovile", "Menta", "Cumino", "Fagiolo",
                    "Alcanna", "Faraona", "Salvia", "Aglio", "Veccia", "Grano", "Ciaramella" },
            // Thermidor
            new String[] { "Spelto", "Tasso barbasso", "Melone", "Loglio", "Ariete", "Equiseto", "Artemisia", "Cartamo", "Mora", "Annaffiatoio", "Eringio",
                    "Salicornia", "Albicocca", "Basilico", "Pecora", "Altea", "Lino", "Mandorla", "Genziana", "Chiusa", "Carlina bianca", "Cappero",
                    "Lenticchia", "Enula", "Lontra", "Mirto", "Colza", "Lupino", "Cotone", "Mulino" },
            // Fructidor
            new String[] { "Prugna", "Miglio", "Vescia", "Orzo maschio", "Salmone", "Tuberosa", "Orzo comune", "Apocino", "Liquirizia", "Scala", "Anguria",
                    "Finocchio", "Crespino", "Noce", "Trota", "Limone", "Cardo", "Alaterno", "Garofano d'India", "Gerla", "Rosa canina", "Nocciola", "Luppolo",
                    "Sorgo", "Gambero", "Arancio amaro", "Verga d'oro", "Granoturco", "Castagna", "Cesta" },
            // Sanculotides
            new String[] { "Virtù", "Genio", "Lavoro", "Opinione", "Ricompense", "Rivoluzione" }, };

    FrenchRevolutionaryCalendarLabelsIT() {
        super(WEEKDAYS, MONTHS, DAY_OF_YEAR);
    }

}
