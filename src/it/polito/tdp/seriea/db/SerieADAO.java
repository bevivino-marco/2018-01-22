package it.polito.tdp.seriea.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.seriea.model.Annata;
import it.polito.tdp.seriea.model.Season;
import it.polito.tdp.seriea.model.Team;

public class SerieADAO {

	public List<Season> listAllSeasons() {
		String sql = "SELECT season, description FROM seasons";
		List<Season> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Season(res.getInt("season"), res.getString("description")));
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Team> listTeams() {
		String sql = "SELECT team FROM teams";
		List<Team> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Team(res.getString("team")));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	
	public Map<Integer, Annata> getAnnate(Team team, Map<Integer, Annata> mappaA) {
		String sql = "SELECT Season ,Hometeam, AwayTeam, FTHG, FTAG, FTR " + 
				"FROM matches " + 
				"WHERE HomeTeam=? OR AwayTeam=?";
		Map<Integer, Annata> result = new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, team.getTeam());
			st.setString(2, team.getTeam());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				if (!result.containsKey(res.getInt("Season"))) {
					result.put(res.getInt("Season"), new Annata (res.getInt("Season")));
				}else {
					if (res.getString("HomeTeam").compareTo(team.getTeam())==0) {
						if (res.getInt("FTHG")> res.getInt("FTAG"))
							result.get(res.getInt("Season")).setPunteggio(3);
						else if (res.getInt("FTHG")== res.getInt("FTAG"))
							result.get(res.getInt("Season")).setPunteggio(1);
					}else if (res.getString("AwayTeam").compareTo(team.getTeam())==0) {
						if (res.getInt("FTAG")> res.getInt("FTHG"))
							result.get(res.getInt("Season")).setPunteggio(3);
						else if (res.getInt("FTHG")== res.getInt("FTAG"))
							result.get(res.getInt("Season")).setPunteggio(1);
					}
				}
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}}
