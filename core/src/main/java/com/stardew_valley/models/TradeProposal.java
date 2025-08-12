package com.stardew_valley.models;

public class TradeProposal {
    private String senderUsername;
    private String receiverUsername;
    private int tradeNumber;
    private ProposalStatus status;

    public enum ProposalStatus {
        PENDING, ACCEPTED, REJECTED
    }

    public TradeProposal(String senderUsername, String receiverUsername, int tradeNumber) {
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
        this.tradeNumber = tradeNumber;
        this.status = ProposalStatus.PENDING;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public int getTradeNumber() {
        return tradeNumber;
    }

    public ProposalStatus getStatus() {
        return status;
    }

    public void setStatus(ProposalStatus status) {
        this.status = status;
    }

    public void accept() {
        this.status = ProposalStatus.ACCEPTED;
    }

    public void reject() {
        this.status = ProposalStatus.REJECTED;
    }
}
