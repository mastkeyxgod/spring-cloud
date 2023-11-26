package ru.mastkey.bill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mastkey.bill.controller.dto.BillRequest;
import ru.mastkey.bill.controller.dto.BillResponse;
import ru.mastkey.bill.service.BillService;

import java.util.List;
import java.util.stream.Collectors;

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
        System.out.println(billRequest.getIsDefault());
        return billService.createBill(billRequest.getAccountId(), billRequest.getAmount(),
                 billRequest.getOverdraftEnable()
        );
    }

    @PutMapping("/{billId}")
    public BillResponse updateBill(@PathVariable Long billId,
                                   @RequestBody BillRequest billRequest) {
        return new BillResponse(billService.updateBill(billId, billRequest.getAccountId(),
                billRequest.getAmount(), billRequest.getIsDefault(), billRequest.getOverdraftEnable())
        );
    }

    @DeleteMapping("/{billId}")
    public BillResponse deleteBill(@PathVariable Long billId) {
        return new BillResponse(billService.deleteBill(billId));
    }

    @GetMapping("/account/{accountId}")
    public List<BillResponse> getBillsByAccountId(@PathVariable Long accountId) {
        return billService.getBillsByAccountId(accountId)
                .stream()
                .map(BillResponse::new)
                .collect(Collectors.toList());
    }
}
