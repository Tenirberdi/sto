package com.sto.check.service;

import com.sto.check.model.Bid;

public interface CheckService {
    void formCheck(Bid bid);
    Bid getCheck(Long bidId);
}
