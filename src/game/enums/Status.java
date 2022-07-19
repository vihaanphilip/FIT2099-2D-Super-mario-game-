package game.enums;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {

    DESTROY_DORMANT, // Ability to destroy the dormant
    HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    TALL, // use this status to tell that current instance has "grown".
    JUMP, // provide capability to the actor to jump
    FERTILE, // this status would identify the ground to be fertile
    SUPER_MUSHROOM, // this would tell that the player has consumed super mushroom
    PLAYER, // this would tell that the actor is player
    POWER_STAR, // this would tell that the player has consumed power star
    RESET, // this would tell that the resettable should be reset
    CAN_RESET, // this would tell that the player can reset the map
    ENEMY, // Tell that the actor is actually enemy
    HAS_POWER_STAR, // tells if actor holds a Power Star in their inventory
    NO_DROP_TRADE, // tells that this item cannot be dropped if it is obtained through trade
    NORMAL_MAP, // tells that the map is normal map
    LAVA_MAP, // tells that the map is lava map,
    HOLD_KEY, // tells that the actor has the key after defeating Bowser
    BOWSER, // tells that this is Bowser
    FLY, // tells that this gives flying capability
    FOUNTAIN, // tells that the ground is fountain
    REFILLABLE,
    NEED_HEAL,
    ALLY,
    FIRE_FLOWER, // this status tells that player has consumed fire flower.

}