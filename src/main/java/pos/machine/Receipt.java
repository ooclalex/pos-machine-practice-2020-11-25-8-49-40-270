package pos.machine;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    List<ReceiptItem> listReceipt;
    List<String> listBarcodes;
    public Receipt() {
        listReceipt = new ArrayList<ReceiptItem>();
        listBarcodes = new ArrayList<String>();
    }

    public void updateUsingBarcodes(List<String> barcodes) {
        List<ItemInfo> itemInfos = ItemDataLoader.loadAllItemInfos();

        for (String barcode: barcodes) {
            if (listBarcodes.contains(barcode)) {
                ReceiptItem receiptItem = listReceipt.stream().filter(i -> i.getBarcode().equals(barcode)).findFirst().orElse(null);
                assert receiptItem != null;
                receiptItem.addQuantity();
            } else {
                listBarcodes.add(barcode);
                ItemInfo item = itemInfos.stream().filter(i -> i.getBarcode().equals(barcode)).findFirst().orElse(null);
                assert item != null;
                ReceiptItem receiptItem = new ReceiptItem(item.getPrice(), 1, item.getName(), barcode);
                listReceipt.add(receiptItem);
            }
        }
    }

    public String print() {
        StringBuilder stringBuilder = new StringBuilder("***<store earning no money>Receipt***\n");

        for (ReceiptItem receiptItem: listReceipt) {
            stringBuilder.append("Name: " + receiptItem.getName() + ", Quantity: " + receiptItem.getQuantity() +
                    ", Unit price: " + receiptItem.getPrice() + " (yuan), Subtotal: " +
                   receiptItem.getSubtotal() + " (yuan)\n");
        }
        stringBuilder.append("----------------------\n");
        stringBuilder.append("Total: ").append(listReceipt.stream().map(ReceiptItem::getSubtotal).reduce(0, Integer::sum)).append(" (yuan)\n");
        stringBuilder.append("**********************");
        //listReceipt.stream().map(i -> stringBuilder.append("Name: " + i.getName() + "\n"));
        return stringBuilder.toString();
    }
}
