package com.martaviadev.neoprismaticos.classes;

public enum RPGClass {
    NONE("Ninguno", "Sin clase asignada"),

    OUTER("Outer", "Invisibilidad, visión nocturna, hackeo táctico, reflejos aumentados"),
    MAUPOOL("Maupool", "Super velocidad, invulnerabilidad"),
    ANTO("Anto", "Teletransportación, magnetismo"),
    RAICHUKA("Raichuka", "Súper fuerza, súper resistencia"),
    KEI("Kei", "Viaje temporal, alquimia"),
    CORVUS("Corvus", "Niebla de la peste, invocación de cuervos"),
    CLOUDNONE("Cloudnone", "Control del clima y electricidad"),
    KIKE("KiK3", "Modo Setso, Quickshots"),
    DALDORIAN("Daldorian", "Control elemental, poderes psíquicos"),
    DANJIPEZ("Danjipez", "Premonición, psíquico"),
    XENVIL("Xenvil", "Tecnomancia neural, energía neoprismática"),
    YISHAQ("Yishaq", "Invisibilidad total, hackear cualquier cosa"),
    JOR("Jor", "Control de electricidad, crear café");

    private final String displayName;
    private final String description;

    RPGClass(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }
}
