package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Status;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.wallet.Wallet;
import game.wallet.WalletManager;

/**
 * TradeAction class represents a trading action between a buyer and seller
 */
public class TradeAction extends Action {

    /**
     * The actor which is buying from the seller
     */
    private final Actor buyer;

    /**
     * The item which is up for trade
     */
    private final Item item;

    /**
     * The price of the item for trade
     */
    private final int price;

    /**
     * Constructor for TradeAction class
     * @param buyer the actor which is buying from the seller
     * @param item the item which is up for trade
     * @param price the price of the item for trade
     */
    public TradeAction(Actor buyer, Item item, int price){
        this.buyer = buyer;
        this.item = item;
        this.price = price;
    }

    /**
     * Execution of the TradeAction action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a message displayed after the trade is completed
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        // find the actor's wallet
        int i = 0;
        for (Wallet wallet : WalletManager.getInstance().getWallets()){
            if (wallet.getActor() == buyer){
                break;
            }
            i++;
        }

        int balance = WalletManager.getInstance().getWallets().get(i).getBalance();

        if (balance < price) {
            return "You don't have enough coins!";
        } else {
            WalletManager.getInstance().getWallets().get(i).withdraw(price);

            // disallow player from dropping item if it is Power Star or Super Mushroom
            if (item.hasCapability(Status.NO_DROP_TRADE)){
                item.togglePortability();
            }


            buyer.addItemToInventory(item);
            return buyer + " purchased " + item + " for $" + price;
        }
    }

    /**
     * Description of the trade to be displayed in the console menu
     * @param actor The actor performing the action.
     * @return the description of the trade in String format
     */
    @Override
    public String menuDescription(Actor actor) {
        return buyer + " buys " + item + " ($" + price + ")";
    }
}
