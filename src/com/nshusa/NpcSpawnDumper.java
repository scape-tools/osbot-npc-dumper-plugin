package com.nshusa;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

@ScriptManifest(name = "NpcSpawnDumper", author = "nshusa", version = 1.1D, info = "", logo = "")
public class NpcSpawnDumper extends Script {

	private static final Map<Integer, NpcSpawn> globalNpcIdToSpawnMap = new HashMap<>();
	
	private static final String DIRECTORY = System.getProperty("user.home") + "/OSBot/Data/";	
	
	private static final Set<Integer> pets = new HashSet<>(Arrays.asList(318, 497, 2055, 2131, 5536, 5561, 5892, 6629, 6631, 6633, 6635, 6637,
			6639, 6642, 6715, 6719, 7334, 7337, 7339, 7341, 7343, 7345, 7347, 7349, 495, 964, 2130, 2132, 5537, 5884, 6628, 6630, 6632, 6634, 6636,
			6638, 6640, 7520, 6718, 6723, 7336, 6720, 7335, 7338, 7340, 7342, 7344, 7346, 7348, 7350));

	public void onStart() {
		log("npc spawn dumper script started");
	}

	@Override
	public int onLoop() throws InterruptedException {
		List<NPC> npcs = this.getNpcs().getAll();

		for (NPC npc : npcs) {

			if (!npc.isVisible()) {
				continue;
			}
			
			if (!npc.exists()) {
				continue;
			}
			
			if (pets.contains(npc.getId())) {
				continue;
			}

			if (globalNpcIdToSpawnMap.containsKey(npc.getIndex())) {				
				NpcSpawn s = globalNpcIdToSpawnMap.get(npc.getIndex());
				
				if (s.getPosition().getX() != npc.getX() && s.getPosition().getY() != npc.getY() && s.getPosition().getZ() == npc.getZ()) {
			         s.setPosition(new Position(npc.getX(), npc.getY(), npc.getZ()));
			         int x = IntUtils.distance(s.getPosition(), s.getCreatedPosition());
			         if(s.getRadius() < x) {
			            s.setRadius(x);
			         }
				}				
				continue;
			}

			globalNpcIdToSpawnMap.put(npc.getIndex(), new NpcSpawn(npc.getId(), new Position(npc.getX(), npc.getY(), npc.getZ()), npc.getRotation()));
		}

		return 500;
	}

	@Override
	public void onExit() {
		File dir = new File(DIRECTORY);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		try (PrintWriter writer = new PrintWriter(new FileWriter(new File(dir, "npc_spawns.json")))) {
			List<NpcSpawn> list = new ArrayList<>(globalNpcIdToSpawnMap.values());
			
			writer.println("[");

			for (int i = 0; i < list.size(); ++i) {
				NpcSpawn spawn = list.get(i);
				writer.println("\t{");
				writer.println("\t\t\"id\": " + spawn.getId() + ",");
				writer.println("\t\t\"position\": {");
				writer.println("\t\t\t\"x\": " + spawn.getPosition().getX() + ",");
				writer.println("\t\t\t\"y\": " + spawn.getPosition().getY() + ",");
				writer.println("\t\t\t\"z\": " + spawn.getPosition().getZ());
				writer.println("\t\t},");
				writer.println("\t\t\"facing\": \"" + spawn.getDirection() + "\"" + ",");
				writer.println("\t\t\"radius\": " + spawn.getRadius());
				writer.println("\t}" + (i == list.size() - 1 ? "" : ","));
			}

			writer.println("]");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		log("npc spawn dumper script ended");

	}

}
