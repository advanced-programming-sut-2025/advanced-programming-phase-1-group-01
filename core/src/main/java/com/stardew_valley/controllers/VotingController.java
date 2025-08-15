package com.stardew_valley.controllers;

import com.stardew_valley.models.Result;
import com.stardew_valley.models.Voting;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.network.Network;
import com.stardew_valley.views.GameView;

import java.util.List;

public class VotingController {
    private static Voting currentVoting;

    public static Result startBanPlayerVoting(String username) {
        if (currentVoting != null) {
            return new Result(false, "We have a active voting!");
        }

        if (Repository.getRepo().getUserByUsername(username) == null) {
            return new Result(false, "Wrong username!");
        }

        currentVoting = new Voting(username, Repository.getRepo().getCurrentGame().getPlayers().size());
        return new Result(true, "New Ban Player voting started for " + username + "!");
    }

    public static Result startForceTerminateVoting() {
        if (currentVoting != null) {
            return new Result(false, "We have a active voting!");
        }

        currentVoting = new Voting(Repository.getRepo().getCurrentGame().getPlayers().size());
        return new Result(true, "New Force Terminate voting started!");
    }

    public static boolean isVoting() {
        return currentVoting != null;
    }

    public static Voting getCurrentVoting() {
        return currentVoting;
    }

    public static void endVoting() {
        currentVoting = null;
    }

    public static void banPlayer(String username) {
        // TODO : implement ban player for voting
        GameView.setMessage("PLAYER BANNED!!!");
    }

    public static void forceTerminate() {
        System.exit(0);
        GameView.setMessage("FORCE TERMINATE!!!");
    }
}
