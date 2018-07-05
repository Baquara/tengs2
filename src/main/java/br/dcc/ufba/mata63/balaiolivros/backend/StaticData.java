package br.dcc.ufba.mata63.balaiolivros.backend;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

class StaticData {

    public static final String UNDEFINED = "Undefined";

    static final Map<String, String> LIVROS = new LinkedHashMap<>();
    static final ArrayList<String> CATEGORIAS = new ArrayList<>();

    static
    {
        /*
        Stream.of("Evian",
                "Voss",
                "Veen",
                "San Pellegrino",
                "Perrier")
                .forEach(name -> LIVROS.put(name, grupo3ao5));

        Stream.of("Coca-Cola",
                "Fanta",
                "Sprite")
                .forEach(name -> LIVROS.put(name, PrimAQuintEF));

        Stream.of("Maxwell Ready-to-Drink 6º ano EF",
                "Nescafé Gold",
                "Starbucks East Timor Tatamailau")
                .forEach(name -> LIVROS.put(name, SEXTOEF));

        Stream.of("Prince Of Peace Organic White 7º ano EF",
                "Pai Mu Tan White Peony 7º ano EF",
                "Tazo Zen Green 7º ano EF",
                "Dilmah Sencha Green 7º ano EF",
                "Twinings Earl Grey",
                "Twinings Lady Grey",
                "Classic Indian Chai")
                .forEach(name -> LIVROS.put(name, SETIMOEF));

        Stream.of("Cow's Milk",
                "Goat's Milk",
                "Unicorn's Milk",
                "Salt Lassi",
                "Mango Lassi",
                "Airag")
                .forEach(name -> LIVROS.put(name, OITAVOEF));

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
                .forEach(name -> LIVROS.put(name, NONOEF));

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
                .forEach(name -> LIVROS.put(name, ENSINO_MEDIO));

        Stream.of("Jacob's Creek Classic Shiraz",
                "Chateau d’Yquem Sauternes",
                "Oremus Tokaji Aszú 5 Puttonyos")
                .forEach(name -> LIVROS.put(name, OITAVOEF));
         */

        //LIVROS.clear();
        //LIVROS.put("", UNDEFINED);
        //LIVROS.put("HUE", OITAVOEF);
        Stream.of("Grupo 3 Ensino Infatil",
                "Grupo 4 Ensino Infantil",
                "Grupo 5 Ensino Infantil",
                "1º Ano Ensino Fundamental",
                "2º Ano Ensino Fundamental",
                "3º Ano Ensino Fundamental",
                "4º Ano Ensino Fundamental",
                "5º Ano Ensino Fundamental",
                "6º Ano Ensino Fundamental",
                "7º Ano Ensino Fundamental",
                "8º Ano Ensino Fundamental",
                "9º Ano Ensino Fundamental",
                "1º Ano Ensino Médio",
                "2º Ano Ensino Médio",
                "3º Ano Ensino Médio",
                "Ensino Superior"
        ).forEach(name -> CATEGORIAS.add(name));

    }

    /**
     * This class is not meant to be instantiated.
     */
    private StaticData() {
    }
}
