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

 * Provides trnaslations of weekday names, month names, and day of year names.

 * WEEKDAYS and MONTHS come from Wikipedia.
 * Wikipedia article: http://eu.wikipedia.org/wiki/Egutegi_errepublikanoa

 * @author beriain
 */
internal class FrenchRevolutionaryCalendarLabelsEU : FrenchRevolutionaryCalendarLabels(FrenchRevolutionaryCalendarLabelsEU.WEEKDAYS, FrenchRevolutionaryCalendarLabelsEU.MONTHS, FrenchRevolutionaryCalendarLabelsEU.DAY_OF_YEAR, FrenchRevolutionaryCalendarLabelsEU.DAILY_OBJECT_TYPES) {
    companion object {

        @JvmField
        val WEEKDAYS = arrayOf("Legun", "Bigun", "Higun", "Logun", "Bogun", "Segun", "Pegun", "Zogun", "Bedegun", "Margun")
        @JvmField
        val MONTHS = arrayOf("Mahaxte", "Lainote", "Izozte", "Elurkor", "Eurikor", "Haizekor", "Sapadun", "Lilidun", "Belardun", "Bihilis", "Berolis", "Frutilis", "Betagailak")

        @JvmField
        val DAY_OF_YEAR = arrayOf(
                // Vendemiaire - Mahaxte
                arrayOf("Mahatsa", "Azafraia", "Gaztaina", "Kolkida", "Zaldia", "Baltsamina", "Azenarioa", "Amarantoa", "Txiribia", "Treska", "Patata", "Betibizi horia", "Cucurbita maxima", "Herezea", "Astoa", "Gau-lorea", "Kuia", "Artobeltza", "Ekilorea", "Dolarea", "Kalamua", "Mertxika", "Arbia", "Amaryllis", "Idia", "Alberjinia", "Piperra", "Tomatea", "Garagarra", "Upela"),
                // Brumaire - Lainote
                arrayOf("Sagarra", "Apioa", "Madaria", "Beterraba", "Antzara", "Heliotropoa", "Pikua", "Sendapokia", "Hostazuria", "Goldea", "Terebuza", "Ur gaztaina", "Topinanbua", "Endibia", "Indioiloa", "Sium sisarum", "Berroa", "Plumbago", "Alesagarra", "Area", "Baccharis halimifolia", "Mazpil-elorria", "Tindu-otxarra", "Laranja", "Faisaia", "Pistatxoa", "Lathyrus tuberosus", "Irasagarra", "Gurbeondoa", "Arrabola"),
                // Frimaire - Izozte
                arrayOf("Ezkila-lorea", "Arbia", "Txikoria", "Mizpira", "Txerria", "Ardi-mihia", "Azalorea", "Eztia", "Orrea", "Pikotxa", "Argizaria", "Errefau mina", "Zedroa", "Izeia", "Orkatza", "Otea", "Nekosta", "Huntza", "Miterra", "Aitzur luzea", "Iharra", "Txilarra", "Kanabera", "Lapaitza", "Kilkerra", "Pinazia", "Tortotxa", "Boilurra", "Oliba", "Pala"),
                // Nivôse - Elurkor
                arrayOf("Zohikatza", "Ikatza", "Ziraia", "Sufrea", "Txakurra", "Laba", "Humusa", "Simaurra", "Kresala", "Idaurra", "Granitoa", "Buztina", "Arbela", "Hareharria", "Untxia", "Silexa", "Marga", "Kareharria", "Marmola", "Bahea", "Igeltsua", "Gatza", "Burdina", "Kobrea", "Katua", "Eztainua", "Beruna", "Zinka", "Merkurioa", "Bahea"),
                //Pluviôse - Eurikor
                arrayOf("Garatxo-belarra", "Goroldioa", "Erratza", "Galanthus", "Zezena", "Gogortxua", "Ardagaia", "Ereinoztxoa", "Makala", "Aizkora", "Otsababa", "Brokolia", "Ereinotza", "Hurritza", "Behia", "Ezpela", "Likena", "Hagina", "Biri-belarra", "Inauskaia", "Thlaspli", "Torbiskoa", "Askiluzea", "Odolurra", "Erbia", "Urdinbelarra", "Corylusa", "Ziklamena", "Zaran-belarra", "Lera"),
                // Ventôse - Haizekor
                arrayOf("Eztul-belarra", "Zuhandorra", "Ahuntz-praka", "Binorria", "Akerra", "Jengibrea", "Karraskila", "Bioleta", "Ahuntz-sahatsa", "Laia", "Lilipa", "Zumarra", "Negakina", "Erysimum", "Ahuntza", "Ziazerba", "Doronicum", "Anagallis", "Perraitza", "Haria", "Urriloa", "Perrexila", "Cochlearia", "Bitxilorea", "Atuna", "Txikoria-belarra", "Baso-eguerdililia", "Garaizka", "Lizarra", "Landatzailea"),
                // Germinal - Sapadun
                arrayOf("Udaberri-lore goiztiarra", "Sikomoroa", "Zainzuria", "Idi-bihotza", "Oiloa", "Zerba", "Urkia", "Lilipa", "Haltza", "Inkubagailua", "Inkonte-belar txikia", "Xarma", "Karraspina", "Pagoa", "Erlea", "Uraza", "Alertzea", "Astaperrexila", "Errefaua", "Erlauntza", "Judasen arbola", "Uraza erromatarra", "Indigaztainondoa", "Eruca sativa", "Usoa", "Lila", "Eguerdililia", "Pentsamendua", "Ahabia", "Aiztoa"),
                // Floréal - Lilidun
                arrayOf("Arrosa", "Haritza", "Iratzea", "Crataegus", "Urretxindorra", "Kuku-belarra", "Convallaria majalis", "Perretxikoa", "Hiazintoa", "Arrastelua", "Rheum", "Astorkia", "Urrezko makila", "Palmondo nanoa", "Zeta-harra", "Zolda-belarra", "Gaitun txikia", "Urrezko saskia", "Garadaizka", "Galjorraia", "Itsas krabelina", "Fritillaria", "Borraja", "Belar bedeinkatua", "Karpa", "Basaerramua", "Tipulina", "Anchusa", "Ziape zuria", "Garadaizka"),
                // Plairial - Belardun
                arrayOf("Luzerna", "Hemerocallis", "Hirusta", "Aingeru-belarra", "Ahatea", "Garraiska", "Arrhenatherum elatius", "Zitori gorria", "Sarpoila", "Sega", "Marrubia", "Salbia", "Ilarra", "Akazia", "Galeperra", "Krabelina", "Intsusa", "Lo-belarra", "Ezkia", "Sardea", "Barboa", "Kamamila", "Sasiama", "Ziabelarra", "Tenka", "Jasmina", "Berbena", "Ezkaia", "Oinlodia", "Gurdia"),
                // Messidor - Bihilis
                arrayOf("Zekalea", "Oloa", "Tipula", "Veronica", "Mandoa", "Erromeroa", "Luzokerra", "Tipulatxa", "Absenta", "Igitaia", "Martorria", "Orburua", "Iltzea", "Izpilikua", "Sarrioa", "Tabakoa", "Andere-mahatsa", "Lathyrus", "Gerezia", "Eskorta", "Menda", "Kuminoa", "Indaba", "Alkanna tinctoria", "Numididae", "Salbia", "Baratxuria", "Zalkea", "Garia", "Chalémie"),
                // Thermidor - Berolis
                arrayOf("Espelta", "Apo-belarra", "Meloia", "Iraka", "Aharia", "Equisetopsida", "Zizare-belarra", "Kartamoa", "Masusta", "Ureztagailua", "Panicum", "Salicornia", "Abrikotondoa", "Albaka", "Ardia", "Malba zuria", "Lihoa", "Arbendola", "Errosta", "Esklusa", "Eguzki-lorea", "Kaparrondoa", "Dilista", "Haltza", "Igaraba", "Mirtoa", "Koltza", "Eskuhoria", "Kotoia", "Errota"),
                // Fructidor - Frutilis
                arrayOf("Arana", "Artatxikia", "Otsoaren putza", "Garagarra", "Izokina", "Akara", "Garagarra", "Asclepias syriaca", "Erregaliza", "Eskala", "Angurria", "Mihilua", "Isuski-garratza", "Intxaurra", "Amuarraina", "Limoia", "Astalarra", "Rhamnus", "Tagetes", "-Saskia", "Arkakaratsa", "Hurra", "Lupulua", "Basartoa", "Ibai-karramarroa", "Laranja mingotsa", "Solidagoa", "Artoa", "Gaztaina", "Otarrea"),
                // Sanculotides - Betagailak
                arrayOf("Bertutea", "Aztitasuna", "Lana", "Iritzia", "Ordain-sari", "Iraultza"))

        @JvmField
        val DAILY_OBJECT_TYPES = arrayOf("Landarea", "Animalia", "Tresna", "Minerala", "Kontzeptua")
    }

}
