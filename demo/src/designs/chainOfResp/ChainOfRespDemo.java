package chainOfResp;

public class ChainOfRespDemo {
    public static void main(String[] args) {
        CashHandler hundredHandler = new HundredDollarHandler();
        CashHandler fiftyHandler = new FiftyDollarHandler();
        CashHandler twentyHandler = new TwentyDollarHandler();
        CashHandler tenHandler = new TenDollarHandler();

        hundredHandler.setNextHandler(fiftyHandler);
        fiftyHandler.setNextHandler(twentyHandler);
        twentyHandler.setNextHandler(tenHandler);

        CashRequest request = new CashRequest(22340);
        hundredHandler.dispense(request);
    }
}

class CashRequest {
    public int amount;

    CashRequest(int amount) {
        this.amount = amount;
    }
}

interface CashHandler {
    void setNextHandler(CashHandler handler);

    void dispense(CashRequest request);
}

class BaseCashHandler implements CashHandler {
    protected CashHandler nextHandler;
    protected int denomination;
    protected int noOfNotes;

    BaseCashHandler(int denomination, int noOfNotes) {
        this.denomination = denomination;
        this.noOfNotes = noOfNotes;
    }

    @Override
    public void setNextHandler(CashHandler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void dispense(CashRequest request) {
        if (request.amount >= denomination) {
            int numNotes = request.amount / denomination;
            if (numNotes > noOfNotes) {
                numNotes = noOfNotes;
            }
            System.out.println("Dispensing " + numNotes + " notes of " + denomination);
            request.amount = request.amount - (numNotes * denomination);
            noOfNotes -= numNotes;
        }
        if (nextHandler != null) {
            nextHandler.dispense(request);
        } else if (request.amount > 0) {
            System.out.println("Cannot dispense remaining amount: " + request.amount);
        }
    }
}

class HundredDollarHandler extends BaseCashHandler {
    public HundredDollarHandler() {
        super(100, 10);
    }
}

class FiftyDollarHandler extends BaseCashHandler {
    public FiftyDollarHandler() {
        super(50, 20);
    }
}

class TwentyDollarHandler extends BaseCashHandler {
    public TwentyDollarHandler() {
        super(20, 5);
    }
}

class TenDollarHandler extends BaseCashHandler {
    public TenDollarHandler() {
        super(10, 10);
    }
}
