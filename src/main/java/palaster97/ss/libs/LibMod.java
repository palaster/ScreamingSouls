package palaster97.ss.libs;

public class LibMod {

	public static final String modid = "ss";
	public static final String name = "Screaming Souls";
	public static final String version = "@VERSION@";
	public static final String dependencies = "required-after:Forge@[11.14.1.1329,);";
	
	public static final String client = "palaster97.ss.core.proxy.ClientProxy";
	public static final String server = "palaster97.ss.core.proxy.CommonProxy";
}
