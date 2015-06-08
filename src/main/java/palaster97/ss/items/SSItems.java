package palaster97.ss.items;

import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

public class SSItems {
	
	public static Item souls, 
	atmanSword,
	soulBinder,
	worldSoulBinder,
	staffSkeleton,
	staffEfreet,
	staffTime,
	staffVoidWalker,
	magicBow,
	animalHerder,
	magicDuctTape;
	
	public static Item debug,
	yinYang;
	
	public static void init() {
		souls = new ItemSoul();
		atmanSword = new ItemAtmanSword(EnumHelper.addToolMaterial("atman", 2, -1, 6.0f, 2.0f, 0));
		soulBinder = new ItemSoulBinder();
		worldSoulBinder = new ItemWorldSoulBinder();
		staffSkeleton = new ItemStaffSkeleton();
		staffEfreet = new ItemStaffEfreet();
		staffTime = new ItemStaffTime();
		staffVoidWalker = new ItemStaffVoidWalker();
		magicBow = new ItemMagicBow();
		animalHerder = new ItemAnimalHerder();
		magicDuctTape = new ItemMagicDuctTape();
		
		debug = new ItemDebug();
		yinYang = new ItemYinYang();
	}
}