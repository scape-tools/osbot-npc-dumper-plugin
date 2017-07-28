#OSBot Npc Dumper Plugin (1.1)

A simple plugin for [OSBot](http://osbot.org/) that dumps npc spawns into [JSON](http://www.json.org/JSON) format.

### Features
* Excludes pets
* Excludes hidden npcs
* Exports to JSON
* Determines npcs walking distance depending on how far they walk from when the program initially picks them up.
* Determines the npcs facing direction (NORTH, EAST, SOUTH, WEST) depending on their rotation.

### How to use
1. Download plugin
2. Place plugin in OSBot/Scripts folder
3. Login to Old School RuneScape with OSBot
4. Refresh plugins
5. Start the plugin
6. Walk around RuneScape (Get npcs you want dumped in range of your minimap)
7. Stop the plugin to see the spawns.
8. The spawns are located in the OSBot/Data folder.

### Format
```json
[
	{
		"id": 5810,
		"position": {
			"x": 2932,
			"y": 3287,
			"z": 0
		},
		"facing": "NORTH",
		"radius": 0
	},
	{
		"id": 2806,
		"position": {
			"x": 2932,
			"y": 3276,
			"z": 0
		},
		"facing": "EAST",
		"radius": 2
	}
]
```