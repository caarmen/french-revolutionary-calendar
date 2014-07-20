/*
 * French Revolutionary Calendar Library
 * 
 * Copyright (2012-2014) Carmen Alvarez
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
package ca.rmen.lfrc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 
 * Provides conversion between Gregorian calendar dates and French Revolutionary Calendar dates.
 * Wikipedia article: http://en.wikipedia.org/wiki/French_Republican_Calendar
 * 
 * @author calvarez
 */
public class FrenchRevolutionaryCalendar { // NO_UCD (use default)
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
            new String[] { "Vertu", "Génie", "Tavail", "Opinion", "Récompenses", "Révolution" }, };

    public static final String[][] DAY_OF_YEAR_EN = new String[][] {
            // Vendemiaire
            new String[] { "Grape", "Saffron", "Chestnut", "Crocus", "Horse", "Impatiens", "Carrot", "Amaranth", "Parsnip", "Vat", "Potato", "Strawflower",
                    "Butter Squash", "Mignonette", "Donkey", "The four o'clock flower", "Pumpkin", "Buckwheat", "Sunflower", "Wine-Press", "Hemp", "Peach",
                    "Turnip", "Amaryllis", "Ox", "Eggplant", "Chili Pepper", "Tomato", "Barley", "Barrel", },
            // Brumaire
            new String[] { "Apple", "Celery", "Pear", "Beet root", "Goose", "Heliotrope", "Common Fig", "Black Salsify", "Chequer Tree", "Plough", "Salsify",
                    "Water chestnut", "Jerusalem Artichoke", "Endive", "Turkey", "Skirret", "Watercress", "Leadworts", "Pomegranate", "Harrow",
                    "Asarum baccharis", "Azarole", "Madder", "Orange", "Pheasant", "Pistachio", "Tuberous pea", "Quince", "Service tree", "Roller", },
            // Frimaire
            new String[] { "Rampion", "Turnip", "Chicory", "Medlar", "Pig", "Corn Salad", "Cauliflower", "Honey", "Juniper", "Pickaxe", "Wax", "Horseradish",
                    "Cedar tree", "Fir tree", "Roe Deer", "Gorse", "Cypress Tree", "Ivy", "Savin Juniper", "Grub-hoe", "Sugar Maple", "Heather", "Reed plant",
                    "Sorrel", "Cricket", "Pinenut", "Cork", "Truffle", "Olive", "Shovel", },
            // Nivôse
            new String[] { "Peat", "Coal", "Bitumen", "Sulphur", "Dog", "Lava", "Topsoil", "Manure", "Saltpeter", "Flail", "Granite stone", "Clay", "Slate",
                    "Sandstone", "Rabbit", "Flint", "Marl", "Limestone", "Marble", "Winnowing basket", "Gypsum", "Salt", "Iron", "Copper", "Cat", "Tin",
                    "Lead", "Zinc", "Mercury", "Sieve", },
            // Pluviôse
            new String[] { "Spurge-laurel", "Moss", "Butcher's Broom", "Snowdrop", "Bull", "Laurustinus", "Tinder polypore", "Daphne mezereum", "Poplar Tree",
                    "Axe", "Hellebore", "Broccoli", "Laurel", "Filbert", "Cow", "Box Tree", "Lichen", "Yew tree", "Lungwort", "Billhook", "Pennycress",
                    "Rose Daphne", "Couch Grass", "Common Knotgrass", "Hare", "Woad", "Hazel", "Cyclamen", "Celandine", "Sleigh", },
            // Ventôse
            new String[] { "Coltsfoot", "Dogwood", "Matthiola", "Privet", "Billygoat", "Wild Ginger", "Italian Buckthorn", "Violet", "Goat Willow", "Spade",
                    "Narcissus", "Elm Tree", "Common fumitory", "Hedge Mustard", "Goat", "Spinach", "Large-flowered Leopard's Bane", "Pimpernel", "Chervil",
                    "Twine", "Mandrake", "Parsley", "Scurvy-grass", "Daisy", "Tuna", "Dandelion", "Wood Anemone", "Maidenhair fern", "Ash Tree", "Dibber", },
            // Germinal
            new String[] { "Primrose", "Plane Tree", "Asparagus", "Tulip", "Hen", "Chard Plant", "Birch Tree", "Daffodil", "Alder", "Hatchery", "Periwinkle",
                    "Hornbeam", "Morel", "European Beech Tree", "Bee", "Lettuce", "Larch", "Hemlock", "Radish", "Hive", "Judas tree", "Lettuce",
                    "Horse chestnut", "Arugula or Rocket", "Pigeon", "Lilac", "Anemone", "Pansy", "Blueberry", "Knife", },
            // Floréal
            new String[] { "Rose", "Oak Tree", "Fern", "Hawthorn", "Nightingale", "Common Columbine", "Lily of the Valley", "Button mushroom", "Hyacinth",
                    "Rake", "Rhubarb", "Sainfoin", "Wallflower", "Fan Palm tree", "Silkworm", "Comfrey", "Salad Burnet", "Basket of Gold", "Orache",
                    "Garden hoe", "Thrift", "Fritillary", "Borage", "Valerian", "Carp", "Spindle", "Chive", "Bugloss", "Wild mustard", "Shepherd's crook", },
            // Plairial
            new String[] { "Alfalfa", "Daylily", "Clover", "Angelica", "Duck", "Lemon Balm", "Oat grass", "Martagon lily", "Wild Thyme ", "Scythe",
                    "Strawberry", "Woundwort", "Pea", "Acacia", "Quail", "Carnation", "Elderberry", "Poppy plant", "Linden or Lime tree", "Pitchfork",
                    "Cornflower", "Camomile", "Honeysuckle", "Bedstraw", "Tench", "Jasmine Plant", "Verbena", "Thyme Plant", "Peony Plant", "Hand Cart", },
            // Messidor
            new String[] { "Rye", "Oats", "Onion", "Speedwell", "Mule", "Rosemary", "Cucumber", "Shallot", "Wormwood", "Sickle", "Coriander", "Artichoke",
                    "Clove", "Lavender", "Chamois", "Tobacco", "Currant", "Hairy Vetchling", "Cherry", "Park", "Mint", "Cumin", "Bean", "Alkanet",
                    "Guinea fowl", "Sage Plant", "Garlic", "Tare", "Wheat", "Shawm", },
            // Thermidor
            new String[] { "Spelt", "Common Mullein", "Melon", "Ryegrass", "Ram", "Horsetail", "Mugwort", "Safflower", "Blackberry", "Watering Can",
                    "Switchgrass", "Common Glasswort", "Apricot", "Basil", "Ewe", "Marshmallow", "Flax", "Almond", "Gentian", "Lock", "Carline thistle",
                    "Caper", "Lentil", "Inula", "Otter", "Myrtle", "Rapeseed", "Lupin", "Cotton", "Mill", },
            // Fructidor
            new String[] { "Plum", "Millet", "Puffball", "Six-row Barley", "Salmon", "Tuberose", "Winter Barley", "Apocynum", "Liquorice", "Ladder",
                    "Watermelon", "Fennel", "Barberry", "Walnut", "Trout", "Lemon", "Teasel", "Buckthorn", "Mexican Marigold", "Harvesting basket",
                    "Wild Rose", "Hazelnut", "Hops", "Sorghum", "Crayfish", "Bitter Orange", "Goldenrod", "Maize or Corn", "Sweet Chestnut", "Pack Basket", },
            // Sanculotides
            new String[] { "Virtue", "Talent", "Labor", "Convictions", "Honors", "Revolution", },

    };

    public static enum CalculationMethod {
        EQUINOX, ROMME
    };

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String FRENCH_ERA_BEGIN = "1792-09-22 00:00:00";
    private static final String FRENCH_ERA_END = "1811-09-23 00:00:00";
    private static final long NUM_MILLISECONDS_IN_DAY = 1000 * 60 * 60 * 24;
    private static final int EQUINOX_MONTH = 8; // September

    private Calendar frenchEraBegin;
    private Calendar frenchEraEnd;
    private CalculationMethod calculationMethod;

    public FrenchRevolutionaryCalendar(CalculationMethod calculationMethod) {
        // How will we calculate the Gregorian date of the beginning of each
        // French year?
        setCalculationMethod(calculationMethod);

        // Read in the dates of the beginning and end of the French calendar,
        // for which equinoxes were used.
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        try {

            frenchEraBegin = Calendar.getInstance();
            frenchEraBegin.setTime(sdf.parse(FRENCH_ERA_BEGIN));
            frenchEraEnd = Calendar.getInstance();
            frenchEraEnd.setTime(sdf.parse(FRENCH_ERA_END));
        } catch (ParseException e) {
            debug("Error reading French epoch " + FRENCH_ERA_BEGIN, e);
        }
    }

    /**
     * @param calculationMethod
     *            the method to calculate the first day of the French
     *            year, in the Gregorian calendar.
     */
    public void setCalculationMethod(CalculationMethod calculationMethod) {
        this.calculationMethod = calculationMethod;
    }

    /**
     * @param gregorianDate
     * @return the French date corresponding to the Gregorian calendar date.
     */
    public FrenchRevolutionaryCalendarDate getDate(GregorianCalendar gregorianDate) {

        // Determine the method of calculating the first day of the French year.

        // If we are using the equinox calculation method, or if we are within the dates the
        // calendar was used (regardless of the selected calculation method), use the equinox
        // calculation method.
        FrenchRevolutionaryCalendarDate result = null;
        if (calculationMethod == CalculationMethod.EQUINOX || (gregorianDate.after(frenchEraBegin) && gregorianDate.before(frenchEraEnd))) {
            result = getDateEquinox(gregorianDate);
        } else if (calculationMethod == CalculationMethod.ROMME) {
            result = getDateRomme(gregorianDate);
        } else {
            throw new IllegalArgumentException("Can't convert date " + gregorianDate + " using method " + calculationMethod);
        }
        // Get the decimal time portion of the French date
        if (result != null) {
            int[] timeInDay = getFrenchTime(gregorianDate);
            result = new FrenchRevolutionaryCalendarDate(result.year, result.month, result.dayOfMonth, timeInDay[0], timeInDay[1], timeInDay[2]);
        }
        return result;
    }

    /**
     * @param gregorianDate
     * @return The French date corresponding to the Gregorian calendar date,
     *         using the equinox method to determine the Gregorian date for the
     *         first day of the French year.
     */
    private FrenchRevolutionaryCalendarDate getDateEquinox(Calendar gregorianDate) {

        int gyear = gregorianDate.get(Calendar.YEAR);
        Calendar gAutumnEquinox = getAutumnEquinox(gyear);
        if (gAutumnEquinox == null) throw new IllegalArgumentException("Date not supported: " + gregorianDate);

        // Determine the first day of the French year.
        Calendar g1stVendemiaire = gAutumnEquinox;
        // Case 1, date from January to September, use the equinox date from
        // last year
        if (gregorianDate.compareTo(gAutumnEquinox) < 0) {
            g1stVendemiaire = getAutumnEquinox(gyear - 1);
            if (g1stVendemiaire == null) throw new IllegalArgumentException("Date not supported: " + gregorianDate);
        }
        // Case 2, date from September to December
        else {

        }
        // The year in the French date: Gregorian year at 1st vendemiaire -
        // 1792. This is the number of years elapsed since the French calendar
        // had been started.
        int frenchYear = g1stVendemiaire.get(Calendar.YEAR) - frenchEraBegin.get(Calendar.YEAR) + 1;
        // The DAY_OF_YEAR in the French year, from 0 to 365. This is the
        // number of days elapsed 1stVendemiaire of the French year and the
        // given timestamp.
        int numberDaysInFrenchYear = (int) ((gregorianDate.getTimeInMillis() - g1stVendemiaire.getTimeInMillis()) / NUM_MILLISECONDS_IN_DAY);
        // Create and return the French calendar object.
        FrenchRevolutionaryCalendarDate result = getFrenchDate(frenchYear, numberDaysInFrenchYear);
        return result;
    }

    /**
     * @param gregorianDate
     * @return The French date corresponding to the Gregorian calendar date,
     *         using the Romme method to determine the Gregorian date for the
     *         first day of the French year.
     */
    private FrenchRevolutionaryCalendarDate getDateRomme(Calendar gregorianDate) {
        // Time elapsed between the end of the French calendar and the given
        // date. We have to include the daylight savings offset, because back in
        // 1792-1811,
        // daylight savings time wasn't being used. If we don't take into
        // account the offset, a calculation like 8/5/1996 00:00:00 - 8/5/1796
        // 00:00:00 will not return 200 years, but 200 years - 1 hour, which is
        // not
        // the desired result.
        long numMillisSinceEndOfFrenchEra = gregorianDate.getTimeInMillis() - frenchEraEnd.getTimeInMillis() + gregorianDate.get(Calendar.DST_OFFSET);

        // The Romme method applies the same
        // rules (mostly) of the Gregorian calendar to the French calendar.
        // One difference is the year 4000, which is a leap year in the
        // Gregorian system, but not in the French system. For now we ignore
        // this difference. We may address it and fix this code in the year
        // 3999 :)

        // Create a fake calendar object (fake because this is not really a
        // Gregorian date), corresponding to the end of the French calendar era.
        // This was in the French year 20. Since in the Gregorian year 20, there
        // were no leap years yet, we add 10000 to the year, so that the
        // Gregorian calendar implementation can handle the leap years.

        // The end of the French calendar system was the beginning of the year
        // 20.
        long fakeEndFrenchEraTimestamp = new GregorianCalendar(10020, 0, 1).getTimeInMillis();
        // Add the elapsed time to the French date.
        long fakeFrenchTimestamp = fakeEndFrenchEraTimestamp + numMillisSinceEndOfFrenchEra;

        // Create a calendar object for the French date
        Calendar fakeFrenchDate = Calendar.getInstance();
        fakeFrenchDate.setTimeInMillis(fakeFrenchTimestamp);

        // Extract the year, and day in year from the French date.
        int frenchYear = fakeFrenchDate.get(Calendar.YEAR);
        int frenchDayInYear = fakeFrenchDate.get(Calendar.DAY_OF_YEAR);

        // Create and return a French calendar object.
        FrenchRevolutionaryCalendarDate result = getFrenchDate(frenchYear - 10000, frenchDayInYear - 1);
        return result;
    }

    /**
     * @param frenchYear
     *            the year in the French calendar
     * @param numberDaysInFrenchYear
     *            number of days since 1st Vendemiare of the given year,
     *            starting with 0.
     * @return A French calendar object for the given French day.
     */
    private FrenchRevolutionaryCalendarDate getFrenchDate(int frenchYear, int numberDaysInFrenchYear) {
        // Find the month in the French year, starting from 0.
        int numberMonthInFrenchYear = numberDaysInFrenchYear / 30;

        // Find the day in the French month, starting from 0.
        int numberDaysInFrenchMonth = numberDaysInFrenchYear - (numberMonthInFrenchYear * 30);

        // Create and return the French calendar object.
        FrenchRevolutionaryCalendarDate result = new FrenchRevolutionaryCalendarDate(frenchYear, numberMonthInFrenchYear + 1, numberDaysInFrenchMonth + 1, 0,
                0, 0);
        return result;
    }

    /**
     * @param gtime
     * @return a decimal representation of the time within this day. Returns
     *         three ints for hour, minutes, seconds, respectively. The hour is
     *         from 0 to 9, the minute is from 0 to 99, and the second is from
     *         0 to 99.
     */
    private int[] getFrenchTime(Calendar gtime) {
        int ghour = gtime.get(Calendar.HOUR_OF_DAY);
        int gmin = gtime.get(Calendar.MINUTE);
        int gsec = gtime.get(Calendar.SECOND);

        float dayFraction = ((float) ghour / 24) + ((float) gmin / 1440) + ((float) gsec / 86400);
        int fhour = (int) (dayFraction * 10);
        int fmin = (int) ((dayFraction * 10 - fhour) * 100);
        int fsec = (int) ((dayFraction * 10 - (fhour + (float) fmin / 100)) * 10000);
        return new int[] { fhour, fmin, fsec };
    }

    /**
     * @param year
     *            a year in the Gregorian calendar
     * @return the day, in the Gregorian calendar, of the autumn equinox (at
     *         midnight) in the current timezone, for the given year.
     */
    private Calendar getAutumnEquinox(int gyear) {
        // Create a date object for the autumn equinox date (at midnight) in the
        // current timezone.
        Integer gAutumnEquinoxDay = EquinoxDates.getAutumnEquinox(gyear);
        if (gAutumnEquinoxDay == null) return null;
        Calendar gAutumnEquinox = Calendar.getInstance();
        gAutumnEquinox.set(Calendar.YEAR, gyear);
        gAutumnEquinox.set(Calendar.MONTH, EQUINOX_MONTH);
        gAutumnEquinox.set(Calendar.DAY_OF_MONTH, gAutumnEquinoxDay);
        gAutumnEquinox.set(Calendar.HOUR_OF_DAY, 0);
        gAutumnEquinox.set(Calendar.MINUTE, 0);
        gAutumnEquinox.set(Calendar.SECOND, 0);
        gAutumnEquinox.set(Calendar.MILLISECOND, 0);
        return gAutumnEquinox;
    }

    private void debug(Object o, Throwable t) {
        System.out.println(getClass().getName() + ": " + o);
        if (t != null) t.printStackTrace();
    }
}
