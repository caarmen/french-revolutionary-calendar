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

 * Provides translations of weekday names, month names, and day of year names, in English.

 * All translations come from Wikipedia.
 * Wikipedia article: http://en.wikipedia.org/wiki/French_Republican_Calendar

 * @author calvarez
 */
internal class FrenchRevolutionaryCalendarLabelsEN : FrenchRevolutionaryCalendarLabels(FrenchRevolutionaryCalendarLabelsFR.WEEKDAYS, FrenchRevolutionaryCalendarLabelsFR.MONTHS, FrenchRevolutionaryCalendarLabelsEN.DAY_OF_YEAR, FrenchRevolutionaryCalendarLabelsEN.DAILY_OBJECT_TYPES) {
    companion object {

        @JvmField
        val DAY_OF_YEAR = arrayOf(
                // Vendemiaire
                arrayOf("Grape", "Saffron", "Chestnut", "Crocus", "Horse", "Impatiens", "Carrot", "Amaranth", "Parsnip", "Vat", "Potato", "Strawflower", "Butter Squash", "Mignonette", "Donkey", "The four o'clock flower", "Pumpkin", "Buckwheat", "Sunflower", "Wine-Press", "Hemp", "Peach", "Turnip", "Amaryllis", "Ox", "Eggplant", "Chili Pepper", "Tomato", "Barley", "Barrel"),
                // Brumaire
                arrayOf("Apple", "Celery", "Pear", "Beet root", "Goose", "Heliotrope", "Common Fig", "Black Salsify", "Chequer Tree", "Plough", "Salsify", "Water chestnut", "Jerusalem Artichoke", "Endive", "Turkey", "Skirret", "Watercress", "Leadworts", "Pomegranate", "Harrow", "Asarum baccharis", "Azarole", "Madder", "Orange", "Pheasant", "Pistachio", "Tuberous pea", "Quince", "Service tree", "Roller"),
                // Frimaire
                arrayOf("Rampion", "Turnip", "Chicory", "Medlar", "Pig", "Corn Salad", "Cauliflower", "Honey", "Juniper", "Pickaxe", "Wax", "Horseradish", "Cedar tree", "Fir tree", "Roe Deer", "Gorse", "Cypress Tree", "Ivy", "Savin Juniper", "Grub-hoe", "Sugar Maple", "Heather", "Reed plant", "Sorrel", "Cricket", "Pinenut", "Cork", "Truffle", "Olive", "Shovel"),
                // Nivôse
                arrayOf("Peat", "Coal", "Bitumen", "Sulphur", "Dog", "Lava", "Topsoil", "Manure", "Saltpeter", "Flail", "Granite stone", "Clay", "Slate", "Sandstone", "Rabbit", "Flint", "Marl", "Limestone", "Marble", "Winnowing basket", "Gypsum", "Salt", "Iron", "Copper", "Cat", "Tin", "Lead", "Zinc", "Mercury", "Sieve"),
                // Pluviôse
                arrayOf("Spurge-laurel", "Moss", "Butcher's Broom", "Snowdrop", "Bull", "Laurustinus", "Tinder polypore", "Daphne mezereum", "Poplar Tree", "Axe", "Hellebore", "Broccoli", "Laurel", "Filbert", "Cow", "Box Tree", "Lichen", "Yew tree", "Lungwort", "Billhook", "Pennycress", "Rose Daphne", "Couch Grass", "Common Knotgrass", "Hare", "Woad", "Hazel", "Cyclamen", "Celandine", "Sleigh"),
                // Ventôse
                arrayOf("Coltsfoot", "Dogwood", "Matthiola", "Privet", "Billygoat", "Wild Ginger", "Italian Buckthorn", "Violet", "Goat Willow", "Spade", "Narcissus", "Elm Tree", "Common fumitory", "Hedge Mustard", "Goat", "Spinach", "Large-flowered Leopard's Bane", "Pimpernel", "Chervil", "Twine", "Mandrake", "Parsley", "Scurvy-grass", "Daisy", "Tuna", "Dandelion", "Wood Anemone", "Maidenhair fern", "Ash Tree", "Dibber"),
                // Germinal
                arrayOf("Primrose", "Plane Tree", "Asparagus", "Tulip", "Hen", "Chard Plant", "Birch Tree", "Daffodil", "Alder", "Hatchery", "Periwinkle", "Hornbeam", "Morel", "European Beech Tree", "Bee", "Lettuce", "Larch", "Hemlock", "Radish", "Hive", "Judas tree", "Lettuce", "Horse chestnut", "Arugula or Rocket", "Pigeon", "Lilac", "Anemone", "Pansy", "Blueberry", "Knife"),
                // Floréal
                arrayOf("Rose", "Oak Tree", "Fern", "Hawthorn", "Nightingale", "Common Columbine", "Lily of the Valley", "Button mushroom", "Hyacinth", "Rake", "Rhubarb", "Sainfoin", "Wallflower", "Fan Palm tree", "Silkworm", "Comfrey", "Salad Burnet", "Basket of Gold", "Orache", "Garden hoe", "Thrift", "Fritillary", "Borage", "Valerian", "Carp", "Spindle", "Chive", "Bugloss", "Wild mustard", "Shepherd's crook"),
                // Plairial
                arrayOf("Alfalfa", "Daylily", "Clover", "Angelica", "Duck", "Lemon Balm", "Oat grass", "Martagon lily", "Wild Thyme ", "Scythe", "Strawberry", "Woundwort", "Pea", "Acacia", "Quail", "Carnation", "Elderberry", "Poppy plant", "Linden or Lime tree", "Pitchfork", "Cornflower", "Camomile", "Honeysuckle", "Bedstraw", "Tench", "Jasmine Plant", "Verbena", "Thyme Plant", "Peony Plant", "Hand Cart"),
                // Messidor
                arrayOf("Rye", "Oats", "Onion", "Speedwell", "Mule", "Rosemary", "Cucumber", "Shallot", "Wormwood", "Sickle", "Coriander", "Artichoke", "Clove", "Lavender", "Chamois", "Tobacco", "Currant", "Hairy Vetchling", "Cherry", "Pen", "Mint", "Cumin", "Bean", "Alkanet", "Guinea fowl", "Sage Plant", "Garlic", "Tare", "Wheat", "Shawm"),
                // Thermidor
                arrayOf("Spelt", "Common Mullein", "Melon", "Ryegrass", "Ram", "Horsetail", "Mugwort", "Safflower", "Blackberry", "Watering Can", "Switchgrass", "Common Glasswort", "Apricot", "Basil", "Ewe", "Marshmallow", "Flax", "Almond", "Gentian", "Lock", "Carline thistle", "Caper", "Lentil", "Inula", "Otter", "Myrtle", "Rapeseed", "Lupin", "Cotton", "Mill"),
                // Fructidor
                arrayOf("Plum", "Millet", "Puffball", "Six-row Barley", "Salmon", "Tuberose", "Winter Barley", "Apocynum", "Liquorice", "Ladder", "Watermelon", "Fennel", "Barberry", "Walnut", "Trout", "Lemon", "Teasel", "Buckthorn", "Mexican Marigold", "Harvesting basket", "Wild Rose", "Hazelnut", "Hops", "Sorghum", "Crayfish", "Bitter Orange", "Goldenrod", "Maize or Corn", "Sweet Chestnut", "Pack Basket"),
                // Sanculotides
                arrayOf("Virtue", "Talent", "Labor", "Convictions", "Honors", "Revolution"))

        @JvmField
        val DAILY_OBJECT_TYPES = arrayOf("The plant", "The animal", "The tool", "The mineral", "The concept")
    }

}
