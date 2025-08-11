package com.stardew_valley.models;

import com.stardew_valley.controllers.VotingController;
import com.stardew_valley.views.GameView;

import java.util.HashMap;
import java.util.Map;

public class Voting {
    private final Map<String, Vote> votes = new HashMap<>(); // username to vote map
    private final Type type;
    private boolean isEnded;
    private final int numOfPlayers;
    private boolean result;
    private String votingUsername;

    public Voting(int numOfPlayers) {
        this.type = Type.FORCE_TERMINATE;
        this.numOfPlayers = numOfPlayers;
    }

    public Voting(String votingUsername, int numOfPlayers) {
        this.type = Type.BAN_PLAYER;
        this.votingUsername = votingUsername;
        this.numOfPlayers = numOfPlayers;
    }

    public Type type() {
        return type;
    }

    public boolean isEnded() {
        return isEnded;
    }

    public void setEnded(boolean ended) {
        isEnded = ended;
    }

    public int numOfPlayers() {
        return numOfPlayers;
    }

    public enum Type {
        BAN_PLAYER, FORCE_TERMINATE;

        @Override
        public String toString() {
            return switch (this) {
                case BAN_PLAYER -> "Ban Player";
                case FORCE_TERMINATE -> "Force Terminate";
            };
        }
    }

    public enum Vote {
        YES, NO
    }

    public boolean vote(String username, Vote vote) {
        if (votes.containsKey(username)) return false;

        votes.put(username, vote);
        checkIfEnd();
        return true;
    }

    public void checkIfEnd() {
        int numOfYeses = 0;

        for (Vote vote : votes.values()) {
            if (vote == Vote.YES) numOfYeses++;
        }

        if (numOfYeses > numOfPlayers / 2) {
            result = true;
            isEnded = true;
            if (type == Type.FORCE_TERMINATE) {
                VotingController.forceTerminate();
            } else if (type == Type.BAN_PLAYER) {
                VotingController.banPlayer(votingUsername);
            }
            VotingController.endVoting();
            return;
        }

        if (votes.size() == numOfPlayers) {
            result = false;
            isEnded = true;
            GameView.setMessage("Voting failed.");
            VotingController.endVoting();
        }
    }

    public boolean getResult() {
       return isEnded && result;
    }

    public boolean isVoted(String username) {
        return votes.containsKey(username);
    }

    public String getVotingUsername() {
        return votingUsername;
    }
}
