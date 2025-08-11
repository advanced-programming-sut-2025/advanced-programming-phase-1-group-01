package com.stardew_valley.models;

import java.util.*;

public class TradeProposalService {
    private final List<TradeProposal> proposals = new ArrayList<>();
    private String message;
    private boolean tradeAccepted = false;

    public TradeProposalService() {
        message = "";
    }

    public TradeProposal createProposal(String sender, String receiver) {
        TradeProposal proposal = new TradeProposal(sender, receiver);
        proposals.add(proposal);
        return proposal;
    }

    public void acceptProposal(String sender, String receiver) {
        findProposal(sender, receiver).ifPresent(TradeProposal::accept);
    }

    public void rejectProposal(String sender, String receiver) {
        findProposal(sender, receiver).ifPresent(TradeProposal::reject);
    }

    public Optional<TradeProposal> findProposal(String sender, String receiver) {
        return proposals.stream()
            .filter(p -> p.getSenderUsername().equals(sender)
                && p.getReceiverUsername().equals(receiver))
            .findFirst();
    }

    public List<TradeProposal> getProposals() {
        return proposals;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String massage) {
        this.message = massage;
    }

    public void setTradeAccepted(boolean accepted) {
        this.tradeAccepted = accepted;
    }

    public boolean isTradeAccepted() {
        return tradeAccepted;
    }
}
