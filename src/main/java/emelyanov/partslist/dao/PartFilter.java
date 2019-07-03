package emelyanov.partslist.dao;

/**
 * Перечисление режимов фильтрации.
 */
public enum PartFilter {
    ALL(
            "from Part",
            "select count(*) from Part",
            "Все"
    ),
    ESSENTIAL(
            "from Part where essential = true",
            "select count(*) from Part where essential = true",
            "Необходимые"
    ),
    OPTIONAL(
            "from Part where essential = false",
            "select count(*) from Part where essential = false",
            "Опциональные"
    ),
    NAME_SEARCH(
            "from Part where name like :nameParam",
            "select count(*) from Part where name like :nameParam",
            "Поиск"
    );

    private String sqlList;
    private String sqlCount;
    private String title;

    PartFilter(String sqlList, String sqlCount, String title) {
        this.sqlList = sqlList;
        this.sqlCount = sqlCount;
        this.title = title;
    }

    public String getSqlList() {
        return sqlList;
    }

    public String getSqlCount() {
        return sqlCount;
    }

    public String getTitle() {
        return title;
    }
}
