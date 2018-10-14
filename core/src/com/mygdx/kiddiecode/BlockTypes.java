package com.mygdx.kiddiecode;

public enum BlockTypes {
    ONLOAD_TRIGGER("On Load"),
    IF_LESS_THAN("If Less Than"),
    LOOP_FROM_TO("For"),
    VAR_DECLARE("Var"),//does not do anything
    PLACE_BLOCK("Place Block"),
    PLACE_PLAYER("Place Player"),
    ONTICK_TRIGGER("On Tick"),
    MOVE_PLAYER_BY("Move Player"),
    IF_EQUAL_TO("If Equal To"),
    IF_GREATER_THAN("If Greater Than"),
    IF_NOT_EQUAL_TO("If Not Equal To"),
    SPAWN_ENTITY_AT("Spawn Entity"),
    MOVE_ENTITY("Move Entity"),
    ONTICK_CLASS_TRIGGER("On Tick Class");

    String s;

    BlockTypes(String string) {
        s = string;
    }
}
