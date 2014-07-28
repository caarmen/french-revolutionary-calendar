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
 * Provides translations of weekday names, month names, and day of year names, in Catalan.
 * 
 * All translations come from Wikipedia.
 * Wikipedia article: http://ca.wikipedia.org/wiki/Calendari_republic%C3%A0_franc%C3%A8s
 * 
 * @author calvarez
 */
class FrenchRevolutionaryCalendarLabelsCA extends FrenchRevolutionaryCalendarLabels {

    public static final String[] MONTHS = new String[] { "Veremari", "Brumari", "Frimari", "Nivós", "Pluviós", "Ventós", "Germinal", "Floreal", "Pradal",
            "Messidor", "Termidor", "Fructidor", "Sanculotides" };

    public static final String[][] DAY_OF_YEAR = new String[][] {
            // Vendemiaire
            new String[] { "Raïm", "Safrà", "Castanya", "Còlquic", "Cavall", "Balsamina", "Pastanaga", "Amarant", "Xirivia", "Tina", "Patata", "Sempreviva",
                    "Carbassera de rabequet", "Reseda", "Ase", "Flor de nit", "Carbassa", "Blat negre", "Girasol", "Trull", "Cànnabis", "Préssec", "Nap",
                    "Amaril·lis", "Bou", "Albergínia", "Pebrot", "Tomàquet", "Ordi", "Bóta", },
            // Brumaire
            new String[] { "Poma", "Api", "Pera", "Remolatxa", "Oca", "Heliotrop", "Figa", "Escurçonera", "Moixera de pastor", "Arada", "Barbeta",
                    "Castanya d'aigua", "Nyàmera", "Endívia", "Gall dindi", "Escaravia", "Creixens", "Malvesc", "Magrana", "Rascle", "Baccharis", "Atzerola",
                    "Rogeta", "Taronja", "Faisà", "Festuc", "Guixó tuberós", "Codony", "Servera", "Corró", },
            // Frimaire
            new String[] { "Fiteuma", "Nap", "Xicoira", "Nespra", "Porc", "Canonge", "Coliflor", "Mel", "Ginebre", "Pic", "Cera", "Rave rusticà", "Cedre",
                    "Avet", "Cabirol", "Gatosa", "Xiprer", "Heura", "Savina", "Aixadell", "Auró del sucre", "Bruc", "Canya", "Agrella", "Grill", "Pinyó",
                    "Suro", "Tòfona", "Olivera", "Pala", },
            // Nivôse
            new String[] { "Torba", "Carbó", "Betum", "Sofre", "Gos", "Lava", "Terra vegetal", "Fem", "Salpetre", "Plaga", "Granit", "Argila", "Pissarra",
                    "Gres", "Conill", "Sílex", "Marga", "Pedra calcària", "Marbre", "Ventadora", "Pedra de guix", "Sal", "Ferro", "Coure", "Gat", "Estany",
                    "Plom", "Zenc", "Mercuri", "Garbell", },
            //Pluviôse
            new String[] { "Lloreret", "Molsa", "Galzeran", "Lliri de neu", "Brau", "Marfull", "Bolet de soca", "Tintorell", "Pollancre", "Destral gran",
                    "El·lèbor", "Bròquil", "Llorer", "Avellaner", "Vaca", "Boix", "Liquen", "Teix", "Pulmonària", "Podall", "Traspic", "Dafne pirinenc",
                    "Agram", "Passacamins", "Llebre", "Herba del pastell", "Avellaner", "Ciclamen", "Celidònia", "Trineu", },
            // Ventôse
            new String[] { "Pota de cavall", "Sanguinyol", "Violer groc", "Troana", "Boc", "Atzarí", "Aladern", "Violeta", "Gatsaule", "Fanga", "Narcís", "Om",
                    "Fumària", "Sisimbri oficinal", "Cabra", "Espinac", "Dorònic", "Borrissol", "Cerfull", "Cordill", "Mandràgora", "Julivert", "Cocleària",
                    "Margarida", "Tonyina", "Pixallits", "Herba fetgera", "Capil·lera", "Freixe", "Plantador", },
            // Germinal
            new String[] { "Primula", "Plàtan", "Espàrrec", "Tulipa", "Gallina", "Bleda", "Bedoll", "Almesquí", "Vern", "Eclosionadora", "Vinca", "Carpí",
                    "Múrgola", "Faig", "Abella", "Enciam", "Làrix", "Cicuta", "Rave", "Rusc", "Arbre de l'amor", "Enciam romà", "Castanyer", "Ruca", "Colom",
                    "Lilà", "Anèmona", "Pensament", "Nabiu", "Ganivet d'empeltar", },
            // Floréal
            new String[] { "Rosa", "Roure", "Falguera", "Arç blanc", "Rossinyol", "Aguilera", "Muguet", "Xampinyó", "Jacint", "Rascle", "Ruibarbre",
                    "Trepadella", "Rosella", "Margalló", "Cuc de seda", "Consolda", "Pimpinella", "Paneret", "Salat blanc", "Aixadell", "Gasó alpí",
                    "Fritil·lària", "Borratja", "Valeriana", "Carpa", "Evònim", "Cibulet", "Llengua de bou", "Mostassa negra", "Gaiata", },
            // Plairial
            new String[] { "Alfals", "Hemerocal·lis", "Trèvol", "Angèlica", "ànec", "Melissa", "Fromental", "Marcòlic", "Serpoll", "Dalla", "Maduixa",
                    "Betònica", "Pèsol", "Acàcia", "Guatlla", "Clavell", "Saüc", "Cascall", "Til·ler", "Forca", "Barb", "Camamilla", "Xuclamel", "Quallallet",
                    "Tenca", "Gessamí", "Berbena", "Farigola", "Peònia", "Carretó", },
            // Messidor
            new String[] { "Sègol", "Civada", "Ceba", "Verònica", "Mula", "Romaní", "Cogombre", "Escalunya", "Absenta", "Falç", "Coriandre", "Carxofa",
                    "Girofle", "Lavanda", "Isard", "Tabac", "Grosella", "Guixa", "Cirera", "Cleda", "Menta", "Comí", "Mongeta", "Alcanna", "Pintada", "Sàlvia",
                    "All", "Veça", "Blat", "Dolçaina", },
            // Thermidor
            new String[] { "Espelta", "Herba blenera", "Meló", "Zitzània", "Marrà", "Cua de cavall", "Artemísia", "Càrtam", "Móra", "Regadora", "Panical",
                    "Salicorn", "Albercoc", "Alfàbrega", "Ovella", "Malví", "Lli", "Ametlla", "Genciana", "Resclosa", "Carlina", "Tàpera", "Llentia", "ínula",
                    "Llúdria", "Murta", "Colza", "Tramús", "Cotó", "Molí", },
            // Fructidor
            new String[] { "Pruna", "Mill", "Pet de llop", "Ordi de dues carreres", "Salmó", "Nard", "Ordi de sis carreres", "Baladre", "Regalèssia", "Escala",
                    "Síndria", "Fonoll", "Coralet", "Nou", "Truita de riu", "Llimona", "Cardó", "Rhamnus sp.", "Tagetes sp.", "Cove", "Englantina", "Avellana",
                    "Llúpol", "Sorgo", "Cranc de riu", "Taronja agre", "Vara d'or", "Blat de moro", "Castanya", "Cistell", },
            // Sanculotides
            new String[] { "Virtut", "Geni", "Treball", "Opinió", "Recompenses", "Revolució" }, };

    FrenchRevolutionaryCalendarLabelsCA() {
        super(FrenchRevolutionaryCalendarLabelsFR.WEEKDAYS, MONTHS, DAY_OF_YEAR);
    }

}
