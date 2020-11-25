package pos.machine;

import java.util.List;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        Receipt receipt = new Receipt();
        receipt.updateUsingBarcodes(barcodes);
        return receipt.print();
    }
}
