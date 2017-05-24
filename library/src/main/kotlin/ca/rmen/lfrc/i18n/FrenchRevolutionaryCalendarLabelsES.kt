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

 * All translations come from Wikipedia.
 * Wikipedia article: http://es.wikipedia.org/wiki/Calendario_republicano_franc%C3%A9s

 * @author calvarez
 */
internal class FrenchRevolutionaryCalendarLabelsES : FrenchRevolutionaryCalendarLabels(FrenchRevolutionaryCalendarLabelsFR.WEEKDAYS, FrenchRevolutionaryCalendarLabelsES.MONTHS, FrenchRevolutionaryCalendarLabelsES.DAY_OF_YEAR, FrenchRevolutionaryCalendarLabelsES.DAILY_OBJECT_TYPES) {
    companion object {

        @JvmField
        val MONTHS = arrayOf("Vendimiario", "Brumario", "Frimario", "Nivoso", "Pluvioso", "Ventoso", "Germinal", "Floreal", "Pradial", "Mesidor", "Termidor", "Fructidor", "Sanculotides")

        @JvmField
        val DAY_OF_YEAR = arrayOf(

                // Vendemiaire
                arrayOf("Uva", "Azafrán", "Castaña", "Cólquida", "Caballo", "Balsamina", "Zanahoria", "Amaranto", "Chirivía", "Tinaja", "Patata", "Flor de papel", "Calabaza", "Reseda", "Asno", "Dondiego de día", "Calabaza otoñal", "Alforfón", "Girasol", "Lagar", "Cáñamo", "Melocotón", "Nabo", "Amarilis", "Buey", "Berenjena", "Pimiento", "Tomate", "Cebada", "Barril"),
                // Brumaire
                arrayOf("Manzana", "Apio", "Pera", "Remolacha", "Oca", "Heliótropo", "Higo", "Escorzonera", "Mostajo", "Arado", "Salsifí", "Castaña de agua", "Tupinambo", "Endibia", "Guajolote", "Escaravía", "Berro", "Dentelaria", "Granada", "Grada", "Bacante", "Acerolo", "Rubia roja", "Naranja", "Faisán", "Pistacho", "Guija tuberosa", "Membrillo", "Serbal", "Rodillo"),
                // Frimaire
                arrayOf("Rapónchigo", "Nabo forrajero", "Achicoria", "Níspero", "Cerdo", "Canónigo", "Coliflor", "Miel", "Enebro", "Pico", "Cera", "Rábano picante", "Cedro", "Abeto", "Corzo", "Tojo", "Ciprés", "Hiedra", "Sabina", "Azadón", "Arce", "Brezo", "Caña", "Acedera", "Grillo", "Piñón", "Corcho", "Trufa", "Aceituna", "Pala"),
                // Nivôse
                arrayOf("Turba", "Carbón", "Betún", "Azufre", "Perro", "Lava", "Tierra vegetal", "Estiércol", "Salitre", "Mayal", "Granito", "Arcilla", "Pizarra", "Arenisca", "Conejo", "Sílex", "Marga", "Piedra de cal", "Mármol", "Aventadora de cereal", "Piedra de yeso", "Sal", "Hierro", "Cobre", "Gato", "Estaño", "Plomo", "Cinc", "Mercurio", "Tamiz"),
                //Pluviôse
                arrayOf("Laureola", "Musgo", "Rusco", "Galanto", "Toro", "Laurentino", "Hongo yesquero", "Mezereón", "álamo", "Hacha", "Eléboro", "Brécol", "Laurel", "Avellano", "Vaca", "Boj", "Liquen", "Tejo", "Pulmonaria", "Navaja podadora", "Carraspique", "Torvisco", "Gramilla", "Centinodia", "Liebre", "Isatide", "Avellano", "Ciclamen", "Celidonia mayor", "Trineo"),
                // Ventôse
                arrayOf("Tusilago", "Corno", "Alhelí", "Aligustre", "Macho cabrío", "Jengibre silvestre", "Aladierno", "Violeta", "Sauce cabruno", "Laya", "Narciso", "Olmo", "Fumaria", "Erísimo", "Cabra", "Espinaca", "Doronicum", "Anagallis", "Perifollo", "Hilo", "Mandrágora", "Perejil", "Coclearia", "Margarita", "Atún", "Diente de león", "Anémona de bosque", "Culantrillo", "Fresno", "Plantador"),
                // Germinal
                arrayOf("Primavera", "Sicomoro", "Espárrago", "Tulipán", "Gallina", "Acelga", "Abedul", "Junquillo", "Alnus", "Nidal", "Vincapervinca", "Carpe", "Morilla", "Haya", "Abeja", "Lechuga", "Alerce", "Cicuta", "Rábano", "Colmena", "árbol de Judea", "Lechuga romana", "Castaño de Indias", "Roqueta", "Paloma", "Lila", "Anémona", "Pensamiento", "Arándano", "Cuchillo"),
                // Floréal
                arrayOf("Rosa", "Roble", "Helecho", "Espino albar", "Ruiseñor", "Aguileña", "Convalaria", "Seta", "Jacinto", "Rastrillo", "Ruibarbo", "Esparceta", "Erysimun", "Palmito", "Gusano de seda", "Consuelda", "Algáfita", "Alyssum", "Atriplex", "Escardillo", "Clavelina de mar", "Fritillaria", "Borraja", "Valeriana", "Carpa", "Bonetero", "Cebolleta", "Anchusa", "Mostaza negra", "Armuelle"),
                // Plairial
                arrayOf("Alfalfa", "Lirio de día", "Trébol", "Angélica", "Pato", "Melisa", "Frumentario", "Martagón", "Serpol", "Guadaña", "Fresa", "Salvia", "Guisante", "Acacia", "Codorniz", "Clavel", "Saúco", "Adormidera", "Tilo", "Horca", "Barbo", "Camomila", "Madreselva", "Galium", "Tenca", "Jazmín", "Verbena", "Tomillo", "Peonía", "Carro"),
                // Messidor
                arrayOf("Centeno", "Avena", "Cebolla", "Verónica", "Mula", "Romero", "Pepino", "Chalote", "Absenta", "Hoz", "Cilantro", "Alcachofa", "Clavo", "Lavanda", "Gamuza", "Tabaco", "Grosella", "Lathyrus", "Cereza", "Corral", "Menta", "Comino", "Judía", "Palomilla de tintes", "Caparrón", "Salvia", "Ajo", "Algarroba", "Trigo", "Shawn"),
                // Thermidor
                arrayOf("Espelta", "Verbasco", "Melón", "Cizaña", "Carnero", "Cola de caballo", "Artemisa", "Cártamo", "Mora", "Regadera", "Pan", "Salicor", "Albaricoque", "Albahaca", "Oveja", "Malvavisco", "Lino", "Almendra", "Genciana", "Esclusa", "Carlina", "Alcaparra", "Lenteja", "Aliso", "Nutria", "Mirto", "Colza", "Altramuz", "Algodón", "Molino"),
                // Fructidor
                arrayOf("Ciruela", "Mijo", "Soplo de lobo", "Cebada", "Salmón", "Nardo", "Cebada", "Apocynaceae", "Regaliz", "Escala", "Sandía", "Hinojo", "Berberis", "Nuez", "Trucha", "Limón", "Cardencha", "Espino cerval", "Clavelón", "Cesto", "Escaramujo", "Avellana", "Lúpulo", "Sorgo", "Cangrejo de río", "Naranja amarga", "Vara de oro", "Maíz", "Castaña", "Cesta"),

                // Sanculotides
                arrayOf("Virtud", "Talento", "Trabajo", "Opinión", "Recompensas", "Revolución"))

        @JvmField
        val DAILY_OBJECT_TYPES = arrayOf("La planta", "El animal", "La herramienta", "El mineral", "El concepto")
    }

}
