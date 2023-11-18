package ru.mastkey.bill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mastkey.bill.controller.dto.BillRequest;
import ru.mastkey.bill.controller.dto.BillResponse;
import ru.mastkey.bill.service.BillService;

@RestController
public class BillController {
    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping("/{billId}")
    public BillResponse getBill(@PathVariable Long billId) {
        return new BillResponse(billService.getBillById(billId));
    }

    @PostMapping("/")
    public Long createBill(@RequestBody BillRequest billRequest) {
        return billService.createBill(billRequest.getAccountId(), billRequest.getAmount(),
                billRequest.getDefault(), billRequest.getOverdraftEnable()
        );
    }

    @PutMapping("/{billId}")
    public BillResponse updateBill(@PathVariable Long billId,
                                   @RequestBody BillRequest billRequest) {
        return new BillResponse(billService.updateBill(billId, billRequest.getAccountId(),
                billRequest.getAmount(), billRequest.getDefault(), billRequest.getOverdraftEnable())
        );
    }

    @DeleteMapping("/{billId}")
    public BillResponse deleteBill(@PathVariable Long billId) {
        return new BillResponse(billService.deleteBill(billId));
    }
}
