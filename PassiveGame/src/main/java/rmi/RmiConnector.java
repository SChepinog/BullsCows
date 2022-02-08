package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import game.common.Variation;
import game.common.VariationResult;

public interface RmiConnector extends Remote {

    VariationResult testVariation(Variation variation) throws RemoteException;
}
