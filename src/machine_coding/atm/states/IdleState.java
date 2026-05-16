package machine_coding.atm.states;

import machine_coding.atm.ATM;
import machine_coding.atm.entities.Card;
import machine_coding.atm.enums.OperationType;

public class IdleState implements ATMState {

    @Override
    public void insertCard(ATM atm, String cardNumber) {
        System.out.println("Card inserted. Please enter your PIN.");
        Card card = atm.getBankService().authenticate(cardNumber);

        if (card == null) {
            ejectCard(atm);
        } else {
            atm.setCurrentCard(card);
            atm.changeState(new HasCardState());
        }
    }

    @Override
    public void ejectCard(ATM atm) {
        System.out.println("No card to eject.");
    }

    @Override
    public void selectOperation(ATM atm, OperationType operation, int... args) {
        System.out.println("Please insert your card first.");
    }

    @Override
    public void enterPin(ATM atm, String pin) {
        System.out.println("Please insert your card first.");
    }

}
