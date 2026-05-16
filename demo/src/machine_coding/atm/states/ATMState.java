package machine_coding.atm.states;

import machine_coding.atm.ATM;
import machine_coding.atm.enums.OperationType;

public interface ATMState {
    void insertCard(ATM atm, String cardNumber);

    void ejectCard(ATM atm);

    void selectOperation(ATM atm, OperationType operation, int... args);

    void enterPin(ATM atm, String pin);
}
