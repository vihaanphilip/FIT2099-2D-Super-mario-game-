package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;
import game.reset.Resettable;
import game.wallet.Wallet;
import game.wallet.WalletManager;

import java.util.ArrayList;

public class Coin extends Item implements Resettable {
    /**
     * Indicating the value of the coin
     */
    private int value;

    /**
     * Constructor for coin
     * @param value value of the coin
     */

    public Coin(int value) {
        super("coin", '$', true);
        this.value = value;
        registerInstance();
    }

    /**
     * Keeps track of the coin picked up by the player, transfers it immediately to the player's wallet
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        for (Item item : new ArrayList<Item>(actor.getInventory())) {

            // if the player picks up the Coin, add the value of the coin to their wallet
            if (item.getClass().getSimpleName().equals("Coin")){
                Coin coin = (Coin) item;

                // remove the coin from their inventory
                actor.removeItemFromInventory(item);

                // find the actor's wallet
                int i = 0;
                for (Wallet wallet : WalletManager.getInstance().getWallets()){
                    if (wallet.getActor() == actor){
                        break;
                    }
                    i++;
                }

                // add value to the wallet
                WalletManager.getInstance().getWallets().get(i).deposit(coin.getValue());
            }
        }
    }

    /**
     * Keeps track of the coin in the map
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        // If reset action button called, remove the coin
        if (hasCapability(Status.RESET)) {
            currentLocation.removeItem(this);
        }
    }

    /**
     * gets the value of the coin
     * @return
     */
    public int getValue(){
        return this.value;
    }

    /**
     * Reset the coin during reset action
     * @see Resettable#resetInstance()
     */
    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }

    /**
     * Implements the toString method of coin
     * @return updated toString method of coin
     */
    @Override
    public String toString() {
        return "Coin ($" + value + ")";
    }
}
