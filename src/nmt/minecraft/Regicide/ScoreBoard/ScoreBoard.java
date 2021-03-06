package nmt.minecraft.Regicide.ScoreBoard;

import java.util.Collection;

import nmt.minecraft.Regicide.Game.Player.RPlayer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

public class ScoreBoard {
	
	private org.bukkit.scoreboard.Scoreboard board; 
	
	private Objective scoreObjective;
	
	private PointBar scoreBar;
	
	private Team kingTeam, playerTeam;
	
	private RPlayer currentKing;
	
	public ScoreBoard() {
		this.board = Bukkit.getScoreboardManager().getNewScoreboard();
		scoreObjective = board.registerNewObjective("Points", "dummy");
		
		scoreObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		scoreBar = new PointBar();
		
		playerTeam = board.registerNewTeam("Players");
		kingTeam = board.registerNewTeam("King");
		
		kingTeam.setPrefix(ChatColor.GOLD.toString());
		playerTeam.setPrefix(ChatColor.BLUE.toString());
		
	}
	
	public void updateScore(RPlayer player, int score) {
		scoreObjective.getScore(player.getPlayer().getDisplayName()).setScore(score);
	}
	
	public void displayScoreboard(Collection<RPlayer> players) {
		for (RPlayer play : players) {
			updateScore(play, 0);
			play.getPlayer().setScoreboard(board);
			playerTeam.addEntry(play.getPlayer().getDisplayName());
		}
	}
	
	public void updateKing(RPlayer king) {
		
		scoreBar.setKing(king);
		
		//update names on scoreboard
		if (currentKing != null) {
			kingTeam.removeEntry(currentKing.getPlayer().getDisplayName());
			playerTeam.addEntry(currentKing.getPlayer().getDisplayName());
			
		}
		playerTeam.removeEntry(king.getPlayer().getDisplayName());
		kingTeam.addEntry(king.getPlayer().getDisplayName());
		
		currentKing = king;
		
		king.getPlayer().sendMessage(ChatColor.BLUE + "You are now the king!");
	}
	
	/**
	 * Updates the scoreboard and PointBar to accurately show information
	 * @param time num from 0.0 to 1.0 describing how close to another point the king is
	 */
	public void update(float time) {
		scoreBar.update(time);
	}
	
	public void removePlayer(RPlayer player) {
		board.resetScores(player.getPlayer().getDisplayName());
		player.getPlayer().setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
	}
}
