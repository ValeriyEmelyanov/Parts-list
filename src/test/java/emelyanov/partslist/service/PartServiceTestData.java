package emelyanov.partslist.service;

import emelyanov.partslist.model.Part;

public class PartServiceTestData {
    public static final int ID_1 = 1;

    public static final int PAGE_1 = 1;
    public static final int PAGE_2 = 2;

    public static final int SIZE_ALL = 12;
    public static final int SIZE_ESSENTIAL = 3;
    public static final int SIZE_OPTIONAL = 9;
    public static final int SIZE_NAME_SEARCH = 9;
    public static final int SIZE_ABILITY = 8;

    public static final Part PART_1 = new Part(ID_1, "Деталь 01", true,  8);
}
