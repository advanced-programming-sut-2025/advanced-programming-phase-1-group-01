package com.stardew_valley.models;

import java.util.*;

public class TradeProposalService {
    private final List<TradeProposal> proposals = new ArrayList<>();
    private final Map<String, Integer> tradeCounters = new HashMap<>();

    private String message;
    private boolean messageShown = true;

    public TradeProposal createProposal(String sender, String receiver) {
        String key = sender + ":" + receiver;
        int count = tradeCounters.getOrDefault(key, 0) + 1;
        tradeCounters.put(key, count);

        TradeProposal proposal = new TradeProposal(sender, receiver, count);
        proposals.add(proposal);
        return proposal;
    }

    public List<TradeProposal> findProposals(String sender, String receiver) {
        List<TradeProposal> result = new ArrayList<>();
        for (TradeProposal p : proposals) {
            if (p.getSenderUsername().equals(sender) && p.getReceiverUsername().equals(receiver)) {
                result.add(p);
            }
        }
        return result;
    }

    public Optional<TradeProposal> findProposal(String sender, String receiver, int tradeNumber) {
        return proposals.stream()
            .filter(p -> p.getSenderUsername().equals(sender)
                && p.getReceiverUsername().equals(receiver)
                && p.getTradeNumber() == tradeNumber)
            .findFirst();
    }

    public void acceptProposal(String sender, String receiver, int tradeNumber) {
        findProposal(sender, receiver, tradeNumber).ifPresent(TradeProposal::accept);
    }

    public void rejectProposal(String sender, String receiver, int tradeNumber) {
        findProposal(sender, receiver, tradeNumber).ifPresent(TradeProposal::reject);
    }

    public List<TradeProposal> getProposals() {
        return Collections.unmodifiableList(proposals);
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public boolean isMessageShown() {
        return messageShown;
    }
    public void setMessageShown(boolean messageShown) {
        this.messageShown = messageShown;
    }
}
