package palaster97.ss.api;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.common.network.handshake.FMLHandshakeMessage;
import net.minecraftforge.fml.relauncher.ModListHelper;

import java.util.ArrayList;
import java.util.List;

public class SSApi {

    private static List<Class<? extends EntityLiving>> excludeFromBloodLink = new ArrayList<Class<? extends EntityLiving>>();

    public static void addEntityLivingToExclude(EntityLiving el) {
        if(el != null) {
            for(Class<? extends EntityLiving> classEL : excludeFromBloodLink)
                if(classEL.getName().equals(el.getClass().getName())) {
                    System.out.println(el.getClass().getName() + " has already been excluded.");
                    return;
                }
            excludeFromBloodLink.add(el.getClass());
        }
    }

    public static void addEntityLivingClassToExclude(Class<? extends EntityLiving> classELTemp) {
        if(classELTemp != null) {
            for(Class<? extends EntityLiving> classEL : excludeFromBloodLink)
                if(classEL.getName().equals(classELTemp.getName())) {
                    System.out.println(classELTemp.getName() + " has already been excluded.");
                    return;
                }
            excludeFromBloodLink.add(classELTemp);
        }
    }

    public static boolean checkExcludesForEntity(EntityLiving el) {
        for(Class<? extends EntityLiving> classEL : excludeFromBloodLink)
            if(classEL.getName().equals(el.getClass().getName()))
                return true;
        return false;
    }

    public static boolean checkExcludesForClass(Class<? extends  EntityLiving> classELTemp) {
        for(Class<? extends EntityLiving> classEL : excludeFromBloodLink)
            if(classEL.getName().equals(classELTemp.getName()))
                return true;
        return false;
    }
}
