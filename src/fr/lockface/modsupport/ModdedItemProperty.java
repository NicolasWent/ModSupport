package fr.lockface.modsupport;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.hsgamer.bettergui.object.Icon;
import me.hsgamer.bettergui.object.property.item.ItemProperty;

public class ModdedItemProperty extends ItemProperty<String, Material>
{

	public ModdedItemProperty(Icon icon)
	{
		super(icon);
	}

	@Override
	public boolean compareWithItemStack(Player p, ItemStack toCompare) 
	{
		Material parsed = getParsed(p);
		Material compare = toCompare.getType();
		return compare.equals(parsed);
	}

	@Override
	public Material getParsed(Player p)
	{
		Material ret = Material.valueOf(getValue());
		if (ret == null)
		{
			System.out.println("[BetterGUI][Modded-Support addon] Error, " + getValue() +
				" is not valid, use /mohist item info to get the valid type");
			return Material.valueOf("STONE");
		}
		return ret;
	}

	@Override
	public ItemStack parse(Player p, ItemStack parent)
	{
		Material parsed = getParsed(p);
		parent.setType(parsed);
		return parent;
	}
	
}

