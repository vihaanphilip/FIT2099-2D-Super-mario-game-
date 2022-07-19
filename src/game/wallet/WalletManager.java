package game.wallet;

import java.util.ArrayList;
import java.util.List;

/**
 * Wallet Manager class represents a Manager which stores all the Wallets in the game, held by multiple actors
 */
public class WalletManager {

    /**
     * List of wallets
     */
    private List<Wallet> walletList;

    /**
     * An instance of Wallet Manager
     */
    private static WalletManager instance;

    /**
     * Gets an instance of Wallet Manager
     * @return an instance of Wallet Manager
     */
    public static WalletManager getInstance(){
        if(instance == null){
            instance = new WalletManager();
        }
        return instance;
    }

    /**
     * Private constructor of Wallet Manager
     */
    private WalletManager(){
        walletList = new ArrayList<>();
    }

    /**
     * Adds a Wallet into the Wallet Manager
     * @param wallet
     */
    public void appendWalletInstance(Wallet wallet){
        walletList.add(wallet);
    }

    /**
     * Get a list of all the Wallets in the game
     * @return a list of all the Wallets in the game
     */
    public ArrayList<Wallet> getWallets(){
        return new ArrayList<Wallet>(this.walletList);
    }

}
