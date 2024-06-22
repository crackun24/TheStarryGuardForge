package xyz.starrylandserver.thestarryguardforge.DataType;


public class Action {//玩家的行为类
//    public static final String ATTACK_ACTION = "attack";
//    public static final String FIRE_BLOCK_ACTION = "fire_block";
//    public static final String BLOCK_PLACE_ACTION = "block_place";
//    public static final String TNT_USE_ACTION = "tnt_use";
//    public static final String BLOCK_BREAK_ACTION = "block_break";
//    public static final String KILL_ENTITY_ACTION = "kill_entity";
//    public static final String KILL_PLAYER_ACTION = "kill_player";
//    public static final String BUKKIT_USE_ACTION = "bukkit_use";//使用桶装物品
//    public static final String CONTAINER_CHANGE_ACTION = "container_change";//和容器的交互事件
//    public static final String RIGHT_CLICK_BLOCK_ACTION = "right_click_block";//右键方块的事件

    public TgPlayer player;
    public int posX;
    public int posY;
    public int posZ;
    public String dimension;//玩家触发这个事件的维度
    public String actionData;//事件的数据
    public long time;//触发事件的时间
    public ActionType actionType;
    public Target target;//事件的目标


    public Action(ActionType action_type, TgPlayer player, int x, int y, int z, String dimension,Target target,long time) {
        this.player = player;
        this.posX = x;
        this.posY = y;
        this.posZ = z;//设置玩家影响的目标的坐标
        this.dimension = dimension;//设置玩家触发事件的维度
        this.actionType = action_type;
        this.target  = target;//设置目标
        this.time  = time;
    }
}
