package com.example.test;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Runelite-Test"
)
public class RuneliteTestPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private RuneliteTestConfig config;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Runelite-Test started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Runelite-Test stopped!");
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Runelite-Test says " + config.greeting(), null);
		}
	}

	@Provides
	RuneliteTestConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(RuneliteTestConfig.class);
	}
}
