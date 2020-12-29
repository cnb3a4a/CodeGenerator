package com.b3a4a.groups.generator.db.type;

public enum ColumnType {
    BIT("bit"),
    BOOLEAN("boolean"),
    TINYINT("tinyint"),
    SMALLINT("smallint"),
    INT("int"),
    BIGINT("bigint"),
    FLOAT("float"),
    DOUBLE("double"),
    DECIMAL("decimal"),
    VARCHAR("varchar"),
    DATETIME("datetime"),
    BLOB("blob");

    private String type;
    ColumnType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
}
