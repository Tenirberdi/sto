package com.sto.bid.service;

import com.sto.bid.model.rest.Bid;
import com.sto.bid.model.rest.request.BidRequest;

public interface BidService {
    Long save(BidRequest bid);
    Bid get(Long bidId);
    void process(Long bidId);
}
