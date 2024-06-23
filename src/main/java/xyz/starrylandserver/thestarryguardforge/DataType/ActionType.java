package xyz.starrylandserver.thestarryguardforge.DataType;

public enum ActionType {

    ATTACK_ENTITY_ACTION("attack_entity"),//done
    ATTACK_PLAYER_ACTION("attack_player"),//done
    FIRE_BLOCK_ACTION("fire_block"),//done
    BLOCK_PLACE_ACTION("block_place"),//done
    BLOCK_BREAK_ACTION("block_break"),//done
    KILL_ENTITY_ACTION("kill_entity"),
    KILL_PLAYER_ACTION("kill_player"),
    BUKKIT_USE_ACTION("bukkit_use"),
    CONTAINER_CHANGE_ACTION("container_change"),
    RIGHT_CLICK_BLOCK_ACTION("right_click_block");//done

    String strType;

    public String getDBName()//获取行为储存在数据库里面的名字
    {
        return this.strType;
    }

    public static ActionType fromString(String str_type)
    {
        for (ActionType type : ActionType.values()) {
            if (type.strType.equalsIgnoreCase(str_type)) {
                return type;
            }
        }
        throw new RuntimeException("Could not parse the string type.");
    }

    public TargetType getTargetType()//根据行为的类型获取行为目标的类型
    {
        return switch (this) {
            case RIGHT_CLICK_BLOCK_ACTION, BLOCK_PLACE_ACTION,FIRE_BLOCK_ACTION ,
                 BLOCK_BREAK_ACTION, BUKKIT_USE_ACTION ,CONTAINER_CHANGE_ACTION->
                    TargetType.BLOCK;
            case KILL_PLAYER_ACTION,ATTACK_PLAYER_ACTION -> TargetType.PLAYER;
            case KILL_ENTITY_ACTION,ATTACK_ENTITY_ACTION -> TargetType.ENTITY;
            default -> throw new RuntimeException("Could not parse action type to target type.");
        };
    }

    ActionType(String str_type)
    {
        this.strType = str_type;
    }
}
