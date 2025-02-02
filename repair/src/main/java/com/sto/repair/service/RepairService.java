package com.sto.repair.service;

import com.sto.repair.model.Bid;

public interface RepairService {
    void startOfRepair(Bid bid);
    void endOfRepair(Long bidId);

    Bid getRepairStatus(Long bidId);
}
