package game.wallet;


import edu.monash.fit2099.engine.actors.Actor;

/**
 * Wallet class represents a wallet object that an actor can use to store money
 */
public class Wallet {

    /**
     * Balance of the wallet
     */
    private int balance;

    /**
     * Actor using the wallet
     */
    private Actor actor;

    /**
     * Constructor for Wallet class
     * @param actor the actor holding the wallet
     */
    public Wallet(Actor actor) {
        this.actor = actor;
        this.balance = 10000;
    }

    /**
     * Gets the Wallet balance
     * @return balance of the wallet
     */
    public int getBalance(){
        return balance;
    }

    /**
     * Gets the actor using the Wallet
     * @return actor using the Wallet
     */
    public Actor getActor(){
        return actor;
    }

    /**
     * Deposit money into the wallet
     * @param value amount of money to deposit into the wallet
     */
    public void deposit(int value){
        balance += value;
    }

    /**
     * Withdraw money from the wallet
     * @param value amount of money to withdraw from wallet
     */
    public void withdraw(int value) {
        balance -= value;
    }

    /**
     * Registers instance of Wallet into the Wallet Manager
     */
    public void registerInstance(){
        WalletManager.getInstance().appendWalletInstance(this);
    }
}
