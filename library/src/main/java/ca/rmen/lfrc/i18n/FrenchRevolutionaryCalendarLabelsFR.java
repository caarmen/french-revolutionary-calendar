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
 * All translations come from Wikipedia.
 * Wikipedia article: http://fr.wikipedia.org/wiki/Calendrier_r%C3%A9publicain
 * 
 * @author calvarez
 */
class FrenchRevolutionaryCalendarLabelsFR extends FrenchRevolutionaryCalendarLabels {

    public static final String[] WEEKDAYS = new String[] { "Primidi", "Duodi", "Tridi", "Quartidi", "Quintidi", "Sextidi", "Septidi", "Octidi", "Nonidi",
            "Décadi"

    };
    public static final String[] MONTHS = new String[] { "Vendémiaire", "Brumaire", "Frimaire", "Nivôse", "Pluviôse", "Ventôse", "Germinal", "Floréal",
            "Prairial", "Messidor", "Thermidor", "Fructidor", "Sanculotides"

    };

    public static final String[][] DAY_OF_YEAR = new String[][] {
            // Vendemiaire 
            new String[] { "Raisin", "Safran", "Châtaigne", "Colchique", "Cheval", "Balsamine", "Carotte", "Amaranthe", "Panais", "Cuve", "Pomme de terre",
                    "Immortelle", "Potiron", "Réséda", "Âne", "Belle de nuit", "Citrouille", "Sarrasin", "Tournesol", "Pressoir", "Chanvre", "Pêche", "Navet",
                    "Amaryllis", "Bœuf", "Aubergine", "Piment", "Tomate", "Orge", "Tonneau" },
            // Brumaire
            new String[] { "Pomme", "Céleri", "Poire", "Betterave", "Oie", "Héliotrope", "Figue", "Scorsonère", "Alisier", "Charrue", "Salsifis", "Mâcre",
                    "Topinambour", "Endive", "Dindon", "Chervis", "Cresson", "Dentelaire", "Grenade", "Herse", "Bacchante", "Azerole", "Garance", "Orange",
                    "Faisan", "Pistache", "Macjonc", "Coing", "Cormier", "Rouleau" },
            // Frimaire
            new String[] { "Raiponce", "Turneps", "Chicorée", "Nèfle", "Cochon", "Mâche", "Chou-fleur", "Miel", "Genièvre", "Pioche", "Cire", "Raifort",
                    "Cèdre", "Sapin", "Chevreuil", "Ajonc", "Cyprès", "Lierre", "Sabine", "Hoyau", "Érable à sucre", "Bruyère", "Roseau", "Oseille", "Grillon",
                    "Pignon", "Liège", "Truffe", "Olive", "Pelle" },
            // Nivôse
            new String[] { "Tourbe", "Houille", "Bitume", "Soufre", "Chien", "Lave", "Terre végétale", "Fumier", "Salpêtre", "Fléau", "Granit", "Argile",
                    "Ardoise", "Grès", "Lapin", "Silex", "Marne", "Pierre à chaux", "Marbre", "Van", "Pierre à plâtre", "Sel", "Fer", "Cuivre", "Chat",
                    "Étain", "Plomb", "Zinc", "Mercure", "Crible" },
            //Pluviôse
            new String[] { "Lauréole", "Mousse", "Fragon", "Perce-neige", "Taureau", "Laurier-thym", "Amadouvier", "Mézéréon", "Peuplier", "Coignée",
                    "Ellébore", "Brocoli", "Laurier", "Avelinier", "Vache", "Buis", "Lichen", "If", "Pulmonaire", "Serpette", "Thlaspi", "Thimelé",
                    "Chiendent", "Trainasse", "Lièvre", "Guède", "Noisetier", "Cyclamen", "Chélidoine", "Traîneau" },
            // Ventôse
            new String[] { "Tussilage", "Cornouiller", "Violier", "Troène", "Bouc", "Asaret", "Alaterne", "Violette", "Marceau", "Bêche", "Narcisse", "Orme",
                    "Fumeterre", "Vélar", "Chèvre", "Épinard", "Doronic", "Mouron", "Cerfeuil", "Cordeau", "Mandragore", "Persil", "Cochléaria", "Pâquerette",
                    "Thon", "Pissenlit", "Sylvie", "Capillaire", "Frêne", "Plantoir" },
            // Germinal
            new String[] { "Primevère", "Platane", "Asperge", "Tulipe", "Poule", "Bette", "Bouleau", "Jonquille", "Aulne", "Couvoir", "Pervenche", "Charme",
                    "Morille", "Hêtre", "Abeille", "Laitue", "Mélèze", "Ciguë", "Radis", "Ruche", "Gainier", "Romaine", "Marronnier", "Roquette", "Pigeon",
                    "Lilas", "Anémone", "Pensée", "Myrtille", "Greffoir" },
            // Floréal
            new String[] { "Rose", "Chêne", "Fougère", "Aubépine", "Rossignol", "Ancolie", "Muguet", "Champignon", "Hyacinthe", "Râteau", "Rhubarbe",
                    "Sainfoin", "Bâton d'or", "Chamerisier", "Ver à soie", "Consoude", "Pimprenelle", "Corbeille d'or", "Arroche", "Sarcloir", "Statice",
                    "Fritillaire", "Bourrache", "Valériane", "Carpe", "Fusain", "Civette", "Buglosse", "Sénevé", "Houlette" },
            // Plairial
            new String[] { "Luzerne", "Hémérocalle", "Trèfle", "Angélique", "Canard", "Mélisse", "Fromental", "Martagon", "Serpolet", "Faux", "Fraise",
                    "Bétoine", "Pois", "Acacia", "Caille", "Œillet", "Sureau", "Pavot", "Tilleul", "Fourche", "Barbeau", "Camomille", "Chèvrefeuille",
                    "Caille-lait", "Tanche", "Jasmin", "Verveine", "Thym", "Pivoine", "Chariot" },
            // Messidor
            new String[] { "Seigle", "Avoine", "Oignon", "Véronique", "Mulet", "Romarin", "Concombre", "Échalote", "Absinthe", "Faucille", "Coriandre",
                    "Artichaut", "Girofle", "Lavande", "Chamois", "Tabac", "Groseille", "Gesse", "Cerise", "Parc", "Menthe", "Cumin", "Haricot", "Orcanète",
                    "Pintade", "Sauge", "Ail", "Vesce", "Blé", "Chalémie" },
            // Thermidor
            new String[] { "Épeautre", "Bouillon blanc", "Melon", "Ivraie", "Bélier", "Prêle", "Armoise", "Carthame", "Mûre", "Arrosoir", "Panic", "Salicorne",
                    "Abricot", "Basilic", "Brebis", "Guimauve", "Lin", "Amande", "Gentiane", "Écluse", "Carline", "Câprier", "Lentille", "Aunée", "Loutre",
                    "Myrte", "Colza", "Lupin", "Coton", "Moulin" },
            // Fructidor
            new String[] { "Prune", "Millet", "Lycoperdon", "Escourgeon", "Saumon", "Tubéreuse", "Sucrion", "Apocyn", "Réglisse", "Échelle", "Pastèque",
                    "Fenouil", "Épine vinette", "Noix", "Truite", "Citron", "Cardère", "Nerprun", "Tagette", "Hotte", "Églantier", "Noisette", "Houblon",
                    "Sorgho", "Écrevisse", "Bigarade", "Verge d'or", "Maïs", "Marron", "Panier" },
            // Sanculotides
            new String[] { "Vertu", "Génie", "Travail", "Opinion", "Récompenses", "Révolution" }, };

    public static final String[] DAILY_OBJECT_TYPES = new String[]{
            "La plante", "L'animal", "L'outil", "Le minérale", "Le concept"
    };

    FrenchRevolutionaryCalendarLabelsFR() {
        super(WEEKDAYS, MONTHS, DAY_OF_YEAR, DAILY_OBJECT_TYPES);
    }

}
