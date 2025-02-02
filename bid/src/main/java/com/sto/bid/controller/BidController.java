package com.sto.bid.controller;

import com.sto.bid.model.rest.Bid;
import com.sto.bid.model.rest.request.BidRequest;
import com.sto.bid.model.rest.response.RestResponse;
import com.sto.bid.service.BidService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
public class BidController {
    private final BidService bidService;

    @PostMapping("/bids")
    public RestResponse<Map<String, Long>> createBid(@RequestBody @Valid BidRequest bid) {
        return RestResponse.success(Map.of("bidId", bidService.save(bid)));
    }

    @GetMapping("/bids/{id}")
    public RestResponse<Bid> getBid(@PathVariable Long id) {
        return RestResponse.success(bidService.get(id));
    }

    @PostMapping("/bids/{id}/process")
    public RestResponse<Void> processBid(@PathVariable Long id) {
        bidService.process(id);
        return RestResponse.success();
    }
}
