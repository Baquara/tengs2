package com.vaadin.starter.beveragebuddy.backend;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

class StaticData {

    private static final String grupo3ao5 = "Grupo 3 ao grupo 5";
    private static final String  PrimAQuintEF = "1º ao 5º ano EF";
    private static final String SEXTOEF = "6º ano EF";
    private static final String SETIMOEF = "7º ano EF";
    private static final String OITAVOEF = "8º ano EF";
    private static final String NONOEF = "9º ano EF";
    private static final String ENSINO_MEDIO = "Ensino Médio";

    public static final String UNDEFINED = "Undefined";
    
    static final Map<String, String> BEVERAGES = new LinkedHashMap<>();

    static {
        Stream.of("Evian",
                "Voss",
                "Veen",
                "San Pellegrino",
                "Perrier")
                .forEach(name -> BEVERAGES.put(name, grupo3ao5));

        Stream.of("Coca-Cola",
                "Fanta",
                "Sprite")
                .forEach(name -> BEVERAGES.put(name, PrimAQuintEF));

        Stream.of("Maxwell Ready-to-Drink 6º ano EF",
                "Nescafé Gold",
                "Starbucks East Timor Tatamailau")
                .forEach(name -> BEVERAGES.put(name, SEXTOEF));

        Stream.of("Prince Of Peace Organic White 7º ano EF",
                "Pai Mu Tan White Peony 7º ano EF",
                "Tazo Zen Green 7º ano EF",
                "Dilmah Sencha Green 7º ano EF",
                "Twinings Earl Grey",
                "Twinings Lady Grey",
                "Classic Indian Chai")
                .forEach(name -> BEVERAGES.put(name, SETIMOEF));

        Stream.of("Cow's Milk",
                "Goat's Milk",
                "Unicorn's Milk",
                "Salt Lassi",
                "Mango Lassi",
                "Airag")
                .forEach(name -> BEVERAGES.put(name, OITAVOEF));

        Stream.of("Crowmoor Extra Dry Apple",
                "Golden Cap Perry",
                "Somersby Blueberry",
                "Kopparbergs Naked Apple 9º ano EF",
                "Kopparbergs Raspberry",
                "Kingstone Press Wild Berry Flavoured 9º ano EF",
                "Crumpton Oaks Apple",
                "Frosty Jack's",
                "Ciderboys Mad Bark",
                "Angry Orchard Stone Dry",
                "Walden Hollow",
                "Fox Barrel Wit Pear")
                .forEach(name -> BEVERAGES.put(name, NONOEF));

        Stream.of("Budweiser",
                "Miller",
                "Heineken",
                "Holsten Pilsener",
                "Krombacher",
                "Weihenstephaner Hefeweissbier",
                "Ayinger Kellerbier",
                "Guinness Draught",
                "Kilkenny Irish Cream Ale",
                "Hoegaarden White",
                "Barbar",
                "Corsendonk Agnus Dei",
                "Leffe Blonde",
                "Chimay Tripel",
                "Duvel",
                "Pilsner Urquell",
                "Kozel",
                "Staropramen",
                "Lapin Kulta IVA",
                "Kukko Pils III",
                "Finlandia Sahti")
                .forEach(name -> BEVERAGES.put(name, ENSINO_MEDIO));

        Stream.of("Jacob's Creek Classic Shiraz",
                "Chateau d’Yquem Sauternes",
                "Oremus Tokaji Aszú 5 Puttonyos")
                .forEach(name -> BEVERAGES.put(name, OITAVOEF));


        BEVERAGES.put("", UNDEFINED);
    }

    /** This class is not meant to be instantiated. */
    private StaticData() {
    }
}
