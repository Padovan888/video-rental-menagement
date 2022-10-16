package br.com.videolocadorapassatempo.service.enums;

public enum Entity {

    ACTOR("Ator"),
    DIRECTOR("Diretor"),
    CLASS("Classe"),
    TITLE("Título"),
    ITEM("Item"),
    CUSTOMER("Cliente");

    private final String name;

    Entity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
