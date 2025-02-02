package com.sto.repair.controller;

import com.sto.repair.model.Bid;
import com.sto.repair.model.response.RestResponse;
import com.sto.repair.service.RepairService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RepairController {
    private final RepairService repairService;

    @GetMapping("/repair-status/bids/{id}")
    public RestResponse<Bid> getBidStatus(@PathVariable Long id) {
        return RestResponse.success(repairService.getRepairStatus(id));
    }
}
