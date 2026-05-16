package atm.states;

import atm.ATM;
import atm.enums.OperationType;

public interface ATMState {
    void insertCard(ATM atm, String cardNumber);

    void ejectCard(ATM atm);

    void selectOperation(ATM atm, OperationType operation, int... args);

    void enterPin(ATM atm, String pin);
}
