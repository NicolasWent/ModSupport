package fr.lockface.modsupport;

import java.util.Optional;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.hsgamer.bettergui.object.Icon;
import me.hsgamer.bettergui.object.property.item.ItemProperty;

public class ModdedItemProperty extends ItemProperty<String, Optional<Material>>
{

	public ModdedItemProperty(Icon icon)
	{
		super(icon);
	}

	@Override
	public boolean compareWithItemStack(Player p, ItemStack toCompare) 
	{
		Optional<Material> parsed = getParsed(p);
		Material compare = toCompare.getType();
		return parsed.isPresent() && compare.equals(parsed.get());
	}

	@Override
	public Optional<Material> getParsed(Player p)
	{
		Material ret = Material.valueOf(getValue());
		if (ret == null)
		{
			System.out.println("[BetterGUI][Modded-Support addon] Error, " + getValue() +
				" is not valid, use /mohist item info to get the valid type");
			return Optional.empty();
		}
		return Optional.of(ret);
	}

	@Override
	public ItemStack parse(Player p, ItemStack parent)
	{
		Optional<Material> parsed = getParsed(p);
		if (parsed.isPresent())
			parent.setType(parsed.get());
		return parent;
	}
	
}

